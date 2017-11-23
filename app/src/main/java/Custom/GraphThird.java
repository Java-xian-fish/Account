package Custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jiangtao on 2017/11/21.
 */

public class GraphThird extends View {

    TextView text;
    TextView text2;
    private int lastX;
    private int lastY;
    private int sizeX;
    private int sizeY;
    Canvas canvas;

    public GraphThird(Context context) {
        super(context);
    }

    public GraphThird(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GraphThird(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        this.text2 = _text;
    }

    @Override
    protected void onDraw(Canvas _canvas){
        canvas = _canvas;
        sizeX = getWidth();
        sizeY = getHeight();
        //text2.setText(""+getWidth() + "," + getHeight());
        Point pointLineChartStart = new Point(20,120);//折线图开始的地方
        Point basePoint = new Point(25,getHeight()-25);//折线图的坐标原点
        Paint paint1 = new Paint();
        paint1.setColor(Color.RED);
        paint1.setStrokeWidth(5);
        float bX = 30f;
        float bY = 20f;
        canvas.drawLine(bX,bY,bX,4*sizeY/5f,paint1);
        canvas.drawLine(bX,4*sizeY/5f,sizeX-bX,4*sizeY/5f,paint1);
        canvas.drawLine(bX,bY,bX-8,bY+15,paint1);
        canvas.drawLine(bX,bY,bX+8,bY+15,paint1);
        canvas.drawLine(sizeX-bX,4*sizeY/5f,sizeX-bX-15,4*sizeY/5f-8,paint1);
        canvas.drawLine(sizeX-bX,4*sizeY/5f,sizeX-bX-15,4*sizeY/5f+8,paint1);

        Paint paint2 = new Paint();
        paint2.setColor(Color.argb(255,224,102,255));
        paint2.setStrokeWidth(2);
        float[] data = getData();
        int maxd = getMax(data);
        float max = data[maxd];
        int min = getMin(data);
        float baseX = ((float) (sizeX-bX)) / (float) data.length;
        float baseY = (float)(sizeY*4/5f-bY-40) / max;
        Paint paint3 = new Paint();
        paint3.setTextSize(40);
        //text2.setText(""+sizeY);
        for(int i=0;i<data.length-1;i++){
            canvas.drawLine((i)*baseX+bX+5,sizeY*4/5-data[i]*baseY,(i+1)*baseX+5+bX,sizeY*4/5-data[i+1]*baseY,paint2);//画折现
            paint3.setColor(Color.GRAY);
            for (int j=0;j<11;j++){
                canvas.drawLine((i+1)*baseX+5+bX,j*((sizeY*4/5-bY)/11)+(sizeY*4/5-bY)/22,(i+1)*baseX+2+bX,(j+1)*(sizeY*4/5-bY)/11,paint3);
            }
        }
        if(maxd==0||min==0){
            if(sizeY*4/5-data[0]*baseY<60){
                canvas.drawText(""+data[0],5+bX ,sizeY*4/5-data[0]*baseY,paint3);
            }else{
                canvas.drawText(""+data[0],5+bX ,sizeY*4/5-data[0]*baseY-60,paint3);
            }
            int a = maxd==0?min:maxd;
        }
    }

    public float[] getData(){//1----代表日账单。。。2---代表周账单。。。3--代表月账单。。。4--代表年账单
        float[] data = new float[10];
        for(int i=0;i<data.length;i++){
            data[i] = (float)(1+Math.random()*(20-1+1));
            data[i] = Math.round( data[i] * 100 ) / 100.0f;
        }
        return data;
    }

    public int getMax(float[] _data){
        float max = 0.0f;
        int dex=0;
        for(int i=0;i<_data.length;i++){
            if(_data[i]>max){
                max = _data[i];
                dex = i;
            }
        }
        return dex;
    }

    public int getMin(float[] _data){
        float max = 0.0f;
        int dex=0;
        for(int i=0;i<_data.length;i++){
            if(_data[i]<max){
                max = _data[i];
                dex = i;
            }
        }
        return dex;
    }

    public int getLength(float f){
        return (""+f).length();
    }
}

