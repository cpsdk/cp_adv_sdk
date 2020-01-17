package com.cloudpoint.app.adv.sdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.cloudpoint.plugins.sdk.adv.AdvPlayerHandler;
import com.cloudpoint.plugins.sdk.adv.AdvView;
import com.cloudpoint.plugins.sdk.adv.CPAdvSdk;

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

    }

    AdvView advView;
    private void bindView(){
        advView = findViewById(R.id.adv_view);

        //绑定视图，之后会启动服务，设置视图为null，停止服务
        CPAdvSdk.bindView(advView);

    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        // 3. 绑定view
         bindView();

    }




}
