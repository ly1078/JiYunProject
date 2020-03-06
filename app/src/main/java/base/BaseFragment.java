package base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import interfaces.IBasePresenter;
import interfaces.IBaseView;

public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView {
    protected Context context;
    protected Activity activity;
    protected P presenter;
    private Unbinder bind;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        activity = getActivity();
        View view  = LayoutInflater.from(context).inflate(getLayout(),container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(view);
        presenter = initPresenter();
        if(presenter!=null){
            presenter.onAttachView(this);
        }
        initView(view);
        initData();
    }
    protected abstract void initData();
    protected abstract void initView(View v);
    protected abstract P initPresenter();
    protected abstract int getLayout();
    @Override
    public void showLog(String msg) {
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.unAttachView();
        }
        if(bind!=null){
            bind.unbind();
        }
    }
}
