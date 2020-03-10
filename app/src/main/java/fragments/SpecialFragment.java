package fragments;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jiyunproject.R;

import java.util.ArrayList;
import java.util.List;

import adapter.special.FragmentSpecialListAdapter;
import base.BaseFragment;
import interfaces.special.SpecialConstract;
import model.bean.SpecialLeadData;
import presenter.special.SpecialPresenter;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

/*
    分类
 */
public class SpecialFragment extends BaseFragment<SpecialConstract.Presenter> implements SpecialConstract.View, VerticalTabLayout.OnTabSelectedListener {

    private VerticalTabLayout vTab;
    private TextView special_show_titlename;
    private TextView special_fornt_name;
    private ImageView special_img;
    private RecyclerView recycler_special;
    private List<SpecialLeadData.DataBean.CategoryListBean> categoryList;
    private FragmentSpecialListAdapter specialListAdapter;
    private ArrayList<SpecialLeadData.DataBean.CurrentCategoryBean.SubCategoryListBean> special_list;

    @Override
    protected void initData() {
        presenter.getSpeciaLead();
    }

    @Override
    protected void initView(View v) {
        // SubCategoryListBean
        special_list = new ArrayList<SpecialLeadData.DataBean.CurrentCategoryBean.SubCategoryListBean>();
        recycler_special = v.findViewById(R.id.recycler_special);
        recycler_special.setLayoutManager(new GridLayoutManager(context,3));
        specialListAdapter = new FragmentSpecialListAdapter(context,special_list);
        recycler_special.setAdapter(specialListAdapter);

        special_img = v.findViewById(R.id.special_img);
        special_fornt_name = v.findViewById(R.id.special_fornt_name);
        special_show_titlename = v.findViewById(R.id.special_show_titlename);

        vTab = v.findViewById(R.id.vTab);
        vTab.addOnTabSelectedListener(this);
    }

    @Override
    protected SpecialConstract.Presenter initPresenter() {
        return new SpecialPresenter();
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_special;
    }

    // 导航数据
    @Override
    public void returnSpeciaLeadResult(SpecialLeadData leadData) {
        SpecialLeadData.DataBean data = leadData.getData();
        categoryList = data.getCategoryList();
        SpecialLeadData.DataBean.CurrentCategoryBean currentCategory = data.getCurrentCategory();
        showLead(categoryList); // 导航
        showData(currentCategory); // 第一页数据
    }
    //页面数据
    private void showData(SpecialLeadData.DataBean.CurrentCategoryBean currentCategory) {

        Glide.with(context).load(currentCategory.getWap_banner_url()).into(special_img);
        special_fornt_name.setText(currentCategory.getFront_name());
        special_show_titlename.setText("---"+currentCategory.getName()+"分类---");

        List<SpecialLeadData.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList = currentCategory.getSubCategoryList();
        specialListAdapter.refreshAdapter(subCategoryList);
    }
    // 导航
    private void showLead(List<SpecialLeadData.DataBean.CategoryListBean> categoryList) {
        for (int i = 0; i <categoryList.size() ; i++) {
            QTabView qTabView = new QTabView(context);
            TextView titleView = qTabView.getTitleView();
            titleView.setText(categoryList.get(i).getName());
            vTab.addTab(qTabView);
        }
    }

    //刷新结果
    @Override
    public void returnSpeciaDataResult(SpecialLeadData listData) {
        SpecialLeadData.DataBean.CurrentCategoryBean currentCategory = listData.getData().getCurrentCategory();
        showData(currentCategory);
    }

    //刷新请求
    @Override
    public void onTabSelected(TabView tab, int position) {
        int id = categoryList.get(position).getId();
        presenter.getSpeciaListData(id);
    }

    @Override
    public void onTabReselected(TabView tab, int position) {

    }
}
