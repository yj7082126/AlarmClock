package yongjaekwon.alarmclock;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by Kwon on 10/13/2015.
 */
public class ClockReceiver extends WakefulBroadcastReceiver {
    //receive alarm trigger on set time.
    @Override
    public void onReceive(Context context, Intent intent) {
        //update UI with message
        ClockActivity inst=ClockActivity.instance();
        inst.setAlarmText("Wake Up!");

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        //notification message
        ComponentName comp = new ComponentName(context.getPackageName(), ClockService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}
