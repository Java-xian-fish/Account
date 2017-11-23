package com.example.jiangtao.account;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by jiangtao on 2017/11/21.
 */

public class Graph extends RelativeLayout implements View.OnClickListener{

    private  int lastX;
    private  int lastY;
    TextView tb1;
    TextView tb2;
    TextView tb3;

    private void initView(Context context) {
        View inflate;
        inflate = View.inflate(context, R.layout.layout_graph,Graph.this);
    }

    public Graph(Context context) {
        super(context);
        initView(context);
    }

    public Graph(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public Graph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public Graph(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }



    /*public boolean dispatchTouchEvent(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                if(offsetX<-120){
                    Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    super.dispatchTouchEvent(event);
                }
                break;
        }
        return true;
    }*/

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}