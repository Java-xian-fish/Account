package Tools;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by jiangtao on 2017/11/21.
 */

public class timeManager {

    GregorianCalendar calendar;

    public timeManager(){
        this.calendar = new GregorianCalendar();
    }

    public timeManager(GregorianCalendar _calendar){
        this.calendar = _calendar;
    }

    public timeManager(int _year,int _month,int _day){//要是没有传分秒过来，就默认61:00
        this.calendar = new GregorianCalendar(_year,_month,_day,61,00);
    }

    public timeManager(int _year,int _month,int _day,int _minute,int _second){
        this.calendar = new GregorianCalendar(_year,_month,_day,_minute,_second);
    }

    public int storageID(){
        return (this.calendar.get(Calendar.MONTH))*100+this.calendar.get(Calendar.DAY_OF_MONTH);
    }

    public String storageAll(){
        return this.calendar.get(Calendar.MONTH)
                +""+this.calendar.get(Calendar.DAY_OF_MONTH)+""+this.calendar.get(Calendar.HOUR_OF_DAY)+""+this.calendar.get(Calendar.MINUTE)
                +""+this.calendar.get(Calendar.SECOND);
    }

    public GregorianCalendar changeID(String _str){
        GregorianCalendar _calendar =  new GregorianCalendar();
        _calendar.set(Calendar.MONTH,Integer.parseInt(_str.substring(0,_str.indexOf('-'))));
        _calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(_str.substring(_str.indexOf('-')+1)));
        return _calendar;
    }

    public GregorianCalendar changeAll(String _str){
        GregorianCalendar _calendar =  new GregorianCalendar();
        _calendar.set(Calendar.YEAR,Integer.parseInt(_str.substring(0,_str.indexOf('\\'))));
        _calendar.set(Calendar.MONTH,Integer.parseInt(_str.substring(_str.indexOf('\\')+1,_str.indexOf('-'))));
        _calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(_str.substring(_str.indexOf('-')+1),_str.indexOf('_')));
        _calendar.set(Calendar.MINUTE,Integer.parseInt(_str.substring(_str.indexOf('_')+1),_str.indexOf(':')));
        _calendar.set(Calendar.SECOND,Integer.parseInt(_str.substring(_str.indexOf(':')+1)));
        return _calendar;
    }

    public int[] changeId(String _str){
        return new int[]{Integer.parseInt(_str.substring(0,_str.indexOf('-'))),Integer.parseInt(_str.substring(_str.indexOf('-')+1))};
    }

    public static boolean hasnotMS(GregorianCalendar _calendar){
        int a = _calendar.get(Calendar.MINUTE);
        return a ==61;
    }

    public GregorianCalendar changeDay(int _day){
        GregorianCalendar _calendar = new GregorianCalendar();
        _calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
        _calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));
        _calendar.add(Calendar.DAY_OF_MONTH,_day);
        return _calendar;
    }

    public static boolean hasnotMS(String _str){
        return Integer.parseInt(_str.substring(_str.indexOf('_')+1),_str.indexOf(':'))==61;
    }
}
