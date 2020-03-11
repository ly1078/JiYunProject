package model;

import android.util.Log;

import java.io.File;
import java.io.IOException;

import constan.Constan;
import model.api.APIService;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.SpUtils;
import utils.SystemUtils;

public class HttpManager {

    private APIService apiService;

    private static volatile HttpManager instance;
    public static HttpManager getInstance(){
        if(instance == null){
            if(instance == null){
                instance = new HttpManager();
            }
        }
        return instance;
    }

    private Retrofit getRetrofit(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient())
                .baseUrl(baseUrl)
                .build();
        return  retrofit;
    }

    private OkHttpClient okHttpClient(){

        File file = new File(Constan.PATH_CACHE);
        Cache cache = new Cache(file, 100 * 1024 * 1024);
        OkHttpClient build = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HeadersInterceptor())
                .addInterceptor(new NetWorkInterceptor())
                .cache(cache)
                .build();
        return build;
    }


    public APIService getApiService(){
        if(apiService == null){
            synchronized (APIService.class){
                if(apiService == null){
                    apiService = getRetrofit(Constan.BASE_SHOP_URL).create(APIService.class);
                }
            }
        }
        return apiService;
    }

    public APIService getApiServiceII(){
        if(apiService == null){
            synchronized (APIService.class){
                if(apiService == null){
                    apiService = getRetrofit(Constan.BASE_SHOP_URL_II).create(APIService.class);
                }
            }
        }
        return apiService;
    }

    /**
     * 日志拦截器，打印请求接口的报文信息
     * 提供日志信息帮组优化代码
     */
    static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //通过系统时间的差打印接口请求的信息
            long time = System.nanoTime();
            Request request = chain.request();
            Log.i("Request:",String.format("Sending request %s on %s%n%s",request.url(),chain.connection(),request.headers()));
            Response response = chain.proceed(request);
            long now = System.nanoTime();
            Log.i("Received:",String.format("Received response for %s in %.1fms%n%s",response.request().url(),(now-time)/1e6d,response.headers()));
            return response;
        }
    }

    /**
     * 请求的修改设置
     */
    static class HeadersInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Client-Type","ANDROID")
                    .addHeader("X-Nideshop-Token", SpUtils.getInstance().getString("token"))
                    .build();
            return chain.proceed(request);
        }
    }

    /**
     * 网络拦截器
     */
    static class NetWorkInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if(!SystemUtils.checkNetWork()){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            //通过判断网络连接是否存在获取本地或者服务器的数据
            if(!SystemUtils.checkNetWork()){
                int maxAge = 0;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public ,max-age="+maxAge).build();
            }else{
                int maxStale = 60*60*24*28; //设置缓存数据的保存时间
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public, onlyif-cached, max-stale="+maxStale).build();
            }
        }
    }
}
