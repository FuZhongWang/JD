package xudeyang.bawie.com.jd.presenter;

import java.util.Map;

import xudeyang.bawie.com.jd.model.LoginModel;
import xudeyang.bawie.com.jd.view.Iview.ToBack;
import xudeyang.bawie.com.jd.view.Iview.ToFrag1GridBack;

/**
 * Created by Mac on 2018/4/10.
 */

public class Frag1GridPrensenter implements LoginInterPre {
    ToFrag1GridBack toFrag1GridBack;
    LoginModel loginModel;
    public Frag1GridPrensenter(ToFrag1GridBack toFrag1GridBack){
        this.toFrag1GridBack=toFrag1GridBack;
        loginModel=new LoginModel();
    }

    @Override
    public void suceuss(String result) {
        toFrag1GridBack.toFrag1GridBack(result);
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
