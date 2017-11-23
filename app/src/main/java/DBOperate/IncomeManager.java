package DBOperate;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import Tools.timeManager;

/**
 * Created by jiangtao on 2017/11/22.
 */



public class IncomeManager{
    private Context context;
    private int year;
    private SQLiteDatabase db;




    public ArrayList<String> getPossibleName(String anotherV){
        GregorianCalendar calendar1 = new GregorianCalendar();
        timeManager man1 = new timeManager(calendar1);
        GregorianCalendar calendar2 = man1.changeDay(-7);
        timeManager man2 = new timeManager(calendar2);
        return getOneInformationByAnother(man2.storageID(),man1.storageID(),"NAME","TYPE",anotherV);
    }


    public ArrayList<String> getOneInformationByAnother(int _start, int _end,String one,String another,String anotherV) {
        Cursor cursor;
        if( anotherV==null||anotherV=="") {
            cursor = db.rawQuery("select DISTINCT " + one + " ,COUNT(*) AS TOTAL from income" +year + " where DATE <= " + _end + " AND DATE>=" + _start +
                    " GROUP BY " + one + " ORDER BY TOTAL DESC", null);
        }
        else {
            cursor = db.rawQuery("select DISTINCT " + one + " ,COUNT(*) AS TOTAL from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start +
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

    public ArrayList<String> getPossibleType(){
        GregorianCalendar calendar1 = new GregorianCalendar();
        timeManager man1 = new timeManager(calendar1);
        GregorianCalendar calendar2 = man1.changeDay(-7);
        timeManager man2 = new timeManager(calendar2);
        return getOneInformation(man2.storageID(),man1.storageID()," TYPE ");
    }

    public ArrayList<String> getOneInformation( int _start, int _end,String type) {
        Cursor cursor;
        cursor = db.rawQuery("select DISTINCT "+ type + ", COUNT(*) AS TOTAL from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start +
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

    public ArrayList<String> getAllInformation(int _start, int _end) {
        Cursor cursor;
        cursor = db.rawQuery("select * from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start, null);
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
                array.add(""+cursor.getFloat(7));
            } while (cursor.moveToNext());
        }
        return array;
    }

    public ArrayList<String> selectAll(){
        Cursor cursor = db.rawQuery("select * from income" + year,null);
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
                array.add(""+cursor.getFloat(7));
                array.add(""+cursor.getInt(8));
            } while (cursor.moveToNext());
        }
        return array;
    }

    public IncomeManager(Context _context,int _year){
        this.context = _context;
        SharedPreferences e = context.getSharedPreferences("data",0);
        String id = e.getString("login","");
        createDatabase create = new createDatabase(context, id+_year, null, 1);
        db = create.getWritableDatabase();
        this.year = _year;
    }
    public boolean Write(String _type,String _name,  String _payee,String _payWay,String incoType,String _location, String _description, float _sum,
                         GregorianCalendar _calendar) {//第一个参数代表职业类型
        timeManager man =  new timeManager(_calendar);
        ContentValues values = new ContentValues();
        values.put("PATICULARTIME",man.storageAll());
        values.put("TYPE", _type);
        values.put("PAYEE", _payee);
        values.put("PAY_WAY", _payWay);
        values.put("TIME_LIMIT", incoType);
        values.put("LOCATION", _location);
        values.put("DESCRIPTION", _description);
        values.put("SUM", _sum);
        values.put("DATE",man.storageID());
        long bool = db.insert("income"+ year, null, values);
        Log.e("*********存数据",""+bool);
        values.clear();
        if (bool == 9) {
            int i=0;
            return true;
        }
        return false;
    }


    public ArrayList<String> getAllInformationByConfirmField(String[] key,String[] value,int _start, int _end) {
        Cursor cursor;
        if(key.length==1){
            cursor = db.rawQuery("select * from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0], null);
        }else if(key.length==2){
            cursor = db.rawQuery("select * from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1], null);
        }else if(key.length==3){
            cursor = db.rawQuery("select * from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1]+ " AND "+key[2]+"=" + value[2], null);
        }else if(key.length==4){
            cursor = db.rawQuery("select * from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1]+ " AND "+key[2]+"=" + value[2]+ " AND "+key[3]+"=" + value[3], null);
        }else if(key.length==5){
            cursor = db.rawQuery("select * from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1]+ " AND "+key[2]+"=" + value[2] +" AND "+key[3]+"=" + value[3]+" AND "+key[4]+"=" + value[4], null);
        }else if(key.length==6){
            cursor = db.rawQuery("select * from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1]+ " AND "+key[2]+"=" + value[2] +" AND "+key[3]+"=" + value[3]+" AND "+key[4]+"=" + value[4]+" AND "+key[5]+"=" + value[5], null);
        }else if(key.length==7){
            cursor = db.rawQuery("select * from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1]+ " AND "+key[2]+"=" + value[2] +" AND "+key[3]+"=" + value[3]+" AND "+key[4]+"=" + value[4]+" AND "+key[5]+"=" + value[5]+" AND "+key[6]+"=" + value[6], null);
        }else{
            cursor = db.rawQuery("select * from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start, null);
        }
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

    public ArrayList<String> getSumByConfirmField(String[] key,String[] value,int _start, int _end) {
        Cursor cursor;
        if(key.length==1){
            cursor = db.rawQuery("select SUM(SUM) ,COUNT(SUM) from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0], null);
        }else if(key.length==2){
            cursor = db.rawQuery("select SUM(SUM) ,COUNT(SUM) from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1], null);
        }else if(key.length==3){
            cursor = db.rawQuery("select SUM(SUM) ,COUNT(SUM) from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1]+ " AND "+key[2]+"=" + value[2], null);
        }else if(key.length==4){
            cursor = db.rawQuery("select SUM(SUM) ,COUNT(SUM) from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1]+ " AND "+key[2]+"=" + value[2]+ " AND "+key[3]+"=" + value[3], null);
        }else if(key.length==5){
            cursor = db.rawQuery("select SUM(SUM) ,COUNT(SUM) from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1]+ " AND "+key[2]+"=" + value[2] +" AND "+key[3]+"=" + value[3]+" AND "+key[4]+"=" + value[4], null);
        }else if(key.length==6){
            cursor = db.rawQuery("select SUM(SUM) ,COUNT(SUM) from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1]+ " AND "+key[2]+"=" + value[2] +" AND "+key[3]+"=" + value[3]+" AND "+key[4]+"=" + value[4]+" AND "+key[5]+"=" + value[5], null);
        }else if(key.length==7){
            cursor = db.rawQuery("select SUM(SUM) ,COUNT(SUM) from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1]+ " AND "+key[2]+"=" + value[2] +" AND "+key[3]+"=" + value[3]+" AND "+key[4]+"=" + value[4]+" AND "+key[5]+"=" + value[5]+" AND "+key[6]+"=" + value[6], null);
        }else{
            cursor = db.rawQuery("select * from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start, null);
        }
        ArrayList<String> array = new ArrayList<String>();
        int i =0;
        if(cursor.moveToFirst()) {
            do {
                array.add(""+cursor.getFloat(0));
                array.add(""+cursor.getInt(1));
            } while (cursor.moveToNext());
        }
        return array;
    }

    public ArrayList<String> getOneByConfirmField(String[] key,String[] value,String field,int _start, int _end) {
        Cursor cursor;
        if(key.length==1){
            cursor = db.rawQuery("select "+field+" from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0], null);
        }else if(key.length==2){
            cursor = db.rawQuery("select "+field+" from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1], null);
        }else if(key.length==3){
            cursor = db.rawQuery("select "+field+" from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1]+ " AND "+key[2]+"=" + value[2], null);
        }else if(key.length==4){
            cursor = db.rawQuery("select "+field+" from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1]+ " AND "+key[2]+"=" + value[2]+ " AND "+key[3]+"=" + value[3], null);
        }else if(key.length==5){
            cursor = db.rawQuery("select "+field+" from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1]+ " AND "+key[2]+"=" + value[2] +" AND "+key[3]+"=" + value[3]+" AND "+key[4]+"=" + value[4], null);
        }else if(key.length==6){
            cursor = db.rawQuery("select "+field+" from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1]+ " AND "+key[2]+"=" + value[2] +" AND "+key[3]+"=" + value[3]+" AND "+key[4]+"=" + value[4]+" AND "+key[5]+"=" + value[5], null);
        }else if(key.length==7){
            cursor = db.rawQuery("select "+field+" from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+"=" + value[0]+ " AND "+key[1]+"=" + value[1]+ " AND "+key[2]+"=" + value[2] +" AND "+key[3]+"=" + value[3]+" AND "+key[4]+"=" + value[4]+" AND "+key[5]+"=" + value[5]+" AND "+key[6]+"=" + value[6], null);
        }else{
            cursor = db.rawQuery("select "+field+" from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start, null);
        }
        ArrayList<String> array = new ArrayList<String>();
        int i =0;
        if(cursor.moveToFirst()) {
            do {
                array.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return array;
    }
    public ArrayList<String> getAllInformationByUncertainField(String[] key,String[] value,int _start, int _end) {
        Cursor cursor;
        if(key.length==1){
            cursor = db.rawQuery("select * from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+" LIKE %" + value[0] + "%", null);
        }else if(key.length==2){
            cursor = db.rawQuery("select * from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+" LIKE %" + value[0] + "%"+ " AND "+key[1]+" LIKE %" + value[1] + "%", null);
        }else {
            cursor = db.rawQuery("select * from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start, null);
        }
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

    public ArrayList<String> getSumByUncertainField(String[] key,String[] value,int _start, int _end) {
        Cursor cursor;
        if(key.length==1){
            cursor = db.rawQuery("select SUM(SUM) ,COUNT(SUM) from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+" LIKE %" + value[0] + "%", null);
        }else if(key.length==2){
            cursor = db.rawQuery("select SUM(SUM) ,COUNT(SUM) from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+" LIKE %" + value[0] + "%"+ " AND "+key[1]+" LIKE %" + value[1] + "%", null);
        }else {
            cursor = db.rawQuery("select SUM(SUM) ,COUNT(SUM) from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start, null);
        }
        ArrayList<String> array = new ArrayList<String>();
        int i =0;
        if(cursor.moveToFirst()) {
            do {
                array.add(""+cursor.getFloat(0));
                array.add(""+cursor.getInt(1));
            } while (cursor.moveToNext());
        }
        return array;
    }

    public ArrayList<String> getAllInformationByUncertainField(String[] key,String[] value,String field,int _start, int _end) {
        Cursor cursor;
        if(key.length==1){
            cursor = db.rawQuery("select "+field+" from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+" LIKE %" + value[0] + "%", null);
        }else if(key.length==2){
            cursor = db.rawQuery("select "+field+" from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start+ " AND "+key[0]+" LIKE %" + value[0] + "%"+ " AND "+key[1]+" LIKE %" + value[1] + "%", null);
        }else {
            cursor = db.rawQuery("select "+field+" from income" + year + " where DATE <= " + _end + " AND DATE>=" + _start, null);
        }
        ArrayList<String> array = new ArrayList<String>();
        int i =0;
        if(cursor.moveToFirst()) {
            do {
                array.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return array;
    }
}