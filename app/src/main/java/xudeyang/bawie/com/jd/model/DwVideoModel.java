package xudeyang.bawie.com.jd.model;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xudeyang.bawie.com.jd.bean.DuowanVideoBean;
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.view.Iview.ToF3BData;

/**
 * Created by Mac on 2018/4/25.
 */

public class DwVideoModel {
    public static void getData(final ToF3BData toF3BData){
        RxRetrofitUtil.doGet().getDuowanVideo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<DuowanVideoBean>() {
                    @Override
                    public void onNext(DuowanVideoBean duowanVideoBean) {
                        List<DuowanVideoBean.DataBean> data = duowanVideoBean.getData();
                        toF3BData.toback(data);
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
