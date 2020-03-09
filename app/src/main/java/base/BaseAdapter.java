package base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {

    protected Context conext;
    protected ArrayList<T> list;

    // 刷新
    public void refreshAdapter(List<T> arr){
        list.clear();;
        list.addAll(arr);
        notifyDataSetChanged();
    }
    // 加载更多
    public void addMore(List<T> arr){
        list.addAll(arr);
        notifyDataSetChanged();
    }

    public BaseAdapter(Context conext, ArrayList<T> list) {
        this.conext = conext;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(conext).inflate(getLayout(),parent,false);
        BaseHolder baseHolder = new BaseHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClik!=null){
                    itemClik.onItemClick(baseHolder.getLayoutPosition());
                }
            }
        });
        return baseHolder;
    }

    protected abstract int getLayout();

    @Override
    public  void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        BaseHolder baseHolder = (BaseHolder) holder;
        T t = list.get(position);
        bindData(baseHolder,t);
    }

    protected abstract void bindData(BaseHolder holder,T t);

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BaseHolder extends RecyclerView.ViewHolder{
        SparseArray views;
        public BaseHolder(@NonNull View itemView) {
            super(itemView);
            views = new SparseArray();
        }
        public View getViewById(int id){
            View view = (View) views.get(id);
            if(view == null){
                view  = itemView.findViewById(id);
                views.append(id,view);
            }
            return view ;
        }
    }
    private BaseOnItemClik itemClik;

    public void setItemClik(BaseOnItemClik itemClik) {
        this.itemClik = itemClik;
    }

    //接口回调实现点击
    public interface BaseOnItemClik{
        void onItemClick(int position);
    }
}
