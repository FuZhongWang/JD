package xudeyang.bawie.com.jd.model;

import java.util.Map;

import xudeyang.bawie.com.jd.presenter.LoginInterPre;

/**
 * Created by Mac on 2018/4/11.
 */

public interface LoginInterMod {
    void toUrl(String url, LoginInterPre loginInterPre);
    void toUrlPost(String url, Map<String,String> params, LoginInterPre loginInterPre);
}
