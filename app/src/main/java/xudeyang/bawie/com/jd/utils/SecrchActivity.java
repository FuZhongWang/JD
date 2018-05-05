package xudeyang.bawie.com.jd.utils;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.activity.CaptureActivity;

import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.view.activity.CacaaActivity;
import xudeyang.bawie.com.jd.view.activity.SeachActivity;


/**
 * Created by Mac on 2018/3/20.
 */

public class SecrchActivity extends RelativeLayout {

    private Context mycon;
    private static TextView ed;
    SearchActivity.Suocha ss;
    private ImageView cha;
    private ImageView img;
    private LinearLayout erweima;

    public SecrchActivity(Context context) {
        this(context,null);
    }

    public SecrchActivity(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SecrchActivity(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //把上下文写成全局变量
        mycon=context;
        //初始化布局
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cc, this, true);
        cha = view.findViewById(R.id.c_cha);
        ed = view.findViewById(R.id.c_ed);
        img = view.findViewById(R.id.c_img);
        erweima = view.findViewById(R.id.c_erweima);
        erweima.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CacaaActivity.class));
            }
        });
        cha.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,SeachActivity.class);
                context.startActivity(intent);
            }
        });
        ed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,SeachActivity.class);
                context.startActivity(intent);
            }
        });
      /*  img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,RootActivity.class);
                context.startActivity(intent);
            }
        });*/
    }



    //写一个接口
    public interface Suocha{
        void dianji(String sou);
    }

    //写一个方法给外部访问
    public void fang(SearchActivity.Suocha sss){
        ss=sss;
    }

    public static void setEd(String e){
        ed.setText(e);
    }
}