package xudeyang.bawie.com.jd.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xudeyang.bawie.com.jd.R;
import xudeyang.bawie.com.jd.bean.CartsBean;
import xudeyang.bawie.com.jd.bean.ListCartsBean;
import xudeyang.bawie.com.jd.helper.AllUpdateChecked;
import xudeyang.bawie.com.jd.helper.EventAllChecked;
import xudeyang.bawie.com.jd.helper.EventAllUpdate;
import xudeyang.bawie.com.jd.helper.EventFragTwoLeft;
import xudeyang.bawie.com.jd.helper.PriceAndCountEvent;
import xudeyang.bawie.com.jd.model.UpdateCarts;
import xudeyang.bawie.com.jd.utils.CountView;

/**
 * Created by Mac on 2018/4/21.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "ExpandableAdapter";
    private List<CartsBean> datas;
    private List<List<ListCartsBean>> cdatas;
    private Context context;
    private String token;
    private String uid;
    public ExpandableAdapter(Context context, List<CartsBean> datas, List<List<ListCartsBean>> cdatas,String token,String uid){
        this.context=context;
        this.datas=datas;
        this.token=token;
        this.cdatas=cdatas;
        this.uid=uid;
    }
    @Override
    public int getGroupCount() {
        return datas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return cdatas.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return datas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return cdatas.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final groupVH gvh;
        if (convertView==null){
            gvh=new groupVH();
            convertView=View.inflate(context, R.layout.exparentlist,null);
            gvh.parent_cb=convertView.findViewById(R.id.parent_cb);
            gvh.parent_tv=convertView.findViewById(R.id.parent_tv);
            convertView.setTag(gvh);
        }
        else {
            gvh = (groupVH) convertView.getTag();
        }
        gvh.parent_cb.setChecked(datas.get(groupPosition).getIsChecked());
        gvh.parent_tv.setText(datas.get(groupPosition).getSellerName());
        gvh.parent_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas.get(groupPosition).setIsChecked(gvh.parent_cb.isChecked());
                for (int i = 0; i < cdatas.get(groupPosition).size(); i++) {
                    cdatas.get(groupPosition).get(i).setCisChecked(gvh.parent_cb.isChecked());
                }
                changeAllState(isAllGroupSelect());
                EventBus.getDefault().post(computer());
                notifyDataSetChanged();
            }
        });
        EventBus.getDefault().post(computer());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ListCartsBean listCartsBean = cdatas.get(groupPosition).get(childPosition);
        final childVH cvh;
        if (convertView==null){
            cvh=new childVH();
            convertView=View.inflate(context, R.layout.exchildlist,null);
            cvh.child_cb=convertView.findViewById(R.id.child_cb);
            cvh.child_img=convertView.findViewById(R.id.child_img);
            cvh.child_tv=convertView.findViewById(R.id.child_tv);
            cvh.countView=convertView.findViewById(R.id.child_countView);
            cvh.price=convertView.findViewById(R.id.child_price);
            convertView.setTag(cvh);
        }
        else {
            cvh = (childVH) convertView.getTag();
        }

        Button jia = cvh.countView.findViewById(R.id.btn_increase);
        Button jian = cvh.countView.findViewById(R.id.btn_decrease);
        final TextView tv = cvh.countView.findViewById(R.id.tv_number);

        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i2 =Integer.parseInt(tv.getText().toString());
                i2++;
                tv.setText(String.valueOf(i2));
                datas.get(groupPosition).getList().get(childPosition).setNum(i2);
                update(groupPosition,childPosition);
                EventBus.getDefault().post(computer());
            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i1 = Integer.parseInt(tv.getText().toString());
                if (i1> 1) {
                    i1--;
                    tv.setText(String.valueOf(i1));
                    datas.get(groupPosition).getList().get(childPosition).setNum(i1);
                }
                update(groupPosition,childPosition);
                EventBus.getDefault().post(computer());
            }
        });
        tv.setText(cdatas.get(groupPosition).get(childPosition).getNum()+"");
        cvh.child_cb.setChecked(datas.get(groupPosition).getList().get(childPosition).getCisChecked());
        cvh.child_tv.setText(datas.get(groupPosition).getList().get(childPosition).getTitle()+"");
        cvh.price.setText("￥"+datas.get(groupPosition).getList().get(childPosition).getPrice());
        String images = datas.get(groupPosition).getList().get(childPosition).getImages();
        String[] sp=images.split("\\|");
        Glide.with(context).load(sp[0]).into(cvh.child_img);
        if (isAllChildCbSelected(groupPosition)){
            datas.get(groupPosition).setIsChecked(true);
        }
        cvh.child_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listCartsBean.setCisChecked(cvh.child_cb.isChecked());
                EventBus.getDefault().post(computer());
                if (cvh.child_cb.isChecked()){
                    if (isAllChildCbSelected(groupPosition)){
                        datas.get(groupPosition).setIsChecked(true);
                        changeAllState(isAllGroupSelect());
                    }
                }else{
                    datas.get(groupPosition).setIsChecked(false);
                    changeAllState(isAllGroupSelect());
                }
                update(groupPosition,childPosition);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    //判断一级列表是否全部选中
    public boolean isAllGroupSelect() {
        for (int i = 0; i < datas.size(); i++) {
            if (!datas.get(i).getIsChecked()) {
                return false;
            }
        }
        return true;
    }

    private void changeAllState(boolean bb) {
        EventAllChecked eventAllChecked=new EventAllChecked();
        eventAllChecked.setChecked(bb);
        EventBus.getDefault().post(eventAllChecked);
    }

    //判断二级列表是否全部选中
    private boolean isAllChildCbSelected(int groupPosition) {
        List<ListCartsBean> list = datas.get(groupPosition).getList();
        for (int j = 0; j < list.size(); j++) {
            ListCartsBean listCartsBean = list.get(j);
            if (!listCartsBean.getCisChecked()) {
                return false;
            }
        }
        return true;
    }

    public void update(int groupPosition,int childPosition){
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("sellerid",datas.get(groupPosition).getSellerid()+"");
        map.put("selected",(datas.get(groupPosition).getList().get(childPosition).getCisChecked()?1:0)+"");
        map.put("pid",cdatas.get(groupPosition).get(childPosition).getPid()+"");
        map.put("num",cdatas.get(groupPosition).get(childPosition).getNum()+"");
        map.put("token",token);
        UpdateCarts.onUpdateCarts(map,context);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class groupVH {
        CheckBox parent_cb;
        TextView parent_tv;
    }

    class childVH {
        CheckBox child_cb;
        ImageView child_img;
        TextView child_tv;
        TextView price;
        CountView countView;
    }
    public void changeAllListCbState(boolean cc){
        for (int i = 0; i < datas.size(); i++) {
            datas.get(i).setIsChecked(cc);
            for (int j = 0; j < cdatas.get(i).size(); j++) {
                cdatas.get(i).get(j).setCisChecked(cc);
            }
        }
        AllUpdateChecked allUpdateChecked = new AllUpdateChecked(datas);
        allUpdateChecked.forData(uid,token);
        EventBus.getDefault().post(computer());
        EventBus.getDefault().post(new EventAllUpdate(3));
        notifyDataSetChanged();
    }
    //计算列表的价钱
    private PriceAndCountEvent computer() {
        int count = 0;
        int price = 0;
        int to = 0;
        for (int i = 0; i < cdatas.size(); i++) {
            List<ListCartsBean> listCartsBeans = cdatas.get(i);
            for (int j = 0; j < listCartsBeans.size(); j++) {
                ListCartsBean listCartsBean = listCartsBeans.get(j);
                if (listCartsBean.getCisChecked()) {
                    price += listCartsBean.getNum() * listCartsBean.getPrice();
                    count += listCartsBean.getNum();
                    to += listCartsBean.getNum();
                }
            }
        }
        PriceAndCountEvent priceAndCountEvent = new PriceAndCountEvent();
        priceAndCountEvent.setCount(count);
        priceAndCountEvent.setPrice(price);
        priceAndCountEvent.setTo(to);
        return priceAndCountEvent;
    }

}
