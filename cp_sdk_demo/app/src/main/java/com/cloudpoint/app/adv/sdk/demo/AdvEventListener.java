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


    private Adv advId;
    int count = 0;
    /**
     *
     *
     * 接收广告信息
     *
     * @param adv 接收到服务器推送广告
     * @param b true:线上广告
     */
    @Override
    public void setAdv(final Adv adv, boolean b) {
        d(adv.toString());
//        advId = adv;
//        if(count==0){
//            AdvPlayerHandler.getInstance().play(adv);
//            count++;
//        }
        //收到广告准备好的信息了。
        AdvPlayerHandler.getInstance().play(adv);
        //开始播放广告，必须在广告超时前完成播放。


//        final int seconds = 5;
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                d(" delay "+seconds+" seconds play!"+adv.getAdvId());
//                AdvPlayerHandler.getInstance().play(adv);
//            }
//        },seconds*1000);


    }

    /**
     *
     * 播放器开始播放
     */
    @Override
    public void onStart() {

        d(" onStart");

    }

    /**
     * 播放器播放失败
     */
    @Override
    public void onError() {

        d(" onError");

    }

    /**
     *
     * 播放器播放结束
     */
    @Override
    public void onEnd() {
        d(" onEnd");


    }

    /**
     *
     *
     *  计费结束
     */
    @Override
    public void onCleanup() {
        d(" onCleanup");
    }

    /**
     *
     * 通知广告状态变化，包含广告id,
     *
     *
     * @param s 广告id，唯一标识
     * @param i 广告状态
     *
     *          0： 播放开始
     *          1： 播放结束
     *          2： 计费结束
     *          -1 或其它： 播放失败
     */
    @Override
    public void notifyAdvTrackerStates(String s, int i) {

        d(" advId" + s +" , code:"+i);

    }
}
