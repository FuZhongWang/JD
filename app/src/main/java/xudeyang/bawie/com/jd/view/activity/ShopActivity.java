package xudeyang.bawie.com.jd.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.androidprogresslayout.ProgressLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.adapter.ShopAdapter;
import xudeyang.bawie.com.jd.adapter.ShopBdapter;
import xudeyang.bawie.com.jd.bean.Daobean;
import xudeyang.bawie.com.jd.bean.Frag2RightBean;
import xudeyang.bawie.com.jd.bean.ShopBean;
import xudeyang.bawie.com.jd.helper.BlogService;
import xudeyang.bawie.com.jd.helper.EventFragTwoLeft;
import xudeyang.bawie.com.jd.presenter.ShopPrensenter;
import xudeyang.bawie.com.jd.utils.NetWorkUtil;
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.utils.SebrchActivity;
import xudeyang.bawie.com.jd.utils.SedrchActivity;
import xudeyang.bawie.com.jd.view.Iview.ToBackShop;
import xudeyang.bawie.com.jd.view.base.BaseActivity;
import xudeyang.bawie.com.jd.view.base.BaseBean;

public class ShopActivity extends BaseActivity implements View.OnClickListener,ToBackShop {


    SedrchActivity shopSb;
    RecyclerView shopRcv;
    private ImageView retn;
    private ShopPrensenter shopPrensenter;
    private ShopAdapter shopAdapter;
    private TextView tv,et,sort_price,sort_default,sort_sales;
    private ProgressLayout pro;
    private ImageView d_img;
    private boolean bb;
    private List<ShopBean> results=new ArrayList<>();
    private ShopBdapter shopBdapter;
    private String name;

    @Override
    public void initView() {
        setContentView(R.layout.activity_shop);
        tv = findViewById(R.id.shop_tv);
        shopRcv=findViewById(R.id.shop_rcv);
        pro = findViewById(R.id.progress_layout);
        shopSb=findViewById(R.id.shop_sb);
        sort_price=findViewById(R.id.sort_price);
        sort_sales=findViewById(R.id.sort_sales);
        sort_default=findViewById(R.id.sort_default);
        et = shopSb.findViewById(R.id.d_ed);
        retn = shopSb.findViewById(R.id.dretn);
        d_img = shopSb.findViewById(R.id.d_img);
        shopPrensenter = new ShopPrensenter(this);
        retn.setOnClickListener(this);
        et.setOnClickListener(this);
        bb = true;
    }

    @Override
    public void initHttp() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        pro.showContent();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true)
    public void onEvent(EventFragTwoLeft eventFragTwoLeft){
        name = eventFragTwoLeft.getName();
        et.setText(name);
        Map<String,String> map= new HashMap<>();
        map.put("keywords", name);
        map.put("page","1");
        //map.put("source","android");
        int netWork = NetWorkUtil.getNetWork(ShopActivity.this);
        if (netWork!=-1){
        shopPrensenter.tomap("searchProducts",map);
        pro.showProgress();
        }else {
            Toast.makeText(ShopActivity.this, "网络连接似乎没有了~", Toast.LENGTH_LONG).show();
        }

        sort_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                results.clear();
                Map<String,String> mapp= new HashMap<>();
                mapp.put("keywords", name);
                mapp.put("page","1");
                mapp.put("sort","2");
                //mapp.put("source","android");
                shopPrensenter.tomap("searchProducts",mapp);
                pro.showProgress();
            }
        });
        sort_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                results.clear();
                Map<String,String> mapp= new HashMap<>();
                mapp.put("keywords", name);
                mapp.put("page","1");
                mapp.put("sort","0");
                //mapp.put("source","android");
                shopPrensenter.tomap("searchProducts",mapp);
                pro.showProgress();
            }
        });
        sort_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                results.clear();
                Map<String,String> mapp= new HashMap<>();
                mapp.put("keywords", name);
                mapp.put("page","1");
                mapp.put("sort","1");
                //mapp.put("source","android");
                shopPrensenter.tomap("searchProducts",mapp);
                pro.showProgress();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dretn:
                //startActivity(new Intent(ShopActivity.this,MainActivity.class));
                finish();
                break;
            case R.id.d_ed:
                startActivity(new Intent(ShopActivity.this,SeachActivity.class));
                finish();
                break;

        }
    }

    @Override
    public void toBackShop(final List<ShopBean> result) {
        if (result!=null){
        if (result.size()==0||result==null){
            tv.setVisibility(View.VISIBLE);
        }
        results.clear();
        results.addAll(result);}
        d_img.setImageResource(R.drawable.kind_grid);
        shopBdapter = new ShopBdapter(results,ShopActivity.this);
        shopRcv.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        shopRcv.setAdapter(shopBdapter);
        if(bb){
            d_img.setImageResource(R.drawable.kind_liner);
            shopAdapter = new ShopAdapter(results,ShopActivity.this);
            shopRcv.setLayoutManager(new LinearLayoutManager(ShopActivity.this,LinearLayoutManager.VERTICAL,false));
            shopRcv.setAdapter(shopAdapter);
        }
        d_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result!=null){
                results.clear();
                results.addAll(result);}
                bb =!bb;
                if(bb){
                    d_img.setImageResource(R.drawable.kind_liner);
                    shopRcv.setLayoutManager(new LinearLayoutManager(ShopActivity.this,LinearLayoutManager.VERTICAL,false));
                    shopRcv.setAdapter(shopAdapter);
                }else{
                    d_img.setImageResource(R.drawable.kind_grid);
                    shopRcv.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
                    shopRcv.setAdapter(shopBdapter);
                }
            }
        });
        pro.showContent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (shopPrensenter!=null) {
            shopPrensenter.ondestroy();
        }
    }
}
