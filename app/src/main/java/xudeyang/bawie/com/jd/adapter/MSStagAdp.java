package xudeyang.bawie.com.jd.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.bean.StagBean;
import xudeyang.bawie.com.jd.bean.YunifangBean;

/**
 * Created by Mac on 2018/3/16.
 */

public class MSStagAdp extends RecyclerView.Adapter{
    List<YunifangBean.DataBean.DefaultGoodsListBean> stagdata;
    Context context;
    public MSStagAdp(List<YunifangBean.DataBean.DefaultGoodsListBean> stagdata, Context context) {
        this.stagdata=stagdata;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ms_staglist,null, false);
        StagHolder stagHolder=new StagHolder(view);

        return stagHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        StagHolder stagHolder= (StagHolder) holder;
        stagHolder.name.setText("￥"+stagdata.get(position).getMarket_price());
        stagHolder.name.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        stagHolder.price.setText("￥"+stagdata.get(position).getShop_price());

        stagHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent=new Intent(context, WebActivity.class);
                intent.putExtra("url",stagdata.get(position).getUrl());
                context.startActivity(intent);*/
            }
        });
        Glide.with(context).load(stagdata.get(position).getGoods_img()).into(stagHolder.img);
    }

    @Override
    public int getItemCount() {
        if (stagdata.size()==0){
            return 0;
        }
        return stagdata.size();
    }
    class StagHolder extends RecyclerView.ViewHolder{
        TextView name,price;
        ImageView img;
        public StagHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.ms_stagtv);
            price=itemView.findViewById(R.id.ms_stagprice);
            img=itemView.findViewById(R.id.ms_stagimg);
        }
    }
}
