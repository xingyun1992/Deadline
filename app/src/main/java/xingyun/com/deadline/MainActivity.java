package xingyun.com.deadline;

import android.app.DatePickerDialog;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvTime,tv_count;
    private Button btSet;
    private EditText editText;
    private Button btCommit;
    private DatePickerDialog dialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = (TextView) findViewById(R.id.tv_time);
        btSet = (Button) findViewById(R.id.bt_set);
        editText = (EditText) findViewById(R.id.editText);
        btCommit = (Button) findViewById(R.id.bt_commit);
        tv_count = (TextView) findViewById(R.id.tv_count);

        tvTime.setText(SPUtils.getInstane(this).getTime());
        editText.setText(SPUtils.getInstane(this).getDec());
        tv_count.setText("更新总次数："+SPUtils.getInstane(this).getCount());
        btCommit.setOnClickListener(this);
        btSet.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();

        dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat(
                        "yyyy-MM-dd");
                String time = format.format(calendar.getTime());
                SPUtils.getInstane(MainActivity.this).setTime(time);
                tvTime.setText(time);
            }
        },  calendar.get(Calendar.YEAR) ,calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

    }


    public void notifyWidget(){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS,new int[]{1});
        intent.putExtras(bundle);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        sendBroadcast(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_set:
                dialog.show();
                break;
            case R.id.bt_commit:
                SPUtils.getInstane(this).setDec(editText.getText().toString());
                notifyWidget();
                break;
        }
    }
}
