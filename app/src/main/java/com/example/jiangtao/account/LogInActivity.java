package com.example.jiangtao.account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.GregorianCalendar;

import COMMUNICATION.LogIn;
import DBOperate.createTable;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTextMessage;
    Button signin;
    Button register;
    EditText id;
    EditText password;
    CheckBox remember;
    CheckBox zidong;
    String login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        signin = (Button) findViewById(R.id.button1);
        register = (Button) findViewById(R.id.button2);
        signin.setOnClickListener(this);
        register.setOnClickListener(this);
        id = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        remember = (CheckBox) findViewById(R.id.radioButton);
        zidong = (CheckBox) findViewById(R.id.radioButton2);
        SharedPreferences editor1 = getSharedPreferences("data",0);
        if(editor1.getBoolean("is_remember",false)){
            remember.setChecked(true);
        }
        if(editor1.getBoolean("is_zidong",false)){
            zidong.setChecked(true);
        }
        if(editor1.getBoolean("is_first",true)){
            SharedPreferences.Editor editor = editor1.edit();
            editor.putString("id","00000000");
            editor.putString("password","0");
            editor.commit();
        }else {
            SharedPreferences editor = getSharedPreferences("data", 0);
            if (editor.getBoolean("is_remember", false)) {
                if (!editor.getString("id", "").equals("00000000")) {
                    id.setText(editor.getString("id", "").toCharArray(),0,editor.getString("id", "").length());
                }
                if (!editor.getString("password", "").equals("0")) {
                    password.setText(editor.getString("password", "").toCharArray(),0,editor.getString("password", "").length());

                }
                //Toast.makeText(this,editor.getString("id","") + editor.getString("password",""),Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())

        {
            case R.id.button1:{
                String _id = id.getText().toString();
                String _password = password.getText().toString();
                LogIn log = new LogIn(_id,_password);
                if(log.check()){   //   通过服务器验证

                    SharedPreferences e = getSharedPreferences("data",0);
                    if(remember.isChecked()&&id.getText()!=null&&password.getText()!=null){
                        if(e.getString("id","").equals("00000000")||e.getString("password","").equals("0")){
                            SharedPreferences.Editor editor = getSharedPreferences("data",0).edit();
                            editor.putString("id",id.getText().toString());
                            editor.putString("password",password.getText().toString());
                            if(zidong.isChecked()){
                                editor.putBoolean("is_zidong",true);
                            }else{
                                editor.putBoolean("is_zidong",false);
                            }
                            if(remember.isChecked()){
                                editor.putBoolean("is_remember",true);
                            }else{
                                editor.putBoolean("is_remember",false);
                            }
                            editor.commit();
                        }

                        SharedPreferences.Editor _editor = getSharedPreferences("data",0).edit();
                        login = id.getText().toString();
                        _editor.putString("login",login);
                        _editor.commit();

                    }
                    if(!remember.isChecked()){
                        SharedPreferences.Editor editor = getSharedPreferences("data",0).edit();
                        editor.putString("password","0");
                        editor.commit();
                    }
                }else{
                    Toast.makeText(this,"登陆失败，密码或者账号错误！",Toast.LENGTH_SHORT).show();
                    break;
                }
                SharedPreferences ed = getSharedPreferences("data",0);
                if(ed.getBoolean("is_first",true)){//******************************用来初始化的  ！！！！！！！！！！！！！！！！
                    SharedPreferences.Editor editor = getSharedPreferences("data",0).edit();
                    editor.putBoolean("is_first",false);
                    GregorianCalendar calendar = new GregorianCalendar();
                    editor.putInt(login+"ID",calendar.get(Calendar.YEAR)*1000000);
                    editor.commit();
                    new createTable(this,calendar.get(Calendar.YEAR)).createStudentTable();
                    //   再开另一个线程创建数据库

                    //creatDatabase db = new creatDatabase(this,id.getText().toString()+"_db",null,1);
                    //db.getWritableDatabase();
                    //new add(this);
                    //classCommunication cc = new classCommunication(this);//测试用的！！！！！！！！！！！！！！！！！！！！
                    //cc.login();
                    //ArrayList<String> array = cc.getClassList();
                    // Iterator it = array.iterator();
                    //while(it.hasNext()){
                    //   new classTableDBManager((String)it.next(),this,1).putInto();
                    // }
                }
                Intent intent = new Intent(LogInActivity.this,MainActivity.class);
                startActivity(intent);
                break;

            }
            case R.id.button2:{
                //***************这个稍后再议
                Toast.makeText(this,"这个稍后再议！",Toast.LENGTH_SHORT).show();
                break;
            }
            default:break;
        }
    }

}
