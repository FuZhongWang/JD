package xudeyang.bawie.com.jd.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.helper.EventImage;
import xudeyang.bawie.com.jd.helper.EventNetWork;
import xudeyang.bawie.com.jd.helper.EventShow;
import xudeyang.bawie.com.jd.helper.EventUser;
import xudeyang.bawie.com.jd.utils.NetWorkUtil;
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.view.base.BaseActivity;
import xudeyang.bawie.com.jd.view.base.BaseBean;

public class ShowActivity extends BaseActivity {
    private static final String TAG = "ShowActivity";
    @BindView(R.id.xq_img)
    Banner xqImg;
    @BindView(R.id.xq_title)
    TextView xqTitle;
    @BindView(R.id.xq_subhead)
    TextView xqSubhead;
    @BindView(R.id.xq_price)
    TextView xqPrice;
    @BindView(R.id.xq_fh)
    ImageView xqFh;
    @BindView(R.id.xq_fx)
    ImageView xqFx;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.xq_insert)
    TextView xqInsert;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.xq_buy)
    TextView xqBuy;
    int mDistanceY = 300;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private SharedPreferences shared;
    private boolean is;
    private String uid;
    private String token;

    @Override
    public void initView() {
        setContentView(R.layout.activity_show);

    }

    @Override
    public void initHttp() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        collapsingToolbar.setContentScrimColor(Color.parseColor("#4C4C4C"));
        collapsingToolbar.setBackgroundColor(Color.parseColor("#4C4C4C"));
        collapsingToolbar.setStatusBarScrimColor(Color.parseColor("#4C4C4C"));
        collapsingToolbar.setTitle("商品详情");
        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#00000000"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @OnClick({R.id.xq_fh, R.id.xq_fx, R.id.xq_insert,R.id.xq_buy})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.xq_fh:
                finish();
                break;
            case R.id.xq_fx:

                break;
            case R.id.xq_insert:

                break;
            case R.id.xq_buy:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true)
    public void onEvents(final EventShow eventShow) {
        String imageUrl = eventShow.getImageUrl();
        final String[] split = imageUrl.split("\\|");
        List<String> imgs = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            imgs.add(split[i]);
        }
        xqImg.setImageLoader(new MyLoader());
        xqImg.setDelayTime(3500);
        xqImg.setImages(imgs).setIndicatorGravity(BannerConfig.CENTER)
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        EventImage eventImage = new EventImage();
                        eventImage.setName(split[position]);
                        EventBus.getDefault().postSticky(eventImage);
                        openActivity(ImageActivity.class);
                    }
                }).start();
       /*
        xqImg.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return split.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                View v=View.inflate(ShowActivity.this,R.layout.viewpager_item,null);
                ImageView img = v.findViewById(R.id.viewpager_img);
                Glide.with(ShowActivity.this).load(split[position]).into(img);
                container.addView(v);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                return v;
            }
        });*/

        xqTitle.setText(eventShow.getTitle());
        xqPrice.setText(eventShow.getPrice());
        xqSubhead.setText(eventShow.getSubHead());
        xqBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared = getSharedPreferences("shared", MODE_PRIVATE);
                is = shared.getBoolean("is", false);
                uid = shared.getString("uid", "");
                token = shared.getString("token", "");
                final Map<String,String> map=new HashMap<>();
                map.put("uid",uid);
                map.put("price",eventShow.getPrice()+"");
                map.put("token",token);
                Log.i(TAG, "onClick: "+uid+eventShow.getPrice()+token);
                if (is){
                    RxRetrofitUtil.doGet().getCreateOrder(map)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new DisposableSubscriber<BaseBean>() {
                                @Override
                                public void onNext(BaseBean baseBean) {
                                    Toast.makeText(ShowActivity.this, ""+baseBean.getMsg(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(Throwable t) {
                                    Log.e(TAG, "onError: "+t.getMessage());
                                    Toast.makeText(ShowActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }else{
                    Toast.makeText(ShowActivity.this, "请先登录!", Toast.LENGTH_SHORT).show();
                    openActivity(RegisterActivity.class);

                }
            }
        });
        xqInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared = getSharedPreferences("shared", MODE_PRIVATE);
                is = shared.getBoolean("is", false);
                uid = shared.getString("uid", "");
                token = shared.getString("token", "");
                final Map<String,String> map=new HashMap<>();
                map.put("pid",eventShow.getPid()+"");
                map.put("uid",uid);
                map.put("token",token);
                //map.put("source","android");
                if (is){
                    RxRetrofitUtil.doGet().getAddCart(map)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new DisposableSubscriber<BaseBean>() {
                                @Override
                                public void onNext(BaseBean baseBean) {
                                    Log.e(TAG, "onNext: " );
                                    Toast.makeText(ShowActivity.this, ""+baseBean.getMsg(), Toast.LENGTH_SHORT).show();
                                    EventBus.getDefault().post(new EventUser(10));
                                }

                                @Override
                                public void onError(Throwable t) {
                                    Log.i(TAG, "onError: "+t.getMessage());
                                }

                                @Override
                                public void onComplete() {
                                }
                            });
                }else {
                    Toast.makeText(ShowActivity.this, "请先登录!", Toast.LENGTH_SHORT).show();
                    openActivity(RegisterActivity.class);

                }

            }
        });
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
}
