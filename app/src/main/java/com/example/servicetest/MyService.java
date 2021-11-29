package com.example.servicetest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    private DownloadBinder mBinder =new DownloadBinder();
    class DownloadBinder extends Binder {

        public void startDownload(){
            Log.d("MyService","startDownload");

        }

        public int getProgress(){
            Log.d("MyService","getProgress");
            return 0;
        }






    }
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("MyService","onCreate executed");
        String CHANNEL_ID = "com.example.recyclerviewtest.N1";
        String CHANNEL_NAME = "TEST";
        NotificationChannel notificationChannel = null;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent intent =new Intent(this,MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
        Notification notification=new NotificationCompat.Builder(this,CHANNEL_ID)
                      .setContentTitle("this is content title")
                       .setContentText("this is content text")
                       .setWhen(System.currentTimeMillis())
                       .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                         .build();
        startForeground(1,notification);



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService","onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }
}