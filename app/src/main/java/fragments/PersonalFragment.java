package fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jiyunproject.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

import adapter.RclPersonAdapter;
import base.BaseFragment;
import interfaces.IBasePresenter;
import interfaces.IBaseView;
import model.bean.PersonItemBean;
import utils.SpUtils;

/**
 * 我的
 */
public class PersonalFragment extends BaseFragment implements IBaseView {

    private ArrayList<PersonItemBean> arrayList;
    private RecyclerView recycler_my;
    private TextView tv_person_name;
    private ImageView iv_login;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View v) {
        arrayList = new ArrayList<>();
        arrayList.add(new PersonItemBean("我的订单",R.drawable.icon_discover_u));
        arrayList.add(new PersonItemBean("优惠券",R.drawable.icon_knowledge_hierarchy_not_selected));
        arrayList.add(new PersonItemBean("礼品卡",R.drawable.icon_navigation_not_selected));
        arrayList.add(new PersonItemBean("我的收藏",R.drawable.home_unselected));
        arrayList.add(new PersonItemBean("我的足迹",R.drawable.icon_navigation_not_selected));
        arrayList.add(new PersonItemBean("会员福利",R.drawable.icon_knowledge_hierarchy_not_selected));
        arrayList.add(new PersonItemBean("地址管理",R.drawable.icon_navigation_not_selected));
        arrayList.add(new PersonItemBean("账号安全",R.drawable.icon_navigation_not_selected));
        arrayList.add(new PersonItemBean("联系客服",R.drawable.icon_discover_u));
        arrayList.add(new PersonItemBean("帮助中心",R.drawable.icon_knowledge_hierarchy_not_selected));
        arrayList.add(new PersonItemBean("意见反馈",R.drawable.icon_discover_u));

        tv_person_name = v.findViewById(R.id.tv_person_name);
        iv_login = v.findViewById(R.id.iv_login);

        String name = SpUtils.getInstance().getString("name");
        if(name!=null)
        tv_person_name.setText(name);

        recycler_my = v.findViewById(R.id.recycler_my);
        recycler_my.setLayoutManager(new GridLayoutManager(getContext(),3));
        RclPersonAdapter rclPersonAdapter = new RclPersonAdapter(getContext(), arrayList);
        recycler_my.setAdapter(rclPersonAdapter);
    }

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_person;
    }
}
