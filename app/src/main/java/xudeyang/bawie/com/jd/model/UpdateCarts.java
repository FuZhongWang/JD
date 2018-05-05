package xudeyang.bawie.com.jd.model;

import android.content.Context;
import android.util.Log;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.view.base.BaseBean;

/**
 * Created by Mac on 2018/4/22.
 */

public class UpdateCarts {
    private static final String TAG = "UpdateCarts";
    public static void onUpdateCarts(Map<String,String> params, final Context context){
        RxRetrofitUtil.doGet().getUpdateCarts(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {

                        Log.e(TAG, "onNext: "+baseBean.getCode() );
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
