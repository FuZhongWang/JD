package xudeyang.bawie.com.jd.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.bean.ShopBean;
import xudeyang.bawie.com.jd.helper.EventShow;
import xudeyang.bawie.com.jd.view.activity.ShowActivity;

/**
 * Created by Mac on 2018/4/17.
 */

public class ShopBdapter extends RecyclerView.Adapter{
    private List<ShopBean> datas;
    private Context context;
    public ShopBdapter(List<ShopBean> datas, Context context) {
        this.datas=datas;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.shob_rcv_list,null);
        Vh vh=new Vh(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Vh vh= (Vh) holder;
        String images = datas.get(position).getImages();
        String[] sp=images.split("\\|");
        Glide.with(context).load(sp[0]).into(vh.img);
        vh.price.setText("￥:"+datas.get(position).getPrice());
        vh.title.setText(datas.get(position).getTitle());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String images = datas.get(position).getImages();
                String[] sp=images.split("\\|");
                EventShow eventShow=new EventShow();
                eventShow.setPid(datas.get(position).getPid()+"");
                eventShow.setImageUrl(sp[0]);
                eventShow.setPrice("￥:"+datas.get(position).getPrice());
                eventShow.setSubHead(datas.get(position).getSubhead()+"");
                eventShow.setTitle(datas.get(position).getTitle());
                EventBus.getDefault().postSticky(eventShow);
                context.startActivity(new Intent(context, ShowActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    class Vh extends RecyclerView.ViewHolder{
        ImageView img;
        TextView title,price;
        public Vh(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.shob_rcv_img);
            price=itemView.findViewById(R.id.shob_rcv_price);
            title=itemView.findViewById(R.id.shob_rcv_title);
        }
    }
}
