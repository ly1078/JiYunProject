package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jiyunproject.R;

import java.util.ArrayList;

import adapter.car.RvlSureAdapter;
import model.bean.car.CarInfo;

public class SuerBuyActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * ￥0
     */
    private TextView mTvShowAllPrice;
    /**
     * ￥0
     */
    private TextView mTvSendPrice;
    /**
     * -￥0
     */
    private TextView mTvShowReducePrice;
    private RecyclerView mShowRecyclerOk;
    /**
     * 实付：￥0
     */
    private TextView mTvPayPrice;
    /**
     * 去付款
     */
    private Button mBtnGoPay;
    private RelativeLayout mRelBottom;
    private TextView tv_edit_address;
    private ArrayList<CarInfo.DataBean.CartListBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suer_buy);
        initView();
    }
    private void initView() {
        tv_edit_address = findViewById(R.id.tv_edit_address);
        tv_edit_address.setOnClickListener(this);
        mTvShowAllPrice = (TextView) findViewById(R.id.tv_show_all_price);
        mTvSendPrice = (TextView) findViewById(R.id.tv_send_price);
        mTvShowReducePrice = (TextView) findViewById(R.id.tv_show_reduce_price);
        mTvShowReducePrice.setOnClickListener(this);
        mShowRecyclerOk = (RecyclerView) findViewById(R.id.show_recycler_ok);
        mTvPayPrice = (TextView) findViewById(R.id.tv_pay_price);
        mBtnGoPay = (Button) findViewById(R.id.btn_go_pay);
        mBtnGoPay.setOnClickListener(this);
        mRelBottom = (RelativeLayout) findViewById(R.id.rel_bottom);

        Intent intent = getIntent();
        //下单页面要显示的数据
        list = (ArrayList<CarInfo.DataBean.CartListBean>) intent.getSerializableExtra("getAll");

        setAllPrice();

        mShowRecyclerOk.setLayoutManager(new LinearLayoutManager(this));
        RvlSureAdapter carRlvAdapter = new RvlSureAdapter(this,list);
        mShowRecyclerOk.setAdapter(carRlvAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_show_reduce_price:// 选择优惠券   弹窗   关闭

               // mTvPayPrice.setText("实付：￥"+sum);// 总结 - 优惠  =  实付
                break;

            case R.id.btn_go_pay://去支付

                break;

            case R.id.tv_edit_address:// 去编辑地址

                break;
        }
    }

    private void setAllPrice() {
        //计算一下总价   选中item存储的集合
        int sum = 0;

        for (int i = 0; i < list.size(); i++) {
            sum+=list.get(i).getRetail_price()*list.get(i).getNumber();
        }
        mTvShowAllPrice.setText("￥"+sum);
        mTvPayPrice.setText("实付：￥"+sum);
    }
}
