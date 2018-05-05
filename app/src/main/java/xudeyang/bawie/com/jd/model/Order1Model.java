package xudeyang.bawie.com.jd.model;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xudeyang.bawie.com.jd.bean.OrderBean;
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.view.Iview.ToOrder1View;
import xudeyang.bawie.com.jd.view.base.BaseBean;

/**
 * Created by Mac on 2018/4/26.
 */

public class Order1Model {
    public static void getData(Map<String,String> params, final ToOrder1View toOrder1View){
        RxRetrofitUtil.doGet().getOrders(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<BaseBean<List<OrderBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<OrderBean>> listBaseBean) {
                        toOrder1View.success(listBaseBean.getData());
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
