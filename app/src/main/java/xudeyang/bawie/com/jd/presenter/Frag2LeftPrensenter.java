package xudeyang.bawie.com.jd.presenter;

import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.Map;

import xudeyang.bawie.com.jd.bean.Frag2LeftBean;
import xudeyang.bawie.com.jd.bean.Frag2RightBean;
import xudeyang.bawie.com.jd.model.F2LeftModel;
import xudeyang.bawie.com.jd.model.LoginModel;
import xudeyang.bawie.com.jd.view.Iview.ToFrag1GridBack;
import xudeyang.bawie.com.jd.view.Iview.ToFrag2LeftBack;

/**
 * Created by Mac on 2018/4/10.
 */

public class Frag2LeftPrensenter implements F2Pre<List<Frag2LeftBean>> {
    ToFrag2LeftBack toFrag2LeftBack;
    Context context;
    public Frag2LeftPrensenter(ToFrag2LeftBack toFrag2LeftBack, Context context){
        this.toFrag2LeftBack=toFrag2LeftBack;
        this.context=context;

    }

    @Override
    public void suceuss(List<Frag2LeftBean> result) {
        toFrag2LeftBack.toFrag2LeftBack(result);
    }

    @Override
    public void error(Exception e) {

    }

    @Override
    public void toUrl() {

        F2LeftModel f2LeftModel = new F2LeftModel(context, this);
        f2LeftModel.getData();
    }

    @Override
    public void toUrlPost(String url) {

    }

}
