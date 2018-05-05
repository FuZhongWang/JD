package xudeyang.bawie.com.jd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.bean.ShopBean;
import xudeyang.bawie.com.jd.helper.EventFragTwoLeft;

/**
 * Created by Mac on 2018/4/17.
 */

public class SearchAdapter extends RecyclerView.Adapter{
    private List<String> datas;
    private Context context;
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener (OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public SearchAdapter(List<String> datas, Context context) {
        this.datas=datas;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.search_rcv_list,null);
        Vh vh=new Vh(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Vh vh= (Vh) holder;
        vh.tv.setText(datas.get(position).toString());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    class Vh extends RecyclerView.ViewHolder{
        TextView tv;
        public Vh(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.search_rcv_tv);
        }
    }
    public interface OnItemClickListener{
        void onClick(int position);
    }
}
