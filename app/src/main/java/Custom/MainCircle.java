package Custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jiangtao on 2017/11/22.
 */


public class MainCircle extends View {

    private Canvas canvas;
    private int sizeX;
    private int sizeY;
    private Point center;//圆心
    private float radius2;//外半径
    private float startAngle;//

    public MainCircle(Context context) {
        super(context);
    }

    public MainCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MainCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas _canvas){//invalidate方法重绘
        this.canvas = _canvas;
        sizeX = getWidth();
        sizeY = getHeight();
        setData();
        drawCircle();
        Paint paint1 = new Paint();//画昨天和本周
        paint1.setTextAlign(Paint.Align.CENTER);
        paint1.setTextSize(40);
        paint1.setFlags(3);
        paint1.setColor(Color.BLACK);
        canvas.drawText("昨天",sizeX/10,23*sizeY/25,paint1);
        canvas.drawText("本周",sizeX*9/10,23*sizeY/25,paint1);
        paint1.setTextSize(70);
        paint1.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText(""+getYesterdayCost(),sizeX/10,23*sizeY/25-45,paint1);
        canvas.drawText(""+getThisWeekCost(),sizeX*9/10,23*sizeY/25-45,paint1);
        //写圆环里面的东西了
        Paint paint2 = new Paint();
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setTextSize(40);
        paint2.setFlags(3);
        paint2.setColor(Color.BLACK);
        canvas.drawText("今天总共用了",center.x,center.y-80,paint2);
        Paint paint3 = new Paint();
        paint3.setTextAlign(Paint.Align.RIGHT);
        paint3.setTextSize(100);
        paint3.setTypeface(Typeface.DEFAULT_BOLD);
        paint3.setColor(Color.BLACK);
        paint3.setFlags(3);
        canvas.drawText(""+getTodayCost()+"/",center.x+80,center.y+20,paint3);
        paint2.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(""+getBudget()+"元",center.x+80,center.y+20,paint2);
        if(getTodayCost()/getBudget()<0.5f){
            paint3.setColor(Color.argb(50,135,206,255));
        }else  if(getTodayCost()/getBudget()<1.0f){
            paint3.setColor(Color.argb(170,0,0,238));
        }else{
            paint3.setColor(Color.argb(255,255,48,48));
        }
        paint3.setTextAlign(Paint.Align.CENTER);
        paint3.setTextSize(70);
        canvas.drawText(""+getDigital(getTodayCost()/getBudget()*100,1)+"%",center.x,sizeY*47/50,paint3);
    }

    private void drawCircle(){
        RectF outsideRect = new RectF(center.x-radius2,center.y-radius2,center.x+radius2,center.y+radius2);
        final int division = 57;
        final float scale = 1.0f/3.0f;
        float costScale = getTodayCost()/getBudget();
        float sweep = (360 - startAngle*2) / division * scale;
        if(costScale>1.0f){//画红色的
            int redDivision = (int)(57*(costScale-1)/2);
            float start;
            for(int i=0;i<redDivision;i++){
                start = 90 + startAngle + (360 - startAngle*2) / division * i;
                Paint paint = new Paint();
                paint.setFlags(3);
                paint.setAntiAlias(true);
                paint.setStrokeWidth(3*sizeY/50);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.argb(255,255,48,48));
                canvas.drawArc(outsideRect,start,sweep,false,paint);
            }
            for(int i=redDivision-1;i<division;i++){
                start = 90 + startAngle + (360 - startAngle*2) / division * i;
                Paint paint = new Paint();
                paint.setFlags(3);
                paint.setAntiAlias(true);
                paint.setStrokeWidth(3*sizeY/50);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.argb(170,0,0,238));
                canvas.drawArc(outsideRect,start,sweep,false,paint);
            }
        }else{
            int blueDivision = (int)(57*costScale);
            float start;
            for(int i=0;i<blueDivision;i++){
                start = -270 + startAngle + (360 - startAngle*2) / division * i;
                Paint paint = new Paint();
                paint.setFlags(3);
                paint.setAntiAlias(true);
                paint.setStrokeWidth(3*sizeY/50);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.argb(170,0,0,238));
                canvas.drawColor(Color.TRANSPARENT);
                canvas.drawArc(outsideRect,start,sweep,false,paint);
            }
            for(int i=blueDivision-1;i<division;i++){
                start = -270 + startAngle + (360 - startAngle*2) / division * i;
                Paint paint = new Paint();
                paint.setFlags(3);
                paint.setAntiAlias(true);
                paint.setStrokeWidth(3*sizeY/50);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.argb(50,135,206,255));
                canvas.drawColor(Color.TRANSPARENT);
                canvas.drawArc(outsideRect,start,sweep,false,paint);
            }
        }
    }

    private void setData(){
        this.center = new Point(sizeX/2,sizeY*3/5);
        this.radius2 = 2*sizeY/5;
        this.startAngle = 78f/2;
    }

    private float getTodayCost(){//这四个方法全都要访问数据库
        return 20.0f;
    }

    private float getThisWeekCost(){
        return 100.0f;
    }

    private float getYesterdayCost(){
        return 23.0f;
    }

    private float getBudget(){
        return 30.0f;
    }

    public static float getDigital(float f,int post){
        return (float) (((int)(f*Math.pow(10,post)))/Math.pow(10,post));
    }
}
