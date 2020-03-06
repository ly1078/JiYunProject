package fragments;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jiyunproject.R;

import java.util.ArrayList;

import adapter.classifragment.ClassiAdapter;
import base.BaseFragment;
import interfaces.IBaseView;
import interfaces.topic.ClassiConstract;
import model.bean.TopicBean;
import presenter.classi.ClassiPresenter;

/*
    专题
     */
public class ClassificationFragment extends BaseFragment<ClassiConstract.Persenter> implements ClassiConstract.View {
    private TextView tv;
    ArrayList<TopicBean.DataBeanX.DataBean> list = new ArrayList<>();
    private ClassiAdapter classiAdapter;

    @Override
    protected void initData() {
        presenter.getTopicData(1,10);
    }
    @Override
    protected void initView(View v) {

        tv = v.findViewById(R.id.tv);
        RecyclerView  classi_recycler = v.findViewById(R.id.classi_recycler);

        classi_recycler.setLayoutManager(new LinearLayoutManager(context));
        classiAdapter = new ClassiAdapter(context, list);
        classi_recycler.setAdapter(classiAdapter);

    }
    @Override
    protected ClassiConstract.Persenter initPresenter() {
        return new ClassiPresenter();
    }
    @Override
    protected int getLayout() {
        return R.layout.layout_classification;
    }
    @Override
    public void getTopicDataReturn(TopicBean result) {
        Log.i("tag","===> "+ result.getData().getData().get(0).getTitle());

        list.addAll(result.getData().getData());
        classiAdapter.notifyDataSetChanged();

    }
}
