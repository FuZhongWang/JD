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

import xudeyang.bawie.com.jd.R;


/**
 * Created by Mac on 2018/3/14.
 */

public class SearchActivity extends RelativeLayout{

    private Context mycon;
    private static TextView ed;
    Suocha ss;
    private ImageView cha;
    private ImageView img;
    private LinearLayout erweima,xx;

    public SearchActivity(Context context) {
        this(context,null);
    }

    public SearchActivity(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SearchActivity(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //把上下文写成全局变量
        mycon=context;
        //初始化布局
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.aa, this, true);
        cha = view.findViewById(R.id.a_cha);
        xx = view.findViewById(R.id.aa_xx);
        ed = view.findViewById(R.id.a_ed);
        img = view.findViewById(R.id.aa_img);
        erweima = view.findViewById(R.id.f1_erweima);
        /*erweima.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,CramaActivity.class);
                context.startActivity(intent);
            }
        });
        cha.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = ed.getText().toString();
                ss.dianji(s1);

            }
        });
        ed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,SechActivity.class);
                context.startActivity(intent);
            }
        });
        img.setOnClickListener(new OnClickListener() {
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
    public void fang(Suocha sss){
        ss=sss;
    }

    public static void setEd(String e){
        ed.setText(e);
    }
}