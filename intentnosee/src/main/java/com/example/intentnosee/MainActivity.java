package com.example.intentnosee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startaty(View view) {
        try {
            startActivity(new Intent("com.example.myhelloworld.intent.action.ContainerActivity"));

        }catch (Exception e){
            Toast.makeText(MainActivity.this,"无法打开activity",Toast.LENGTH_LONG).show();
        }
    }
}
