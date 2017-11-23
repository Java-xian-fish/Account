package Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jiangtao.account.R;

import java.util.GregorianCalendar;

/**
 * Created by jiangtao on 2017/11/21.
 */

public class Invest extends Dialog implements View.OnClickListener{

    LinearLayout[] layout = new LinearLayout[4];
    EditText[] text = new EditText[19];
    RadioGroup[] radio = new RadioGroup[2];
    RadioButton radioButton;
    Button[] button = new Button[9];
    EditText adjust;//这个用来获取调整后的差价的
    int times;

    public Invest(Context _context){
        super(_context);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_invest);
        layout[0] = (LinearLayout)findViewById(R.id.layout1);
        layout[1] = (LinearLayout)findViewById(R.id.layout2);
        layout[2] = (LinearLayout)findViewById(R.id.layout3);
        layout[3] = (LinearLayout)findViewById(R.id.layout4);
        layout[0].setVisibility(View.VISIBLE);
        layout[1].setVisibility(View.GONE);
        layout[2].setVisibility(View.GONE);
        layout[3].setVisibility(View.GONE);
        text[0] = (EditText)findViewById(R.id.text1);
        text[1] = (EditText)findViewById(R.id.text2);
        text[2] = (EditText)findViewById(R.id.text3);
        text[3] = (EditText)findViewById(R.id.text4);
        text[4] = (EditText)findViewById(R.id.text5);
        text[5] = (EditText)findViewById(R.id.text6);
        text[6] = (EditText)findViewById(R.id.text7);
        text[7] = (EditText)findViewById(R.id.text8);
        text[8] = (EditText)findViewById(R.id.text9);
        text[9] = (EditText)findViewById(R.id.text10);
        text[10] = (EditText)findViewById(R.id.text11);
        text[11] = (EditText)findViewById(R.id.text12);
        text[12] = (EditText)findViewById(R.id.text13);
        text[13] = (EditText)findViewById(R.id.text14);
        text[14] = (EditText)findViewById(R.id.text15);
        text[15] = (EditText)findViewById(R.id.text16);
        text[16] = (EditText)findViewById(R.id.text17);
        text[17] = (EditText)findViewById(R.id.text18);
        text[18] = (EditText)findViewById(R.id.text19);
        radio[0] = (RadioGroup)findViewById(R.id.group1);
        radio[0].setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio1:{
                        layout[0].setVisibility(View.VISIBLE);
                        layout[1].setVisibility(View.GONE);
                        layout[2].setVisibility(View.GONE);
                        layout[3].setVisibility(View.GONE);
                        break;
                    }
                    case R.id.radio2:{
                        layout[0].setVisibility(View.GONE);
                        layout[1].setVisibility(View.VISIBLE);
                        layout[2].setVisibility(View.GONE);
                        layout[3].setVisibility(View.GONE);
                        break;
                    }
                    case R.id.radio3:{
                        layout[1].setVisibility(View.GONE);
                        layout[0].setVisibility(View.GONE);
                        layout[2].setVisibility(View.VISIBLE);
                        layout[3].setVisibility(View.GONE);
                        break;
                    }
                }
            }
        });
        radio[1] = (RadioGroup)findViewById(R.id.group2);
        radio[1].setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio4:{
                        break;
                    }
                    case R.id.radio5:{
                        break;
                    }
                }
            }
        });
        radioButton = (RadioButton) findViewById(R.id.radio6);
        button[0] = (Button)findViewById(R.id.button1);
        button[1] = (Button)findViewById(R.id.button2);
        button[2] = (Button)findViewById(R.id.button3);
        button[3] = (Button)findViewById(R.id.button4);
        button[4] = (Button)findViewById(R.id.button5);
        button[5] = (Button)findViewById(R.id.button6);
        button[6] = (Button)findViewById(R.id.button7);
        button[7] = (Button)findViewById(R.id.button8);
        button[8] = (Button)findViewById(R.id.button9);
        for(int i=0;i<9;i++){
            button[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:{
                dismiss();
                break;
            }
            case R.id.button2:{
                GregorianCalendar calendar = new GregorianCalendar();
                layout[3].setVisibility(View.VISIBLE);
                layout[1].setVisibility(View.GONE);
                layout[2].setVisibility(View.GONE);
                layout[0].setVisibility(View.GONE);
                break;
            }
            case R.id.button3:{
                dismiss();
                break;
            }
            case R.id.button4:{
                adjust.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.button5:{
                GregorianCalendar calendar = new GregorianCalendar();
                layout[3].setVisibility(View.VISIBLE);
                layout[1].setVisibility(View.GONE);
                layout[2].setVisibility(View.GONE);
                layout[0].setVisibility(View.GONE);
                break;
            }
            case R.id.button6:{
                dismiss();
                break;
            }
            case R.id.button7:{
                GregorianCalendar calendar = new GregorianCalendar();
                layout[3].setVisibility(View.VISIBLE);
                layout[1].setVisibility(View.GONE);
                layout[2].setVisibility(View.GONE);
                layout[0].setVisibility(View.GONE);
                break;
            }
            case R.id.button8:{
                RadioButton radio = (RadioButton)findViewById(R.id.radio1);
                if(radio.isChecked()){
                    layout[0].setVisibility(View.VISIBLE);
                    layout[1].setVisibility(View.GONE);
                    layout[2].setVisibility(View.GONE);
                    layout[3].setVisibility(View.GONE);
                    break;
                }
                radio = (RadioButton)findViewById(R.id.radio2);
                if(radio.isChecked()){
                    layout[1].setVisibility(View.VISIBLE);
                    layout[0].setVisibility(View.GONE);
                    layout[2].setVisibility(View.GONE);
                    layout[3].setVisibility(View.GONE);
                    break;
                }
                radio = (RadioButton)findViewById(R.id.radio3);
                if(radio.isChecked()){
                    layout[2].setVisibility(View.VISIBLE);
                    layout[0].setVisibility(View.GONE);
                    layout[1].setVisibility(View.GONE);
                    layout[3].setVisibility(View.GONE);
                    break;
                }
                break;
            }
            case R.id.button9:{
                GregorianCalendar calendar = new GregorianCalendar();
                layout[3].setVisibility(View.VISIBLE);
                layout[1].setVisibility(View.GONE);
                layout[2].setVisibility(View.GONE);
                layout[0].setVisibility(View.GONE);
                break;
            }
        }

    }
}
