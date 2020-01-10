package com.cloudpoint.app.adv.sdk.demo;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.cloudpoint.plugins.log.CPLogger;
import com.cloudpoint.plugins.log.CrashHandler;
import com.cloudpoint.plugins.sdk.adv.AdvPlayerHandler;
import com.cloudpoint.plugins.sdk.adv.CPAdvSdk;
import com.cloudpoint.plugins.sdk.adv.IAdvPlayer;
import com.cloudpoint.shell.common.device.DeviceAddress;
import com.cloudpoint.shell.common.device.DeviceLatitudeLongitude;
import com.cloudpoint.shell.common.device.DeviceLocation;
import com.google.gson.Gson;


/**
 * @author sparrow
 * @date 2019/8/21
 * @copyright Beijing CloudPoint Technology Co.,Ltd.
 * @email qiuzhang.rui@cpoao.com
 * @description:
 *
 * //System.setProperty(VMCheckNetwork.OFFLINE_MODE,"true");
 */

public class AdvApplication extends Application {

    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate() {
        super.onCreate();
        //可输出异常日志
        CrashHandler.createHandler(getApplicationContext());
        //设置是否输出debug日志
        CPAdvSdk.setDebug(BuildConfig.DEBUG);


        // 4. 初始化应用
        boolean doInitialized = CPAdvSdk.init(AdvApplication.this,"10000");

        if(doInitialized){
            // 5. 增加监听接口。

            //DefaultAdvManager defaultAdvManager;

            final AdvPlayerHandler handler = AdvPlayerHandler.getInstance();
            AdvEventListener advEventListener = new AdvEventListener();
            handler.setAdvertisementEvent(advEventListener);
            //启用手动播放模式
            handler.enableManualMode(true);
            //关闭轮播图
            handler.enableRoundPlay(true);
            //开启广告/关闭广告
            //handler.enableAdv(true);


            if(false) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 测试关闭广告
                        CPLogger.d(" Disable Online Adv");
                        handler.enableAdv(false);
                    }
                }, 30000);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //测试开启广告
                        CPLogger.d(" Enable Online Adv");
                        handler.enableAdv(true);
                    }
                }, 60000);

            }
            // 检测播放器是否能够正常工作，如果报出异常，是播放器没有初始化成功
            // If the player exception.
            handler.setAdvPlayerLisenter(new IAdvPlayer() {
                @Override
                public void onPlayerError() {
                    // if this happens
                    CPLogger.d("AdvPlayer not Running.");
                }
            });



            /**
             *
             *
             * loc['address']='中国北京市朝阳区北苑路229号'
             loc['city']='北京市'
             loc['country']='中国'
             loc['disrict']='朝阳区'
             loc['location_desc']='在金泉港附近'
             loc['province']='北京市'
             loc['street']='北苑路'
             gps['coor_type']='bd09ll'
             gps['error_code']=161
             gps['langtitude']=116.423292
             gps['latitude']=40.010727
             gps['radius']=50.499428
             */
            DeviceAddress address =new DeviceAddress();
            address.setAddress("中国北京市朝阳区北苑路229号");
            address.setCity("北京市");
            address.setDistrict("朝阳区");
            address.setLocationDescrible("在金泉港附近 国伟aaaa");
            address.setProvince("北京市");
            address.setStreet("北苑路");

            DeviceLatitudeLongitude gps = new DeviceLatitudeLongitude();
            gps.setCoorType("bd09ll");
            gps.setErrorCode(161);
            gps.setLangtitude(116.423292);
            gps.setLatitude(40.010727);
            gps.setRadius(50.499428f);

            DeviceLocation location = new DeviceLocation(gps,address);
            Gson g = new Gson();
            String loc =g.toJson(location);
            System.out.println("loc:"+loc);

            //
            // {"location":{"address":"中国北京市朝阳区北苑路229号","city":"北京市","disrict":"朝阳区","location_desc":"在金泉港附近","province":"北京市","street":"北苑路"},"gps":{"coor_type":"bd09ll","error_code":161,"langtitude":116.423292,"latitude":40.010727,"radius":50.499428}}
            //6. 设置 location信息
            //loc = null; // 使用集成百度定位sdk时，将loc设置为空

            CPAdvSdk.setLocation(loc);
        }





    }


    @Override
    public void onTerminate() {


        super.onTerminate();

    }
}
