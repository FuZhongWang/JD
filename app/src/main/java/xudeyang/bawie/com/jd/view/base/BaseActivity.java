package xudeyang.bawie.com.jd.view.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Stack;

import butterknife.ButterKnife;
import hei.permission.PermissionActivity;
import xudeyang.bawie.com.jd.utils.NetWorkUtil;

/**
 * Created by Mac on 2018/4/9.
 */

public abstract class BaseActivity extends PermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        // 系统状态栏透明
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        int netWork = NetWorkUtil.getNetWork(BaseActivity.this);

        initView();
        initData();
        initHttp();
    }



    public abstract void initView();

    public abstract void initHttp();

    public abstract void initData();

    public void openActivity(Class<?> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(this, targetActivityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void openActivity(Class<?> targetActivityClass) {
        openActivity(targetActivityClass, null);
    }

    private Fragment currentFragment;
    /** Fragment替换(核心为隐藏当前的,显示现在的,用过的将不会destrory与create) */


    public void smartFragmentReplace(int target, Fragment toFragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        // 如有当前在使用的->隐藏当前的
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        String toClassName = toFragment.getClass().getSimpleName();
        // toFragment之前添加使用过->显示出来
        if (manager.findFragmentByTag(toClassName) != null) {
            transaction.show(toFragment);
        } else {// toFragment还没添加使用过->添加上去
            transaction.add(target, toFragment, toClassName);
        }
        transaction.commit();
        // toFragment更新为当前的
        currentFragment = toFragment;
    }
}
