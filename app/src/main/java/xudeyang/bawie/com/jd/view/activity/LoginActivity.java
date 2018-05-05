package xudeyang.bawie.com.jd.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.activity.CaptureActivity;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import hei.permission.PermissionActivity;
import io.reactivex.functions.Consumer;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.view.base.BaseActivity;


public class LoginActivity extends BaseActivity  {
    int a=2;

    private TextView tv,tv1;
    private Subscription subscribe;

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        tv = findViewById(R.id.login_tv);
        tv1 = findViewById(R.id.main_textview);

    }
    @Override
    public void initHttp() {

    }
    @Override
    public void initData() {

        checkPermission(new CheckPermListener() {
                            @Override
                            public void superPermission() {
                                subscribe = Observable.interval(0, 1, TimeUnit.SECONDS)
                                        .take(3)
                                        .subscribe(new Observer<Long>() {
                                            @Override
                                            public void onCompleted() {
                                                openActivity(MainActivity.class);
                                                finish();
                                            }
                                            @Override
                                            public void onError(Throwable e) {
                                            }
                                            @Override
                                            public void onNext(Long aLong) {
                                                tv.setText(a + "");
                                                a--;
                                            }
                                        });

                            }
                        },R.string.app_name,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        RxView.clicks(tv1).
                throttleFirst(1,TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        a=-1;
                        subscribe.unsubscribe();
                        openActivity(MainActivity.class);
                        finish();
                    }
                });
    }

}
