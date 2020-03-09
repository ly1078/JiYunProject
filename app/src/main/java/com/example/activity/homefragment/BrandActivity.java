package com.example.activity.homefragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.jiyunproject.R;

import model.bean.HomeBean;

public class BrandActivity extends AppCompatActivity {

    private ImageView mBrandImgShow;
    /**
     * name
     */
    private TextView mBrandNameShow;
    /**
     * 测试
     */
    private TextView mBrandDescShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        initView();
    }

    private void initView() {
        mBrandImgShow = (ImageView) findViewById(R.id.brand_img_show);
        mBrandNameShow = (TextView) findViewById(R.id.brand_name_show);
        mBrandDescShow = (TextView) findViewById(R.id.brand_desc_show);

        Intent intent = getIntent();
        HomeBean.DataBean.BrandListBean  brand = (HomeBean.DataBean.BrandListBean) intent.getSerializableExtra("brand");

        mBrandNameShow.setText(brand.getName());
        mBrandDescShow.setText(brand.getSimple_desc());
        Glide.with(this).load(brand.getList_pic_url()).into(mBrandImgShow);
    }
}
