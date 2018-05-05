package xudeyang.bawie.com.jd.view.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.adapter.Frag2LeftAdapter;
import xudeyang.bawie.com.jd.adapter.Frag2RightAdapter;
import xudeyang.bawie.com.jd.bean.Frag2LeftBean;
import xudeyang.bawie.com.jd.bean.Frag2RightBean;
import xudeyang.bawie.com.jd.helper.BlogService;
import xudeyang.bawie.com.jd.helper.EventFragTwoLeft;
import xudeyang.bawie.com.jd.helper.EventNetWork;
import xudeyang.bawie.com.jd.helper.MyApp;
import xudeyang.bawie.com.jd.presenter.Frag2LeftPrensenter;
import xudeyang.bawie.com.jd.presenter.Frag2RightPrensenter;
import xudeyang.bawie.com.jd.utils.SecrchActivity;
import xudeyang.bawie.com.jd.view.Iview.ToFrag2LeftBack;
import xudeyang.bawie.com.jd.view.Iview.ToFrag2RightBack;
import xudeyang.bawie.com.jd.view.base.BaseBean;
import xudeyang.bawie.com.jd.view.base.BaseFragment;

/**
 * Created by Mac on 2018/4/10.
 */

public class Fragment_2 extends BaseFragment implements ToFrag2LeftBack<List<Frag2LeftBean>>, ToFrag2RightBack<List<Frag2RightBean>> {
    @BindView(R.id.frag2_cc)
    SecrchActivity frag2Cc;
    @BindView(R.id.f2_left_rcl)
    RecyclerView f2_left_rcl;
    @BindView(R.id.f2_right_rcl)
    RecyclerView f2_right_rcl;
    Unbinder unbinder;
    ImageView f2Imggg;
    private Frag2LeftPrensenter frag2LeftPrensenter;
    private Frag2RightPrensenter frag2RightPrensenter;
    private List<Frag2LeftBean> leftLists = new ArrayList<>();
    private List<Frag2RightBean> rightLists = new ArrayList<>();
    private Frag2LeftAdapter frag2LeftAdapter;
    private BaseBean<Frag2LeftBean> frag2LeftBeanBaseBean;
    private Frag2RightAdapter frag2RightAdapter;
    private BlogService service;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_2;
    }

    @Override
    protected void initView() {
        f2Imggg=findViewById(R.id.f2_imggg);
    }

    @Override
    protected void initData() {
        frag2LeftAdapter = new Frag2LeftAdapter(leftLists, getActivity());
        f2_left_rcl.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        f2_left_rcl.setAdapter(frag2LeftAdapter);
        frag2RightAdapter = new Frag2RightAdapter(rightLists, getActivity());
        f2_right_rcl.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        f2_right_rcl.setAdapter(frag2RightAdapter);
        frag2LeftPrensenter = new Frag2LeftPrensenter(this, MyApp.getInstance().getApplicationContext());
        frag2LeftPrensenter.toUrl();
        frag2RightPrensenter = new Frag2RightPrensenter(this, MyApp.getInstance().getApplicationContext());
        frag2RightPrensenter.toUrlPost("1");
       /* Glide.with(this).load("http://img1.gamedog.cn/2014/05/29/91-1405291103380-50.gif")
               .placeholder(R.drawable.qiche)
                .into(f2Imggg);*/

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onType(EventNetWork eventNetWork) {
        if (eventNetWork.getType() == 1) {
            frag2RightPrensenter.toUrlPost("1");
            //Glide.with(this).load("http://img1.gamedog.cn/2014/05/29/91-1405291103380-50.gif").into(f2Imggg);
        }
    }

    @Override
    protected void bindEvent() {

    }

    @Subscribe
    protected void bindEvent(EventFragTwoLeft eventFragTwoLeft) {
        frag2LeftAdapter.setAdpPostion(eventFragTwoLeft.getPosition());
        frag2LeftAdapter.notifyDataSetChanged();
        frag2RightPrensenter.toUrlPost((eventFragTwoLeft.getPosition() + 1) + "");

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
    public void toFrag2LeftBack(List<Frag2LeftBean> result) {

        leftLists.addAll(result);
        frag2LeftAdapter.notifyDataSetChanged();
    }


    @Override
    public void toFrag2RightBack(List<Frag2RightBean> result) {
        if (result != null) {
            rightLists.clear();
            rightLists.addAll(result);
            frag2RightAdapter.notifyDataSetChanged();
        }
    }
}
