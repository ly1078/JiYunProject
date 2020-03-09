package adapter.homefragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jiyunproject.R;

import java.util.ArrayList;

import base.BaseAdapter;
import model.bean.HomeBean;

public class HomeCategoryItemAdapter extends BaseAdapter {

    public HomeCategoryItemAdapter(Context conext, ArrayList list) {
        super(conext, list);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_categorylist_item;
    }

    @Override
    protected void bindData(BaseHolder holder, Object o) {
            HomeBean.DataBean.CategoryListBean.GoodsListBean goodsListBean = (HomeBean.DataBean.CategoryListBean.GoodsListBean) o;
            TextView name = (TextView) holder.getViewById(R.id.tv_item_name);
            ImageView iv_new = (ImageView) holder.getViewById(R.id.iv_item);
            TextView tv_new_price = (TextView) holder.getViewById(R.id.tv_item_price);

            name.setText(goodsListBean.getName());
            tv_new_price.setText(goodsListBean.getRetail_price()+"");
            Glide.with(conext).load(goodsListBean.getList_pic_url()).into(iv_new);

    }
}
