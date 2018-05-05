package xudeyang.bawie.com.jd.presenter;

import java.util.List;
import java.util.Map;

import xudeyang.bawie.com.jd.bean.ShopBean;

/**
 * Created by Mac on 2018/4/16.
 */

public interface ShopPre {
    void success(List<ShopBean> o);
    void tomap(String jie,Map<String,String> map);
}
