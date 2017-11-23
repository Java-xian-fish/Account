package Custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jiangtao on 2017/11/21.
 */

public class GraphSecond extends View {

    Canvas canvas;
    int click=0;//代表点击的是哪部分。。分类优于时间
    int sizeX;
    int sizeY;
    private int lastX;
    private int lastY;
    TextView text;

    public GraphSecond(Context context) {
        super(context);
    }
    public GraphSecond(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public GraphSecond(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas _canvas){
        canvas = _canvas;
        sizeX = getWidth();
        sizeY = getHeight();
        Paint paint1 = new Paint();
        paint1.setColor(Color.BLACK);
        paint1.setTextSize(sizeY/3f);
        Paint paint2 = new Paint();
        paint2.setColor(Color.BLUE);
        paint2.setTextSize(sizeY/3f);
        if(click==0){
            canvas.drawText("7天",sizeX/4,2*sizeY/3,paint2);
            canvas.drawLine(30,2*sizeY/3+20,sizeX/4+sizeY/3f,2*sizeY/3+20,paint2);
            canvas.drawLine(sizeX/4+sizeY/6f,2*sizeY/3+20,sizeX/4+sizeY/6f+10,2*sizeY/3+5,paint2);
            canvas.drawLine(sizeX/4+sizeY/6f+10,2*sizeY/3+5,sizeX/4+sizeY/6f+20,2*sizeY/3+20,paint2);
            canvas.drawLine(sizeX/4+sizeY/6f+20,2*sizeY/3+20,3*sizeX/8+sizeY/6f,2*sizeY/3+20,paint2);
            canvas.drawLine(3*sizeX/8+sizeY/6f,2*sizeY/3+20,sizeX-30,2*sizeY/3+20,paint1);
            canvas.drawText("月",2*sizeX/4 ,2*sizeY/3,paint1);
            canvas.drawText("年", 3*sizeX/4,2*sizeY/3,paint1);
        }else if(click==1){
            canvas.drawText("7天",sizeX/4,2*sizeY/3,paint1);
            canvas.drawText("月",2*sizeX/4 ,2*sizeY/3,paint2);
            canvas.drawText("年", 3*sizeX/4,2*sizeY/3,paint1);
        }else{
            canvas.drawText("7天",sizeX/4,2*sizeY/3,paint1);
            canvas.drawText("月",2*sizeX/4 ,2*sizeY/3,paint1);
            canvas.drawText("年", 3*sizeX/4,2*sizeY/3,paint2);
        }
    }

    public boolean onTouchEvent(MotionEvent event){
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
                layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);
                //text.setText("移动的横向距离是：" + offsetX +"；；；纵向距离是：" + offsetY+"   右下角的坐标是（" + getRight()+"," + getBottom()+").");
                break;
        }
        return true;
    }

    public void setText(TextView _text){
        text = _text;
    }
}
