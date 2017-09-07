package com.absurd.demo_messagebus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.absurd.messagebus.MessageBus;


/**
 * Author: mr-absurd
 * Github: http://github.com/mr-absurd
 * Data: 2017/8/21.
 */

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("TAG", "----------------onClick---------");
                MessageBus.getBus().send(new Oklick());
            }
        });
    }


}
