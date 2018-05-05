package xudeyang.bawie.com.jd.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import xudeyang.bawie.com.jd.helper.EventNetWork;
import xudeyang.bawie.com.jd.utils.NetWorkUtil;

/**
 * Created by Mac on 2018/4/19.
 */

public class MyNetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int netWork = NetWorkUtil.getNetWork(context);
        if (netWork==-1){
            Toast.makeText(context, "网络连接似乎没有了~", Toast.LENGTH_LONG).show();
            EventNetWork eventNetWork = new EventNetWork();
            eventNetWork.setType(-1);
            EventBus.getDefault().post(eventNetWork);
        }else {
            EventNetWork eventNetWork = new EventNetWork();
            eventNetWork.setType(1);
            EventBus.getDefault().post(eventNetWork);
        }


    }
}
