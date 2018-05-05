package xudeyang.bawie.com.jd.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.bean.YunifangBean;

/**
 * Created by Mac on 2018/4/26.
 */

public class F3AViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<YunifangBean.DataBean.SubjectsBean> datas;
    public F3AViewAdapter(Context context, List<YunifangBean.DataBean.SubjectsBean> datas) {
        this.context=context;
        this.datas=datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.frag_3_a_view_list,null);
        Viewh viewh=new Viewh(v);
        return viewh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Viewh viewh= (Viewh) holder;
        viewh.author.setText(datas.get(position).getTitle());
        viewh.title.setText(datas.get(position).getDetail());
        viewh.count.setText("开始时间:"+datas.get(position).getStart_time());
        viewh.count2.setText("结束时间:"+datas.get(position).getEnd_time());
        F3aViewListAdapter f3aViewListAdapter = new F3aViewListAdapter(context, datas.get(position).getGoodsRelationList());
        viewh.rcv.setLayoutManager(new GridLayoutManager(context,3));
        viewh.rcv.setAdapter(f3aViewListAdapter);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    class Viewh extends RecyclerView.ViewHolder {
        TextView author,title,count,count2;
        RecyclerView rcv;
        public Viewh(View itemView) {
            super(itemView);
            rcv=itemView.findViewById(R.id.a_view_list_rcv);
            author=itemView.findViewById(R.id.a_author);
            title=itemView.findViewById(R.id.a_title);
            count=itemView.findViewById(R.id.a_count);
            count2=itemView.findViewById(R.id.a_count2);
        }
    }
}
