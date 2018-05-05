package xudeyang.bawie.com.jd.model;

import android.util.Log;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xudeyang.bawie.com.jd.bean.Frag2RightBean;
import xudeyang.bawie.com.jd.bean.ShopBean;
import xudeyang.bawie.com.jd.presenter.ShopPre;
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.view.base.BaseBean;

/**
 * Created by Mac on 2018/4/17.
 */

public class ShopModel {
    public void toMap(String jie,Map<String,String> map, final ShopPre shopPre){
        RxRetrofitUtil.doGet().getShop(jie,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<BaseBean<List<ShopBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<ShopBean>> listBaseBean) {
                        Log.e("shoppppp","-----------"+listBaseBean.getCode());
                        shopPre.success(listBaseBean.getData());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("shopcccccc","-----------"+t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
