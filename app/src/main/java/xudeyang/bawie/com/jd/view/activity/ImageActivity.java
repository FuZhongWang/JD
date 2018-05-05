package xudeyang.bawie.com.jd.view.activity;

import android.view.View;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.helper.EventFragTwoLeft;
import xudeyang.bawie.com.jd.helper.EventImage;
import xudeyang.bawie.com.jd.view.base.BaseActivity;

public class ImageActivity extends BaseActivity {


    private PhotoView img;

    @Override
    public void initView() {
        setContentView(R.layout.activity_image);
        img = findViewById(R.id.img_img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initHttp() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(sticky = true)
    public void onEvent(EventImage eventImage){
        String name = eventImage.getName();
        Glide.with(this).load(name).into(img);
    }
}
