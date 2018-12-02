package com.developer.wise4rmgod.upnepa.work_manager_class;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import com.developer.wise4rmgod.upnepa.R;
import com.developer.wise4rmgod.upnepa.UpnepaActivity;

import androidx.work.Data;
import androidx.work.Worker;

public class Workmanagerclass extends Worker {
    private int notificationzx=0;
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_TEXT = "text";
    public static final String EXTRA_OUTPUT_MESSAGE = "output_message";

    @NonNull
    @Override
    public Result doWork() {

        String titledata = "Up Nepa";
        String messagedata = "Your Phone Is Charging";


        String title = getInputData().getString(EXTRA_TITLE, "My Phone");
        String text = getInputData().getString(EXTRA_TEXT, "its charging");

        //sendNotification("Simple Work Manager", "I have been send by WorkManager!");


        Data output = new Data.Builder()
                .putString(EXTRA_OUTPUT_MESSAGE, "its charging!")
                .build();

        setOutputData(output);
        sendnotification(titledata,messagedata);

        return Result.SUCCESS;
    }

    public void sendnotification(String title,String message){

      //  Intent intent=new Intent(getApplicationContext(),UpnepaActivity.class);
              //    getApplicationContext().startActivity(intent);
        MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.upnepamp3);
                    mPlayer.start();

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        //If on Oreo then notification required a notification channel.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default", "Default", NotificationManager.IMPORTANCE_DEFAULT);
                channel.enableLights(true);
                channel.shouldVibrate();
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "default")
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.lightbulb_icon_pink);


        notificationManager.notify(++notificationzx, notification.build());

        }


}
