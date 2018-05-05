package xudeyang.bawie.com.jd.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.adapter.AddrAdpater;
import xudeyang.bawie.com.jd.bean.AddrBean;
import xudeyang.bawie.com.jd.helper.EventAddAddr;
import xudeyang.bawie.com.jd.model.AddrModel;
import xudeyang.bawie.com.jd.view.Iview.ToAddrBack;
import xudeyang.bawie.com.jd.view.base.BaseActivity;

public class AddrActivity extends BaseActivity implements ToAddrBack<List<AddrBean>> {

    List<AddrBean> datas = new ArrayList<>();
    RecyclerView addrRcv;
    private AddrAdpater addrAdpater;

    @Override
    public void initView() {
        setContentView(R.layout.activity_addr);
        addrRcv = findViewById(R.id.addr_rcv);
        addrAdpater = new AddrAdpater(this, datas);
        addrRcv.setLayoutManager(new LinearLayoutManager(AddrActivity.this, LinearLayoutManager.VERTICAL, false));
        addrRcv.setAdapter(addrAdpater);
    }

    @Override
    public void initHttp() {

    }

    @Override
    public void initData() {
        SharedPreferences shared = getSharedPreferences("shared", MODE_PRIVATE);
        String uid = shared.getString("uid", "");
        String token = shared.getString("token", "");
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("token", token);
        map.put("source", "android");
        AddrModel.getData(map, this);
    }

    @Override
    public void success(List<AddrBean> data) {
        datas.clear();
        datas.addAll(data);
        addrAdpater.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }


    @OnClick({R.id.addr_return, R.id.addr_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addr_return:
                finish();
                break;
            case R.id.addr_add:
                openActivity(AddAddrActivity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void onEvent(EventAddAddr eventAddAddr){
        if (eventAddAddr.getCode()==1){
            SharedPreferences shared = getSharedPreferences("shared", MODE_PRIVATE);
            String uid = shared.getString("uid", "");
            String token = shared.getString("token", "");
            Map<String, String> map = new HashMap<>();
            map.put("uid", uid);
            map.put("token", token);
            map.put("source", "android");
            AddrModel.getData(map, this);
        }
    }
}
