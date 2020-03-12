package com.example.activity.goodsinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jiyunproject.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import interfaces.IBasePresenter;
import interfaces.goodsdetail.GoodsDetailConstract;
import model.bean.special.RelatedBean;
import presenter.special.specialitem.GoodsDetailPresenter;

public class GoodsDetailsActivity extends BaseActivity<GoodsDetailConstract.Persenter> implements GoodsDetailConstract.View {

    private Banner banner;
    private WebView myWebView;
    private TextView txt_price;
    private TextView txt_title;
    private TextView txt_des;
    private TextView txt_metarial;
    private TextView txt_color;
    private TextView txt_size;
    private TextView txt_place;
    private TextView txt_norm;
    private ArrayList<TextView> tvlist;

    @Override
    protected GoodsDetailConstract.Persenter initPresenter() {
        return new GoodsDetailPresenter();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        persenter.getGoodsDetail(id);
    }

    @Override
    protected void initView() {

        txt_price = findViewById(R.id.txt_price);
        txt_title = findViewById(R.id.txt_title);
        txt_des = findViewById(R.id.txt_des);

        //材质
        txt_metarial = findViewById(R.id.txt_metarial);
        //颜色
        txt_color = findViewById(R.id.txt_color);
        //尺寸
        txt_size = findViewById(R.id.txt_size);
        //产地
        txt_place = findViewById(R.id.txt_place);
        //执行标准
        txt_norm = findViewById(R.id.txt_norm);

        banner = findViewById(R.id.banner_detail);
        banner.setImageLoader(new BannerLoader());

        myWebView = findViewById(R.id.myWebView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_goodinfo;
    }
    @Override
    public void getGoodsDetailResult(RelatedBean relatedBean) {
        //轮播图
        updateBanner(relatedBean.getData().getGallery());

        String price = getResources().getString(R.string.price_news_model)
                .replace("$",String.valueOf(relatedBean.getData().getInfo().getRetail_price()));

        // 名称 描述 价格
        updatePrice(relatedBean.getData().getInfo().getName(),
                relatedBean.getData().getInfo().getGoods_brief(),price);

        //加载网页
        updateWebView(relatedBean.getData().getInfo());

        //加载商品参数
        productMsg(relatedBean.getData().getAttribute());


    }

    private void productMsg(List<RelatedBean.DataBeanX.AttributeBean> attribute) {

        for (int i = 0; i <attribute.size(); i++) {
            String name = attribute.get(i).getName();
            String value = attribute.get(i).getValue();
            if(name.equals("材质")){
                txt_metarial.setText("材质:"+value);
            }else if(name.equals("尺寸")){
                txt_size.setText("尺寸:"+value);
            }else if(name.equals("颜色")){
                txt_color.setText("颜色:"+value);
            }else if(name.equals("执行标准")){
                txt_norm.setText("执行标准:"+value);
            }else if(name.equals("产地")){
                txt_place.setText("产地:"+value);
            }
        }
    }

    private void updatePrice(String name, String goods_brief, String price) {

        txt_price.setText(price);
        txt_title.setText(name);
        txt_des.setText(goods_brief);
    }

    private void updateWebView(RelatedBean.DataBeanX.InfoBean info) {

        String css_str = getResources().getString(R.string.css_goods);
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>");
        sb.append("<style>"+css_str+"</style></head><body>");
        sb.append(info.getGoods_desc()+"</body></html>");
        myWebView.loadData(sb.toString(),"text/html","utf-8");

        /*List<String> urlList = new ArrayList<>();
        String[] arr = info.getGoods_desc().split("<p>");
        String head = "<img src=\"";
        String foot = ".jpg";
        for(int i=0; i<arr.length; i++){
            if(TextUtils.isEmpty(arr[i])) continue;
            int start = arr[i].indexOf(head)+head.length();
            if(start == -1) continue;
            int end = arr[i].indexOf(foot)+foot.length();
            String url = arr[i].substring(start,end);
            urlList.add(url);
            Log.i("url",url);
        }*/

    }

    //轮播图
    private void updateBanner(List<RelatedBean.DataBeanX.GalleryBean> gallery) {
        RelatedBean.DataBeanX.GalleryBean galleryBean = gallery.get(0);
        banner.setImages(gallery);
        banner.start();
    }

    class BannerLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            String img_url = ((RelatedBean.DataBeanX.GalleryBean) path).getImg_url();
            Glide.with(context).load(img_url).into(imageView);
        }
    }
}
