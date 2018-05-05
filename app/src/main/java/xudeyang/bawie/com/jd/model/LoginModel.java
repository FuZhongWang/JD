package xudeyang.bawie.com.jd.model;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xudeyang.bawie.com.jd.bean.Frag2LeftBean;
import xudeyang.bawie.com.jd.helper.BlogService;
import xudeyang.bawie.com.jd.presenter.LoginInterPre;
import xudeyang.bawie.com.jd.utils.OkHttpUtils;
import xudeyang.bawie.com.jd.utils.UrlAddress;
import xudeyang.bawie.com.jd.view.base.BaseBean;

/**
 * Created by Mac on 2018/4/11.
 */

public class LoginModel implements LoginInterMod {
    @Override
    public void toUrl(String url, final LoginInterPre loginInterPre) {
        OkHttpUtils.OnGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                loginInterPre.error(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                loginInterPre.suceuss(string);
            }
        });
    }

    @Override
    public void toUrlPost(String url, Map<String, String> params, final LoginInterPre loginInterPre) {

    }
}
