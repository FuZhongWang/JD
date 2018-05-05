package xudeyang.bawie.com.jd.presenter;

import java.util.Map;

import xudeyang.bawie.com.jd.model.LoginModel;
import xudeyang.bawie.com.jd.view.Iview.ToBack;

/**
 * Created by Mac on 2018/4/10.
 */

public class LoginPrensenter  implements LoginInterPre {
    ToBack toBack;
    LoginModel loginModel;
    public  LoginPrensenter(ToBack toBack){
        this.toBack=toBack;
        loginModel=new LoginModel();
    }

    @Override
    public void suceuss(String result) {
        toBack.toBack(result);
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
