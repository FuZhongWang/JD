package xudeyang.bawie.com.jd.utils;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Mac on 2018/3/30.
 */


 public   class LoggerInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Log.d(TAG,String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.d(TAG, String.format("Received response for %s in %.1fms%n%sconnection=%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers(), chain.connection()));

            return response;
        }
    }