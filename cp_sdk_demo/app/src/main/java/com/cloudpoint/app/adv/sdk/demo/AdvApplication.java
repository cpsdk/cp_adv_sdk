package com.cloudpoint.app.adv.sdk.demo;

import android.app.Application;
import android.app.admin.DevicePolicyManager;
import android.util.Log;

import com.cloudpoint.plugins.sdk.adv.AdvPlayerHandler;
import com.cloudpoint.plugins.sdk.adv.CPAdvSdk;
import com.cloudpoint.shell.adv.DefaultAdvManager;
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
 */

public class AdvApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // 3.设置日志输出
        System.setProperty(CPAdvSdk.LOG_ADV,"false");
        System.setProperty(CPAdvSdk.LOG_DEBUG,"false");
        System.setProperty(CPAdvSdk.LOG_FILE,"false");
        System.setProperty(CPAdvSdk.LOG_HTTP,"false");
        System.setProperty(CPAdvSdk.LOG_WEBSOCKET,"false");
        System.setProperty(CPAdvSdk.LOG_DEBUG_EX,"false");


        // 4. 初始化应用
        boolean doInitialized = CPAdvSdk.init(AdvApplication.this);

        if(doInitialized){
            // 5. 增加监听接口。

            //DefaultAdvManager defaultAdvManager;

            AdvPlayerHandler handler = AdvPlayerHandler.getInstance();
            AdvEventListener advEventListener = new AdvEventListener();
            handler.setAdvertisementEvent(advEventListener);
            handler.enableManualMode(true);

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
            CPAdvSdk.setLocation(loc);
        }





    }


    @Override
    public void onTerminate() {


        super.onTerminate();

    }
}
