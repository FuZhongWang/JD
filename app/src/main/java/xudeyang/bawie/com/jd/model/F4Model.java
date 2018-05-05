package xudeyang.bawie.com.jd.model;

import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xudeyang.bawie.com.jd.bean.CartsBean;
import xudeyang.bawie.com.jd.bean.CartsBeanDao;
import xudeyang.bawie.com.jd.bean.ListCartsBean;
import xudeyang.bawie.com.jd.bean.ListCartsBeanDao;
import xudeyang.bawie.com.jd.helper.MyApp;
import xudeyang.bawie.com.jd.presenter.F4Pre;
import xudeyang.bawie.com.jd.utils.DBMaster;
import xudeyang.bawie.com.jd.utils.NetWorkUtil;
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.view.base.BaseBean;

/**
 * Created by Mac on 2018/4/21.
 */

public class F4Model {
    private static final String TAG = "F4Model";
    private Context context;
    private F4Pre f4Pre;
    public  F4Model(Context context,F4Pre f4Pre){
        this.context=context;
        this.f4Pre=f4Pre;
    }
    public void getData(Map<String,String> params){
        List<CartsBean> cartsBeans = MyApp.getInstance().getCartsBeans();
        if (cartsBeans != null) {
            Log.e(TAG, "getData: 内存中有数据!" );
            f4Pre.getData(cartsBeans);
        }else {
            getDataForDao(params);
            Log.e(TAG, "getData: 内存中没有数据,请求磁盘数据!" );
        }
    }

    private void getDataForDao(Map<String,String> params) {
        CartsBeanDao cartsBeanDao = DBMaster.getInstance(context).getCartsBeanDao();
        List<CartsBean > cartsBeans = cartsBeanDao.loadAll();
        ListCartsBeanDao listCartsBeanDao = DBMaster.getInstance(context).getListCartsBeanDao();
        for (CartsBean inner:cartsBeans) {
            List<ListCartsBean> list = listCartsBeanDao.queryBuilder().where(ListCartsBeanDao.Properties.Sellerid.eq(inner.getSellerid())).build().list();
            if (list != null) {
                Log.e(TAG, "getDataForDao: 磁盘数据已经拿到!" );
                inner.setList(list);
            }
        }
        if (cartsBeans!=null){
            if (cartsBeans.size()>0){
                MyApp.getInstance().setCartsBeans(cartsBeans);
            }
        }
        f4Pre.getData(cartsBeans);
        if (NetWorkUtil.getNetWork(context)!=-1){
            onGet(params);
        }
    }

    public void onGet(Map<String,String> params){
        RxRetrofitUtil.doGet().getCarts(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<BaseBean<List<CartsBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<CartsBean>> listBaseBean) {
                        List<CartsBean> data = listBaseBean.getData();
                        if (data != null) {
                            CartsBeanDao cartsBeanDao = DBMaster.getInstance(context).getCartsBeanDao();
                            cartsBeanDao.insertOrReplaceInTx(data);
                            for (CartsBean inner:data) {
                                List<ListCartsBean> list = inner.getList();
                                if (list != null) {
                                    ListCartsBeanDao listCartsBeanDao = DBMaster.getInstance(context).getListCartsBeanDao();
                                    listCartsBeanDao.insertOrReplaceInTx(list);
                                }
                            }
                            if (data.size()>0){
                                MyApp.getInstance().setCartsBeans(data);
                            }
                        }
                        f4Pre.getData(data);
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
