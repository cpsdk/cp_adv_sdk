package com.cloudpoint.app.adv.sdk.demo;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.cloudpoint.plugins.asset.module.fsm.impl.VMCheckNetwork;
import com.cloudpoint.plugins.asset.service.VMService;
import com.cloudpoint.plugins.log.CPLogger;
import com.cloudpoint.plugins.log.CrashHandler;
import com.cloudpoint.plugins.network.NetworkStats;
import com.cloudpoint.plugins.sdk.adv.AdvPlayerHandler;
import com.cloudpoint.plugins.sdk.adv.CPAdvSdk;
import com.cloudpoint.plugins.sdk.adv.IAdvPlayer;
import com.cloudpoint.plugins.sdk.adv.ISDkState;
import com.cloudpoint.shell.adv.mediaplayer.IAdvertisementPlayerListener;
import com.cloudpoint.shell.common.device.DeviceAddress;
import com.cloudpoint.shell.common.device.DeviceLatitudeLongitude;
import com.cloudpoint.shell.common.device.DeviceLocation;

import com.cloudpoint.shell.device.broadcast.DeviceInformationBroadCastReciever;
import com.cloudpoint.shell.device.location.BaiduLocationService;
import com.cloudpoint.shell.file.FileDownloadService;

import com.google.gson.Gson;


/**
 * @author sparrow
 * @date 2019/8/21
 * @copyright Beijing CloudPoint Technology Co.,Ltd.
 * @email qiuzhang.rui@cpoao.com
 * @description:
 *
 * //System.setProperty(VMCheckNetwork.OFFLINE_MODE,"true");
 * System.setProperty("offline_mode","true");
 */

public class AdvApplication extends Application {


    boolean  enableBindStatedNotified = true;
    boolean  enableSdkDataUsage = true;

    @Override
    public void onCreate() {
        super.onCreate();

        //设置是否输出debug日志
        CPAdvSdk.setDebug(BuildConfig.DEBUG);






        // 4. 初始化应用
        // 可以选择两种方式初始化。
        boolean initialized = enableBindStatedNotified?

                CPAdvSdk.init(AdvApplication.this,
                            "201901155854",
                            true,
                            true,
                            new ISDkState() {

                                @Override
                                public void onBinded() {
                                    //设备已绑定，回调通知已绑定
                                    System.out.println("Device binded!");
                                }
                            }):

                CPAdvSdk.init(AdvApplication.this,
                        "201901155854",
                        true,
                        true);




        if(initialized) {
            // 5. 检测是否初始化播放器
            CPAdvSdk.setPlayerListener(new IAdvPlayer() {
                @Override
                public void onPlayerError() {
                    System.out.println("播放器未加载！");
                }
            });

            //处理计费或手动播放线上广告
            CPAdvSdk.setAdvertisementEvent(new AdvEventListener());

            if(BuildConfig.ENABLE_BAIDU_LOCATION_API){
                locationWithBaiduApi();
            }else{
                locationWithoutBaiduApi();
            }

            //输出流量使用
            if(enableSdkDataUsage)
                    printUsage();



        }


    }





    private void printUsage(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                while(true){

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    long ts = System.currentTimeMillis();
                    NetworkStats.DataUsage usage = CPAdvSdk.getDataUsage();

                    long bytes = usage.getRx()+usage.getTx();

                    long k = 1024;
                    long m = 1024*k;
                    long g = 1024*m;
                    double u = 0.0f;
                    String data ="";
                    if(bytes/g>1){
                        u = bytes*1.0/g;
                        data = String.format(" usage : %5.2f g , used : %5d ms",u,(System.currentTimeMillis()-ts));
                    } else if (bytes/m>1){
                        u = bytes*1.0/m;
                        data = String.format(" usage : %5.2f m , used : %5d ms",u,(System.currentTimeMillis()-ts));
                    }
                    else if(bytes/k>1){
                        u = bytes/k;

                        data = String.format(" usage : %5.2f k  , used : %5d ms",u,(System.currentTimeMillis()-ts));

                    }else{
                        u = bytes;
                        data = String.format(" usage : %5.2f b , used : %5d ms",u,(System.currentTimeMillis()-ts));

                    }

                    System.out.println(data);
                }

            }
        }).start();
    }


    private void locationWithBaiduApi(){
        //6. 设置 location信息
        String loc = null; // 使用集成百度定位sdk时，将loc设置为空
        CPAdvSdk.setLocation(loc);
    }


    private void locationWithoutBaiduApi(){
        //设置位置信息
        DeviceAddress address = new DeviceAddress();
        address.setAddress("中国北京市朝阳区北苑路229号");
        address.setCity("北京市");
        address.setDistrict("朝阳区");
        address.setLocationDescrible("在金泉港附近");
        address.setProvince("北京市");
        address.setStreet("北苑路");

        DeviceLatitudeLongitude gps = new DeviceLatitudeLongitude();
        gps.setCoorType("bd09ll");
        gps.setErrorCode(161);
        gps.setLangtitude(116.423292);
        gps.setLatitude(40.010727);
        gps.setRadius(50.499428f);

        DeviceLocation location = new DeviceLocation(gps, address);
        Gson g = new Gson();
        String loc = g.toJson(location);
        //
        // {"location":{"address":"中国北京市朝阳区北苑路229号","city":"北京市","disrict":"朝阳区","location_desc":"在金泉港附近","province":"北京市","street":"北苑路"},"gps":{"coor_type":"bd09ll","error_code":161,"langtitude":116.423292,"latitude":40.010727,"radius":50.499428}}
        //6. 设置 location信息
        loc = null; // 使用集成百度定位sdk时，将loc设置为空
        CPAdvSdk.setLocation(loc);

    }


    @Override
    public void onTerminate() {


        super.onTerminate();

    }
}
