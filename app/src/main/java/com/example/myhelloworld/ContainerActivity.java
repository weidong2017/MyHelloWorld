package com.example.myhelloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.myhelloworld.fragment.AFragment;
import com.example.myhelloworld.fragment.BFragment;

public class ContainerActivity extends AppCompatActivity implements AFragment.IMessageClick {

    private FrameLayout frContainer;
    private Button btnstr;
    private AFragment aFragment;
    private BFragment bFragment;
    private TextView tvContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        frContainer=findViewById(R.id.fr_container);
        btnstr=findViewById(R.id.button);
        tvContainer=findViewById(R.id.tv_container);
        aFragment = AFragment.NewInstance("我是新的aFragment");
        getSupportFragmentManager().beginTransaction().add(R.id.fr_container,aFragment,"a").commitAllowingStateLoss();

        btnstr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(bFragment == null){
//                    bFragment = new BFragment();
//                }
//                    getSupportFragmentManager().beginTransaction().hide(aFragment).add(R.id.fr_container,bFragment).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }

    @Override
    public void onClick(String msg) {
        tvContainer.setText(msg);
    }
}
