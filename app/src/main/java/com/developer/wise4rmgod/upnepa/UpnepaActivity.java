package com.developer.wise4rmgod.upnepa;

import android.arch.lifecycle.Observer;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.developer.wise4rmgod.upnepa.work_manager_class.Workmanagerclass;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class UpnepaActivity extends AppCompatActivity {
public Button buttonOn,buttonOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upnepa);

        buttonOn=findViewById(R.id.buttonon);
        buttonOff=findViewById(R.id.buttonoff);

        Data data = new Data.Builder()
                .putString("title","Up Nepa")
                .putString("message","Your Phone Is Charging")
                .build();

        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();
        final OneTimeWorkRequest oneTimeWorkRequest=new OneTimeWorkRequest.Builder(Workmanagerclass.class)
                .setConstraints(constraints)
                .setInputData(data)
                .build();
        final PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(Workmanagerclass.class,100,TimeUnit.MILLISECONDS)

                .setConstraints(constraints)
                .build();

     final UUID workid=oneTimeWorkRequest.getId();

        buttonOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // WorkManager.getInstance().beginWith(oneTimeWorkRequest).enqueue();
                WorkManager.getInstance().enqueue(oneTimeWorkRequest);
                Toast.makeText(getApplicationContext(),"Waiting for Nepa Light Signal",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        buttonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   WorkManager.getInstance().cancelWorkById(periodicWorkRequest.getId());
                Toast.makeText(getApplicationContext(),"Stopped Nepa Light Signal",Toast.LENGTH_SHORT).show();
                WorkManager.getInstance().cancelWorkById(workid);
            }
        });


    }
}
