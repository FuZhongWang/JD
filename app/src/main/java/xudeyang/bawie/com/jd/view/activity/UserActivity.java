package xudeyang.bawie.com.jd.view.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.helper.EventUser;
import xudeyang.bawie.com.jd.view.base.BaseActivity;

public class UserActivity extends BaseActivity implements View.OnClickListener{

    private ImageView userImg,fh;
    private Button zx;
    private SharedPreferences shared;
    private SharedPreferences.Editor edit;
    private TextView nickname;
    private RelativeLayout shouhuo;

    @Override
    public void initView() {
        setContentView(R.layout.activity_user);
        fh = findViewById(R.id.l3_fh);
        zx = findViewById(R.id.l3_zx);
        userImg= findViewById(R.id.l3_img);
        nickname = findViewById(R.id.l3_nickname);
        shouhuo = findViewById(R.id.l3__5);
        shouhuo.setOnClickListener(this);
        fh.setOnClickListener(this);
        zx.setOnClickListener(this);
    }

    @Override
    public void initHttp() {

    }

    @Override
    public void initData() {
        shared = getSharedPreferences("shared", MODE_PRIVATE);
        edit = shared.edit();
        String icon = shared.getString("icon", "");
        String nickname1 = shared.getString("nickname", "");
        nickname.setText(nickname1);
        Glide.with(this).load(icon).into(userImg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.l3_fh:
                finish();
                break;
            case R.id.l3_zx:
                EventUser eventUser = new EventUser(9);
                EventBus.getDefault().post(eventUser);
                edit.clear();
                edit.commit();
                finish();
                break;
            case R.id.l3__5:
                openActivity(AddrActivity.class);
                break;
        }
    }
}
