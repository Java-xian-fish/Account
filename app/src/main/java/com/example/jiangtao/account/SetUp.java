package com.example.jiangtao.account;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by jiangtao on 2017/11/21.
 */

public class SetUp extends RelativeLayout {
    private void initView(Context context) {
        View inflate;
        inflate = View.inflate(context, R.layout.layout_set_up, SetUp.this);
    }

    public SetUp(Context context) {
        super(context);
        initView(context);
    }

    public SetUp(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SetUp(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public SetUp(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }
}
