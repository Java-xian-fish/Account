package Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.jiangtao.account.R;
import java.util.Calendar;
import java.util.GregorianCalendar;
import Adapter.poplist;
import DBOperate.PayOutManager;

/**
 * Created by jiangtao on 2017/11/21.
 */



public class PayOutDialog extends Dialog implements View.OnClickListener{

    Context context;
    RelativeLayout layout1;
    RelativeLayout layout2;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    EditText[] text;
    DatePicker date;
    TimePicker time;
    GregorianCalendar calendar;
    AppCompatSpinner spinner;
    String id;
    int ID;
    int times = 0;
    int career;
    String pay_way;
    SharedPreferences e;

    public PayOutDialog(@NonNull Context context, int _career) {
        super(context);
        this.context = context;
        this.career = _career;
        this.e = context.getSharedPreferences("data",0);
        this.id = e.getString("login","");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pay_out);
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
        text = new EditText[7];
        poplist aa;
        text[0] = (EditText)findViewById(R.id.text1);
        text[0].setOnClickListener(new poplist(text[0],this.context));
        text[1] = (EditText)findViewById(R.id.text2);
        text[1].setOnClickListener(new poplist(text[1],this.context));
        text[2] = (EditText)findViewById(R.id.text3);
        text[2].setOnClickListener(new poplist(text[2],this.context));
        text[3] = (EditText)findViewById(R.id.text4);
        text[3].setOnClickListener(new poplist(text[3],this.context));
        text[4] = (EditText)findViewById(R.id.text5);
        text[4].setOnClickListener(new poplist(text[4],this.context));
        text[5] = (EditText)findViewById(R.id.text6);
        text[5].setOnClickListener(aa=new poplist(text[5],this.context));
        text[6] = (EditText)findViewById(R.id.text7);
        spinner = findViewById(R.id.pay_way);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] payWay = context.getResources().getStringArray(R.array.pay_way);
                pay_way = payWay[pos];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        date = (DatePicker) findViewById(R.id.date);
        time = (TimePicker)findViewById(R.id.time);
        layout1 = (RelativeLayout)findViewById(R.id.layout1);
        layout2 = (RelativeLayout)findViewById(R.id.layout2);
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View view) {
        calendar = new GregorianCalendar();
        switch (view.getId()){
            case R.id.button1:{
                dismiss();
                break;
            }
            case R.id.button2:{
                if(!hasEmpty()&&times==0) {
                    if (text[2].getText().toString().equals("") || (text[2].getText() == null)) {
                        times++;
                        Toast.makeText(context, "您信息没填满请仔细检查，如果确定要提交请再次点击确定！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                if(!isFloat()){
                    Toast.makeText(context,"您填入的金额不是一个正确的数字，请仔细检查！",Toast.LENGTH_SHORT).show();
                    break;
                }
                layout2.setVisibility(View.VISIBLE);
                layout1.setVisibility(View.GONE);
                break;
            }
            case R.id.button3:{
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                break;
            }
            case R.id.button4:{
                date.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR,year);
                        calendar.set(Calendar.MONTH,monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                    }

                });
                time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);
                    }
                });
                PayOutManager db = new PayOutManager(context,calendar.get(Calendar.YEAR));
                ID = e.getInt(this.id+"ID",0);
                setID();
                db.Write(career,text[0].getText().toString(),text[1].getText().toString(),text[2].getText().toString()
                        ,text[3].getText().toString(),pay_way,text[4].getText().toString(),text[6].getText().toString(),
                        Float.parseFloat(text[5].getText().toString()),calendar);
                text = null;
                dismiss();
                break;
            }
        }
    }

    private boolean hasEmpty(){
        for(int i=0;i<text.length;i++){
            if(text[i].getText().toString().equals("")||(text[i].getText()==null)){
                return false;
            }
        }
        return true;
    }

    private  boolean isFloat(){
        try{
            Float.parseFloat(text[5].getText().toString());
            return true;
        }catch (Exception e){
            return false;
        }
    }
    private void setID(){
        SharedPreferences.Editor editor = context.getSharedPreferences("data",0).edit();
        editor.putInt(id+"ID",ID+1);
        editor.commit();
    }
}