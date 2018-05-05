package xudeyang.bawie.com.jd.helper;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xudeyang.bawie.com.jd.bean.CartsBean;
import xudeyang.bawie.com.jd.bean.ListCartsBean;
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.view.base.BaseBean;

/**
 * Created by Mac on 2018/4/24.
 */

public class AllDeleteChecked {
    private static final String TAG = "AllDeleteChecked";
    private int a=0;
    private int b=0;
    private List<List<ListCartsBean>> datas;
    public AllDeleteChecked(List<List<ListCartsBean>> datas){
        this.datas=datas;
    }
    public  void forData(final String uid, final String token){
        if (datas.get(a).size()==0) {
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("pid",datas.get(a).get(0).getPid()+"");
        map.put("token",token);
        RxRetrofitUtil.doGet().getDeleteCart(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        if (a<datas.size()-1){
                            a++;
                            forData(uid,token);
                        }else {
                            EventBus.getDefault().post(new EventAllDelete(4));
                        }
                    }
                });
        }else {
            Map<String,String> map=new HashMap<>();
            map.put("uid",uid);
            map.put("pid",datas.get(a).get(b).getPid()+"");
            map.put("token",token);
            RxRetrofitUtil.doGet().getDeleteCart(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSubscriber<BaseBean>() {
                        @Override
                        public void onNext(BaseBean baseBean) {

                        }

                        @Override
                        public void onError(Throwable t) {

                        }

                        @Override
                        public void onComplete() {
                            if (b<datas.get(a).size()){
                                forData(uid,token);
                                b++;
                            }else{
                                b=0;
                                if (a<datas.size()-1){
                                    a++;
                                    forData(uid,token);
                                }else {
                                    EventBus.getDefault().post(new EventAllDelete(4));
                                }
                            }
                        }
                    });
        }



    }

}
