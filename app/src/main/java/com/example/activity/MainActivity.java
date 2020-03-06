package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jiyunproject.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import adapter.MainVpAdapter;
import fragments.ClassificationFragment;
import fragments.HomeFragment;
import fragments.PersonalFragment;
import fragments.ShoppingCarFragment;
import fragments.SpecialFragment;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private SpecialFragment specialFragment;
    private ClassificationFragment classificationFragment;
    private ShoppingCarFragment shoppingCarFragment;
    private PersonalFragment personalFragment;
    private TabLayout main_tab;
    private ViewPager main_vp;
    private int [] tabIcons ={R.drawable.home_selector,R.drawable.special_selector,
            R.drawable.classi_selector,R.drawable.shopping_selector,R.drawable.me_selector};
    private String [] tabTitles ={"首页","专题","分类","购物车","我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFragment();
    }

    private void initView() {

        main_tab = findViewById(R.id.main_tab);
        main_vp = findViewById(R.id.main_vp);

    }

    private void initFragment() {

        homeFragment = new HomeFragment();
        specialFragment = new SpecialFragment();
        classificationFragment = new ClassificationFragment();
        shoppingCarFragment = new ShoppingCarFragment();
        personalFragment = new PersonalFragment();

        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(homeFragment);
        fragments.add(classificationFragment);
        fragments.add(specialFragment);
        fragments.add(shoppingCarFragment);
        fragments.add(personalFragment);

        MainVpAdapter mainVpAdapter = new MainVpAdapter(getSupportFragmentManager(),fragments);
        main_vp.setAdapter(mainVpAdapter);
        main_tab.setupWithViewPager(main_vp);

        initTatbItem(fragments);

    }

    private void initTatbItem(ArrayList<Fragment> fragments) {
        for (int i = 0; i <fragments.size(); i++) {
            main_tab.getTabAt(i).setCustomView(setTabIten(tabIcons[i],tabTitles[i]));
        }
    }

    private View setTabIten(int icon,String tabTitle){
        View view = LayoutInflater.from(this).inflate(R.layout.layout_main_tab_item,null);
        ImageView iv = view.findViewById(R.id.main_tab_item_icon);
        TextView tv = view.findViewById(R.id.main_tab_item_title);
        iv.setBackgroundResource(icon);
        tv.setText(tabTitle);
        return view;
    }
}
