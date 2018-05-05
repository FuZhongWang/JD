package xudeyang.bawie.com.jd.view.Fragment.OrderFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.adapter.Order1Adapter;
import xudeyang.bawie.com.jd.bean.OrderBean;
import xudeyang.bawie.com.jd.model.Order1Model;
import xudeyang.bawie.com.jd.view.Iview.ToOrder1View;
import xudeyang.bawie.com.jd.view.base.BaseFragment;

/**
 * Created by Mac on 2018/4/10.
 */

public class OrderFrag1 extends BaseFragment implements ToOrder1View<List<OrderBean>>{
    private List<OrderBean> datas=new ArrayList<>();
    private Order1Adapter order1Adapter;
    private RecyclerView order_1_rcv;

    @Override
    protected int bindLayout() {
        return R.layout.order_frag_1;
    }

    @Override
    protected void initView() {
        order_1_rcv = findViewById(R.id.order_1_rcv);
        SharedPreferences shared = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        boolean is = shared.getBoolean("is", false);
        String uid = shared.getString("uid", "");
        String token = shared.getString("token", "");
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("token",token);
        if (is ) {
            Order1Model.getData(map,this);
        }
        order1Adapter = new Order1Adapter(datas,getActivity());
        order_1_rcv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        order_1_rcv.setAdapter(order1Adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void success(List<OrderBean> data) {
        datas.addAll(data);
        order1Adapter.notifyDataSetChanged();
    }
}
