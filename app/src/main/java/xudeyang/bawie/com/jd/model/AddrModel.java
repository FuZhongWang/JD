package xudeyang.bawie.com.jd.model;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xudeyang.bawie.com.jd.bean.AddrBean;
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.view.Iview.ToAddrBack;
import xudeyang.bawie.com.jd.view.base.BaseBean;

/**
 * Created by Mac on 2018/4/26.
 */

public class AddrModel {
    public static void getData(Map<String,String> map, final ToAddrBack toAddrBack){
        RxRetrofitUtil.doGet().getAddrs(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<BaseBean<List<AddrBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<AddrBean>> listBaseBean) {
                        toAddrBack.success(listBaseBean.getData());
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
