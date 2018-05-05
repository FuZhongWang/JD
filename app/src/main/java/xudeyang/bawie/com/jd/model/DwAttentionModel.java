package xudeyang.bawie.com.jd.model;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xudeyang.bawie.com.jd.bean.DuowanVideoBean;
import xudeyang.bawie.com.jd.bean.YunifangBean;
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.view.Iview.ToF3AData;
import xudeyang.bawie.com.jd.view.Iview.ToF3BData;

/**
 * Created by Mac on 2018/4/25.
 */

public class DwAttentionModel {
    public static void getData(final ToF3AData toF3AData){
        RxRetrofitUtil.doGet().getYunnifang()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<YunifangBean>() {
                    @Override
                    public void onNext(YunifangBean yunifangBean) {
                        toF3AData.toback(yunifangBean.getData().getSubjects());
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
