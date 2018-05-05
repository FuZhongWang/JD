package xudeyang.bawie.com.jd.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Mac on 2018/4/19.
 */

public class NetWorkUtil {
    private static int Type=0;
    public static int getNetWork(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            //当前网络状态可用
            int netType = info.getType();
            if (netType == ConnectivityManager.TYPE_WIFI) {
                Log.i("NETSTATUE", "当前网络状态为-wifi");
                Type=1;
            } else if (netType == ConnectivityManager.TYPE_MOBILE) {
                Log.e("NETSTATUE", "当前网络状态为-mobile");
                Type=0;
            }
        } else {
            //当前网络不可用
            Log.e("NETSTATUE", "无网络连接");
            Type=-1;
        }
        return Type;
    }
}
