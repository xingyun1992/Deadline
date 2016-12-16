package xingyun.com.deadline;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class MyWidget extends AppWidgetProvider {

     void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

         RemoteViews  views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
         views.setTextViewText(R.id.tv_time, DeadLine(SPUtils.getInstane(context).getTime()));
         views.setTextViewText(R.id.tv_dec, SPUtils.getInstane(context).getDec());
         SPUtils.getInstane(context).setCount(SPUtils.getInstane(context).getCount()+1);
         ComponentName componentName = new ComponentName(context.getApplicationContext(), MyWidget.class);
         appWidgetManager.updateAppWidget(componentName, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    /**
     * 核心方法
     * @return
     */
    public String DeadLine(String time){

        if ("".equals(time)){
            return "-.-";
        }

        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd");
        Date historyDate = null;
        Date curDate = null;
        try {
            Date date = new Date();
            String curtime = format.format(date);
            curDate = format.parse(curtime);

            historyDate = format.parse(time);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (historyDate==null){
            return ">_<";
        }

        long day=(historyDate.getTime()-curDate.getTime())/(24*60*60*1000);

        if (day<0){
            return ">_<";
        }else if (day==0){
            return "NOW";
        }else{
            return day+"天";
        }

    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.e("xingyun", "onReceive");

    }
}

