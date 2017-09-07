package com.absurd.demo_messagebus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.absurd.messagebus.MessageBus;
import com.absurd.messagebus.base.Subscribe;
import com.absurd.messagebus.base.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MessageBus.getBus().register(this);
        findViewById(R.id.tv_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    @Subscribe(ThreadMode.MAINTHREAD)
    public void click(Oklick oklick) {
        Log.v("TAG", "---------MAINTHREAD-----"+Thread.currentThread().getName());
    }

    @Subscribe(ThreadMode.BACKGROUND)
    public void click1(Oklick oklick) {
        Log.v("TAG", "----------BACKGROUND----------"+Thread.currentThread().getName());
    }

    @Subscribe(ThreadMode.POSTTHREAD)
    public void click2(Oklick oklick) {
        Log.v("TAG", "----------POSTTHREAD-------------- "+Thread.currentThread().getName());
    }

    @Subscribe(ThreadMode.ASYN)
    public void click3(Oklick oklick) {
        Log.v("TAG", "-----ASYN---------"+Thread.currentThread().getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MessageBus.getBus().unregister(this);
    }
}
