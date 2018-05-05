package xudeyang.bawie.com.jd.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.bean.DuowanVideoBean;

/**
 * Created by Mac on 2018/4/25.
 */

public class DwVideoAdapter extends RecyclerView.Adapter{
    private List<DuowanVideoBean.DataBean> datas;
    private Context context;
    public DwVideoAdapter(List<DuowanVideoBean.DataBean> datas, Context context) {
        this.datas=datas;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=View.inflate(context,R.layout.video_list,null);
        Viewh vh=new Viewh(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Viewh vh= (Viewh) holder;
        vh.jcv.setUp(datas.get(position).getFileOptions().get(0).getUrl(),vh.jcv.SCREEN_LAYOUT_NORMAL
        ,datas.get(position).getTitle());
        Glide.with(context).load(datas.get(position).getCoverUrl()).into(vh.jcv.thumbImageView);
        vh.jc_durationMin.setText("时长:"+datas.get(position).getDurationMin());
        vh.anthor.setText("作者:"+datas.get(position).getPosterScreenName());
        vh.time.setText(datas.get(position).getViewCount()+"");
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    class Viewh extends RecyclerView.ViewHolder{
        JCVideoPlayerStandard jcv;
        TextView anthor,time,jc_durationMin;
        public Viewh(View itemView) {
            super(itemView);
            jcv=itemView.findViewById(R.id.jcplayer);
            anthor=itemView.findViewById(R.id.jc_author);
            time=itemView.findViewById(R.id.jc_time);
            jc_durationMin=itemView.findViewById(R.id.jc_durationMin);
        }
    }
}
