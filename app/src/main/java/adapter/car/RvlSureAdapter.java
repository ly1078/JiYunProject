package adapter.car;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jiyunproject.R;

import java.util.ArrayList;

import base.BaseAdapter;
import model.bean.car.CarInfo;

public class RvlSureAdapter extends BaseAdapter {
    public RvlSureAdapter(Context conext, ArrayList list) {
        super(conext, list);
    }

    @Override
    protected int getLayout() {
        return R.layout.sure_item;
    }

    @Override
    protected void bindData(BaseHolder holder, Object o) {
        CarInfo.DataBean.CartListBean cartListBean = (CarInfo.DataBean.CartListBean) o;

        ((TextView)holder.getViewById(R.id.suer_tv_car_name_item)).setText(cartListBean.getGoods_name());
        ((TextView)holder.getViewById(R.id.suer_tv_car_price_item)).setText(cartListBean.getRetail_price()+"");
        ((TextView)holder.getViewById(R.id.suer_tv_car_item_count)).setText("X"+cartListBean.getNumber());
        Glide.with(conext).load(cartListBean.getList_pic_url()).into((ImageView)holder.getViewById(R.id.suer_iv_car_item));

    }
}
