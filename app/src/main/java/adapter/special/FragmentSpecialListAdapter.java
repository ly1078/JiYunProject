package adapter.special;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jiyunproject.R;

import java.util.ArrayList;
import java.util.List;

import base.BaseAdapter;
import model.bean.SpecialLeadData;

public class FragmentSpecialListAdapter extends BaseAdapter {

    public FragmentSpecialListAdapter(Context conext, ArrayList list) {
        super(conext, list);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_special_list_item;
    }

    @Override
    protected void bindData(BaseHolder holder, Object o) {
        SpecialLeadData.DataBean.CurrentCategoryBean.SubCategoryListBean listBean = (SpecialLeadData.DataBean.CurrentCategoryBean.SubCategoryListBean) o;
        ImageView iv_special_item = (ImageView) holder.getViewById(R.id.iv_special_item);
        TextView tv_special_name = (TextView) holder.getViewById(R.id.tv_special_name);

        tv_special_name.setText(listBean.getName());
        Glide.with(conext).load(listBean.getWap_banner_url()).into(iv_special_item);
    }
}
