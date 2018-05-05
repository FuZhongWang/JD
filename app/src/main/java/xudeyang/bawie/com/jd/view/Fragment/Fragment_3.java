package xudeyang.bawie.com.jd.view.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.zxing.activity.CaptureActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.view.Fragment.FragMentThree.Frag_3_A;
import xudeyang.bawie.com.jd.view.Fragment.FragMentThree.Frag_3_B;
import xudeyang.bawie.com.jd.view.activity.CacaaActivity;
import xudeyang.bawie.com.jd.view.base.BaseFragment;

/**
 * Created by Mac on 2018/4/10.
 */

public class Fragment_3 extends BaseFragment {
    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout slidingTabLayout;
    MyPagerAdapter adapter;
    Unbinder unbinder;
    @BindView(R.id.f3_vp)
    ViewPager f3Vp;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "关注", "视频", "美容觉", "直播"
    };

    @Override
    protected int bindLayout() {
        return R.layout.fragment_3;
    }

    @Override
    protected void initView() {
        mFragments.add(new Frag_3_A());
        mFragments.add(new Frag_3_B());
        mFragments.add(new Frag_3_A());
        mFragments.add(new Frag_3_B());
        adapter = new MyPagerAdapter(getFragmentManager());
        f3Vp.setAdapter(adapter);
        slidingTabLayout.setTextSelectColor(Color.RED);
        slidingTabLayout.setTextsize(16);
        slidingTabLayout.setTextBold(true);
        slidingTabLayout.setIndicatorColor(Color.RED); //设置滚动条的颜色
        slidingTabLayout.setViewPager(f3Vp);
    }

    @Override
    protected void initData() {

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
    }

    @OnClick(R.id.f3_sao)
    public void onViewClicked() {
        getActivity().startActivity(new Intent(getActivity(), CacaaActivity.class));
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
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
