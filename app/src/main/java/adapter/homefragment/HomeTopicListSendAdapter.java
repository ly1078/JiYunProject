package adapter.homefragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jiyunproject.R;

import java.util.ArrayList;

import base.BaseAdapter;
import model.bean.HomeBean;

public class HomeTopicListSendAdapter extends BaseAdapter {

    public HomeTopicListSendAdapter(Context conext, ArrayList list) {
        super(conext, list);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_topiclist_item;
    }

    @Override
    protected void bindData(BaseHolder holder, Object o) {

        HomeBean.DataBean.TopicListBean topicListBean = (HomeBean.DataBean.TopicListBean) o;
        TextView title = (TextView) holder.getViewById(R.id.tv_topiclist_title);
        TextView subtitle = (TextView) holder.getViewById(R.id.tv_topiclist_subtitle);
        ImageView iv_topiclist = (ImageView) holder.getViewById(R.id.iv_topiclist);
        TextView tv_topiclist_price = (TextView) holder.getViewById(R.id.tv_topiclist_price);

        title.setText(topicListBean.getTitle());
        subtitle.setText(topicListBean.getSubtitle());
        tv_topiclist_price.setText(topicListBean.getPrice_info()+"");
        Glide.with(conext).load(topicListBean.getItem_pic_url()).into(iv_topiclist);

    }
}
