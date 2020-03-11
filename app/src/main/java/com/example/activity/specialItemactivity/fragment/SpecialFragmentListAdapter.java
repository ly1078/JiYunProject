package com.example.activity.specialItemactivity.fragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jiyunproject.R;

import java.util.ArrayList;

import base.BaseAdapter;
import model.bean.special.SpecialItemShowData;

public class SpecialFragmentListAdapter extends BaseAdapter {

    public SpecialFragmentListAdapter(Context conext, ArrayList list) {
        super(conext, list);
    }

    @Override
    protected int getLayout() {
        return R.layout.special_item_fragment_list_item;
    }

    @Override
    protected void bindData(BaseHolder holder, Object o) {

        SpecialItemShowData.DataBeanX.DataBean dataBean = (SpecialItemShowData.DataBeanX.DataBean) o;

        TextView name = (TextView) holder.getViewById(R.id.tv_retail_name);
        TextView tv_retail_price = (TextView) holder.getViewById(R.id.tv_retail_price);
        ImageView ist_pic_url = (ImageView) holder.getViewById(R.id.special_fragment_iv_list_pic_url);

        name.setText(dataBean.getName());
        tv_retail_price.setText("￥"+dataBean.getRetail_price());
        Glide.with(conext).load(dataBean.getList_pic_url()).into(ist_pic_url);
    }
}
