package xudeyang.bawie.com.jd.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import xudeyang.bawie.com.jd.R;


/**
 * Created by Mac on 2018/3/14.
 */

public class SedrchActivity extends RelativeLayout{

    private Context mycon;
    private static TextView ed;
    Suocha ss;
    private ImageView cha;
    private ImageView retn;

    public SedrchActivity(Context context) {
        this(context,null);
    }

    public SedrchActivity(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SedrchActivity(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //把上下文写成全局变量
        mycon=context;
        //初始化布局
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dd, this, true);
        retn = view.findViewById(R.id.dretn);
        cha = view.findViewById(R.id.d_cha);
        ed = view.findViewById(R.id.d_ed);
        cha.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
               // String s1 = ed.getText().toString();
                //ss.dianji(s1);

            }
        });

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