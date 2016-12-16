package xingyun.com.deadline;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xingyun on 2016/12/1.
 */
public class SPUtils {

    private static SPUtils spUtils;
    private static final String spname = "xingyun";
    private Context context;

    private static final String timename = "timename";
    private static final String decname = "decname";
    private static final String countname = "countname";

    private SharedPreferences sharedPreferences;

    private SPUtils(Context context){
        this.context=context;
        sharedPreferences = context.getSharedPreferences(spname,Context.MODE_PRIVATE);
    }

   public static synchronized SPUtils getInstane(Context context){
        if (spUtils==null){
            spUtils = new SPUtils(context);
        }
       return spUtils;
   }

    public String getTime(){
        return sharedPreferences.getString(timename,"");
    }

    public void setTime(String time){
        sharedPreferences.edit().putString(timename,time).commit();
    }

    public String getDec(){
        return sharedPreferences.getString(decname,"");
    }

    public void setDec(String dec){
        sharedPreferences.edit().putString(decname,dec).commit();
    }

    public int getCount(){
        return sharedPreferences.getInt(countname,0);
    }

    public void setCount(int count){
        sharedPreferences.edit().putInt(countname,count).commit();
    }


}
