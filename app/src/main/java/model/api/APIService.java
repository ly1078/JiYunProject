package model.api;

import io.reactivex.Flowable;
import model.bean.HomeBean;
import model.bean.LoginBean;
import model.bean.SpecialLeadData;
import model.bean.TopicBean;
import model.bean.special.RelatedBean;
import model.bean.special.SpecialItemShowData;
import model.bean.special.SpecialTabLeadList;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    // 首页数据
    @GET("index")
    Flowable<HomeBean> getHome();

    //专题数据请求接口
    @GET("topic/list")
    Flowable<TopicBean> getTopic(@Query("page") int page, @Query("size") int size);

    //登录请求接口
    @POST("auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("nickname") String nickname, @Field("password") String paw);

    // 注册接口
    @POST("auth/register")
    @FormUrlEncoded
    Flowable<LoginBean> regist(@Field("nickname") String nickname, @Field("password") String paw, @Field("verify") String verify);

    //分类竖着导航接口 https://cdwan.cn/api/catalog/index
    @GET("catalog/index")
    Flowable<SpecialLeadData> getSpecialLead();

    //分类右边对应的列表数据 https://cdwan.cn/api/catalog/current?id=1005001
    @GET("catalog/current")
    Flowable<SpecialLeadData> getSpecialData(@Query("id") int id);

    // 分类 条目点击详情页  条目数据   categoryId  =  1008002
    // https://cdwan.cn/api/goods/list?categoryId=1008002&page=1&size=100
    @GET("goods/list")
    Flowable<SpecialItemShowData> getSpecialItemData(@Query("categoryId") int categoryId
    , @Query("page") int page, @Query("size") int size);

    //  分类 条目点击详情页 导航数据  id=1008002
    // http://cdwan.cn/goods/category?id=1008002
    @GET("goods/category")
    Flowable<SpecialTabLeadList> getSpecialItemLeadData(@Query("id") int id);

    // 商品详情页面
    // https://cdwan.cn/api/goods/detail?id=1155000
    @GET("goods/detail")
    Flowable<RelatedBean> getGoodsDetail(@Query("id") int id);

}
