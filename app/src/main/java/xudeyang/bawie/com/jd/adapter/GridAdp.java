package xudeyang.bawie.com.jd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.bean.GridBean;

/**
 * Created by Mac on 2018/3/16.
 */

public class GridAdp extends RecyclerView.Adapter{
    List<GridBean.DataBean> griddata;
    int[] imm;
    Context context;
    public GridAdp(int[] imm, List<GridBean.DataBean> griddata, Context context) {
        this.imm=imm;
        this.griddata=griddata;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(parent.getContext(), R.layout.gridlist,null);
        GridHolder gridHolder=new GridHolder(v);

        return gridHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GridHolder gridHolder= (GridHolder) holder;
        gridHolder.tv.setText(griddata.get(position).getName());
        gridHolder.img.setImageResource(imm[position]);
    }

    @Override
    public int getItemCount() {
        if (griddata.size()==0){
            return 0;
        }
        return griddata.size();
    }
    class GridHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView img;
        public GridHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.grid_tv);
            img=itemView.findViewById(R.id.grid_img);
        }
    }
}
