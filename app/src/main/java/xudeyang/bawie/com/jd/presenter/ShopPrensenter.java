package xudeyang.bawie.com.jd.presenter;

import java.util.List;
import java.util.Map;

import xudeyang.bawie.com.jd.bean.ShopBean;
import xudeyang.bawie.com.jd.model.ShopModel;
import xudeyang.bawie.com.jd.view.Iview.ToBackShop;

/**
 * Created by Mac on 2018/4/16.
 */

public class ShopPrensenter implements ShopPre{
    ShopModel shopModel;
    ToBackShop toBackShop;
    public ShopPrensenter (ToBackShop toBackShop){
        shopModel=new ShopModel();
        this.toBackShop=toBackShop;
    }

    @Override
    public void success(List<ShopBean> o) {
        if (toBackShop!=null) {
            toBackShop.toBackShop(o);
        }
    }

    @Override
    public void tomap(String jie,Map<String, String> map) {
        shopModel.toMap(jie,map,this);
    }
    public void ondestroy(){
        if (toBackShop!=null) {
            toBackShop = null;
        }
    }
}
