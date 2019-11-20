package com.cloudpoint.app.adv.sdk.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.VideoView;

import com.cloudpoint.plugins.log.CPLogger;
import com.cloudpoint.plugins.sdk.adv.AdvPlayerHandler;


public class MainActivity extends AppCompatActivity {

    public static boolean isRunning = false;


    private Handler handler = new Handler(Looper.getMainLooper());

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
        setContentView(R.layout.activity_main);


        isRunning = true;
        // 3. 绑定view
        bindView();
    }


    ImageView imageView;
    VideoView videoView;

    // 3. 绑定view
    private void bindView(){
        if (null == imageView)
            imageView = (ImageView) ((ViewStub) findViewById(R.id.cb_advertisement_picture)).inflate();
        if (null == videoView)
            videoView = (VideoView) (   (ViewStub) findViewById(R.id.vv_advertisemen_video)).inflate();

        AdvPlayerHandler handler = AdvPlayerHandler.getInstance();
        if(handler!=null){
            handler.bind(imageView,videoView);
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}
