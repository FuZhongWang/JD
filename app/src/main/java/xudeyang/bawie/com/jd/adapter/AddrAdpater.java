package xudeyang.bawie.com.jd.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.bean.AddrBean;
import xudeyang.bawie.com.jd.view.activity.AddrActivity;
import xudeyang.bawie.com.jd.view.activity.UpdataAddrActivity;

/**
 * Created by Mac on 2018/4/26.
 */

public class AddrAdpater extends RecyclerView.Adapter{
    private Context context;
    private List<AddrBean> datas;
    public AddrAdpater(Context context, List<AddrBean> datas) {
        this.context=context;
        this.datas=datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.addr_rcv_list,null);
        Viewh viewh=new Viewh(v);
        return viewh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Viewh viewh= (Viewh) holder;
        viewh.addr.setText(datas.get(position).getAddr());
        viewh.id.setText(datas.get(position).getAddrid()+"");
        viewh.name.setText(datas.get(position).getName());
        viewh.tel.setText(datas.get(position).getMobile()+"");
        viewh.addr_rcv_ck.setChecked(datas.get(position).getStatus()==1?true:false);
        viewh.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdataAddrActivity.class);
                intent.putExtra("tel",datas.get(position).getMobile()+"");
                intent.putExtra("name",datas.get(position).getName());
                intent.putExtra("id",datas.get(position).getAddrid()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    class Viewh extends RecyclerView.ViewHolder {
        TextView name,tel,addr,update,del,id;
        CheckBox addr_rcv_ck;
        public Viewh(View itemView) {
            super(itemView);
            addr_rcv_ck=itemView.findViewById(R.id.addr_rcv_ck);
            name=itemView.findViewById(R.id.addr_rcv_name);
            tel=itemView.findViewById(R.id.addr_rcv_tel);
            addr=itemView.findViewById(R.id.addr_rcv_addr);
            update=itemView.findViewById(R.id.addr_rcv_update);
            del=itemView.findViewById(R.id.addr_rcv_del);
            id=itemView.findViewById(R.id.addr_rcv_addrid);
        }
    }
}
