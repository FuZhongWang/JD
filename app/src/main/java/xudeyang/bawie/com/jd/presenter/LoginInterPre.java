package xudeyang.bawie.com.jd.presenter;

import java.util.Map;

/**
 * Created by Mac on 2018/4/11.
 */

public interface LoginInterPre {
    void suceuss(String result);
    void error(Exception e);
    void toUrl(String url);
    void toUrlPost(String url,Map<String,String> params);
}
