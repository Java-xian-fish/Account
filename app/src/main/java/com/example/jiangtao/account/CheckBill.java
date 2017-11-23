package com.example.jiangtao.account;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import DBOperate.PayOutManager;

/**
 * Created by jiangtao on 2017/11/21.
 */

public class CheckBill extends RelativeLayout implements View.OnClickListener{//查账用的

    AppCompatButton button;
    TextView text;
    Context context;
    private void initView(Context context) {
        View inflate;
        inflate = View.inflate(context, R.layout.layout_check_bill,CheckBill.this);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        text = findViewById(R.id.text);
        text.setText("weqweqwwwwwwwwwwq" + "\n"+ "eqweqweqweqweqwe");
    }
    public CheckBill(Context context) {
        super(context);
        initView(context);
        this.context = context;
    }

    public CheckBill(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        this.context = context;
    }

    public CheckBill(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        this.context = context;
    }

    public CheckBill(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button){

            ArrayList<String> str = new PayOutManager(context,2017).selectAll();
            String aa  = "";
            Iterator it = str.iterator();int i =0;
            while(it.hasNext()){
                Log.e("*********进循环了",""+i++);
                aa += it.next() + "\n";
            }
            text.setText(aa);
        }
    }
}
