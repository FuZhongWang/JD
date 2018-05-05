package xudeyang.bawie.com.jd.utils;

import android.content.Context;

import xudeyang.bawie.com.jd.bean.CartsBeanDao;
import xudeyang.bawie.com.jd.bean.DaoMaster;
import xudeyang.bawie.com.jd.bean.DaoSession;
import xudeyang.bawie.com.jd.bean.Frag2LeftBeanDao;
import xudeyang.bawie.com.jd.bean.Frag2RightBeanDao;
import xudeyang.bawie.com.jd.bean.ListBeanDao;
import xudeyang.bawie.com.jd.bean.ListCartsBeanDao;

/**
 * Created by Mac on 2018/4/19.
 */

public class DBMaster {
    private volatile static DBMaster dbMaster;
    private static String DB_NAME="DB.db";
    private final DaoSession daoSession;

    private DBMaster(Context context){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }
    public static DBMaster getInstance(Context context){
        if (dbMaster == null) {
            synchronized (DBMaster.class){
                if (dbMaster == null) {
                    dbMaster=new DBMaster(context);
                }
            }
        }
        return dbMaster;
    }
    public Frag2LeftBeanDao getfrag2LeftBeanDao(){
        return daoSession.getFrag2LeftBeanDao();
    }
    public Frag2RightBeanDao getFrag2RightBeanDao(){
        return daoSession.getFrag2RightBeanDao();
    }
    public ListBeanDao getListBeanDao(){return daoSession.getListBeanDao();}
    public CartsBeanDao getCartsBeanDao(){return daoSession.getCartsBeanDao();}
    public ListCartsBeanDao getListCartsBeanDao(){return daoSession.getListCartsBeanDao();}
}
