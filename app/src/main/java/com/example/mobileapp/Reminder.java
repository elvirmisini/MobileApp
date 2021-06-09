package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Reminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        createNotificationChanel();

        EditText minutes=findViewById(R.id.minutes);
        Button remind=findViewById(R.id.remind);

        remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min = Integer.parseInt(minutes.getText().toString());
                RemindMeIn(min);
            }
        });
    }
    private void createNotificationChanel(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence charSequence="ReminderChanel";
            String description="Chanel for Notification";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel=new NotificationChannel("notificationId",charSequence,importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }
    }
    private void RemindMeIn(int minutes){

        Toast.makeText(Reminder.this,R.string.setreminder,Toast.LENGTH_LONG).show();

        Intent intent=new Intent(Reminder.this,ReminderClass.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(Reminder.this,0,intent,0);

        AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);

        long timeAtButtonClick=System.currentTimeMillis();
        long tenSecondsInMillis=minutes*1000*60;

        alarmManager.set(AlarmManager.RTC_WAKEUP,
                timeAtButtonClick+tenSecondsInMillis
                ,pendingIntent);
    }
}