package fragments;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.activity.homefragment.BrandActivity;
import com.example.jiyunproject.R;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import adapter.homefragment.HomeBrandAdapter;
import adapter.homefragment.HomeCategoryListAdapter;
import adapter.homefragment.HomeNewSendAdapter;
import adapter.homefragment.HomeRecommedAdapter;
import adapter.homefragment.HomeTopicListSendAdapter;
import base.BaseAdapter;
import base.BaseFragment;
import interfaces.home.HomeConstract;
import model.bean.HomeBean;
import presenter.home.HomPresenter;

/*
    首页列表 */
public class HomeFragment extends BaseFragment<HomeConstract.Persenter> implements HomeConstract.View, OnBannerListener {

    private RecyclerView recyclerView;
    private ArrayList<HomeBean.DataBean.BrandListBean> brandListBeans;
    private HomeBrandAdapter homeBrandAdapter;
    private Banner banner;
    private RecyclerView recy_new_send;
    private ArrayList<HomeBean.DataBean.NewGoodsListBean> newGoodsListBeans;
    private HomeNewSendAdapter home_new_adpter;
    private ArrayList<HomeBean.DataBean.HotGoodsListBean> recommend_list;
    private RecyclerView recy_recommend;
    private HomeRecommedAdapter recommend_adapter;
    private TabLayout home_tab;
    private ArrayList<HomeBean.DataBean.TopicListBean> topicListBeans;
    private RecyclerView recy_topiclist;
    private HomeTopicListSendAdapter topiclist_adapter;
    private RecyclerView recy_catogray;
    private ArrayList<HomeBean.DataBean.CategoryListBean> categoryListBeans;
    private HomeCategoryListAdapter homeCategoryListAdapter;

    @Override
    protected void initData() {
        presenter.getHomeData();
    }

    @Override
    protected void initView(View v) {

        home_tab = v.findViewById(R.id.home_fragment_tab);

        // 轮播图
        brandListBeans = new ArrayList<>();
        banner = v.findViewById(R.id.home_banner);
        banner.setOnBannerListener(this);

        //直供
        recyclerView = v.findViewById(R.id.recy_brand);
        brandAdapter();

        //新品
        recy_new_send = v.findViewById(R.id.recy_new_send);
        newGoods();

        //人气推荐
        recy_recommend = v.findViewById(R.id.recy_recommend);
        recommend();

        //专题精选
        recy_topiclist = v.findViewById(R.id.recy_topiclist);
        topicList();

        //居家 餐厨  饮食  配件  服装  婴童  杂货  洗护  志趣
        recy_catogray = v.findViewById(R.id.recy_catogray);
        catograyList();
    }

    private void catograyList() {
        categoryListBeans = new ArrayList<>();
        recy_catogray.setLayoutManager(new LinearLayoutManager(context));
        homeCategoryListAdapter = new HomeCategoryListAdapter(categoryListBeans, context);
        recy_catogray.setAdapter(homeCategoryListAdapter);
    }

    private void topicList() {
        topicListBeans = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recy_topiclist.setLayoutManager(linearLayoutManager);
        topiclist_adapter = new HomeTopicListSendAdapter(context,topicListBeans);
        recy_topiclist.setAdapter(topiclist_adapter);
    }

    private void recommend() {
        recommend_list = new ArrayList<>();
        recy_recommend.setLayoutManager(new LinearLayoutManager(context));
        recommend_adapter = new HomeRecommedAdapter(context,recommend_list);
        recy_recommend.setAdapter(recommend_adapter);
    }

    private void newGoods() {
        newGoodsListBeans = new ArrayList<>();
        recy_new_send.setLayoutManager(new GridLayoutManager(context,2));
        home_new_adpter = new HomeNewSendAdapter(context,newGoodsListBeans);
        recy_new_send.setAdapter(home_new_adpter);
    }

    private void brandAdapter() {

        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        homeBrandAdapter = new HomeBrandAdapter(context,brandListBeans);
        recyclerView.setAdapter(homeBrandAdapter);
        homeBrandAdapter.setItemClik(new BaseAdapter.BaseOnItemClik() {
            @Override
            public void onItemClick(int position) {
                HomeBean.DataBean.BrandListBean brandListBean = brandListBeans.get(position);
                Intent intent = new Intent(context, BrandActivity.class);
                intent.putExtra("brand",brandListBean);
                startActivity(intent);
            }
        });
    }

    @Override
    protected HomeConstract.Persenter initPresenter() {
        return new HomPresenter();
    }
    @Override
    protected int getLayout() {
        return R.layout.layout_home_fragment;
    }
    @Override
    public void getHomeDataReturn(HomeBean result) {

        //tab
        List<HomeBean.DataBean.ChannelBean> channel = result.getData().getChannel();
        for (int i = 0; i < channel.size(); i++) {
            home_tab.addTab(home_tab.newTab().setText(channel.get(i).getName()));
        }

        //直供
        List<HomeBean.DataBean.BrandListBean> brandList = result.getData().getBrandList();
        homeBrandAdapter.refreshAdapter(brandList);
        //轮播图
        banner.setImages(result.getData().getBanner());
        banner.setImageLoader(new HomeBannerLoader());
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();

        // 新品
        List<HomeBean.DataBean.NewGoodsListBean> newGoodsList = result.getData().getNewGoodsList();
        home_new_adpter.refreshAdapter(newGoodsList);

        //人气推荐
        List<HomeBean.DataBean.HotGoodsListBean> hotGoodsList = result.getData().getHotGoodsList();
        recommend_adapter.refreshAdapter(hotGoodsList);

        //专题精选
        List<HomeBean.DataBean.TopicListBean> topicList = result.getData().getTopicList();
        topiclist_adapter.refreshAdapter(topicList);

        // 居家 餐厨  饮食  配件  服装  婴童  杂货  洗护  志趣
        List<HomeBean.DataBean.CategoryListBean> categoryList = result.getData().getCategoryList();
        homeCategoryListAdapter.refreshAdapter(categoryList);

    }

    @Override
    public void OnBannerClick(int position) {
        showLog("点击轮播图： "+position);
    }

    class HomeBannerLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            HomeBean.DataBean.BannerBean bannerBean = (HomeBean.DataBean.BannerBean) path;
            Glide.with(context).load(bannerBean.getImage_url()).into(imageView);
        }
    }

}
