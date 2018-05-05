package xudeyang.bawie.com.jd.utils;

import android.os.Environment;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Mac on 2018/3/9.
 */

public class OkHttpUtils {
    private OkHttpUtils(){};
    static OkHttpClient client;
    public static OkHttpClient getInstance(){
        if (client == null) {
            synchronized (OkHttpUtils.class){
                if (client == null) {
                File file=new File(Environment.getExternalStorageDirectory(),"cacah");
                client=new OkHttpClient().newBuilder()
                        .cache(new Cache(file,10*1024))
                        .connectTimeout(3000, TimeUnit.SECONDS)
                        .readTimeout(3000, TimeUnit.SECONDS)
                        .writeTimeout(3000, TimeUnit.SECONDS)
                        .addInterceptor(new LoggerInterceptor())
                        .build();
                }
            }
        }
        return client;
    }
    public static void OnGet(String url, Callback callback){
        OkHttpClient instance = getInstance();
        Request request =new Request.Builder()
                .url(url)
                .header("User-Agent", "OkHttp Example")
                .build();
        instance.newCall(request).enqueue(callback);
    }
    public static void OnPost(String url, Map<String,String> params , Callback callback){
        OkHttpClient instance = getInstance();
        FormBody.Builder form=new FormBody.Builder();

        for (String in:params.keySet()) {
            form.add(in,params.get(in));
        }

        Request request =new Request.Builder()
                .post(form.build())
                .header("User-Agent", "OkHttp Example")
                .url(url)
                .build();
        instance.newCall(request).enqueue(callback);

    }
    public static void upImager(String url,File file,String filename,Map<String,String> params , Callback callback){
        OkHttpClient instance = getInstance();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (params!=null){
            for (String in:params.keySet()) {
                builder.addFormDataPart(in,params.get(in));
            }
        }
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("file",filename, RequestBody.create(MediaType.parse("application/octet-stream"),file));
        Request request=new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        instance.newCall(request).enqueue(callback);
    }


}
