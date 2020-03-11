package com.example.activity.specialItemactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.activity.specialItemactivity.fragment.SpecialFragmentAdapter;
import com.example.activity.specialItemactivity.fragment.Special_Fragment;
import com.example.jiyunproject.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import base.BaseFragment;
import interfaces.IBasePresenter;
import interfaces.IBaseView;
import interfaces.special.Special_ItemConstract;
import model.bean.special.SpecialItemShowData;
import model.bean.special.SpecialTabLeadList;
import presenter.special.specialitem.SpecialItemPresenter;

public class SpecialItemActivity extends BaseActivity<Special_ItemConstract.Presenter> implements Special_ItemConstract.View, TabLayout.BaseOnTabSelectedListener {

    private TabLayout tab_special;
    private ViewPager vp_special;
    private TextView tvName;
    private TextView tv_front_name;
    private ArrayList<BaseFragment> fragments;
    private List<SpecialTabLeadList.DataBean.BrotherCategoryBean> brotherCategory;


    @Override
    protected Special_ItemConstract.Presenter initPresenter() {

        return new SpecialItemPresenter();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        persenter.getSpeciaItemLead(id);
        persenter.getSpeciaItemListData(id,1,100);
    }

    @Override
    protected void initView() {

        tab_special = findViewById(R.id.shpecial_item_tab);
        vp_special = findViewById(R.id.vp_special);

        tvName = findViewById(R.id.tv_name);
        tv_front_name = findViewById(R.id.tv_front_name);

        tab_special.addOnTabSelectedListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_special_item;
    }

    @Override
    public void returnSpeciaItemLeadResult(SpecialTabLeadList tabLeadList) {
        Log.i("tag","==> "+tabLeadList);
        brotherCategory = tabLeadList.getData().getBrotherCategory();
        /*for (int i = 0; i < brotherCategory.size(); i++) {
            tab_special.addTab(tab_special.newTab().setText(brotherCategory.get(i).getName()));
        }*/
        tvName.setText(brotherCategory.get(0).getName());
        tv_front_name.setText(brotherCategory.get(0).getFront_name());

        initFragment(brotherCategory);
    }

    private void initFragment(List<SpecialTabLeadList.DataBean.BrotherCategoryBean> brotherCategory) {
        fragments = new ArrayList<>();
        for (int i = 0; i < brotherCategory.size(); i++) {
            Special_Fragment special_fragment = new Special_Fragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id",brotherCategory.get(i).getId());
            special_fragment.setArguments(bundle);
            fragments.add(special_fragment);
        }
        SpecialFragmentAdapter specialFragmentAdapter = new SpecialFragmentAdapter(getSupportFragmentManager(),fragments,brotherCategory);
        vp_special.setAdapter(specialFragmentAdapter);

        tab_special.setupWithViewPager(vp_special);
    }

    @Override
    public void returnSpeciaItemDataResult(SpecialItemShowData itemShowData) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        if(brotherCategory!=null) {
            tvName.setText(brotherCategory.get(position).getName());
            tv_front_name.setText(brotherCategory.get(position).getFront_name());
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
