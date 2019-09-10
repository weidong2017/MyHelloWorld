package com.example.myhelloworld.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myhelloworld.R;

public class AFragment  extends Fragment {
    public static AFragment NewInstance(String str){
        AFragment aFragment = new AFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        aFragment.setArguments(bundle);
        return aFragment;

    }
    public interface IMessageClick{
        void onClick(String msg);
    };
    private TextView tva;
    private Button btnChange,btnJump,btnChangeActivity;
    private BFragment bFragment;
    private IMessageClick iMessageClick;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            iMessageClick = (IMessageClick) context;
        }catch (Exception e){
            throw new ClassCastException("Activity 必须实现IMessagesClick接口");
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a,null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tva = view.findViewById(R.id.tv_a);
        if(getArguments()!= null){
            tva.setText(getArguments().getString("title"));
        }
        btnChange= view.findViewById(R.id.btn_change);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tva.setText("我的内容被改变了");
            }
        });
        btnJump=view.findViewById(R.id.btn_jump);
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bFragment == null){
                    bFragment = new BFragment();
                }
                //给bfragment传值
                Bundle bundle = new Bundle();
                bundle.putString("title","我是从Afra跳转到Bfra");
                bFragment.setArguments(bundle);

                Fragment fragment = getFragmentManager().findFragmentByTag("a");
                if(fragment!=null){
                    getFragmentManager().beginTransaction().hide(fragment).add(R.id.fr_container,bFragment).addToBackStack(null).commitAllowingStateLoss();
                }else {
                    getFragmentManager().beginTransaction().add(R.id.fr_container,bFragment).addToBackStack(null).commitAllowingStateLoss();

                }
            }
        });
        btnChangeActivity=view.findViewById(R.id.btn_change_activity);
        btnChangeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iMessageClick.onClick("我是afragment过来的");
            }
        });
    }


}
