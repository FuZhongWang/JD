package xudeyang.bawie.com.jd.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import xudeyang.bawie.com.jd.R;

/**
 * Created by Administrator on 2017/5/4.
 */

public class CountView extends LinearLayout implements View.OnClickListener, TextWatcher {
    private static final String TAG = "CountView";
    private int number = 0;
    private int storage = 0;
    private Button mBtn_decrease;
    private Button mBtn_increase;
    private TextView mTv_number;
    private OnNumberChangeListener onNumberChangeListener;

    public CountView(Context context) {
        this(context, null);
    }

    public CountView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.countview, this);

        mBtn_decrease = (Button) findViewById(R.id.btn_decrease);
        mBtn_increase = (Button) findViewById(R.id.btn_increase);
        mTv_number = (TextView) findViewById(R.id.tv_number);

        mBtn_decrease.setOnClickListener(this);
        mBtn_increase.setOnClickListener(this);
        mTv_number.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_decrease:

                break;
            case R.id.btn_increase:

                break;
        }
        mTv_number.clearFocus();
        if (onNumberChangeListener != null) {
            onNumberChangeListener.onNumberChangeListener(this, number);
        }
    }

    public interface OnNumberChangeListener {
        void onNumberChangeListener(View view, int count);
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }
}
