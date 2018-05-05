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
import xudeyang.bawie.com.jd.utils.RxRetrofitUtil;
import xudeyang.bawie.com.jd.view.base.BaseBean;

/**
 * Created by Mac on 2018/4/24.
 */

public class AllUpdateChecked {
    private int a=0;
    private int b=0;
    private List<CartsBean> datas;
    public AllUpdateChecked(List<CartsBean> datas){
        this.datas=datas;
    }
    public  void forData(final String uid, final String token){
        if (datas.get(a).getList().size()==0) {
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("sellerid",datas.get(a).getSellerid()+"");
        map.put("selected",(datas.get(a).getList().get(0).getCisChecked()?1:0)+"");
        map.put("pid",datas.get(a).getList().get(0).getPid()+"");
        map.put("num",datas.get(a).getList().get(0).getNum()+"");
        map.put("token",token);
        RxRetrofitUtil.doGet().getUpdateCarts(map)
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
                            EventBus.getDefault().post(new EventAllUpdate(4));
                        }
                    }
                });
        }else {
            Map<String,String> map=new HashMap<>();
            map.put("uid",uid);
            map.put("sellerid",datas.get(a).getSellerid()+"");
            map.put("selected",(datas.get(a).getList().get(b).getCisChecked()?1:0)+"");
            map.put("pid",datas.get(a).getList().get(b).getPid()+"");
            map.put("num",datas.get(a).getList().get(b).getNum()+"");
            map.put("token",token);
            RxRetrofitUtil.doGet().getUpdateCarts(map)
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
                            if (b<datas.get(a).getList().size()){
                                forData(uid,token);
                                b++;
                            }else{
                                b=0;
                                if (a<datas.size()-1){
                                    a++;
                                    forData(uid,token);
                                }else {
                                    EventBus.getDefault().post(new EventAllUpdate(4));
                                }
                            }
                        }
                    });
        }



    }

}
