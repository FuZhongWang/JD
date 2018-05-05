package xudeyang.bawie.com.jd.model;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xudeyang.bawie.com.jd.bean.Frag2LeftBean;
import xudeyang.bawie.com.jd.bean.Frag2LeftBeanDao;
import xudeyang.bawie.com.jd.bean.Frag2RightBean;
import xudeyang.bawie.com.jd.helper.MyApp;
import xudeyang.bawie.com.jd.presenter.F2Pre;
import xudeyang.bawie.com.jd.presenter.LoginInterPre;
import xudeyang.bawie.com.jd.utils.DBMaster;
import xudeyang.bawie.com.jd.utils.NetWorkUtil;
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.view.base.BaseBean;

/**
 * Created by Mac on 2018/4/19.
 */

public class F2LeftModel {
    private Context context;
    private F2Pre f2Pre;
    public F2LeftModel(Context context,F2Pre f2Pre){
        this.context=context;
        this.f2Pre=f2Pre;
    }
    public void getData(){
        List<Frag2LeftBean> leftBeans = MyApp.getInstance().getLeftBeans();
        if (leftBeans==null) {
            getDataFromDB(context);
        }else {
            f2Pre.suceuss(leftBeans);
        }
    }
    private void getDataFromDB(Context context) {
        Frag2LeftBeanDao frag2LeftBeanDao = DBMaster.getInstance(context).getfrag2LeftBeanDao();
        List<Frag2LeftBean> frag2LeftBeans = frag2LeftBeanDao.loadAll();
        if (frag2LeftBeans != null) {
            MyApp.getInstance().setLeftBeans(frag2LeftBeans);
            f2Pre.suceuss(frag2LeftBeans);
        }
        if (NetWorkUtil.getNetWork(context)==0){
            onGet();
        }
    }

    public void onGet(){
        RxRetrofitUtil.doGet().getCatagory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<BaseBean<List<Frag2LeftBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<Frag2LeftBean>> listBaseBean) {
                        List<Frag2LeftBean> data = listBaseBean.getData();
                        Frag2LeftBeanDao frag2LeftBeanDao = DBMaster.getInstance(context).getfrag2LeftBeanDao();
                        // 获取到数据之后，存到磁盘中一份
                        if (data != null) {
                            frag2LeftBeanDao.insertOrReplaceInTx(data);
                            MyApp.getInstance().setLeftBeans(data);
                            f2Pre.suceuss(listBaseBean.getData());
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
