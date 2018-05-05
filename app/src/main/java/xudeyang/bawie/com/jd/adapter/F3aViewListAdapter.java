package xudeyang.bawie.com.jd.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.bean.YunifangBean;
import xudeyang.bawie.com.jd.helper.EventImage;
import xudeyang.bawie.com.jd.view.activity.ImageActivity;

/**
 * Created by Mac on 2018/4/26.
 */

public class F3aViewListAdapter extends RecyclerView.Adapter{
    private Context context;
    private  List<YunifangBean.DataBean.SubjectsBean.GoodsRelationListBean> goods;
    public F3aViewListAdapter(Context context, List<YunifangBean.DataBean.SubjectsBean.GoodsRelationListBean> goodsRelationList) {
        this.context=context;
        this.goods=goodsRelationList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.frag_3_a_view_rcv_list,null);
        Viewh viewh=new Viewh(v);
        return viewh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Viewh viewh= (Viewh) holder;
        Glide.with(context).load(goods.get(position).getGoodsImage()).into(viewh.photoView);
        viewh.photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ImageActivity.class));
                EventImage eventImage = new EventImage();
                eventImage.setName(goods.get(position).getGoodsImage());
                EventBus.getDefault().postSticky(eventImage);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }
    class Viewh extends RecyclerView.ViewHolder {
        ImageView photoView;
        public Viewh(View itemView) {
            super(itemView);
            photoView=itemView.findViewById(R.id.a_view_rcv_list_photoview);
        }
    }
}
