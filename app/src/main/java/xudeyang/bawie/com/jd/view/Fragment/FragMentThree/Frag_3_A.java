package xudeyang.bawie.com.jd.view.Fragment.FragMentThree;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.adapter.F3AViewAdapter;
import xudeyang.bawie.com.jd.bean.YunifangBean;
import xudeyang.bawie.com.jd.model.DwAttentionModel;
import xudeyang.bawie.com.jd.view.Iview.ToF3AData;
import xudeyang.bawie.com.jd.view.base.BaseFragment;

/**
 * Created by Mac on 2018/4/10.
 */

public class Frag_3_A extends BaseFragment implements ToF3AData<List<YunifangBean.DataBean.SubjectsBean>>{
    @BindView(R.id.a_view_rcv)
    RecyclerView aViewRcv;
    Unbinder unbinder;
    private List<YunifangBean.DataBean.SubjectsBean> datas=new ArrayList<>();
    private F3AViewAdapter f3AViewAdapter;

    @Override
    protected int bindLayout() {
        return R.layout.frag_3_a_view;
    }

    @Override
    protected void initView() {
        DwAttentionModel.getData(this);
    }

    @Override
    protected void initData() {
        f3AViewAdapter = new F3AViewAdapter(getActivity(),datas);
        aViewRcv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        aViewRcv.setAdapter(f3AViewAdapter);
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

    @Override
    public void toback(List<YunifangBean.DataBean.SubjectsBean> data) {
        datas.clear();
        datas.addAll(data);
        f3AViewAdapter.notifyDataSetChanged();
    }
}
