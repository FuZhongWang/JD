package xudeyang.bawie.com.jd.presenter;

import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import xudeyang.bawie.com.jd.model.RegisterModel;
import xudeyang.bawie.com.jd.view.Iview.ToRegisterBack;
import xudeyang.bawie.com.jd.view.activity.RegisterActivity;

/**
 * Created by Mac on 2018/4/20.
 */

public class RegisterPresenter implements RegisterPre{
    public static final String MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    public static final String PASSWORD = "^[a-zA-Z0-9]{6,20}$";
    private Context context;
    private  ToRegisterBack toRegisterBack;
    private RegisterModel registerModel;
    public RegisterPresenter(Context context, ToRegisterBack toRegisterBack){
        this.context=context;
        this.toRegisterBack=toRegisterBack;
        registerModel = new RegisterModel(context, this);
    }

    @Override
    public void success(String result,String nickname,String Icon) {
        toRegisterBack.toBackMsg(result,nickname, Icon);
    }

    @Override
    public void toParameter(String moblie, String password) {
        boolean mol = Pattern.matches(MOBILE, moblie);
        boolean pwd = Pattern.matches(PASSWORD, password);
        if (mol){
            if (pwd) {
                Map<String,String> map=new HashMap<>();
                map.put("mobile",moblie);
                map.put("password",password);
                registerModel.doGet(map);
            }else{
                Toast.makeText(context,"密码位数需在6-20之间",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(context,"手机号格式不正确",Toast.LENGTH_SHORT).show();
        }
    }
}
