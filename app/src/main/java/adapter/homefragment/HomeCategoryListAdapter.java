package adapter.homefragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jiyunproject.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import base.BaseAdapter;
import model.bean.HomeBean;

public class HomeCategoryListAdapter extends RecyclerView.Adapter {


    private ArrayList<HomeBean.DataBean.CategoryListBean> list;
    private Context context;
    private ArrayList mList = new ArrayList();

    // 刷新列表
    public void refreshAdapter(List<HomeBean.DataBean.CategoryListBean> arr){
        list.clear();;
        list.addAll(arr);
        notifyDataSetChanged();
    }

    public HomeCategoryListAdapter(ArrayList<HomeBean.DataBean.CategoryListBean> list, Context context) {
        this.list = list;
        this.context = context;
        for (int i = 0; i <list.size() ; i++) {
            mList.add(list.get(i).getName());
            mList.add(list.get(i).getGoodsList());
        }
    }
    private int TYPE_TITLE = 1;
    private int TYPE_ITEM = 2;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == 1){
            view = LayoutInflater.from(context).inflate(R.layout.fragment_home_categorylist_item_title,parent,false);
            return new TitleHolder(view);
        }
        view =LayoutInflater.from(context).inflate(R.layout.fragment_catograylist_item_recycler,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //数据不对，正在调
        int itemViewType = getItemViewType(position);
        if(itemViewType == TYPE_TITLE){
            String name = ((HomeBean.DataBean.CategoryListBean)mList.get(position)).getName();
            TitleHolder titleHolder = (TitleHolder) holder;
            titleHolder.title.setText(name);
        }
        if(itemViewType == TYPE_ITEM){
            ItemHolder itemHolder = (ItemHolder) holder;

            ArrayList<HomeBean.DataBean.CategoryListBean.GoodsListBean> goodsList =
                    (ArrayList<HomeBean.DataBean.CategoryListBean.GoodsListBean>)
                            ((HomeBean.DataBean.CategoryListBean) mList.get(position)).getGoodsList();
            HomeCategoryItemAdapter homeCategoryItemAdapter = new HomeCategoryItemAdapter(context, goodsList);
            itemHolder.recyclerView.setLayoutManager(new GridLayoutManager(context,2));
            itemHolder.recyclerView.setAdapter(homeCategoryItemAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return TYPE_TITLE;
        }
        return TYPE_ITEM;
    }

    class TitleHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TitleHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_category_item_title);
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder{
        public RecyclerView recyclerView;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.category_item_recyler);
        }
    }
}
