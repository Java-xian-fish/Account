package DBOperate;

/**
 * Created by jiangtao on 2017/11/21.
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import java.util.Calendar;
import java.util.GregorianCalendar;
import Tools.timeManager;
public class createTable {

    private Context context;
    private SQLiteDatabase db;
    String id;
    public static final String table = "(" +
            "PATICULARTIME TEXT PRIMARY KEY ," +//1
            "TYPE text," +                      //2
            "TIPS text," +                      //3
            "NAME text," +                      //4
            "PAYEE text," +                     //5
            "PAY_WAY text,"+                    //6
            "LOCATION  text," +                 //7
            "DESCRIPTION text," +               //8
            "SUM float," +                      //9
            "DATE integer)";                    //10
    public static final String tableIncome = "(" +
            "PATICULARTIME text PRIMARY KEY," +
            "TYPE text," +
            "PAYEE text," +
            "PAY_WAY text," +
            "TIME_LIMIT text," +
            "LOCATION  text," +
            "DESCRIPTION text," +
            "SUM float," +
            "DATE integer)";
    public static final String tableInvest = "(" +
            "PATICULARTIME text ," +
            "IS_SELL integer ,"+//用来表示这一笔是否卖出，没有卖为1卖了为0
            "TYPE text," +
            "NAME text," +
            "UNIT_PRICE FLOAT," +
            "COUNT FLOAT,"+
            "DESCRIPTION text," +
            "DATE integer," +
            "PRIMARY KEY(PATICULARTIME,IS_SELL))";
    public static final String tableRead = "(" +
            "PATICULAR_START_TIME text ," +
            "TIMES integer," +//0表示这本书已经读完
            "NAME text," +
            "AUTHOR text," +
            "PUBLISH text," +
            "VERSION integer," +
            "START_PAGE integer," +
            "END_PAGE integer," +
            "TOTAL_PAGE integer,"+
            "READING_SECTION text,"+
            "DESCRIPTION text,"+
            "LOCATION  text," +
            "DATE integer," +
            "PRIMARY KEY(PATICULAR_START_TIME,TIMES))";
    private static final String tableEvent = "(" +
            "PATICULAR_START_TIME text ," +
            "PROGRESS integer ," +//0表示这件事已经完成
            "NAME text," +
            "FIGURES text," +
            "LOCATION  text," +
            "SURROUNDINGS text,"+
            "EVENT text,"+
            "DATE integer," +
            "PRIMARY KEY(PATICULAR_START_TIME,PROGRESS))";

    public  createTable(Context _context,int _year){
        this.context = _context;
        SharedPreferences e = context.getSharedPreferences("data",0);
        this.id = e.getString("login","");
        createDatabase create = new createDatabase(context,id+_year,null,1);
        db = create.getWritableDatabase();
    }

    public void createStudentTable(){
        GregorianCalendar calendar = new GregorianCalendar();
        String studentTable = "create table stu" + calendar.get(Calendar.YEAR) + table;
        db.execSQL(studentTable);
        String studentInformationTable1 = "create table income" + tableIncome;
        db.execSQL(studentInformationTable1);
        String studentInformationTable2 = "create table invest" + tableInvest;
        db.execSQL(studentInformationTable2);
        String studentInformationTable3 = "create table read" + tableRead;
        db.execSQL(studentInformationTable3);
        String studentInformationTable4 = "create table event" + tableEvent;
        db.execSQL(studentInformationTable4);
        //String studentInformationTable5 = "create table stuinfor5"  + table5;
        //db.execSQL(studentInformationTable5);
    }
}

