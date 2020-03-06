package com.example.activity.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jiyunproject.R;

import base.BaseActivity;
import interfaces.regist.RegsitConstract;
import model.bean.LoginBean;
import model.bean.RegistBean;
import presenter.regist.RegistPresenter;
import utils.ShowToast;

public class RegisterActivity extends BaseActivity<RegsitConstract.Presenter> implements RegsitConstract.View, View.OnClickListener {
    /**
     * 输入用户名
     */
    private EditText mEditNickname;
    /**
     * 输入密码
     */
    private EditText mEditRegistPw;
    /**
     * 再次输入密码
     */
    private EditText mEditRegistRepw;
    /**
     * 注册
     */
    private Button mBtnRegist;

    @Override
    protected RegsitConstract.Presenter initPresenter() {
        return new RegistPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        mEditNickname = (EditText) findViewById(R.id.edit_nickname);
        mEditRegistPw = (EditText) findViewById(R.id.edit_regist_pw);
        mEditRegistRepw = (EditText) findViewById(R.id.edit_regist_repw);

        mBtnRegist = (Button) findViewById(R.id.btn_regist);
        mBtnRegist.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_regist;
    }

    @Override
    public void getRegistData(LoginBean registBean) {

        Log.i("tag","============> "+registBean);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_regist:
                regist();
                break;
        }
    }

    private void regist() {

        String name = mEditNickname.getText().toString();
        String paw = mEditRegistPw.getText().toString();
        String repaw = mEditRegistRepw.getText().toString();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(paw)) {
            persenter.getRegist(name,paw,repaw);
        } else {
            ShowToast.show("账号密码不能为空");

        }

    }
}
