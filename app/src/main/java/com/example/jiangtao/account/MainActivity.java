package com.example.jiangtao.account;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import Dialog.Income;
import Dialog.Invest;
import Dialog.PayOutDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    GregorianCalendar calendar;
    LinearLayout layout;
    RelativeLayout layout1 ;
    RelativeLayout layout2;
    RelativeLayout layout3;
    List<String> list = new ArrayList<>();
    private  int lastX;
    private  int lastY;
    TextView tb1;
    TextView tb2;
    TextView tb3;
    TextView te1;
    TextView te2;
    TextView te3;
    TextView tb4;
    TextView te4;
    ListView main_list;
    TextView main_text11;
    TextView main_text2;
    TextView main_text22;
    TextView main_text3;
    TextView main_text33;
    TextView main_text1;
    TextView main_text4;
    TextView main_text44;
    TextView main_text5;
    TextView main_text55;
    TextView main_text6;
    TextView main_text66;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.layout_title);
        setContentView(R.layout.activity_main);
        layout = (LinearLayout)findViewById(R.id.layout);
        layout1= (Graph) findViewById(R.id.layout1);
        layout2 = (SetUp) findViewById(R.id.layout2);
            layout3 = (CheckBill) findViewById(R.id.layout3);
        layout.setVisibility(View.VISIBLE);
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
        //最下边的点击事件
        tb1 = (TextView)findViewById(R.id.text3);
        tb1.setOnClickListener(this);
        tb2 = (TextView)findViewById(R.id.text4);
        tb2.setOnClickListener(this);
        tb3 = (TextView)findViewById(R.id.text5);
        tb3.setOnClickListener(this);
        tb4 = (TextView)findViewById(R.id.text6);
        tb4.setOnClickListener(this);
        te1 = (TextView)findViewById(R.id.te1);
        te1.setOnClickListener(this);
        te2 = (TextView)findViewById(R.id.te2);
        te2.setOnClickListener(this);
        te3 = (TextView)findViewById(R.id.te3);
        te3.setOnClickListener(this);
        te4 = (TextView)findViewById(R.id.te4);
        te4.setOnClickListener(this);
        main_text1 = (TextView)findViewById(R.id.main_text_1);
        main_text1.setOnClickListener(this);
        main_text11 = (TextView)findViewById(R.id.main_text_11);
        main_text11.setOnClickListener(this);
        main_text2 = (TextView)findViewById(R.id.main_text_2);
        main_text2.setOnClickListener(this);
        main_text22 = (TextView)findViewById(R.id.main_text_22);
        main_text22.setOnClickListener(this);
        main_text3 = (TextView)findViewById(R.id.main_text_3);
        main_text3.setOnClickListener(this);
        main_text33 = (TextView)findViewById(R.id.main_text_33);
        main_text33.setOnClickListener(this);
        main_text4 = (TextView)findViewById(R.id.main_text_4);
        main_text4.setOnClickListener(this);
        main_text44 = (TextView)findViewById(R.id.main_text_44);
        main_text44.setOnClickListener(this);
        main_text5= (TextView)findViewById(R.id.main_text_5);
        main_text5.setOnClickListener(this);
        main_text55 = (TextView)findViewById(R.id.main_text_55);
        main_text55.setOnClickListener(this);
        main_text6 = (TextView)findViewById(R.id.main_text_6);
        main_text6.setOnClickListener(this);
        main_text66 = (TextView)findViewById(R.id.main_text_66);
        main_text66.setOnClickListener(this);
        main_list = (ListView)findViewById(R.id.main_list);
        SharedPreferences e = this.getSharedPreferences("data",0);
        String id = e.getString("login","");
        int ID = e.getInt(id + "ID",00);
    }

    public void showList(int days){
        ArrayList<String> array = new ArrayList<String>();
        //得到数据
        //Main_ListAdapter adapter = new Main_ListAdapter(MainActivity.this,R.layout.main_list_activity,array);
        //main_list.setAdapter(adapter);
    }

    /*public boolean dispatchTouchEvent(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();
        super.dispatchTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                if(offsetY<-80){

                    LinearLayout loyout1 = (LinearLayout)findViewById(R.id.layout1);
                    LinearLayout loyout2 = (LinearLayout)findViewById(R.id.layout2);
                    loyout1.setVisibility(View.VISIBLE);
                    loyout2.setVisibility(View.GONE);
                }else if(offsetY>80){
                    LinearLayout loyout1 = (LinearLayout)findViewById(R.id.layout1);
                    LinearLayout loyout2 = (LinearLayout)findViewById(R.id.layout2);
                    loyout2.setVisibility(View.VISIBLE);
                    loyout1.setVisibility(View.GONE);

                }else if(offsetX>120){

                }
                else{
                    super.dispatchTouchEvent(event);
                }
                break;
        }
        return true;
    }*/

    public  void setChange(int _change){//这个主要是处理标题按钮被按下而相应作出的反应
        switch(_change){
            case 1:{
                break;
            }
            case 2:{

            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.te1:
            case R.id.text3:{
                Drawable top = getResources().getDrawable(R.drawable.chargeup_fill);
                tb1.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);
                te1.setTextColor(Color.parseColor("#1296db"));
                te3.setTextColor(Color.parseColor("#707070"));
                te4.setTextColor(Color.parseColor("#707070"));
                layout.setVisibility(View.VISIBLE);
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);
                layout3.setVisibility(View.GONE);
                te2.setTextColor(Color.parseColor("#707070"));
                Drawable top3 = getResources().getDrawable(R.drawable.bill);
                tb2.setCompoundDrawablesWithIntrinsicBounds(null, top3 , null, null);
                Drawable top1 = getResources().getDrawable(R.drawable.setup1);
                tb4.setCompoundDrawablesWithIntrinsicBounds(null, top1 , null, null);
                Drawable top2 = getResources().getDrawable(R.drawable.dynamic1);
                tb3.setCompoundDrawablesWithIntrinsicBounds(null, top2 , null, null);
                break;
            }
            case R.id.te2:
            case R.id.text4:{
                te2.setTextColor(Color.parseColor("#1296db"));
                te1.setTextColor(Color.parseColor("#707070"));
                te4.setTextColor(Color.parseColor("#707070"));
                layout1.setVisibility(View.GONE);
                te3.setTextColor(Color.parseColor("#707070"));
                Drawable top3 = getResources().getDrawable(R.drawable.bill_fill);
                tb2.setCompoundDrawablesWithIntrinsicBounds(null, top3 , null, null);
                Drawable top = getResources().getDrawable(R.drawable.chargeup);
                tb1.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);
                Drawable top2 = getResources().getDrawable(R.drawable.dynamic1);
                tb3.setCompoundDrawablesWithIntrinsicBounds(null, top2 , null, null);
                Drawable top1 = getResources().getDrawable(R.drawable.setup1);
                tb4.setCompoundDrawablesWithIntrinsicBounds(null, top1 , null, null);
                layout3.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);
                break;
            }
            case R.id.te3:
            case R.id.text5:{
                te3.setTextColor(Color.parseColor("#1296db"));
                te1.setTextColor(Color.parseColor("#707070"));
                te4.setTextColor(Color.parseColor("#707070"));
                layout3.setVisibility(View.GONE);
                te2.setTextColor(Color.parseColor("#707070"));
                Drawable top3 = getResources().getDrawable(R.drawable.bill);
                tb2.setCompoundDrawablesWithIntrinsicBounds(null, top3 , null, null);
                Drawable top = getResources().getDrawable(R.drawable.chargeup);
                tb1.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);
                Drawable top2 = getResources().getDrawable(R.drawable.dynamic_fill);
                tb3.setCompoundDrawablesWithIntrinsicBounds(null, top2 , null, null);
                Drawable top1 = getResources().getDrawable(R.drawable.setup1);
                tb4.setCompoundDrawablesWithIntrinsicBounds(null, top1 , null, null);
                layout1.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);
                break;
            }
            case R.id.te4:
            case R.id.text6:{
                te4.setTextColor(Color.parseColor("#1296db"));
                te3.setTextColor(Color.parseColor("#707070"));
                te1.setTextColor(Color.parseColor("#707070"));
                layout3.setVisibility(View.GONE);
                te2.setTextColor(Color.parseColor("#707070"));
                Drawable top3 = getResources().getDrawable(R.drawable.bill);
                tb2.setCompoundDrawablesWithIntrinsicBounds(null, top3 , null, null);
                Drawable top2 = getResources().getDrawable(R.drawable.dynamic1);
                tb3.setCompoundDrawablesWithIntrinsicBounds(null, top2 , null, null);
                Drawable top1 = getResources().getDrawable(R.drawable.setup_fill);
                tb4.setCompoundDrawablesWithIntrinsicBounds(null, top1 , null, null);
                Drawable top = getResources().getDrawable(R.drawable.chargeup);
                tb1.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);
                layout2.setVisibility(View.VISIBLE);
                layout1.setVisibility(View.GONE);
                layout.setVisibility(View.GONE);
                break;
            }

            case R.id.main_text_1:
            case R.id.main_text_11:{
                new Invest(this).show();
                break;
            }

            case R.id.main_text_2:
            case R.id.main_text_22:{
                new Income(this).show();
                break;
            }
            case R.id.main_text_3:
            case R.id.main_text_33:{
                new PayOutDialog(this,1).show();
                break;
            }

            case R.id.main_text_4:
            case R.id.main_text_44:{

                break;
            }

            case R.id.main_text_5:
            case R.id.main_text_55:{

                break;
            }

            case R.id.main_text_6:
            case R.id.main_text_66:{

                break;
            }
           /* case 1:{
                ArrayList<String> arrayList = new DBManager(this,2017).getAllInformation(1,2017,0000,9999);
                String str ="";
               for(int i=0;i<arrayList.size()/10;i++) {
                  new InDBManager(this).hasTheSame(1,5,new String[]{arrayList.get(1),arrayList.get(2),arrayList.get(3),arrayList.get(4),arrayList.get(5)});
                   for(int j=0;j<10;j++){
                       str += arrayList.get(j) + "\n";
                   }
               }
                arrayList = null;
                text.setText(str);
                break;
            }*/
        }
    }
}
