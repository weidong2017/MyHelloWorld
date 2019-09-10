package com.example.myhelloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static String ACTION = "com.example.myhelloworld.intent.action.ContainerActivity";
    private Button btnopenfra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnopenfra=findViewById(R.id.btn_open_fragment);
        btnopenfra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(ACTION);
                startActivity(intent);
            }
        });

    }
}
