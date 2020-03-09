package adapter.homefragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jiyunproject.R;

import java.util.ArrayList;

import base.BaseAdapter;
import model.bean.HomeBean;

public class HomeRecommedAdapter extends BaseAdapter {

    public HomeRecommedAdapter(Context conext, ArrayList list) {
        super(conext, list);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_recommend_item;
    }

    @Override
    protected void bindData(BaseHolder holder, Object o) {

        HomeBean.DataBean.HotGoodsListBean hotGoodsListBean = (HomeBean.DataBean.HotGoodsListBean) o;
        TextView tv_recommend_name = (TextView) holder.getViewById(R.id.tv_recommend_name);
        ImageView iv_recommend = (ImageView) holder.getViewById(R.id.iv_recommend);
        TextView tv_recommend_price = (TextView) holder.getViewById(R.id.tv_recommend_price);
        TextView tv_recommend_desc = (TextView) holder.getViewById(R.id.tv_recommend_desc);

        tv_recommend_name.setText(hotGoodsListBean.getName());
        tv_recommend_price.setText(hotGoodsListBean.getRetail_price()+"");
        tv_recommend_desc.setText(hotGoodsListBean.getGoods_brief());
        Glide.with(conext).load(hotGoodsListBean.getList_pic_url()).into(iv_recommend);

    }
}
