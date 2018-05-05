package xudeyang.bawie.com.jd.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.bean.Frag2LeftBean;
import xudeyang.bawie.com.jd.bean.Frag2RightBean;
import xudeyang.bawie.com.jd.helper.EventFragTwoLeft;

/**
 * Created by Mac on 2018/4/13.
 */

public class Frag2RightAdapter extends RecyclerView.Adapter {
    private List<Frag2RightBean> leftLists;
    private  Context context;
    public Frag2RightAdapter(List<Frag2RightBean> leftLists, Context context) {
        this.leftLists=leftLists;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(context,R.layout.f2_right_list,null);
        Viewh viewh=new Viewh(v);
        return viewh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Viewh viewh= (Viewh) holder;
        viewh.tv.setText(leftLists.get(position).getName());
        Frag2RightRcvAdapter frag2RightRcvAdapter = new Frag2RightRcvAdapter(leftLists.get(position).getList(),context);
        viewh.rcv.setLayoutManager(new GridLayoutManager(context,4));
        viewh.rcv.setAdapter(frag2RightRcvAdapter);
    }

    @Override
    public int getItemCount() {
        return leftLists.size();
    }
    class Viewh extends RecyclerView.ViewHolder{
        TextView tv;
        RecyclerView rcv;
        public Viewh(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.f2_right_tv);
            rcv=itemView.findViewById(R.id.f2_right_rcv);
        }
    }
}
