package com.cloudpoint.app.adv.sdk.demo;

import android.util.Log;

import com.cloudpoint.shell.adv.Adv;
import com.cloudpoint.shell.adv.mediaplayer.IAdvertisementEvent;
import com.cloudpoint.shell.adv.mediaplayer.IAdvertisementPlayerListener;


/**
 * @author sparrow
 * @date 2019/8/21
 * @copyright Beijing CloudPoint Technology Co.,Ltd.
 * @email qiuzhang.rui@cpoao.com
 * @description:
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

    @Override
    public void setAdv(Adv adv, boolean b) {
        d(adv.toString());
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
