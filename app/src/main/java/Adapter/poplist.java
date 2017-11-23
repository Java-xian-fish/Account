package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.jiangtao.account.CheckBill;
import com.example.jiangtao.account.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import DBOperate.PayOutManager;

/**
 * Created by jiangtao on 2017/11/21.
 */

public class poplist implements View.OnClickListener {

    private EditText editText;
    private DropdownAdapter adapter;
    private List<String> names;
    private PopupWindow pop;
    private Context context;
    private int times;
    private boolean bolo = false;
    static EditText[] text  = new EditText[6];
    static int len = 0;
    final ListView listView;

    public  poplist(EditText _editText,Context _context){//第三个字符串用来告诉该调用数据库的哪个表
        this.editText = _editText;
        this.context = _context;
        text[len++] = _editText;
        listView = new ListView(context);
        this.times = 0;
        //构造方法写在onCreate方法体中会因为布局没有加载完毕而得不到宽高。
    }

    public List<String> getData() {
        names = new ArrayList<String>();
        GregorianCalendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        PayOutManager indb = new PayOutManager(context,year);

        switch (editText.getId()){
            case R.id.text1:{
                Log.e("poplist***********","text1");
                return indb.getPossibleType(1,year);
            }
            case R.id.text2:{
                Log.e("poplist***********","text2");
                return indb.getPossibleTips(1,year,text[0].getText().toString());
            }
            case R.id.text3:{
                Log.e("poplist***********","text3");
                if(isEmpty(3)){
                    bolo = true;
                    if((text[2].getText()!=null)&&(!text[2].getText().toString().equals(""))){
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        calendar.add(Calendar.DAY_OF_MONTH,-7);
                        int month1 = calendar.get(Calendar.MONTH);
                        return indb.getInforByDataAndName(1,year,text[2].getText().toString(),calendar.get(Calendar.DAY_OF_MONTH)+month1*100,day+month*100);
                    }
                    return indb.getPossibleName(1,year,text[0].getText().toString(),text[1].getText().toString());
                }
                return null;
            }
            case R.id.text4:{
                return indb.getPossibleRest(1,year,text[0].getText().toString(),text[1].getText().toString(),text[2].getText().toString());
            }
        }
        return names;
    }

    static boolean isEmpty(int i){
        for(int j=0;j<i-1;j++){
            if(text[i].getText().toString().equals("")||(text[i].getText()==null)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void onClick(View v) {
        List list = getData();
        Iterator it = list.iterator();
        while(it.hasNext()){
            Log.e("poplist***********",(String)it.next());
        }
            if(list!=null&&list.size()!=0) {
                this.adapter = new DropdownAdapter(context,list , editText);
                this.listView.setAdapter(adapter);
                if((this.times%2)==0){

                    if (null == pop) {
                        //创建一个在输入框下方的popup
                        View layout = (View)editText.getParent();
                        pop = new PopupWindow(listView, layout.getWidth(), names.size() * layout.getHeight());
                        pop.showAsDropDown(layout);
                    }
                    else {
                        if (pop.isShowing()) {
                            pop.dismiss();
                        }
                        else {
                            pop.showAsDropDown(editText);
                        }
                    }
                }else{
                    pop.dismiss();
                }
                this.times++;
            }else{
                times = 0;
            }


    }



    class DropdownAdapter extends BaseAdapter {

        public DropdownAdapter(Context context, List<String> list , EditText _editText) {
            this.context = context;
            this.list = list;
            this.editText1 = _editText;
        }

        public int getCount() {
            if(bolo){
                if(poplist.isEmpty(3)) {
                    return list.size() / 5;
                }
                return  list.size() / 3;
            }
            return list.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            layoutInflater  = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.layout_pay_out_adapter, null);
            close = (ImageButton)convertView.findViewById(R.id.close_row);
            content = (TextView)convertView.findViewById(R.id.text_row);
            final String editContent;
            if(bolo){
                if(poplist.isEmpty(3)) {
                    String str = "";
                    for (int i = 0; i < 5; i++) {
                        str += list.get(position * 3 + i).toString();
                    }
                    editContent = str;
                }else {
                    String str = "";
                    for (int i = 0; i < 3; i++) {
                        str += list.get(position * 3 + i).toString();
                    }
                    editContent = str;
                }
            }else{
                editContent = list.get(position).toString();
            }
            content.setText(editContent);
            content.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(bolo){
                        if(poplist.isEmpty(3)) {
                            text[0].setText(list.get(position * 5));
                            text[1].setText(list.get(position * 5 + 1));
                            text[3].setText(list.get(position * 5 + 2));
                            text[4].setText(list.get(position * 5 + 3));
                            text[5].setText(list.get(position * 5 + 4));
                        }else {
                            text[3].setText(list.get(position * 3 ));
                            text[4].setText(list.get(position * 3 + 1));
                            text[5].setText(list.get(position * 3 + 2));
                        }
                    }
                    editText1.setText(editContent);
                    pop.dismiss();
                    return false;
                }
            });
            close.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    names.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }
        private EditText editText1;
        private Context context;
        private LayoutInflater layoutInflater;
        private List<String> list;
        private TextView content;
        private ImageButton close;
    }
}
