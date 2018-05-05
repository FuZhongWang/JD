package xudeyang.bawie.com.jd.model;

import android.util.Log;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.view.Iview.ToAddAddrBack;
import xudeyang.bawie.com.jd.view.Iview.ToUpdateAddrBack;
import xudeyang.bawie.com.jd.view.base.BaseBean;

/**
 * Created by Mac on 2018/4/27.
 */

public class UpdateAddrModel {
    private static final String TAG = "UpdateAddrModel";
    public static void getData(Map<String,String> map, final ToUpdateAddrBack toAddAddrBack){
        RxRetrofitUtil.doGet().getUserupdateAddr(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        Log.i(TAG, "onNext: "+baseBean.getCode());
                        toAddAddrBack.success(baseBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "onError: "+t.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
