package xudeyang.bawie.com.jd.presenter;

import java.util.Map;

/**
 * Created by Mac on 2018/4/11.
 */

public interface F2Pre<T> {
    void suceuss(T result);
    void error(Exception e);
    void toUrl();
    void toUrlPost(String url);
}
