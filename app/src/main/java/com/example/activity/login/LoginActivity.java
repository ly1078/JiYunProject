package com.example.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.activity.MainActivity;
import com.example.jiyunproject.R;

import base.BaseActivity;
import interfaces.login.LoginConstract;
import model.bean.LoginBean;
import presenter.login.LoginPresenter;
import utils.ShowToast;
import utils.SpUtils;

public class LoginActivity extends BaseActivity<LoginConstract.Presenter> implements LoginConstract.View, View.OnClickListener {

    /**
     * 输入用户名
     */
    private EditText mEditNickname;
    /**
     * 输入密码
     */
    private EditText mEditPw;
    /**
     * login
     */
    private Button mBtnLogin;
    private Button btn_go_regist;
    private String name;
    private String paw;
    private CheckBox cb;

    @Override
    protected LoginConstract.Presenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        cb = findViewById(R.id.cb);
        mEditNickname = (EditText) findViewById(R.id.edit_nickname);
        mEditPw = (EditText) findViewById(R.id.edit_pw);
        mBtnLogin = findViewById(R.id.btn_login);
        btn_go_regist = findViewById(R.id.btn_go_regist);

        mBtnLogin.setOnClickListener(this);
        btn_go_regist.setOnClickListener(this);
        Boolean iscb = SpUtils.getInstance().getBoolean("cb");
        if(iscb){
            mEditNickname.setText(SpUtils.getInstance().getString("name"));
            mEditPw.setText(SpUtils.getInstance().getString("paw"));
        }
        cb.setChecked(iscb);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void getLoginData(LoginBean loginBean) {
        SpUtils.getInstance().setValue("token", loginBean.getData().getToken());
        Log.i("tag", "===> " + loginBean);
        boolean checked = cb.isChecked();
        if(checked){
            SpUtils.getInstance().setValue("name",name);
            SpUtils.getInstance().setValue("paw",paw);
        }
        SpUtils.getInstance().setValue("cb",checked);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void login() {
        name = mEditNickname.getText().toString();
        paw = mEditPw.getText().toString();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(paw)) {
            persenter.getLogin(name, paw);
        } else {
            ShowToast.show("账号密码不能为空");

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_go_regist:
                startActivityForResult(new Intent(this,RegisterActivity.class),1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1 && resultCode ==2){
            String name = data.getStringExtra("name");
            String paw = data.getStringExtra("paw");
            mEditNickname.setText(name);
            mEditPw.setText(paw);
        }
    }
}
