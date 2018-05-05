package xudeyang.bawie.com.jd.view.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.fynn.fluidlayout.FluidLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import xudeyang.bawie.com.jd.R;


import xudeyang.bawie.com.jd.adapter.SearchAdapter;
import xudeyang.bawie.com.jd.bean.DaoMaster;
import xudeyang.bawie.com.jd.bean.DaoSession;
import xudeyang.bawie.com.jd.bean.Daobean;
import xudeyang.bawie.com.jd.bean.DaobeanDao;
import xudeyang.bawie.com.jd.helper.EventFragTwoLeft;
import xudeyang.bawie.com.jd.helper.MyDividerItemDecoration;
import xudeyang.bawie.com.jd.utils.SearchActivity;
import xudeyang.bawie.com.jd.utils.SebrchActivity;

public class SeachActivity extends AppCompatActivity {
    private FluidLayout fluid;
    private SebrchActivity sebh;
    private TextView sousuo;
    private EditText ed;
    private ImageView retn;
    private Button btn;
    private List<String> select=new ArrayList<>();
    private List<String> selects;
    private int ssss=1;
    String [] arrs ={
            "手机", "笔记本", "小米","哨子","玉镯","彩色鞋带","启赋3","门铃感应器","金士顿内存","黄金项链"};
    private ArrayAdapter<String> myAdpa;
    private RecyclerView lv;

    private TextView seach_lsss;

    private SQLiteDatabase bean;
    private DaobeanDao daobeanDao;
    private DaoSession daoSession;
    private List<Daobean> daobeans;
    private DaoMaster daoMaster;
    private SearchAdapter searchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        sebh = findViewById(R.id.sebh);
        sousuo = sebh.findViewById(R.id.b_sous);
        fluid=findViewById(R.id.fluid);
        ed = sebh.findViewById(R.id.b_ed);
        retn = sebh.findViewById(R.id.retn);
        seach_lsss = findViewById(R.id.seach_lsss);
        btn = findViewById(R.id.del);
        lv = findViewById(R.id.s_lv);

        int roundRadius = 15; // 8dp 圆角半径
        int fillColor = Color.parseColor("#DFDFE0");//内部填充颜色
        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setColor(fillColor);
        gd.setCornerRadius(roundRadius);
        for (int x = 0; x < arrs.length; x++) {
            View v=View.inflate(SeachActivity.this,R.layout.flout_fliud,null);
            final TextView tv = v.findViewById(R.id.ff_tv);
            tv.setText(arrs[x]);
            tv.setTextSize(15);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DaobeanDao daobeanDaoa = daoSession.getDaobeanDao();
                    List<Daobean> daobeanss = daobeanDaoa.loadAll();
                    if (daobeanss.size()==0){
                        Daobean daobean = new Daobean();
                        daobean.setName(tv.getText().toString());
                        daobeanDaoa.insert(daobean);
                    }else {
                        boolean b=true;
                        for (int i = 0; i < daobeanss.size(); i++) {
                            if (daobeanss.get(i).getName().equals(tv.getText().toString())) {
                                b=false;
                            }
                        }
                        if (b){
                            Daobean daobean = new Daobean();
                            daobean.setName(tv.getText().toString());
                            daobeanDaoa.insert(daobean);
                        }
                    }
                    EventFragTwoLeft eventFragTwoLeft = new EventFragTwoLeft();
                    eventFragTwoLeft.setName(tv.getText().toString());
                    EventBus.getDefault().postSticky(eventFragTwoLeft);
                    startActivity(new Intent(SeachActivity.this,ShopActivity.class));
                    finish();
                }
            });
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 8, 8, 8);
            fluid.addView(v, params);
        }
        selects=new ArrayList<>();
        searchAdapter = new SearchAdapter(selects, this);
        lv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        lv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        lv.setAdapter(searchAdapter);
        searchAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                String string = selects.get(position).toString();
                DaobeanDao daobeanDaoa = daoSession.getDaobeanDao();
                List<Daobean> daobeanss = daobeanDaoa.loadAll();
                if (daobeans.size()==0){
                    Daobean daobean = new Daobean();
                    daobean.setName(string);
                    daobeanDao.insert(daobean);
                }else {
                    boolean b=true;
                    for (int i = 0; i < SeachActivity.this.daobeans.size(); i++) {
                        if (daobeanss.get(i).getName().equals(string)) {
                            b=false;
                        }
                    }
                    if (b){
                        Daobean daobean = new Daobean();
                        daobean.setName(string);
                        daobeanDao.insert(daobean);
                    }
                }
                EventFragTwoLeft eventFragTwoLeft = new EventFragTwoLeft();
                eventFragTwoLeft.setName(string);
                EventBus.getDefault().postSticky(eventFragTwoLeft);
                startActivity(new Intent(SeachActivity.this,ShopActivity.class));
                finish();
            }
        });
        SQLiteDatabase lsls = new DaoMaster.DevOpenHelper(this, "lsls").getWritableDatabase();
        daoMaster = new DaoMaster(lsls);
        daoSession = daoMaster.newSession();
        daobeanDao = daoSession.getDaobeanDao();
        daobeans = daobeanDao.loadAll();
        if (daobeans.size()!=0){
            for (int i = 0; i < daobeans.size(); i++) {
                selects.add(daobeans.get(i).getName());
            }
            if (seach_lsss.getVisibility()==View.INVISIBLE) {
                seach_lsss.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);
            }
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DaoSession daoSession1 = daoMaster.newSession();
                DaobeanDao daobeanDa = daoSession1.getDaobeanDao();
                List<Daobean> loadAll = daobeanDa.loadAll();
                selects.clear();
                searchAdapter.notifyDataSetChanged();
                for (int i = 0; i < loadAll.size(); i++) {
                    daobeanDa.deleteByKey(loadAll.get(i).getId());
                }
                seach_lsss.setVisibility(View.INVISIBLE);
                btn.setVisibility(View.INVISIBLE);
            }
        });
        retn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = ed.getText().toString();
                if (string != null&!string.equals("")) {
                    DaobeanDao daobeanDaoa = daoSession.getDaobeanDao();
                    List<Daobean> daobeanss = daobeanDaoa.loadAll();
                    if (daobeans.size()==0){
                        Daobean daobean = new Daobean();
                        daobean.setName(string);
                        daobeanDao.insert(daobean);
                    }else {
                        boolean b=true;
                        for (int i = 0; i < SeachActivity.this.daobeans.size(); i++) {
                            if (daobeanss.get(i).getName().equals(string)) {
                                b=false;
                            }
                        }
                        if (b){
                            Daobean daobean = new Daobean();
                            daobean.setName(string);
                            daobeanDao.insert(daobean);
                        }
                    }
                    if (seach_lsss.getVisibility() == View.INVISIBLE) {
                        seach_lsss.setVisibility(View.VISIBLE);
                        btn.setVisibility(View.VISIBLE);
                    }
                    EventFragTwoLeft eventFragTwoLeft = new EventFragTwoLeft();
                    eventFragTwoLeft.setName(ed.getText().toString());
                    EventBus.getDefault().postSticky(eventFragTwoLeft);
                    startActivity(new Intent(SeachActivity.this,ShopActivity.class));
                    finish();
                }
            }
        });
    }

}
