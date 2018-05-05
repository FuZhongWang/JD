package xudeyang.bawie.com.jd.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xudeyang.bawie.com.jd.bean.CartsBean;
import xudeyang.bawie.com.jd.model.F4Model;
import xudeyang.bawie.com.jd.utils.NetWorkUtil;
import xudeyang.bawie.com.jd.view.Iview.ToF4CartsBack;

/**
 * Created by Mac on 2018/4/21.
 */

public class F4Presenter implements F4Pre<List<CartsBean>>{
    private F4Model f4Model;
    private SharedPreferences shared;
    private Context context;
    private Map<String, String> map;
    private ToF4CartsBack toF4CartsBack;
    public F4Presenter(Context context, ToF4CartsBack toF4CartsBack) {
        this.context=context;
        this.toF4CartsBack=toF4CartsBack;
        f4Model=new F4Model(context,this);
        map = new HashMap<>();
        shared = context.getSharedPreferences("shared", Context.MODE_PRIVATE);
    }
    public void toModel(){
        map.clear();
        map.put("uid",shared.getString("uid", ""));
        map.put("token",shared.getString("token", ""));
        map.put("source","android");
        f4Model.onGet(map);
    }
    public void getData(List<CartsBean> data){
        toF4CartsBack.toBack(data);
    }
}
