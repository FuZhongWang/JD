package xudeyang.bawie.com.jd.view.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import xudeyang.bawie.com.jd.utils.NetWorkUtil;

/**
 * Created by Mac on 2018/4/10.
 */
public abstract class BaseFragment extends android.support.v4.app.Fragment{

    private View inflate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(bindLayout(), container, false);
        ButterKnife.bind(inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
            bindEvent();

    }
    /**
     * 通过ID查找控件
     *
     * @param viewId 控件资源ID
     * @param <VIEW> 泛型参数，查找得到的View
     * @return 找到了返回控件对象，否则返回null
     */
    final public <VIEW extends View> VIEW findViewById(@IdRes int viewId) {
        return (VIEW) inflate.findViewById(viewId);
    }

    //绑定视图
    protected abstract int bindLayout();
    //绑定组件
    protected abstract void initView();
    //操作数据
    protected abstract void initData();
    //设置监听
    @Subscribe
    protected abstract void bindEvent();


}
