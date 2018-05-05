package xudeyang.bawie.com.jd.model;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xudeyang.bawie.com.jd.bean.Frag2RightBean;
import xudeyang.bawie.com.jd.bean.Frag2RightBeanDao;
import xudeyang.bawie.com.jd.bean.ListBean;
import xudeyang.bawie.com.jd.bean.ListBeanDao;
import xudeyang.bawie.com.jd.helper.MyApp;
import xudeyang.bawie.com.jd.presenter.F2Pre;
import xudeyang.bawie.com.jd.utils.DBMaster;
import xudeyang.bawie.com.jd.utils.NetWorkUtil;
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.view.base.BaseBean;

/**
 * Created by Mac on 2018/4/20.
 */

public class F2RightModel {
    private static final String TAG = "F2RightModel";
    private Context context;
    private F2Pre f2Pre;
    public F2RightModel(Context context,F2Pre f2Pre){
        this.context=context;
        this.f2Pre=f2Pre;
    }
    public void getData(String cid){
        List<Frag2RightBean> rightBeans = MyApp.getInstance().getRightBeans(cid);
        if (rightBeans!=null){
            f2Pre.suceuss(rightBeans);
        }else{
            getDataForDao(cid);
        }
    }
    private void getDataForDao(String cid) {
        //如果内存中没有数据,在数据库中寻找数据
        Frag2RightBeanDao rightBeanDao = DBMaster.getInstance(context).getFrag2RightBeanDao();
        //根据Cid查询出数据库cid下的分类
        List<Frag2RightBean> list = rightBeanDao.queryBuilder().where(Frag2RightBeanDao.Properties.Cid.eq(cid)).build().list();
        ListBeanDao listBeanDao = DBMaster.getInstance(context).getListBeanDao();
        for (Frag2RightBean inner:  list) {
            //遍历查出所有cid下Pcid一样的数据
            List<ListBean> list1 = listBeanDao.queryBuilder().where(ListBeanDao.Properties.Pcid.eq(inner.getPcid())).build().list();
            if (list1 != null) {
                inner.setList(list1);
            }
        }
        //如果数据库有内容,则缓存到内存一份
        if (list != null) {
            if (list.size() >0) {
                MyApp.getInstance().setRightMap(cid,list);
            }
        }
        //之后把数据发送给P层
        f2Pre.suceuss(list);
        //当有网络的时候更新数据库数据
        if (NetWorkUtil.getNetWork(context)!=-1) {
            getDataForHttp(cid);
        }
    }

    private void getDataForHttp(final String cid) {
        RxRetrofitUtil.doGet().getInfo(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<BaseBean<List<Frag2RightBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<Frag2RightBean>> listBaseBean) {
                        List<Frag2RightBean> data = listBaseBean.getData();
                        if (data!=null){
                            Frag2RightBeanDao frag2RightBeanDao = DBMaster.getInstance(context).getFrag2RightBeanDao();
                            frag2RightBeanDao.insertOrReplaceInTx(data);
                            for (Frag2RightBean inner:data) {
                                List<ListBean> list = inner.getList();
                                if (list != null) {
                                    ListBeanDao listBeanDao = DBMaster.getInstance(context).getListBeanDao();
                                    listBeanDao.insertOrReplaceInTx(list);
                                }
                            }
                            if (data.size()>0){
                                MyApp.getInstance().setRightMap(cid,data);
                            }
                        }

                        f2Pre.suceuss(data);
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
