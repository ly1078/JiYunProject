package fragments;

import android.view.View;

import com.example.jiyunproject.R;

import base.BaseFragment;
import interfaces.IBasePresenter;
import interfaces.IBaseView;

/*
    分类
 */
public class SpecialFragment extends BaseFragment implements IBaseView {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View v) {

    }

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_home_fragment;
    }
}
