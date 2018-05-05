package xudeyang.bawie.com.jd.view.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.helper.EventNetWork;
import xudeyang.bawie.com.jd.helper.EventRegister;
import xudeyang.bawie.com.jd.helper.EventUser;
import xudeyang.bawie.com.jd.utils.GlideCircleTransform;
import xudeyang.bawie.com.jd.utils.NetWorkUtil;
import xudeyang.bawie.com.jd.view.activity.OrderActivity;
import xudeyang.bawie.com.jd.view.activity.RegisterActivity;
import xudeyang.bawie.com.jd.view.activity.UserActivity;
import xudeyang.bawie.com.jd.view.base.BaseFragment;

/**
 * Created by Mac on 2018/4/10.
 */

public class Fragment_5 extends BaseFragment {
    private static final String TAG = "Fragment_5";
    Unbinder unbinder;
    @BindView(R.id.f5_user_btn)
    ImageView f5UserBtn;
    @BindView(R.id.f5_user_tv)
    TextView f5UserTv;
    private SharedPreferences shared;
    private boolean is;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_5;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        shared = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        is = shared.getBoolean("is", false);
        if (is) {
            String nickname = shared.getString("nickname", "");
            String icon = shared.getString("icon", "");
            f5UserTv.setText(nickname);
            int netWork = NetWorkUtil.getNetWork(getActivity());
            if (netWork != -1) {
                if (icon != "") {
                    Glide.with(getActivity()).
                            load(icon).
                            transform(new GlideCircleTransform(getActivity())).
                            into(f5UserBtn);
                }
            }
        }
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

    @OnClick({R.id.f5_user_btn, R.id.f5_user_tv, R.id.f5_user_msg, R.id.f5_user_set,R.id.f5_ding1, R.id.f5_ding2, R.id.f5_ding3, R.id.f5_ding4, R.id.f5_ding5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.f5_user_btn:
                SharedPreferences ssss = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
                boolean ds = ssss.getBoolean("is", false);
                int netWorks = NetWorkUtil.getNetWork(getActivity());
                if (netWorks != -1) {
                    if (ds) {
                        startActivity(new Intent(getActivity(), UserActivity.class));
                    } else {
                        startActivity(new Intent(getActivity(), RegisterActivity.class));
                    }
                } else {
                    Toast.makeText(getActivity(), "请您先看一下网络是否连接哦~", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.f5_user_tv:
                SharedPreferences sssss = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
                boolean dss = sssss.getBoolean("is", false);
                int netWorkss = NetWorkUtil.getNetWork(getActivity());
                if (netWorkss != -1) {
                    if (dss) {
                        startActivity(new Intent(getActivity(), UserActivity.class));
                    } else {
                        startActivity(new Intent(getActivity(), RegisterActivity.class));
                    }
                } else {
                    Toast.makeText(getActivity(), "请您先看一下网络是否连接哦~", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.f5_user_msg:

                break;
            case R.id.f5_user_set:
                SharedPreferences ssssss = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
                boolean dsss = ssssss.getBoolean("is", false);
                int netWorksss = NetWorkUtil.getNetWork(getActivity());
                if (netWorksss != -1) {
                    if (dsss) {
                        startActivity(new Intent(getActivity(), UserActivity.class));
                    } else {
                        startActivity(new Intent(getActivity(), RegisterActivity.class));
                    }
                } else {
                    Toast.makeText(getActivity(), "请您先看一下网络是否连接哦~", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.f5_ding1:
                startActivity(new Intent(getActivity(), OrderActivity.class));
                break;
            case R.id.f5_ding2:
                startActivity(new Intent(getActivity(), OrderActivity.class));
                break;
            case R.id.f5_ding3:
                startActivity(new Intent(getActivity(), OrderActivity.class));
                break;
            case R.id.f5_ding4:
                startActivity(new Intent(getActivity(), OrderActivity.class));
                break;
            case R.id.f5_ding5:
                startActivity(new Intent(getActivity(), OrderActivity.class));
                break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(EventRegister eventRegister) {
        int netWork = NetWorkUtil.getNetWork(getActivity());
        f5UserTv.setText(eventRegister.getName());
        if (netWork != -1) {
            Glide.with(getActivity()).
                    load(eventRegister.getIcon()).
                    transform(new GlideCircleTransform(getActivity())).
                    into(f5UserBtn);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventb(EventNetWork eventNetWork) {
        String icon = shared.getString("icon", "");
        if (icon != "") {
            Glide.with(getActivity()).
                    load(icon).
                    transform(new GlideCircleTransform(getActivity())).
                    into(f5UserBtn);
        }
    }

    @Subscribe()
    public void onCode(EventUser eventUser) {
        if (eventUser.getCode() == 9) {
            Log.e(TAG, "onCode: ");
            f5UserBtn.setImageResource(R.drawable.user);
            f5UserTv.setText("登录/注册");
        }
    }

}
