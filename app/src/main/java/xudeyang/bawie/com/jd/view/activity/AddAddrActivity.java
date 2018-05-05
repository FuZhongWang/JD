package xudeyang.bawie.com.jd.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.bean.China;
import xudeyang.bawie.com.jd.bean.ProvinceBean;
import xudeyang.bawie.com.jd.helper.EventAddAddr;
import xudeyang.bawie.com.jd.model.AddAddrModel;
import xudeyang.bawie.com.jd.view.Iview.ToAddAddrBack;
import xudeyang.bawie.com.jd.view.base.BaseActivity;
import xudeyang.bawie.com.jd.view.base.BaseBean;

public class AddAddrActivity extends BaseActivity implements ToAddAddrBack<BaseBean>{
    private static final String TAG = "AddAddrActivity";
    @BindView(R.id.add_addr_shouhuo)
    EditText addAddrShouhuo;
    @BindView(R.id.add_tel)
    EditText addTel;
    @BindView(R.id.add_diqu)
    RelativeLayout addDiqu;
    @BindView(R.id.add_addrs)
    EditText addAddrs;
    @BindView(R.id.add_diqu_tv)
    TextView addDiquTv;
    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<ArrayList<ArrayList<String>>>();
    private OptionsPickerView opv;
    private Map<String, String> map;

    @Override
    public void initView() {
        setContentView(R.layout.activity_add_addr);
        try {
            //解析json数据
            InputStream is = getResources().getAssets().open("city.json");

            int available = is.available();

            byte [] b=new byte[available];
            int read = is.read(b);
            //注意格式，utf-8 或者gbk  否则解析出来可能会出现乱码
            String json=new String(b,"GBK");

            System.out.println(json);

            Gson gson= new Gson();
            China china = gson.fromJson(json, China.class);
            ArrayList<China.Province> citylist = china.citylist;
            //======省级
            for (China.Province province: citylist) {
                String provinceName = province.p;
                Log.i(TAG, "initView: "+provinceName);
                // System.out.println("provinceName==="+provinceName);
                ArrayList<China.Province.Area> c = province.c;
                //选项1
                options1Items.add(provinceName);
                ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();
                //区级
                //选项2

                ArrayList<String> options2Items_01=new ArrayList<String>();
                if (c!=null) {
                    for (China.Province.Area area : c
                            ) {
                        //System.out.println("area------" + area.n + "------");

                        options2Items_01.add(area.n);
                        ArrayList<China.Province.Area.Street> a = area.a;
                        ArrayList<String> options3Items_01_01=new ArrayList<String>();

                        //县级
                        if (a!=null) {
                            for (China.Province.Area.Street street : a
                                    ) {
                                // System.out.println("street/////" + street.s);
                                options3Items_01_01.add(street.s);
                            }
                            options3Items_01.add(options3Items_01_01);
                        }else{
                            //县级为空的时候
                            options3Items_01_01.add("");
                            options3Items_01.add(options3Items_01_01);
                        }
                    }
                    options2Items.add(options2Items_01);
                }else{ //区级为空的时候 国外
                     options2Items_01.add("");
                     options2Items.add(options2Items_01); }
                options3Items.add(options3Items_01);
                ArrayList<String> options3Items_01_01=new ArrayList<String>();
                options3Items_01_01.add("");
                options3Items_01.add(options3Items_01_01);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回的分别是三个级别的选中位置

        OptionsPickerView.Builder build  = new OptionsPickerView.Builder(AddAddrActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1)
                        + options2Items.get(options1).get(options2)
                        + options3Items.get(options1).get(options2).get(options3);
                addDiquTv.setText(tx);
            }
        });

        opv = build.build();
        //三级联动效果
        opv.setPicker(options1Items, options2Items, options3Items);
    }

    @Override
    public void initHttp() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.add_addr_retur, R.id.add_diqu, R.id.add_addr_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_addr_retur:
                finish();
                break;
            case R.id.add_diqu:
                opv.show();
                break;
            case R.id.add_addr_add:
                SharedPreferences shared = getSharedPreferences("shared", MODE_PRIVATE);
                String uid = shared.getString("uid", "");
                String token = shared.getString("token", "");
                map = new HashMap<>();
                map.put("uid",uid);
                map.put("token",token);
                map.put("name",addAddrShouhuo.getText().toString());
                map.put("mobile",addTel.getText().toString());
                map.put("addr",addDiquTv.getText().toString()+addAddrs.getText().toString());
                //map.put("source","android");
                AddAddrModel.getData(map,this);
                break;
        }
    }

    @Override
    public void success(BaseBean data) {
        if (Integer.parseInt(data.getCode())==0){
            Toast.makeText(this, "添加收货地址成功", Toast.LENGTH_SHORT).show();
            EventAddAddr eventAddAddr=new EventAddAddr();
            eventAddAddr.setCode(1);
            EventBus.getDefault().post(eventAddAddr);
            finish();
        }
    }
}
