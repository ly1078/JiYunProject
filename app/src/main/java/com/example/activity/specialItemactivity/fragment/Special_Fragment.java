package com.example.activity.specialItemactivity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activity.goodsinfo.GoodsDetailsActivity;
import com.example.jiyunproject.R;

import java.util.ArrayList;
import java.util.List;

import base.BaseAdapter;
import base.BaseFragment;
import interfaces.special.Special_ItemConstract;
import model.bean.special.SpecialItemShowData;
import model.bean.special.SpecialTabLeadList;
import presenter.special.specialitem.SpecialItemPresenter;

public class Special_Fragment extends BaseFragment<Special_ItemConstract.Presenter> implements  Special_ItemConstract.View{

    private RecyclerView recyclerView;

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        int id = arguments.getInt("id");
        presenter.getSpeciaItemListData(id,1,100);
    }

    @Override
    protected void initView(View v) {
        recyclerView = v.findViewById(R.id.fragment_recycler_special);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
    }

    @Override
    protected Special_ItemConstract.Presenter initPresenter() {
        return new SpecialItemPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_special_item_showlist;
    }

    @Override
    public void returnSpeciaItemLeadResult(SpecialTabLeadList tabLeadList) {

    }

    @Override
    public void returnSpeciaItemDataResult(SpecialItemShowData itemShowData) {
        ArrayList<SpecialItemShowData.DataBeanX.DataBean> data =
                (ArrayList<SpecialItemShowData.DataBeanX.DataBean>) itemShowData.getData().getData();

        SpecialFragmentListAdapter listAdapter = new SpecialFragmentListAdapter(context,data);

        recyclerView.setAdapter(listAdapter);
        listAdapter.setItemClik(new BaseAdapter.BaseOnItemClik() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                int id = data.get(position).getId();
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
}
