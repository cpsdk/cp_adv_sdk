package com.cloudpoint.app.adv.sdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.cloudpoint.plugins.sdk.adv.AdvPlayerHandler;
import com.cloudpoint.plugins.sdk.adv.AdvView;

public class AdvViewActivity extends Activity {



    @Override
    public void onAttachedToWindow() {

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
        );



        super.onAttachedToWindow();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_view);
        // 3. 绑定view
        bindView();
    }


    AdvView advView;
    private void bindView(){

        advView = findViewById(R.id.adv_view);

        AdvPlayerHandler handler = AdvPlayerHandler.getInstance();
        if(handler!=null){
            handler.bindView(advView);
        }

    }


}
