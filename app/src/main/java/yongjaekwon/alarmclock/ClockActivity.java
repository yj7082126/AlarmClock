package yongjaekwon.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;


public class ClockActivity extends ActionBarActivity {
    AlarmManager mAlarmManager;
    private PendingIntent mPendingIntent;
    private static ClockActivity instance;
    private TimePicker alarmTimePicker;
    private TextView alarmTextView;
    private ToggleButton alarmToggle;
    public static ClockActivity instance(){
        return instance;
    }

    @Override
    protected void onStart() {
        super.onStart();
        instance=this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        alarmTimePicker=(TimePicker)findViewById(R.id.alarmTimePicker);
        alarmTextView=(TextView)findViewById(R.id.alarmText);
        alarmToggle=(ToggleButton)findViewById(R.id.alarmToggle);
        mAlarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
    }

    public void onToggleClicked(View view){
        if(((ToggleButton)view).isChecked()){
            Log.d("ClockActivity", "Alarm On");
            Calendar mCalendar=Calendar.getInstance();
            mCalendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            mCalendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            Intent myIntent=new Intent(ClockActivity.this, ClockReceiver.class);
            mPendingIntent=PendingIntent.getBroadcast(ClockActivity.this, 0, myIntent, 0);
            mAlarmManager.set(AlarmManager.RTC, mCalendar.getTimeInMillis(), mPendingIntent);
        }
        else{
            mAlarmManager.cancel(mPendingIntent);
            alarmTextView.setText("");
            Log.d("ClockActivity", "Alarm Off");
        }
    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clock, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
