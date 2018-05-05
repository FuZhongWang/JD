package xudeyang.bawie.com.jd.view.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.google.zxing.activity.CaptureActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.iwgang.countdownview.CountdownView;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.adapter.GridAdp;
import xudeyang.bawie.com.jd.adapter.MSStagAdp;
import xudeyang.bawie.com.jd.adapter.StagAdp;
import xudeyang.bawie.com.jd.bean.GetAddBean;
import xudeyang.bawie.com.jd.bean.GridBean;
import xudeyang.bawie.com.jd.bean.StagBean;
import xudeyang.bawie.com.jd.bean.YunifangBean;
import xudeyang.bawie.com.jd.helper.EventNetWork;
import xudeyang.bawie.com.jd.helper.MyDividerItemDecoration;
import xudeyang.bawie.com.jd.presenter.Frag1GridPrensenter;
import xudeyang.bawie.com.jd.presenter.Frag1StagPrensenter;
import xudeyang.bawie.com.jd.presenter.LoginPrensenter;
import xudeyang.bawie.com.jd.utils.BannerImage;
import xudeyang.bawie.com.jd.utils.NetWorkUtil;
import xudeyang.bawie.com.jd.utils.SearchActivity;
import xudeyang.bawie.com.jd.utils.UrlAddress;
import xudeyang.bawie.com.jd.view.Iview.ToBack;
import xudeyang.bawie.com.jd.view.Iview.ToFrag1GridBack;
import xudeyang.bawie.com.jd.view.Iview.ToFrag1StagBack;
import xudeyang.bawie.com.jd.view.activity.CacaaActivity;
import xudeyang.bawie.com.jd.view.activity.LoginActivity;
import xudeyang.bawie.com.jd.view.activity.MainActivity;
import xudeyang.bawie.com.jd.view.activity.SeachActivity;
import xudeyang.bawie.com.jd.view.base.BaseActivity;
import xudeyang.bawie.com.jd.view.base.BaseFragment;

/**
 * Created by Mac on 2018/4/10.
 */

public class Fragment_1 extends BaseFragment implements ToBack, ToFrag1GridBack, ToFrag1StagBack {
    @BindView(R.id.frag1)
    ScrollView frag1;
    @BindView(R.id.aa_xiaoxi)
    TextView aa_xiaoxi;
    @BindView(R.id.a_xiaoxi)
    ImageView a_xiaoxi;
    @BindView(R.id.a_cha)
    ImageView a_cha;
    @BindView(R.id.aa_zz)
    TextView aa_zz;
    @BindView(R.id.aa_z)
    ImageView aa_z;
    @BindView(R.id.rflayout)
    SmartRefreshLayout rflayout;
    @BindView(R.id.ss)
    SearchActivity ss;
    Unbinder unbinder;
    int mDistanceY = 300;
    @BindView(R.id.rll)
    RelativeLayout rll;
    @BindView(R.id.bgll)
    LinearLayout bgll;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.frag1_grid)
    RecyclerView frag1_grid;
    @BindView(R.id.filpper)
    ViewFlipper filpper;
    @BindView(R.id.frag1_stag)
    RecyclerView frag1Stag;
    @BindView(R.id.frag1_imgbtn1)
    ImageView frag1Imgbtn1;
    @BindView(R.id.frag1_imgbtn2)
    ImageView frag1Imgbtn2;
    @BindView(R.id.frag1_img)
    ImageView frag1Img;
    @BindView(R.id.imageView111)
    ImageView imageView111;
    @BindView(R.id.frag1_text)
    RelativeLayout frag1Text;
    @BindView(R.id.frag1_weinituijian)
    ImageView frag1Weinituijian;
    List<YunifangBean.DataBean.DefaultGoodsListBean> defaultGoodsLists =new ArrayList<>();
    private LoginPrensenter loginPrensenter;
    private int le = 0;
    private List<String> listBanner = new ArrayList<>();
    private ArrayList<String> lis = new ArrayList<>();
    private List<StagBean.DataBean.ItemsBean> itemss = new ArrayList<>();
    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                YunifangBean.DataBean data = new Gson().fromJson((String) msg.obj, YunifangBean.class).getData();
                List<YunifangBean.DataBean.SubjectsBean> subjects = data.getSubjects();
                for (int i = 0; i < subjects.size(); i++) {
                    listBanner.add(subjects.get(i).getImage());
                }
                banner.setImages(listBanner);
                banner.setDelayTime(3000);
                banner.setImageLoader(new BannerImage());
                banner.start();
                List<YunifangBean.DataBean.DefaultGoodsListBean> defaultGoodsList = data.getDefaultGoodsList();
                defaultGoodsLists.addAll(defaultGoodsList);
                msStagAdp.notifyDataSetChanged();
            } else if (msg.what == 1) {
                int[] immm = {R.drawable.a_05, R.drawable.a_07, R.drawable.a_09, R.drawable.a_11
                        , R.drawable.a_18, R.drawable.a_19, R.drawable.a_20, R.drawable.a_21, R.drawable.a_22
                        , R.drawable.b_03, R.drawable.b_05, R.drawable.b_07, R.drawable.b_09, R.drawable.b_11,
                        R.drawable.b_18, R.drawable.b_19, R.drawable.b_20, R.drawable.b_21, R.drawable.b_22
                };
                GridBean gridBean = new Gson().fromJson((String) msg.obj, GridBean.class);
                List<GridBean.DataBean> data = gridBean.getData();
                GridAdp gridAdp = new GridAdp(immm, data, getActivity());
                frag1_grid.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.HORIZONTAL, false));
                frag1_grid.setAdapter(gridAdp);
            }  else if (msg.what == 3) {
                String stagmsg = (String) msg.obj;
                StagBean stagBean = new Gson().fromJson(stagmsg, StagBean.class);
                itemss.clear();
                List<StagBean.DataBean.ItemsBean> items = stagBean.getData().getItems();
                itemss.addAll(items);
                adp.notifyDataSetChanged();
            } else if (msg.what == 6) {
                ss.setVisibility(View.VISIBLE);
            }
        }
    };
    private Frag1GridPrensenter frag1GridPrensenter;
    private StagAdp adp;
    private Frag1StagPrensenter frag1StagPrensenter;
    private int ccc = 30;
    private LinearLayout ewm;
    private TextView tvvv;
    private RecyclerView miaosha_rcv;
    private MSStagAdp msStagAdp;
    private TextView miaosha_tv;
    private CountdownView countdownView;
    private int times;
    private Subscription subscribe;
    private Flowable<Object> objectFlowable;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_1;
    }

    @Override
    protected void initView() {
        ewm = ss.findViewById(R.id.f1_erweima);
        tvvv = ss.findViewById(R.id.a_ed);
        miaosha_rcv = findViewById(R.id.miaosha_rcv);
        miaosha_tv = findViewById(R.id.miaosha_tv);
        countdownView = findViewById(R.id.countdownView);
        loginPrensenter = new LoginPrensenter(this);
        frag1GridPrensenter = new Frag1GridPrensenter(this);
        frag1StagPrensenter = new Frag1StagPrensenter(this);
        int netWork = NetWorkUtil.getNetWork(getActivity());
        if (netWork !=-1) {
            loginPrensenter.toUrl("http://result.eolinker.com/umIPmfS6c83237d9c70c7c9510c9b0f97171a308d13b611?uri=homepage");
            frag1GridPrensenter.toUrl(UrlAddress.GETCATAGORY);
        objectFlowable = Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> emitter) throws Exception {
                OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url(UrlAddress.HOT_WORDS)//请求接口。如果需要传参拼接到接口后面。
                        .build();//创建Request 对象
                Response response = null;
                response = client.newCall(request).execute();//得到Response 对象
                String string = response.body().string();
                emitter.onNext(string);
            }
        }, BackpressureStrategy.BUFFER);
        objectFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        JSONArray json = null;
                        try {
                            json = new JSONArray((String) o);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i < json.length(); i++) {
                            lis.add(json.optString(i));
                            View view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.layout_custom, null);
                            TextView textView = view.findViewById(R.id.flip_tv);
                            TextView textView1 = view.findViewById(R.id.flip_tvv);
                            if (i % 3==1){
                                textView1.setText("精选");
                            }else if (i % 3==2){
                                textView1.setText("HOT");
                            }else if (i % 3==0){
                                textView1.setText("最新");
                            }
                            textView.setText(lis.get(i));
                            filpper.addView(view);
                        }
                    }
                });

        frag1StagPrensenter.toUrl(UrlAddress.TAOHUASUAN + "30");
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onType(EventNetWork eventNetWork){
        if (eventNetWork.getType() == 1) {
            loginPrensenter.toUrl("http://result.eolinker.com/umIPmfS6c83237d9c70c7c9510c9b0f97171a308d13b611?uri=homepage");
            frag1StagPrensenter.toUrl(UrlAddress.TAOHUASUAN + "30");
        frag1GridPrensenter.toUrl(UrlAddress.GETCATAGORY);
        objectFlowable = Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> emitter) throws Exception {
                OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url(UrlAddress.HOT_WORDS)//请求接口。如果需要传参拼接到接口后面。
                        .build();//创建Request 对象
                Response response = null;
                response = client.newCall(request).execute();//得到Response 对象
                String string = response.body().string();
                emitter.onNext(string);
            }
        }, BackpressureStrategy.BUFFER);
        objectFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        JSONArray json = null;
                        try {
                            json = new JSONArray((String) o);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i < json.length(); i++) {
                            lis.add(json.optString(i));
                            View view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.layout_custom, null);
                            TextView textView = view.findViewById(R.id.flip_tv);
                            TextView textView1 = view.findViewById(R.id.flip_tvv);
                            if (i % 3==1){
                                textView1.setText("精选");
                            }else if (i % 3==2){
                                textView1.setText("HOT");
                            }else if (i % 3==0){
                                textView1.setText("最新");
                            }
                            textView.setText(lis.get(i));
                            filpper.addView(view);
                        }
                    }
                });


        }
    }
    @Override
    protected void initData() {
        ontime();

        ewm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), CacaaActivity.class));
            }
        });
        tvvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), SeachActivity.class));
            }
        });
        adp = new StagAdp(itemss, getActivity());
        frag1Stag.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        frag1Stag.setAdapter(adp);
        frag1Stag.setNestedScrollingEnabled(false);
        msStagAdp = new MSStagAdp(defaultGoodsLists, getActivity());
        miaosha_rcv.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
        miaosha_rcv.addItemDecoration(new MyDividerItemDecoration(getActivity(),1));
        miaosha_rcv.setAdapter(msStagAdp);
        miaosha_rcv.setNestedScrollingEnabled(false);
        rflayout.setRefreshHeader(new BezierRadarHeader(getActivity()));
        rflayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
        frag1.setVerticalScrollBarEnabled(false);
        frag1.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY <= 0) {
                    rll.setBackgroundColor(Color.argb((int) 0, 0, 0, 0));
                    ss.setBackgroundColor(Color.argb((int) 0, 0, 0, 0));//AGB由相关工具获得，或者美工提供
                } else if (scrollY > 0 && scrollY <= mDistanceY) {
                    float scale = (float) scrollY / mDistanceY;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    if (scrollY < mDistanceY - 100) {
                        aa_zz.setTextColor(Color.argb((int) 255, 255, 255, 255));
                        aa_z.setImageResource(R.drawable.a_s);
                        a_xiaoxi.setImageResource(R.drawable.a9x);
                        a_cha.setImageResource(R.drawable.search_icon2);
                        aa_xiaoxi.setTextColor(Color.argb((int) 255, 255, 255, 255));
                    } else {
                        a_cha.setImageResource(R.drawable.search_icon1);
                        aa_z.setImageResource(R.drawable.sao);
                        aa_zz.setTextColor(Color.argb((int) 255, 0, 0, 0));
                        a_xiaoxi.setImageResource(R.drawable.a_9y);
                        aa_xiaoxi.setTextColor(Color.argb((int) 255, 0, 0, 0));
                    }
                    rll.setBackgroundResource(R.color.colormy);
                    //rll.setBackgroundColor(Color.argb((int) alpha, 0, 0, 0));
                    ss.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
                    rll.setBackgroundResource(R.color.colormy);
                    ss.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                    aa_z.setImageResource(R.drawable.sao);
                    aa_zz.setTextColor(Color.argb((int) 255, 0, 0, 0));
                    a_xiaoxi.setImageResource(R.drawable.a_9y);
                    aa_xiaoxi.setTextColor(Color.argb((int) 255, 0, 0, 0));
                    a_cha.setImageResource(R.drawable.search_icon1);
                }
                if (scrollY <= 1000) {
                    frag1Imgbtn1.setVisibility(View.INVISIBLE);
                    frag1Imgbtn2.setVisibility(View.INVISIBLE);
                } else if (scrollY > 1000 && scrollY < 3100) {
                    frag1Imgbtn1.setVisibility(View.VISIBLE);
                    frag1Imgbtn2.setVisibility(View.INVISIBLE);
                } else if (scrollY >= 3100) {
                    frag1Imgbtn1.setVisibility(View.INVISIBLE);
                    frag1Imgbtn2.setVisibility(View.VISIBLE);
                }
            }
        });
        rflayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                ss.setVisibility(View.INVISIBLE);
                refreshLayout.finishRefresh(2000);
                frag1StagPrensenter.toUrl(UrlAddress.TAOHUASUAN + "30");
                h.sendEmptyMessageDelayed(6, 2500);
            }
        });
        rflayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                ccc += 10;
                frag1StagPrensenter.toUrl(UrlAddress.TAOHUASUAN + ccc);
                refreshLayout.finishLoadMore(2000);
            }
        });

    }

    private void ontime() {
        Calendar calendar = Calendar.getInstance();
        //获取系统的日期
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH)+1;
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //获取系统时间
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //分钟
        int minute = calendar.get(Calendar.MINUTE);
        //秒
        int second = calendar.get(Calendar.SECOND);
        times = 0;
        if (hour>=8&&hour<10){
            miaosha_tv.setText("上午场8点场");
            if (hour==8){
                times=120*60*1000-(minute*60+second)*1000;
                countdownView.start(times);
            }else if (hour==9){
                times=120*60*1000-(minute*60+second+60*60)*1000;
                countdownView.start(times);
            }
        }else if(hour>=10&&hour<12){
            miaosha_tv.setText("上午场10点场");
            if (hour==10){
                times=120*60*1000-(minute*60+second)*1000;
                countdownView.start(times);
            }else if (hour==11){
                times=120*60*1000-(minute*60+second+60*60)*1000;
                countdownView.start(times);
            }
        }else if(hour>=12&&hour<14){
            miaosha_tv.setText("中午场12点场");
            if (hour==12){
                times=120*60*1000-(minute*60+second)*1000;
                countdownView.start(times);
            }else if (hour==13){
                times=120*60*1000-(minute*60+second+60*60)*1000;
                countdownView.start(times);
            }
        }else if(hour>=14&&hour<16){
            miaosha_tv.setText("下午场14点场");
            if (hour==14){
                times=120*60*1000-(minute*60+second)*1000;
                countdownView.start(times);
            }else if (hour==15){
                times=120*60*1000-(minute*60+second+60*60)*1000;
                countdownView.start(times);
            }
        }else if(hour>=16&&hour<18){
            miaosha_tv.setText("下午场16点场");
            if (hour==16){
                times=120*60*1000-(minute*60+second)*1000;
                countdownView.start(times);
            }else if (hour==17){
                times=120*60*1000-(minute*60+second+60*60)*1000;
                countdownView.start(times);
            }
        }else if(hour>=18&&hour<20){
            miaosha_tv.setText("下午场18点场");
            if (hour==18){
                times=120*60*1000-(minute*60+second)*1000;
                countdownView.start(times);
            }else if (hour==19){
                times=120*60*1000-(minute*60+second+60*60)*1000;
                countdownView.start(times);
            }
        }else if(hour>=20&&hour<22){
            miaosha_tv.setText("下午场20点场");
            if (hour==20){
                times=120*60*1000-(minute*60+second)*1000;
                countdownView.start(times);
            }else if (hour==21){
                times=120*60*1000-(minute*60+second+60*60)*1000;
                countdownView.start(times);
            }
        }else {
            miaosha_tv.setText("秒杀还未开始");
        }
        CountdownView.OnCountdownEndListener onCountdownEndListener = new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                ontime();
            }
        };
        countdownView.setOnCountdownEndListener( onCountdownEndListener);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (loginPrensenter != null) {
            loginPrensenter = null;
        }
        if (frag1GridPrensenter != null) {
            frag1GridPrensenter = null;
        }
        if (frag1StagPrensenter != null) {
            frag1StagPrensenter = null;
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void toBack(final String result) {
        Message msg = new Message();
        msg.what = 0;
        msg.obj = result;
        h.sendMessage(msg);
    }

    @Override
    public void toFrag1GridBack(String result) {
        Message msg = new Message();
        msg.what = 1;
        msg.obj = result;
        h.sendMessage(msg);
    }

    @Override
    public void toFrag1StagBack(String result) {
        Message msg = new Message();
        msg.what = 3;
        msg.obj = result;
        h.sendMessage(msg);
    }

    @OnClick({R.id.frag1_imgbtn1, R.id.frag1_imgbtn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.frag1_imgbtn1:
                frag1Imgbtn1.setVisibility(View.INVISIBLE);
                frag1Imgbtn2.setVisibility(View.VISIBLE);
                frag1.smoothScrollTo(0,3100);
                break;
            case R.id.frag1_imgbtn2:
                frag1.smoothScrollTo(0,0);
                break;
        }
    }
}
