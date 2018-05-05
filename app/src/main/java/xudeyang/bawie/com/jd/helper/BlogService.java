package xudeyang.bawie.com.jd.helper;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import xudeyang.bawie.com.jd.bean.AddrBean;
import xudeyang.bawie.com.jd.bean.CartsBean;
import xudeyang.bawie.com.jd.bean.DuowanVideoBean;
import xudeyang.bawie.com.jd.bean.Frag2LeftBean;
import xudeyang.bawie.com.jd.bean.Frag2RightBean;
import xudeyang.bawie.com.jd.bean.ListCartsBean;
import xudeyang.bawie.com.jd.bean.LoginBean;
import xudeyang.bawie.com.jd.bean.OrderBean;
import xudeyang.bawie.com.jd.bean.ShopBean;
import xudeyang.bawie.com.jd.bean.YunifangBean;
import xudeyang.bawie.com.jd.view.base.BaseBean;

/**
 * Created by Mac on 2018/4/13.
 */

public interface BlogService {
    @GET("product/getProductCatagory")
    Flowable<BaseBean<List<Frag2RightBean>>> getInfo(@Query("cid") String cid);
    //searchProducts
    @GET("product/{jie}")
    Flowable<BaseBean<List<ShopBean>>> getShop(@Path("jie")String jie, @QueryMap Map<String,String> params);

    @GET("product/getCatagory")
    Flowable<BaseBean<List<Frag2LeftBean>>> getCatagory();

    @GET("user/login")
    Flowable<BaseBean<LoginBean>> getLogin(@QueryMap Map<String,String> params);

    @GET("product/getCarts")
    Flowable<BaseBean<List<CartsBean>>> getCarts(@QueryMap Map<String,String> params);

    @GET("product/updateCarts")
    Flowable<BaseBean> getUpdateCarts(@QueryMap Map<String,String> params);

    @GET("product/addCart")
    Flowable<BaseBean> getAddCart(@QueryMap Map<String,String> params);

    @GET("product/deleteCart")
    Flowable<BaseBean> getDeleteCart(@QueryMap Map<String,String> params);

    @GET("http://api01.bitspaceman.com:8000/video/duowan?apikey=cDNvd2RnYsyPIMHsenHcq1uEcCXINyOmgGmanaUmZB3cWQuYABk3QQczSf3WWvD3&kw=搞笑")
    Flowable<DuowanVideoBean> getDuowanVideo();

    @GET("http://api01.bitspaceman.com:8000/post/duowan?apikey=cDNvd2RnYsyPIMHsenHcq1uEcCXINyOmgGmanaUmZB3cWQuYABk3QQczSf3WWvD3&kw=iPhone&catid=2509")
    Flowable<DuowanVideoBean> getDuowanPost();

    @GET("http://result.eolinker.com/umIPmfS6c83237d9c70c7c9510c9b0f97171a308d13b611?uri=homepage")
    Flowable<YunifangBean> getYunnifang();

    @GET("product/getOrders")
    Flowable<BaseBean<List<OrderBean>>> getOrders(@QueryMap Map<String,String> params);

    @GET("product/createOrder")
    Flowable<BaseBean> getCreateOrder(@QueryMap Map<String,String> params);

    @GET("user/getAddrs")
    Flowable<BaseBean<List<AddrBean>>> getAddrs(@QueryMap Map<String,String> params);

    @GET("user/addAddr")
    Flowable<BaseBean> getAddAddr(@QueryMap Map<String,String> params);

    @GET("user/setAddr")
    Flowable<BaseBean> getUserupdateAddr(@QueryMap Map<String,String> params);
}
