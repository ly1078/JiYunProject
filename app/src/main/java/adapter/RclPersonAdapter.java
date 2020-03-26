package adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jiyunproject.R;

import java.util.ArrayList;

import base.BaseAdapter;
import model.bean.PersonItemBean;

public class RclPersonAdapter extends BaseAdapter {

    public RclPersonAdapter(Context conext, ArrayList list) {
        super(conext, list);
    }

    @Override
    protected int getLayout() {
        return R.layout.person_item;
    }

    @Override
    protected void bindData(BaseHolder holder, Object o) {
        PersonItemBean itemBean = (PersonItemBean) o;
        ((ImageView)holder.getViewById(R.id.person_item_iv)).setImageResource(itemBean.getIcon());
        ((TextView)holder.getViewById(R.id.person_item_tv)).setText(itemBean.getName());
    }
}
