package xudeyang.bawie.com.jd.utils;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Mac on 2018/4/18.
 */

public class RxBus {
    private static volatile RxBus rxBus;
    private final Subject<Object,Object> bus;

    public RxBus() {
        bus=new SerializedSubject<>(PublishSubject.create());
    }
    //单例模式RxBus
    public static RxBus getDefault(){
        if (rxBus == null) {
            synchronized (RxBus.class){
                if (rxBus == null) {
                    rxBus=new RxBus();
                }
            }
        }
        return rxBus;
    }
    // 发送一个新的事件
    public void post (Object o){
        bus.onNext(o);
    }
    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> tObservable(Class<T> eventType){
        return bus.ofType(eventType);
    }

}
