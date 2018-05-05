package xudeyang.bawie.com.jd.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xudeyang.bawie.com.jd.bean.Frag2LeftBean;
import xudeyang.bawie.com.jd.bean.LoginBean;
import xudeyang.bawie.com.jd.presenter.RegisterPre;
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.view.base.BaseBean;

/**
 * Created by Mac on 2018/4/20.
 */

public class RegisterModel {
    private static final String TAG = "RegisterModel";
    private Context context;
    private RegisterPre registerPre;
    public RegisterModel(Context context, RegisterPre registerPre){
        this.context=context;
        this.registerPre=registerPre;
    }
    public void doGet(Map<String,String> params){
        RxRetrofitUtil.doGet().getLogin(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<BaseBean<LoginBean>>() {
                    @Override
                    public void onNext(BaseBean<LoginBean> LoginBean) {
                        SharedPreferences shared = context.getSharedPreferences("shared", context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = shared.edit();
                        BaseBean<LoginBean> loginBean = LoginBean;
                        String code = loginBean.getCode();
                        LoginBean data = loginBean.getData();
                        if (Integer.parseInt(code)==0){
                            edit.putBoolean("is",true);
                            edit.putString("uid",data.getUid()+"");
                            edit.putString("nickname",data.getNickname()+"");
                            edit.putString("mobile",data.getMobile()+"");
                            edit.putString("icon",data.getIcon());
                            edit.putString("token",data.getToken());
                            edit.commit();
                        }
                        registerPre.success(loginBean.getCode(),data.getNickname(),data.getIcon());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "onError: "+t );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
