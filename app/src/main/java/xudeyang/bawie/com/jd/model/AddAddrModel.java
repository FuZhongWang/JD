package xudeyang.bawie.com.jd.model;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.view.Iview.ToAddAddrBack;
import xudeyang.bawie.com.jd.view.base.BaseBean;

/**
 * Created by Mac on 2018/4/27.
 */

public class AddAddrModel {
    public static void getData(Map<String,String> map, final ToAddAddrBack toAddAddrBack){
        RxRetrofitUtil.doGet().getAddAddr(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        toAddAddrBack.success(baseBean);
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
