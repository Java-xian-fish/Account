package Custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiangtao on 2017/11/21.
 */
public class GraphFirst extends View {

    int sizeX;
    int sizeY;
    Canvas canvas;
    double[] value;//

    public  GraphFirst(Context context) {
        super(context);
    }

    public  GraphFirst(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public  GraphFirst(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas _canvas){
        sizeX = getWidth();
        sizeY = getHeight();
        canvas = _canvas;
        Paint paint1 = new Paint();
        paint1.setColor(Color.argb(255,173,216,230));
        paint1.setTextSize(sizeY/8f-20);
        canvas.drawText("您今天一共用了：",30,sizeY/3f,paint1);
        Paint paint2 = new Paint();
        paint2.setColor(Color.argb(255,24,116,205));
        paint2.setTextSize(sizeY/3f);
        if(value==null){
            value = getValue();
        }
        canvas.drawText("" + value[0],(sizeY/8f-20)*9+30,sizeY/3f,paint2);
        paint1.setColor(Color.BLACK);
        canvas.drawText("本周：",30,sizeY/3f + sizeY/8f-20,paint1);
        canvas.drawText("本月：",30 + sizeX/4,sizeY/3f + sizeY/8f-20,paint1);
        canvas.drawText("预算：",30 + sizeX/2,sizeY/3f + sizeY/8f-20,paint1);
        canvas.drawText(""+value[1],30 ,sizeY/3f + (sizeY/8f-20)*2,paint1);
        canvas.drawText(""+value[2],30 + sizeX/4 ,sizeY/3f + (sizeY/8f-20)*2,paint1);
        canvas.drawText(""+value[3],30 + sizeX/2 ,sizeY/3f + (sizeY/8f-20)*2,paint1);
        SimpleDateFormat bartDateFormat = new SimpleDateFormat
                ("yyyy-mm-dd hh-mm");
        Date date = new Date();
        canvas.drawText(bartDateFormat.format(date).toString() + "更新 点击获取详细数据>",30  ,sizeY/3f + (sizeY/8f-20)*3,paint1);

        Paint end = new Paint();
        end.setColor(Color.BLACK);
        canvas.drawLine(0,sizeY-5,sizeX,sizeY-5,end);
    }
    @Override
    public  void draw(Canvas _canvas){
        super.draw(_canvas);
    }

    public void setValue(double[] _value){
        value = _value;
        draw(canvas);
    }

    private double[] getValue(){
        return new double[]{20,200,1400,+10};
    }
}
