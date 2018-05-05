package xudeyang.bawie.com.jd.utils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xudeyang.bawie.com.jd.helper.BlogService;
import xudeyang.bawie.com.jd.view.base.BaseBean;

/**
 * Created by Mac on 2018/4/16.
 */

public class RxRetrofitUtil {
    private static final String LOGIN="https://www.zhaoapi.cn/";
    private static volatile RxRetrofitUtil rxRetrofitUtil;
    private static BlogService service;
    private static RxRetrofitUtil getInstance(){
        if (rxRetrofitUtil == null) {
            synchronized (RxRetrofitUtil.class){
                if (rxRetrofitUtil == null) {
                    rxRetrofitUtil = new RxRetrofitUtil();
                }
            }
        }
        return rxRetrofitUtil;
    }
    public static BlogService doGet(){
        if (service == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(3000, TimeUnit.SECONDS);
            builder.readTimeout(3000, TimeUnit.SECONDS);
            builder.writeTimeout(3000, TimeUnit.SECONDS);
            builder.addInterceptor(new HttpLoggingInterceptor());
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request build = request.newBuilder()
                            .header("source", "android")
                            .method(request.method(), request.body())
                            .build();
                    return chain.proceed(build);
                }
            });
            Retrofit build = new Retrofit.Builder()
                    .baseUrl(LOGIN)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            service = build.create(BlogService.class);
        }
        return service;
    }
}
