package xudeyang.bawie.com.jd.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.bean.Frag2LeftBean;
import xudeyang.bawie.com.jd.helper.EventFragTwoLeft;

/**
 * Created by Mac on 2018/4/13.
 */

public class Frag2LeftAdapter extends RecyclerView.Adapter {
    private List<Frag2LeftBean> leftLists;
    private  Context context;
    public Frag2LeftAdapter(List<Frag2LeftBean> leftLists, Context context) {
        this.leftLists=leftLists;
        this.context=context;
    }
    private int adpPostion;

    public int getAdpPostion() {
        return adpPostion;
    }

    public void setAdpPostion(int adpPostion) {
        this.adpPostion = adpPostion;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(context,R.layout.f2_left_list,null);
        Viewh viewh=new Viewh(v);
        return viewh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Viewh viewh= (Viewh) holder;
        viewh.tv.setText(leftLists.get(position).getName());
        viewh.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventFragTwoLeft(position));
            }
        });
        if (position==adpPostion){
            viewh.tv.setTextColor(Color.parseColor("#FF4040"));
            viewh.tv.setBackgroundColor(Color.parseColor("#EFEFEF"));
        }else{
            viewh.tv.setTextColor(Color.BLACK);
            viewh.tv.setBackgroundColor(Color.WHITE);
        }

    }

    @Override
    public int getItemCount() {
        return leftLists.size();
    }
    class Viewh extends RecyclerView.ViewHolder{
        TextView tv;
        public Viewh(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.f2_left_list_tv);
        }
    }
}
