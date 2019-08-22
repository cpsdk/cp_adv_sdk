package com.cloudpoint.app.adv.sdk.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.VideoView;

import com.cloudpoint.plugins.sdk.adv.AdvPlayerHandler;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // 3. 绑定view
        bindView();
    }


    ImageView imageView;
    VideoView videoView;


    private void bindView(){
        if (null == imageView)
            imageView = (ImageView) ((ViewStub) findViewById(R.id.cb_advertisement_picture)).inflate();
        if (null == videoView)
            videoView = (VideoView) ((ViewStub) findViewById(R.id.vv_advertisemen_video)).inflate();

        AdvPlayerHandler handler = AdvPlayerHandler.getInstance();
        if(handler!=null){
            handler.bind(imageView,videoView);
        }

    }



}
