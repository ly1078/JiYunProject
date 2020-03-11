package com.example.activity.specialItemactivity.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import base.BaseFragment;
import model.bean.special.SpecialTabLeadList;

public class SpecialFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> list;
    private List<SpecialTabLeadList.DataBean.BrotherCategoryBean> brotherCategory;
    public SpecialFragmentAdapter(@NonNull FragmentManager fm, ArrayList<BaseFragment> list, List<SpecialTabLeadList.DataBean.BrotherCategoryBean> brotherCategory) {
        super(fm);
        this.list = list;
        this.brotherCategory = brotherCategory;
    }

    public SpecialFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return brotherCategory.get(position).getName();
    }
}
