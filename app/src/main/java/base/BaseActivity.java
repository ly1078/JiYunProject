package base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import interfaces.IBasePresenter;
import interfaces.IBaseView;

public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    protected P persenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        persenter = initPresenter();
        if(persenter!=null){
            persenter.onAttachView(this);
        }
        initView();
        initData();

    }

    protected abstract P initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayout();


    @Override
    public void showLog(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(persenter!=null){
            persenter.unAttachView();
        }
    }
}
