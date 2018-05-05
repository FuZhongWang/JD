package xudeyang.bawie.com.jd.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Forget on 2018/04/11.
 */

public class BannerImage extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        String imgjson = (String) path;
        Glide.with(context).load(imgjson).into(imageView);
       // com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(imgjson,imageView);
    }
}
