package com.example.learnservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private String TAG = "MyService";
    private boolean serviceRunning = false;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"服务onbind");
        return  new Binder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"服务创建onStartCommand");

        new Thread(){
            @Override
            public void run() {
                while (serviceRunning){
                Log.d(TAG,"服务正在运行。。。。。。");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"服务创建");
        serviceRunning = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"服务销毁");
        serviceRunning= false;
    }
}
