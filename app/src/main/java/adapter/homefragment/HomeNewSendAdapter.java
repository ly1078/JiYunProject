package adapter.homefragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jiyunproject.R;

import java.util.ArrayList;

import base.BaseAdapter;
import model.bean.HomeBean;

public class HomeNewSendAdapter extends BaseAdapter {

    public HomeNewSendAdapter(Context conext, ArrayList list) {
        super(conext, list);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_new_item;
    }

    @Override
    protected void bindData(BaseHolder holder, Object o) {

            HomeBean.DataBean.NewGoodsListBean newGoodsListBean = (HomeBean.DataBean.NewGoodsListBean) o;
            TextView name = (TextView) holder.getViewById(R.id.tv_new_name);
            ImageView iv_new = (ImageView) holder.getViewById(R.id.iv_new);
            TextView tv_new_price = (TextView) holder.getViewById(R.id.tv_new_price);

            name.setText(newGoodsListBean.getName());
            tv_new_price.setText(newGoodsListBean.getRetail_price()+"");
            Glide.with(conext).load(newGoodsListBean.getList_pic_url()).into(iv_new);

    }
}
