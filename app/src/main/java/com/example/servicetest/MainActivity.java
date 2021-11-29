package com.example.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder=(MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startservice=findViewById(R.id.start_service);
        Button stopservice=findViewById(R.id.stop_service);
        startservice.setOnClickListener(this);
        stopservice.setOnClickListener( this);
        Button bindservice=findViewById(R.id.bind_service);
        Button unbindservice=findViewById(R.id.unbind_service);
        Button startIntentService=findViewById(R.id.start_intent_service);

        bindservice.setOnClickListener(this);
        unbindservice.setOnClickListener(this);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent=new Intent(this,MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent=new Intent(this,MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent=new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            case R.id.start_intent_service:
                Log.d("MainActivity","Thread id is "+Thread.currentThread().getId());
                Intent intentService =new Intent(this,MyIntentService.class);
                startService(intentService);
            default:
                break;
        }
    }
}