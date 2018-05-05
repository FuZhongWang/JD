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
import xudeyang.bawie.com.jd.bean.Frag2RightBean;
import xudeyang.bawie.com.jd.bean.ListBean;
import xudeyang.bawie.com.jd.helper.EventFragTwoLeft;
import xudeyang.bawie.com.jd.view.activity.F2ShopActivity;
import xudeyang.bawie.com.jd.view.activity.ShopActivity;

/**
 * Created by Mac on 2018/4/13.
 */

public class Frag2RightRcvAdapter extends RecyclerView.Adapter{
    private List<ListBean> rightLists;
    private Context context;
    public Frag2RightRcvAdapter(List<ListBean> list, Context context) {
        this.rightLists=list;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(context,R.layout.f2_right_rcv_list,null);
        Viewholder viewholder=new Viewholder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Viewholder viewholder= (Viewholder) holder;
        viewholder.tv.setText(rightLists.get(position).getName());
        Glide.with(context).load(rightLists.get(position).getIcon()).into(viewholder.img);
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventFragTwoLeft eventFragTwoLeft = new EventFragTwoLeft();
                eventFragTwoLeft.setName(rightLists.get(position).getPscid()+"");
                EventBus.getDefault().postSticky(eventFragTwoLeft);
                context.startActivity(new Intent(context, F2ShopActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (rightLists.size()==0){
            return 0;
        }
        return rightLists.size();
    }
    class Viewholder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView img;
        public Viewholder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.f2_right_rcv_list_tv);
            img=itemView.findViewById(R.id.f2_right_rcv_list_img);
        }
    }

}
