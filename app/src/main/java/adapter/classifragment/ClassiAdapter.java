package adapter.classifragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jiyunproject.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import model.bean.TopicBean;

public class ClassiAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<TopicBean.DataBeanX.DataBean> list;

    public ClassiAdapter(Context context, ArrayList<TopicBean.DataBeanX.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.layout_classi_item,parent,false);
        return new ClassiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ClassiHolder holder1 = (ClassiHolder) holder;

        holder1.tvdesc.setText(list.get(position).getSubtitle());
        holder1.tvtitle.setText(list.get(position).getTitle());
        holder1.tvprice.setText(list.get(position).getPrice_info()+"");
        Glide.with(context).load(list.get(position).getScene_pic_url()).into(holder1.iv);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ClassiHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tvtitle;
        TextView tvdesc;
        TextView tvprice;
        public ClassiHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.classi_item_iv);
            tvtitle = itemView.findViewById(R.id.classi_item_tvtitle);
            tvdesc = itemView.findViewById(R.id.classi_item_tvdec);
            tvprice = itemView.findViewById(R.id.classi_item_tvprice);
        }
    }
}
