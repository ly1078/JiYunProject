package adapter.homefragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jiyunproject.R;

import java.util.ArrayList;

import base.BaseAdapter;
import model.bean.HomeBean;

public class HomeBrandAdapter extends BaseAdapter {

    public HomeBrandAdapter(Context conext, ArrayList list) {
        super(conext, list);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_brand_item;
    }

    @Override
    protected void bindData(BaseHolder holder, Object o) {
        HomeBean.DataBean.BrandListBean bannerBean = (HomeBean.DataBean.BrandListBean) o;
        ImageView iv = (ImageView) holder.getViewById(R.id.home_img_brand);
        TextView tvtitle = (TextView) holder.getViewById(R.id.home_txt_title);
        TextView tvprice = (TextView) holder.getViewById(R.id.home_txt_price);

        Glide.with(conext).load(bannerBean.getNew_pic_url()).into(iv);
        tvprice.setText(bannerBean.getFloor_price()+"");
        tvtitle.setText(bannerBean.getName());
    }
}
