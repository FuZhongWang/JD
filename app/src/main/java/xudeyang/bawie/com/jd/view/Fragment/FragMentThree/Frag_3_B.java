package xudeyang.bawie.com.jd.view.Fragment.FragMentThree;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.adapter.DwVideoAdapter;
import xudeyang.bawie.com.jd.bean.DuowanVideoBean;
import xudeyang.bawie.com.jd.helper.MyDividerItemDecoration;
import xudeyang.bawie.com.jd.model.DwVideoModel;
import xudeyang.bawie.com.jd.view.Iview.ToF3BData;
import xudeyang.bawie.com.jd.view.base.BaseFragment;

/**
 * Created by Mac on 2018/4/10.
 */

public class Frag_3_B extends BaseFragment implements ToF3BData<List<DuowanVideoBean.DataBean>>{
    private static final String TAG = "Frag_3_B";
    @BindView(R.id.b_view_rcv)
    RecyclerView bViewRcv;
    Unbinder unbinder;
    private List<DuowanVideoBean.DataBean> datas=new ArrayList<>();
    private DwVideoAdapter dwVideoAdapter;

    @Override
    protected int bindLayout() {
        return R.layout.frag_3_b_view;
    }

    @Override
    protected void initView() {
        dwVideoAdapter = new DwVideoAdapter(datas, getActivity());
        bViewRcv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        bViewRcv.setAdapter(dwVideoAdapter);
        DwVideoModel.getData(this);

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

    @Override
    public void toback(List<DuowanVideoBean.DataBean> data) {
        datas.clear();
        datas.addAll(data);
        dwVideoAdapter.notifyDataSetChanged();
    }
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()){
            return;
        }
        this.onBackPressed();
    }
    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
