package xudeyang.bawie.com.jd.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.zxing.activity.CaptureActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.view.Fragment.Fragment_1;
import xudeyang.bawie.com.jd.view.Fragment.Fragment_2;
import xudeyang.bawie.com.jd.view.Fragment.Fragment_3;
import xudeyang.bawie.com.jd.view.Fragment.Fragment_4;
import xudeyang.bawie.com.jd.view.Fragment.Fragment_5;
import xudeyang.bawie.com.jd.view.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private final static int REQ_CODE = 1028;
    private Button btn1, btn2, btn3, btn4, btn5;
    private FrameLayout fl;
    private Fragment_1 f1;
    private Fragment_2 f2;
    private Fragment_3 f3;
    private Fragment_4 f4;
    private Fragment_5 f5;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        f1 = new Fragment_1();
        f2 = new Fragment_2();
        f3 = new Fragment_3();
        f4 = new Fragment_4();
        f5 = new Fragment_5();
        smartFragmentReplace(R.id.fl, f1);
    }

    @Override
    public void initHttp() {

    }

    @Override
    public void initData() {

    }



    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                smartFragmentReplace(R.id.fl, f1);
                break;
            case R.id.btn2:
                smartFragmentReplace(R.id.fl, f2);
                break;
            case R.id.btn3:
                smartFragmentReplace(R.id.fl, f3);
                break;
            case R.id.btn4:
                smartFragmentReplace(R.id.fl, f4);
                break;
            case R.id.btn5:
                smartFragmentReplace(R.id.fl, f5);
                break;
        }
    }


}
