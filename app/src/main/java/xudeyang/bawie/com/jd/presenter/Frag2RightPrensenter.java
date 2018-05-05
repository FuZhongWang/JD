package xudeyang.bawie.com.jd.presenter;

import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.Map;

import xudeyang.bawie.com.jd.bean.Frag2RightBean;
import xudeyang.bawie.com.jd.model.F2RightModel;
import xudeyang.bawie.com.jd.model.LoginModel;
import xudeyang.bawie.com.jd.view.Iview.ToFrag1GridBack;
import xudeyang.bawie.com.jd.view.Iview.ToFrag2RightBack;

/**
 * Created by Mac on 2018/4/10.
 */

public class Frag2RightPrensenter implements F2Pre<List<Frag2RightBean>> {
    private static final String TAG = "Frag2RightPrensenter";
    ToFrag2RightBack toFrag2RightBack;
    F2RightModel f2RightModel;
    Context context;
    public Frag2RightPrensenter(ToFrag2RightBack toFrag2RightBack, Context context){
        this.toFrag2RightBack=toFrag2RightBack;
        this.context=context;
        f2RightModel=new F2RightModel(context,this);
    }


    @Override
    public void suceuss(List<Frag2RightBean> result) {
        toFrag2RightBack.toFrag2RightBack(result);
    }

    @Override
    public void error(Exception e) {

    }

    @Override
    public void toUrl() {

    }

    @Override
    public void toUrlPost(String url) {
        f2RightModel.getData(url);
    }


}
