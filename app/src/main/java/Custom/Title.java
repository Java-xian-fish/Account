package Custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jiangtao on 2017/11/21.
 */

public class Title extends View {

    Canvas canvas;
    public static int sizeX;
    public static int sizeY;

    public Title(Context context) {
        super(context);
    }

    public Title(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public Title(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
    }

    protected void onDraw(Canvas _canvas){
        canvas = _canvas;
        sizeX = getWidth();
        sizeY = getHeight();
        Paint paint1 = new Paint();
        for(int i=0;i<10;i++){
            paint1.setColor(Color.argb(255,24,144,255-i*2));
            canvas.drawRect(new Rect(i*(sizeX/10),0,(i+1)*(sizeX/10),sizeY),paint1);
        }
        paintPart1();
        paintPart2();
    }

    private void paintPart1(){
        Paint paint2 = new Paint();
        paint2.setColor(Color.argb(169,255,255,255));
        float radious = sizeX/76f;
        float circleStart = sizeX/16f;
        float circleStartY = sizeY/4f;
        float circleSeparation = sizeY/4f;
        float roundStartX = circleStart +3f*radious;
        float RectRadious = radious/2f;
        float rectLength = sizeX/11f;
        canvas.drawCircle(circleStart,circleStartY,radious,paint2);
        canvas.drawCircle(sizeX-circleStart,circleStartY,radious,paint2);
        canvas.drawRoundRect(new RectF(roundStartX,(circleStartY-radious),(roundStartX+rectLength),(circleStartY+radious)),RectRadious,RectRadious,paint2);
        canvas.drawCircle(circleStart,circleStartY+ circleSeparation,radious,paint2);
        canvas.drawCircle(sizeX-circleStart,circleStartY+ circleSeparation,radious,paint2);
        canvas.drawRoundRect(new RectF(roundStartX,(circleStartY-radious+circleSeparation),(roundStartX+rectLength),(circleStartY+radious+circleSeparation)),RectRadious,RectRadious,paint2);
        canvas.drawCircle(circleStart,circleStartY+2*circleSeparation,radious,paint2);
        canvas.drawCircle(sizeX-circleStart,circleStartY+2*circleSeparation,radious,paint2);
        canvas.drawRoundRect(new RectF(roundStartX,(circleStartY-radious+2*circleSeparation),(roundStartX+rectLength),(circleStartY+radious+2*circleSeparation)),RectRadious,RectRadious,paint2);
        canvas.drawCircle(circleStart,circleStartY,radious,paint2);
        canvas.drawRoundRect(new RectF(roundStartX,(circleStartY-radious),(roundStartX+rectLength),(circleStartY+radious)),RectRadious,RectRadious,paint2);
        canvas.drawCircle(circleStart,circleStartY+ circleSeparation,radious,paint2);
        canvas.drawRoundRect(new RectF(roundStartX,(circleStartY-radious+circleSeparation),(roundStartX+rectLength),(circleStartY+radious+circleSeparation)),RectRadious,RectRadious,paint2);
        canvas.drawCircle(circleStart,circleStartY+2*circleSeparation,radious,paint2);
        canvas.drawRoundRect(new RectF(roundStartX,(circleStartY-radious+2*circleSeparation),(roundStartX+rectLength),(circleStartY+radious+2*circleSeparation)),RectRadious,RectRadious,paint2);
    }

    private  void paintPart2(){
        Paint paint = new Paint();
        Paint paint2 = new Paint();
        paint.setColor(Color.argb(169,0,0,255));
        paint2.setColor(Color.argb(169,255,255,255));
        float textSize = sizeY*2f/6f;
        float cheek = 10f;
        float bottom = sizeY*4/6f+2*cheek;
        float shifting = 20f;
        float allShiftX = textSize*2+2*cheek+shifting;
        float allShiftY = textSize+2*cheek;
        paint.setTextSize(textSize);
        float radious = sizeX/152f;
        canvas.drawRoundRect(new RectF(sizeX/2-allShiftX,bottom-allShiftY,sizeX/2-shifting,bottom),radious,radious,paint2);
        canvas.drawRoundRect(new RectF(sizeX/2+shifting,bottom-allShiftY,sizeX/2+allShiftX,bottom),radious,radious,paint2);
        canvas.drawRoundRect(new RectF(sizeX/2-allShiftX,bottom-allShiftY,sizeX/2-shifting,bottom),radious,radious,paint2);
        canvas.drawRoundRect(new RectF(sizeX/2+shifting,bottom-allShiftY,sizeX/2+allShiftX,bottom),radious,radious,paint2);
        canvas.drawText("收入", sizeX/2f-sizeY*4f/6f-shifting-cheek,sizeY*4/6f,paint);
        canvas.drawText("支出", sizeX/2f+shifting+cheek,sizeY*4/6f,paint);
    }

}
