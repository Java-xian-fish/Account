package COMMUNICATION;

/**
 * Created by jiangtao on 2017/11/21.
 */

public class LogIn {
    private String id;
    private String passWord;

    public LogIn(String _id,String _passWord){
        this.id = _id;
        this.passWord = _passWord;
    }

    public boolean check(){
        return true;
    }

    public static String getName(String _id){
        return "你好江涛同学！";
    }
}
