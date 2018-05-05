package xudeyang.bawie.com.jd.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.bean.OrderBean;

/**
 * Created by Mac on 2018/4/26.
 */

public class Order1Adapter extends RecyclerView.Adapter{
    private List<OrderBean> datas;
    private Context context;
    public Order1Adapter(List<OrderBean> datas, Context context) {
        this.datas=datas;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=View.inflate(context,R.layout.order_1_rcv_list,null);
        Viewh viewh=new Viewh(v);
        return viewh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Viewh viewh= (Viewh) holder;
        viewh.title.setText(datas.get(position).getTitle());
        viewh.price.setText("实付款: ￥"+datas.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    class Viewh extends RecyclerView.ViewHolder {
        TextView title,price;
        public Viewh(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.order_1_list_title);
            price=itemView.findViewById(R.id.order_1_list_price);
        }
    }
}
