package xudeyang.bawie.com.jd.view.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.androidprogresslayout.ProgressLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.adapter.ExpandableAdapter;
import xudeyang.bawie.com.jd.bean.CartsBean;
import xudeyang.bawie.com.jd.bean.ListCartsBean;
import xudeyang.bawie.com.jd.helper.AllDeleteChecked;
import xudeyang.bawie.com.jd.helper.EventAllChecked;
import xudeyang.bawie.com.jd.helper.EventAllDelete;
import xudeyang.bawie.com.jd.helper.EventAllUpdate;
import xudeyang.bawie.com.jd.helper.EventNetWork;
import xudeyang.bawie.com.jd.helper.EventUser;
import xudeyang.bawie.com.jd.helper.MyApp;
import xudeyang.bawie.com.jd.helper.PriceAndCountEvent;
import xudeyang.bawie.com.jd.presenter.F4Presenter;
import xudeyang.bawie.com.jd.utils.NetWorkUtil;
import xudeyang.bawie.com.jd.view.Iview.ToF4CartsBack;
import xudeyang.bawie.com.jd.view.base.BaseFragment;

/**
 * Created by Mac on 2018/4/10.
 */

public class Fragment_4 extends BaseFragment implements ToF4CartsBack<List<CartsBean>> {
    private static final String TAG = "Fragment_4";
    TextView price_all;
    TextView gobuy;
    Unbinder unbinder;
    @BindView(R.id.exlv)
    ExpandableListView exlv;
    @BindView(R.id.cb_all)
    CheckBox cbAll;
    @BindView(R.id.f4_del)
    Button f4Del;
    @BindView(R.id.f4_rflayout)
    SmartRefreshLayout f4Rflayout;
    @BindView(R.id.f4_kong)
    TextView f4Kong;
    @BindView(R.id.f4rrr)
    RelativeLayout f4rrr;
    private F4Presenter presenter;
    private SharedPreferences shared;
    private List<CartsBean> datas = new ArrayList<>();
    private List<List<ListCartsBean>> cdatas = new ArrayList<>();
    private List<CartsBean> groupdel = new ArrayList<>();
    private List<List<ListCartsBean>> childdel = new ArrayList<>();
    private ExpandableAdapter expandableAdapter;
    private boolean is;
    private ProgressLayout progressLayout;
    private String token;
    private String uid;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_4;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        //f4Rflayout.setEnableRefresh(false);
        f4Rflayout.setEnableLoadMore(false);
        gobuy = findViewById(R.id.f4_gobuy);
        progressLayout = findViewById(R.id.f4_progress_layout);
        price_all = findViewById(R.id.f4_price_all);
        presenter = new F4Presenter(MyApp.getInstance().getApplicationContext(), this);
        shared = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        is = shared.getBoolean("is", false);
        token = shared.getString("token", "");
        uid = shared.getString("uid", "");

        expandableAdapter = new ExpandableAdapter(getActivity(), datas, cdatas, token, uid);
        exlv.setAdapter(expandableAdapter);

        f4Rflayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000);
                SharedPreferences shared1 = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
                boolean is1 = shared1.getBoolean("is", false);
                if (is1) {
                    presenter.toModel();
                }
            }
        });
    }


    @Override
    protected void initData() {
        int netWork = NetWorkUtil.getNetWork(getActivity());
        if (netWork != -1) {
            if (is) {
                progressLayout.showProgress();
                presenter.toModel();

            } else {
                exlv.setVisibility(View.INVISIBLE);
                f4Kong.setVisibility(View.VISIBLE);
            }
            f4rrr.setVisibility(View.INVISIBLE);
        } else {
            exlv.setVisibility(View.INVISIBLE);
            cbAll.setEnabled(false);
            f4Del.setEnabled(false);
        }


    }

    @Override
    protected void bindEvent() {
        f4Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean bbb = false;
                for (int i = 0; i < datas.size(); i++) {
                    for (int j = 0; j < datas.get(i).getList().size(); j++) {
                        if (datas.get(i).getList().get(j).getCisChecked()) {
                            bbb = true;
                        }
                    }
                }
                if (bbb) {
                    AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
                    alert.setTitle("操作提示");
                    alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                    alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    return;
                                }
                            });
                    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    childdel.clear();
                                    groupdel.clear();
                                    for (int i = 0; i < datas.size(); i++) {
                                        if (datas.get(i).getIsChecked()) {
                                            groupdel.add(datas.get(i));
                                            for (int j = 0; j < datas.get(i).getList().size(); j++) {
                                                if (datas.get(i).getList().get(j).getCisChecked()) {
                                                    childdel.add(datas.get(i).getList());
                                                }
                                            }
                                        }
                                        cdatas.removeAll(childdel);
                                    }
                                    EventBus.getDefault().post(new EventAllDelete(3));
                                    AllDeleteChecked allDeleteChecked = new AllDeleteChecked(childdel);
                                    allDeleteChecked.forData(uid, token);
                                    datas.removeAll(groupdel);
                                    cbAll.setChecked(false);
                                    expandableAdapter.notifyDataSetChanged();
                                    onMessageEven(new PriceAndCountEvent());
                                    show();
                                }
                            });
                    alert.show();
                } else {
                    Toast.makeText(getActivity(), "请先选择一个商品~", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void show() {
        if (datas.size() == 0) {
            exlv.setVisibility(View.INVISIBLE);
            f4Kong.setVisibility(View.VISIBLE);
        } else {
            exlv.setVisibility(View.VISIBLE);
            f4Kong.setVisibility(View.INVISIBLE);
        }
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

    @OnClick({R.id.cb_all, R.id.f4_del})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_all:
                expandableAdapter.changeAllListCbState(cbAll.isChecked());
                break;
            case R.id.f4_del:

                break;
        }
    }



    @Override
    public void toBack(List<CartsBean> data) {
        f4rrr.setVisibility(View.VISIBLE);
        datas.clear();
        cdatas.clear();
        f4Kong.setVisibility(View.INVISIBLE);
        for (int i = 0; i < data.size(); i++) {
            List<ListCartsBean> clist = data.get(i).getList();
            for (int j = 0; j < clist.size(); j++) {
                data.get(i).setIsChecked(clist.get(j).getSelected() == 1 ? true : false);
                clist.get(j).setCisChecked(clist.get(j).getSelected() == 1 ? true : false);
            }
            cdatas.add(clist);
        }
        datas.addAll(data);
        progressLayout.showContent();
        expandableAdapter.notifyDataSetChanged();
        cbAll.setEnabled(true);
        f4Del.setEnabled(true);
        int count = exlv.getCount();
            for (int i = 0; i < datas.size(); i++) {
                exlv.expandGroup(i);
            }
        cbAll.setChecked(expandableAdapter.isAllGroupSelect());
        show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onType(EventNetWork eventNetWork) {
        if (eventNetWork.getType() == -1) {
            exlv.setVisibility(View.INVISIBLE);
            cbAll.setEnabled(false);
            f4Del.setEnabled(false);
        } else {
            SharedPreferences shared1 = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
            boolean is1 = shared1.getBoolean("is", false);
            if (is1) {
                cbAll.setEnabled(true);
                f4Del.setEnabled(true);
                presenter.toModel();
                show();
            } else {

            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCheckAll(EventAllChecked eventAllChecked) {
        cbAll.setChecked(eventAllChecked.isChecked());
    }

    @Subscribe
    public void onMessageEven(PriceAndCountEvent event) {
        price_all.setText("合计:￥" + event.getPrice() + ".00");
        gobuy.setText("去结算(" + event.getCount() + ")");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCheckUpdate(EventAllUpdate eventAllUpdate) {
        if (eventAllUpdate.getType() == 3) {
            exlv.setVisibility(View.INVISIBLE);
            progressLayout.showProgress();
            cbAll.setEnabled(false);
            f4Del.setEnabled(false);
        } else {
            cbAll.setEnabled(true);
            f4Del.setEnabled(true);
            exlv.setVisibility(View.VISIBLE);
            progressLayout.showContent();
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCheckDelete(EventAllDelete eventAllDelete) {
        if (eventAllDelete.getType() == 3) {
            exlv.setVisibility(View.INVISIBLE);
            progressLayout.showProgress();
            cbAll.setEnabled(false);
            f4Del.setEnabled(false);
        } else {
            cbAll.setEnabled(true);
            f4Del.setEnabled(true);
            exlv.setVisibility(View.VISIBLE);
            progressLayout.showContent();
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe()
    public void onCode(EventUser eventUser) {
        if (eventUser.getCode() == 9) {
            Log.e(TAG, "onCode: "+eventUser.getCode() );
            datas.clear();
            cdatas.clear();
            expandableAdapter.notifyDataSetChanged();
            f4rrr.setVisibility(View.INVISIBLE);
            show();
        }else{
            presenter.toModel();
        }
    }


}
