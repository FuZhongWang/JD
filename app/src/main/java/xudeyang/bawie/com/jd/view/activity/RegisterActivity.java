package xudeyang.bawie.com.jd.view.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.helper.EventRegister;
import xudeyang.bawie.com.jd.helper.EventUser;
import xudeyang.bawie.com.jd.presenter.RegisterPresenter;
import xudeyang.bawie.com.jd.view.Iview.ToRegisterBack;
import xudeyang.bawie.com.jd.view.base.BaseActivity;

public class RegisterActivity extends BaseActivity implements ToRegisterBack{


    EditText lname;
    EditText lpassword;
    @BindView(R.id.textView3)
    TextView textView3;

    @BindView(R.id.ldl)
    Button ldl;
    private RegisterPresenter presenter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
        lpassword=findViewById(R.id.lpassword);
        lname=findViewById(R.id.lname);
        presenter = new RegisterPresenter(this,this);
        lname.setText("15546692600");
    }

    @Override
    public void initHttp() {

    }

    @Override
    public void initData() {
        lpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //检测是否输入密码
                if (s.length() > 0) {
                    ldl.setEnabled(true);
                }else{
                    ldl.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.lbtn, R.id.ldl, R.id.lzc, R.id.lzh, R.id.imgwx, R.id.imgqq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lbtn:
                finish();
                break;
            case R.id.ldl:
                presenter.toParameter(lname.getText().toString(),lpassword.getText().toString());
                break;
            case R.id.lzc:

                break;
            case R.id.lzh:
                break;
            case R.id.imgwx:
                break;
            case R.id.imgqq:
                break;
        }
    }

    @Override
    public void toBackMsg(String code,String nickname,String Icon) {
        if (Integer.parseInt(code) == 0) {
            Toast.makeText(this, "登陆成功~", Toast.LENGTH_SHORT).show();
            finish();
            EventBus.getDefault().post(new EventUser(10));
            EventBus.getDefault().postSticky(new EventRegister(nickname,Icon));
        }else{
            Toast.makeText(this, "登录失败,请验证手机号或密码是否正确!", Toast.LENGTH_SHORT).show();
        }
    }
}
