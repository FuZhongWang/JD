package xudeyang.bawie.com.jd.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.view.Fragment.OrderFragment.OrderFrag1;
import xudeyang.bawie.com.jd.view.Fragment.OrderFragment.OrderFrag2;
import xudeyang.bawie.com.jd.view.Fragment.OrderFragment.OrderFrag3;
import xudeyang.bawie.com.jd.view.Fragment.OrderFragment.OrderFrag4;
import xudeyang.bawie.com.jd.view.Fragment.OrderFragment.OrderFrag5;
import xudeyang.bawie.com.jd.view.base.BaseActivity;

public class OrderActivity extends BaseActivity {

    SlidingTabLayout orderSlidingTabLayout;
    MyPagerAdapter adapter;
    ViewPager orderVp;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "全部","待付款","待收货", "已完成","已取消"
    };
    @Override
    public void initView() {
        setContentView(R.layout.activity_order);
        orderVp = findViewById(R.id.order_vp);
        orderSlidingTabLayout= findViewById(R.id.order_slidingTabLayout);
        mFragments.add(new OrderFrag1());
        mFragments.add(new OrderFrag2());
        mFragments.add(new OrderFrag3());
        mFragments.add(new OrderFrag4());
        mFragments.add(new OrderFrag5());
        adapter=new MyPagerAdapter(getSupportFragmentManager());
        orderVp.setAdapter(adapter);
        orderSlidingTabLayout.setTextSelectColor(Color.RED);
        orderSlidingTabLayout.setIndicatorColor(Color.RED); //设置滚动条的颜色
        orderSlidingTabLayout.setViewPager(orderVp);
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
    }

    @OnClick(R.id.order_back)
    public void onViewClicked() {
        finish();
    }
    private class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
