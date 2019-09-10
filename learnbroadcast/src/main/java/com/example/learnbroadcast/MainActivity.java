package com.example.learnbroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainAty";
    private BatteryBroadCastReceiver batteryBroadCastReceiver;
    private TextView tv,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerBroadcast();
        initView();
    }

    private void initView() {
        tv=findViewById(R.id.tv);
        tv2=findViewById(R.id.tv2);
    }

    private void registerBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
         batteryBroadCastReceiver = new BatteryBroadCastReceiver();
        this.registerReceiver(batteryBroadCastReceiver,intentFilter);
    }

    class BatteryBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, intent.getAction());
            if(tv!=null){
            switch (intent.getAction()){
                case Intent.ACTION_BATTERY_CHANGED:
                    int current = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                    int maxlevel = intent.getIntExtra(BatteryManager.EXTRA_SCALE,0);
                    double percent = current*1f / maxlevel *100;
                    tv.setText("当前电量: "+percent + "%");
                    break;
                case Intent.ACTION_POWER_CONNECTED:
                    Log.d(TAG,"usb连接上");
                    tv2.setText("连上充电器");
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    Log.d(TAG,"USB断开");

                    tv2.setText("充电器断开");
                    break;
            }


            }
        }
    }
    //取消广播注册

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(batteryBroadCastReceiver!=null){
            this.unregisterReceiver(batteryBroadCastReceiver);
        }
    }
}
