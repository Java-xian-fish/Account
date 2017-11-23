package Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jiangtao.account.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by jiangtao on 2017/11/21.
 */

public class Income extends Dialog implements View.OnClickListener{
    private LinearLayout layout1;
    private LinearLayout layout2;
    private EditText[] text = new EditText[5];//this stands for the type of income
    private Spinner payway;
    private Spinner timeLimit;
    private DatePicker date;
    private TimePicker time;
    private Context context;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    GregorianCalendar calendar;
    int times = 0;

    public Income(Context _context){
        super(_context);
        this.context = _context;
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_income);
        layout1 = (LinearLayout)findViewById(R.id.layout1);
        layout2 = (LinearLayout)findViewById(R.id.layout2);
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);
        text[0] = (EditText)findViewById(R.id.edit_text1);
        text[1] = (EditText)findViewById(R.id.edit_text2);
        text[2] = (EditText)findViewById(R.id.edit_text3);
        text[3] = (EditText)findViewById(R.id.edit_text4);
        text[4] = (EditText)findViewById(R.id.edit_text5);
        payway = (Spinner)findViewById(R.id.spinner1);
        timeLimit = (Spinner)findViewById(R.id.spinner2);
        date = (DatePicker)findViewById(R.id.data_picker);
        time = (TimePicker)findViewById(R.id.time_picker);
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.button1:{
                dismiss();
                break;
            }
            case R.id.button2:{
                if(checkEmpty()){
                    if (times==0) {
                        times++;
                        Toast.makeText(context, "您信息没填满请仔细检查，如果确定要提交请再次点击确定！", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    times = 0;
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
                calendar = new GregorianCalendar();
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
                break;
            }
        }
    }

    private boolean checkEmpty(){
        for(int i=0;i<5;i++){
            if(text[i].getText()==null||text[i].getText().toString()==""){
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
}
