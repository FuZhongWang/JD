package xudeyang.bawie.com.jd.presenter;

import java.util.Map;

import xudeyang.bawie.com.jd.model.LoginModel;
import xudeyang.bawie.com.jd.view.Iview.ToFrag1GridBack;
import xudeyang.bawie.com.jd.view.Iview.ToFrag1StagBack;

/**
 * Created by Mac on 2018/4/10.
 */

public class Frag1StagPrensenter implements LoginInterPre {
    ToFrag1StagBack toFrag1StagBack;
    LoginModel loginModel;
    public Frag1StagPrensenter(ToFrag1StagBack toFrag1StagBack){
        this.toFrag1StagBack=toFrag1StagBack;
        loginModel=new LoginModel();
    }

    @Override
    public void suceuss(String result) {
        toFrag1StagBack.toFrag1StagBack(result);
    }

    @Override
    public void error(Exception e) {

    }

    @Override
    public void toUrl(String url) {
        loginModel.toUrl(url,this);
    }

    @Override
    public void toUrlPost(String url, Map<String, String> params) {

    }

}
