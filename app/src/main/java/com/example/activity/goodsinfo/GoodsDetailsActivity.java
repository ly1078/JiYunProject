package com.example.activity.goodsinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
import model.bean.car.AddCarBean;
import model.bean.special.RelatedBean;
import presenter.special.specialitem.GoodsDetailPresenter;
import utils.ShowToast;

public class GoodsDetailsActivity extends BaseActivity<GoodsDetailConstract.Persenter> implements GoodsDetailConstract.View, View.OnClickListener {

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
    private TextView txt_addCart;
    private ConstraintLayout bottom_con;
    private PopupWindow popupWindow;
    private TextView tv_show_count;

    private boolean flag = false;
    private String img_url;
    private String retail_price;
    private ConstraintLayout layout_nums;
    private RelatedBean bean;

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

        // 选择数量
        layout_nums = findViewById(R.id.layout_nums);

        //底部包裹控件
        bottom_con = findViewById(R.id.bottom_con);

        //加入购物车
        txt_addCart = findViewById(R.id.txt_addCart);

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

        txt_addCart.setOnClickListener(this);
        layout_nums.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_goodinfo;
    }
    @Override
    public void getGoodsDetailResult(RelatedBean relatedBean) {
        bean = relatedBean;
        flag =true;
        img_url = relatedBean.getData().getGallery().get(0).getImg_url();
        retail_price = String.valueOf(relatedBean.getData().getInfo().getRetail_price());
        //轮播图
        updateBanner(relatedBean.getData().getGallery());

        String price = getResources().getString(R.string.price_news_model)
                .replace("$",retail_price);

        // 名称 描述 价格
        updatePrice(relatedBean.getData().getInfo().getName(),
                relatedBean.getData().getInfo().getGoods_brief(),price);

        //加载网页
        updateWebView(relatedBean.getData().getInfo());

        //加载商品参数
        productMsg(relatedBean.getData().getAttribute());


    }

    // 添加商品结果
    @Override
    public void addCarResult(AddCarBean carBean) {
        Log.i("tag","===> "+carBean);
        ShowToast.show("添加成功");
    }

    private void productMsg(List<RelatedBean.DataBeanX.AttributeBean> attribute) {

        for (int i = 0; i <attribute.size(); i++) {
            String name = attribute.get(i).getName();
            String value = attribute.get(i).getValue();
            if(name.equals("材质")){
                txt_metarial.setText(value);
            }else if(name.equals("尺寸")){
                txt_size.setText(value);
            }else if(name.equals("颜色")){
                txt_color.setText(value);
            }else if(name.equals("执行标准")){
                txt_norm.setText(value);
            }else if(name.equals("产地")){
                txt_place.setText(value);
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
    }

    //轮播图
    private void updateBanner(List<RelatedBean.DataBeanX.GalleryBean> gallery) {
        RelatedBean.DataBeanX.GalleryBean galleryBean = gallery.get(0);
        banner.setImages(gallery);
        banner.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_addCart:// 添加购物车
                addInfoCar();
                break;
            case R.id.tv_add_count:
                String text_count = tv_show_count.getText().toString();
                int i = Integer.parseInt(text_count);
                i++;
                tv_show_count.setText(i+"");

                break;
            case R.id.layout_nums:
                if(flag)
                    showDetail();
                break;

            case R.id.tv_reduce_count:
                String count = tv_show_count.getText().toString();
                int c = Integer.parseInt(count);
                if(c>1){
                    c--;
                    tv_show_count.setText(c+"");
                }
                break;
        }
    }

    //把商品添加到购物车
    private void addInfoCar() {
        if(bean!=null && popupWindow!=null) {
            String text_count = tv_show_count.getText().toString();
            int number = Integer.parseInt(text_count);
            for (int i = 0; i < bean.getData().getProductList().size(); i++) {
                persenter.addCarInfo(bean.getData().getProductList().get(i).getGoods_id(),
                        number,
                        bean.getData().getProductList().get(i).getId());
            }
        }else{
            showDetail();
        }
    }

    private void showDetail() {
        View view  = LayoutInflater.from(this).inflate(R.layout.popu_detail,null);
        popupWindow = new PopupWindow(view,GridView.LayoutParams.MATCH_PARENT,
                GridView.LayoutParams.WRAP_CONTENT);

        TextView tv_closs = view.findViewById(R.id.tv_closs);
        TextView tv_car_price = view.findViewById(R.id.tv_car_price);
        TextView tv_reduce_count = view.findViewById(R.id.tv_reduce_count);
        tv_show_count = view.findViewById(R.id.tv_show_count);
        TextView tv_add_count = view.findViewById(R.id.tv_add_count);
        ImageView iv_addcar = view.findViewById(R.id.iv_addcar);

        if(img_url!=null)
        Glide.with(this).load(img_url).into(iv_addcar);

        tv_car_price.setText(retail_price);
        tv_reduce_count.setOnClickListener(this);
        tv_add_count.setOnClickListener(this);

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = view.getMeasuredWidth();
        int popupHeight = view.getMeasuredHeight();
        int[] location = new int[2];
        bottom_con.getLocationOnScreen(location);

        popupWindow.showAtLocation(bottom_con, Gravity.NO_GRAVITY, (
                location[0] + bottom_con.getWidth() / 2) - popupWidth / 2,
                -location[1] - popupHeight);

        //popupWindow.showAsDropDown(bottom_con,0,-height1);

        tv_closs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    class BannerLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            String img_url = ((RelatedBean.DataBeanX.GalleryBean) path).getImg_url();
            Glide.with(context).load(img_url).into(imageView);
        }
    }
}
