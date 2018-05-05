package xudeyang.bawie.com.jd.helper;

import android.app.Application;
import android.content.IntentFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xudeyang.bawie.com.jd.bean.CartsBean;
import xudeyang.bawie.com.jd.bean.Frag2LeftBean;
import xudeyang.bawie.com.jd.bean.Frag2RightBean;
import xudeyang.bawie.com.jd.utils.MyNetworkReceiver;
import xudeyang.bawie.com.jd.utils.NetWorkUtil;

/**
 * Created by Mac on 2018/4/19.
 */

public class MyApp extends Application {
    private List<Frag2LeftBean> leftBeans;
    private Map<String,List<Frag2RightBean>> rightMap;
    private MyNetworkReceiver myNetworkReceiver;
    private List<CartsBean> cartsBeans;
    private static MyApp myApp;
    @Override
    public void onCreate() {
        super.onCreate();
        myApp=this;
        rightMap=new HashMap<>();
        myNetworkReceiver = new MyNetworkReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(myNetworkReceiver,intentFilter);
    }
    public static MyApp getInstance(){
        return myApp;
    }
    public  List<Frag2LeftBean> getLeftBeans() {
        return leftBeans;
    }

    public void setLeftBeans(List<Frag2LeftBean> leftBeans) {
        this.leftBeans = leftBeans;
    }
    //根据cid查出List集合
    public List<Frag2RightBean> getRightBeans(String cid) {
        if (rightMap .containsKey(cid)) {
            return rightMap.get(cid);
        }else{
            return null;
        }
    }
    //根据cid向内存中存储List集合
    public void setRightMap(String cid,List<Frag2RightBean> rightBeans) {
        rightMap.put(cid,rightBeans);
    }

    public List<CartsBean> getCartsBeans() {
        return cartsBeans;
    }

    public void setCartsBeans(List<CartsBean> cartsBeans) {
        this.cartsBeans = cartsBeans;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterReceiver(myNetworkReceiver);
    }
}
