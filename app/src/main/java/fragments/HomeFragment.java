package fragments;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jiyunproject.R;

import base.BaseFragment;
import interfaces.home.HomeConstract;
import model.bean.HomeBean;
import presenter.home.HomPresenter;
/*
    首页
 */
public class HomeFragment extends BaseFragment<HomeConstract.Persenter> implements HomeConstract.View {
    private TextView tv;
    @Override
    protected void initData() {
        presenter.getHomeData();
    }

    @Override
    protected void initView(View v) {

        tv = v.findViewById(R.id.tv);

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

        Log.i("tag","Override getHomeDataReturn===> "+result.getData().getBanner().get(0).getContent());
        tv.setText(result.getData().getBanner().get(0).getContent()+"");
    }

}
