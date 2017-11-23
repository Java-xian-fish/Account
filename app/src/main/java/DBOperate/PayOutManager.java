package DBOperate;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import Tools.timeManager;

/**
 * Created by jiangtao on 2017/11/21.
 * 一丶用来辅助用户输入的方法
 * 1.用来检索所有大类型的方法这个只需要检索一个七天之内的就行而且只需要最后保留十个
 * getPossibleType(int _career,int _year)
 * 辅助方法
 * getOneInformation(int _career,int _year,int _start,int _end,String type)
 * 2.用来检索tips的但是要根据第一个框填的type检索，
 *getPossibleTips(int _career,int _year,String type)
 * 辅助方法
 * getOneInformationByAnother(int _career,int _year,int _start,int _end,String one,String another)
 * 3.用来检索name的
 * getPossibleName(int _career,int _year,String typeV,String tipsV)
 * 辅助方法
 * getOneInformationByTheOther(int _career,int _year,int _start,int _end,String one,String other1,String otherV1,String other2,,String otherV2)
 * 4.用上面三条信息来检索最后剩下的几条
 * getPossibleRest(int _career,int _year,String typeV,String tipsV,,String nameV)
 * 辅助方法
 * getRestInformationByTheThree(int _career,int _year,int _start,int _end,String other1,String otherV1,String other2,String otherV2,String other3,String otherV3)
 * 5.只通过商品名称得到其他信息
 * getInforByDataAndName(int _type, int _year,String _name, int _start, int _end)
 */


public class PayOutManager {

    private Context context;
    private SQLiteDatabase db;
    String id;
    int ID;

    public ArrayList<String> getInforByDataAndName(int _career, int _year,String _name, int _start, int _end){
        Cursor cursor;
        if(_name==null||_name==""){
            cursor = db.rawQuery("select DISTINCT TYPE,TIPS,PAYEE,LOCATION,SUM from " + getCareer(_career) + _year + " where DATE <= " + _end + " AND DATE>=" + _start , null);
        }else {
            cursor = db.rawQuery("select DISTINCT TYPE,TIPS,PAYEE,LOCATION,SUM from " + getCareer(_career) + _year + " where DATE <= " + _end + " AND DATE>=" + _start + " AND NAME = " + _name, null);
        }
        ArrayList<String> array = new ArrayList<String>();
        int i =0;
        if(cursor.moveToFirst()) {
            do {
                array.add(cursor.getString(0));
                array.add(cursor.getString(1));
                array.add(cursor.getString(2));
                array.add(cursor.getString(3));
                array.add(""+cursor.getFloat(4));
            } while (cursor.moveToNext());
        }
        return array;
    }

    public ArrayList<String> getPossibleRest(int _career,int _year,String typeV,String tipsV,String nameV){
        GregorianCalendar calendar1 = new GregorianCalendar();
        timeManager man1 = new timeManager(calendar1);
        GregorianCalendar calendar2 = man1.changeDay(-7);
        timeManager man2 = new timeManager(calendar2);
        return getRestInformationByTheThree(_career,_year,man2.storageID(),man1.storageID(),"NAME",nameV,"TIPS",tipsV,"TYPE",typeV);
    }

    public ArrayList<String> getRestInformationByTheThree(int _career,int _year,int _start,int _end,String other1,String otherV1,String other2,String otherV2,String other3,String otherV3) {
        Cursor cursor;
        if(otherV1==null||otherV2==null||otherV3==null||otherV1==""||otherV2==""||otherV3==""){
            cursor = db.rawQuery("select DISTINCT PAYEE,LOCATION,SUM  COUNT(*) AS TOTAL from " + getCareer(_career) + _year + " where DATE <= " + _end + " AND DATE>=" + _start +
                     " GROUP BY  PAYEE,PAY_WAY,LOCATION,SUM ORDER BY TOTAL DESC ", null);
        }else {
            cursor = db.rawQuery("select DISTINCT PAYEE,LOCATION,SUM  COUNT(*) AS TOTAL from " + getCareer(_career) + _year + " where DATE <= " + _end + " AND DATE>=" + _start +
                    " AND " + other1 + " = " + otherV1 + " AND " + other2 + " = " + otherV2 + " AND " + other3 + " = " + otherV3 + " GROUP BY  PAYEE,PAY_WAY,LOCATION,SUM ORDER BY TOTAL DESC ", null);
        }
        ArrayList<String> array = new ArrayList<String>();
        int i =0;
        if(cursor.moveToFirst()) {
            do {
                array.add(cursor.getString(0));
                array.add(cursor.getString(1));
                array.add(""+cursor.getFloat(2));
            } while (cursor.moveToNext()&&(i++<10));
        }
        return array;
    }

    public ArrayList<String> getPossibleName(int _type, int _year,String typeV,String tipsV){
        GregorianCalendar calendar1 = new GregorianCalendar();
        timeManager man1 = new timeManager(calendar1);
        GregorianCalendar calendar2 = man1.changeDay(-7);
        timeManager man2 = new timeManager(calendar2);
        return getOneInformationByTheOther(_type,_year,man2.storageID(),man1.storageID(),"NAME","TIPS",tipsV,"TYPE",typeV);
    }

    public ArrayList<String> getOneInformationByTheOther(int _career,int _year,int _start,int _end,String one,String other1,String otherV1,String other2,String otherV2) {
        Cursor cursor;
        if(otherV1==null||otherV2==null||otherV1==""||otherV2==""){
            cursor = db.rawQuery("select DISTINCT " + one + " ,COUNT(*) AS TOTAL from " + getCareer(_career) + _year + " where DATE <= " + _end + " AND DATE>=" + _start + " GROUP BY " + one + " ORDER BY TOTAL DESC ", null);
        }else {
            cursor = db.rawQuery("select DISTINCT " + one + " ,COUNT(*) AS TOTAL from " + getCareer(_career) + _year + " where DATE <= " + _end + " AND DATE>=" + _start +
                    " AND " + other1 + " = " + otherV1 + " AND " + other2 + " = " + otherV2 + " GROUP BY " + one + " ORDER BY TOTAL DESC ", null);
        }
        ArrayList<String> array = new ArrayList<String>();
        int i =0;
        if(cursor.moveToFirst()) {
            do {
                array.add(cursor.getString(0));
            } while (cursor.moveToNext()&&(i++<10));
        }
        return array;
    }


    public ArrayList<String> getPossibleTips(int _type, int _year,String anotherV){
        GregorianCalendar calendar1 = new GregorianCalendar();
        timeManager man1 = new timeManager(calendar1);
        GregorianCalendar calendar2 = man1.changeDay(-7);
        timeManager man2 = new timeManager(calendar2);
        return getOneInformationByAnother(_type,_year,man2.storageID(),man1.storageID(),"TIPS","TYPE",anotherV);
    }

    public ArrayList<String> getOneInformationByAnother(int _type, int _year, int _start, int _end,String one,String another,String anotherV) {
        Cursor cursor;
        if( anotherV==null||anotherV=="") {
            cursor = db.rawQuery("select DISTINCT " + one + " ,COUNT(*) AS TOTAL from " + getCareer(_type) + _year + " where DATE <= " + _end + " AND DATE>=" + _start +
                     " GROUP BY " + one + " ORDER BY TOTAL DESC", null);
        }
        else {
            cursor = db.rawQuery("select DISTINCT " + one + " ,COUNT(*) AS TOTAL from " + getCareer(_type) + _year + " where DATE <= " + _end + " AND DATE>=" + _start +
                    " AND " + another + " = " + anotherV + " GROUP BY " + one + " ORDER BY TOTAL DESC", null);
        }
        ArrayList<String> array = new ArrayList<String>();
        int i =0;
        if(cursor.moveToFirst()) {
            do {
                array.add(cursor.getString(0));
            } while (cursor.moveToNext()&&(i++<10));
        }
        return array;
    }

    public ArrayList<String> getPossibleType(int _type, int _year){
        GregorianCalendar calendar1 = new GregorianCalendar();
        timeManager man1 = new timeManager(calendar1);
        GregorianCalendar calendar2 = man1.changeDay(-7);
        timeManager man2 = new timeManager(calendar2);
        return getOneInformation(_type,_year,man2.storageID(),man1.storageID()," TYPE ");
    }

    public ArrayList<String> getOneInformation(int _type, int _year, int _start, int _end,String type) {
        Cursor cursor;
        cursor = db.rawQuery("select DISTINCT "+ type + ", COUNT(*) AS TOTAL from " + getCareer(_type) + _year + " where DATE <= " + _end + " AND DATE>=" + _start +
                " GROUP BY " + type + " ORDER BY TOTAL DESC" , null);
        ArrayList<String> array = new ArrayList<String>();
        int i =0;
        if(cursor.moveToFirst()) {
            do {
                array.add(cursor.getString(0));
            } while (cursor.moveToNext()&&(i++<10));
        }
        return array;
    }

    public ArrayList<String> getAllInformation(int _type, int _year, int _start, int _end) {
        Cursor cursor;
        cursor = db.rawQuery("select * from " + getCareer(_type) + _year + " where DATE <= " + _end + " AND DATE>=" + _start, null);
        ArrayList<String> array = new ArrayList<String>();
        int i =0;
        if(cursor.moveToFirst()) {
            do {
                array.add(cursor.getString(0));
                array.add(cursor.getString(1));
                array.add(cursor.getString(2));
                array.add(cursor.getString(3));
                array.add(cursor.getString(4));
                array.add(cursor.getString(5));
                array.add(cursor.getString(6));
                array.add(cursor.getString(7));
                array.add(""+cursor.getFloat(8));
            } while (cursor.moveToNext());
        }
        return array;
    }
public ArrayList<String> selectAll(){
    Cursor cursor = db.rawQuery("select * from " + getCareer(1) + 2017,null);
    ArrayList<String> array = new ArrayList<String>();
    if(cursor.moveToFirst()) {
        do {
            array.add(cursor.getString(0));
            array.add(cursor.getString(1));
            array.add(cursor.getString(2));
            array.add(cursor.getString(3));
            array.add(cursor.getString(4));
            array.add(cursor.getString(5));
            array.add(cursor.getString(6));
            array.add(cursor.getString(7));
            array.add(""+cursor.getFloat(8));
            array.add(""+cursor.getInt(9));
        } while (cursor.moveToNext());
    }
    return array;
}
    public  PayOutManager(Context _context,int _year) {
        this.context = _context;
        SharedPreferences e = context.getSharedPreferences("data",0);
        this.id = e.getString("login","");
        this.ID = e.getInt(this.id + "ID",00);
        createDatabase create = new createDatabase(context, id+_year, null, 1);
        db = create.getWritableDatabase();
    }

    public boolean Write(int career, String _type, String _tips,String _name,  String _payee,String _payWay,String _location, String _description, float _sum,
                         GregorianCalendar _calendar) {//第一个参数代表职业类型
        timeManager man =  new timeManager(_calendar);
        ContentValues values = new ContentValues();
        values.put("PATICULARTIME",man.storageAll());
        values.put("TYPE", _type);
        values.put("TIPS", _tips);
        values.put("NAME",_name);
        values.put("PAYEE", _payee);
        values.put("PAY_WAY", _payWay);
        values.put("LOCATION", _location);
        values.put("DESCRIPTION", _description);
        values.put("SUM", _sum);
        values.put("DATE",man.storageID());
        long bool = db.insert(getCareer(career) + _calendar.get(Calendar.YEAR), null, values);
        Log.e("*********存数据",""+bool);
        values.clear();
        if (bool == 9) {
            int i=0;
            return true;
        }
        return false;
    }
    public static String getCareer(int career) {
        switch (career) {
            case 1: {
                return "stu";
            }

        }
        return "";
    }
}
