package com.cloudpoint.app.adv.sdk.demo;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.cloudpoint.plugins.sdk.adv.AdvPlayerHandler;
import com.cloudpoint.shell.adv.Adv;
import com.cloudpoint.shell.adv.mediaplayer.IAdvertisementEvent;
import com.cloudpoint.shell.adv.mediaplayer.IAdvertisementPlayerListener;


/**
 * @author sparrow
 * @date 2019/8/21
 * @copyright Beijing CloudPoint Technology Co.,Ltd.
 * @email qiuzhang.rui@cpoao.com
 * @description:
 *
 *
 */

public class AdvEventListener implements IAdvertisementEvent {

    private static final String TAG ="AdvEventListener";


    private void d(String message){
        Log.d(TAG,message);
    }

    @Override
    public IAdvertisementPlayerListener getPlayer() {
        return null;
    }



    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void setAdv(final Adv adv, boolean b) {
        d(adv.toString());
        //收到广告准备好的信息了。
        AdvPlayerHandler.getInstance().play(adv);
//        final int seconds = 60;
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                d(" delay "+seconds+" seconds play!"+adv.getAdvId());
//                AdvPlayerHandler.getInstance().play(adv);
//            }
//        },seconds*1000);


    }

    @Override
    public void onStart() {

        d(" onStart");

    }

    @Override
    public void onError() {

        d(" onError");

    }

    @Override
    public void onEnd() {
        d(" onEnd");
    }

    @Override
    public void onCleanup() {
        d(" onCleanup");
    }

    @Override
    public void notifyAdvTrackerStates(String s, int i) {
        d(" advId" + s +" , code:"+i);
    }
}
