package com.example.learnservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private static final String TAG = "MainActivity";
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);
        findViewById(R.id.btn_bind).setOnClickListener(this);
        findViewById(R.id.btn_unbind).setOnClickListener(this);
        intent = new Intent(MainActivity.this, MyService.class);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_start:
                startService(intent);
                break;
            case R.id.btn_stop:
                stopService(intent);
                break;
            case R.id.btn_bind:
                bindService(intent, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                try {
                    unbindService(this);
                }catch (Exception e){
                    Log.d(TAG,"服务没有注册");
                }


                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG,"服务绑定成功");
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        //服务所在进程崩溃或被杀掉时执行。
        Log.d(TAG,"服务所在进程崩溃或被杀掉时执行");
    }
}
