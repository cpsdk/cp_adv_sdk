package com.cloudpoint.app.adv.sdk.demo;

import android.app.Application;

import com.cloudpoint.plugins.sdk.adv.AdvPlayerHandler;
import com.cloudpoint.plugins.sdk.adv.CPAdvSdk;


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
        System.setProperty(CPAdvSdk.LOG_ADV,"true");
        System.setProperty(CPAdvSdk.LOG_DEBUG,"true");
        System.setProperty(CPAdvSdk.LOG_FILE,"true");
        System.setProperty(CPAdvSdk.LOG_HTTP,"true");
        System.setProperty(CPAdvSdk.LOG_WEBSOCKET,"true");

        // 4. 初始化应用
        boolean doInitialized = CPAdvSdk.init(AdvApplication.this);

        if(doInitialized){
            // 5. 增加监听接口。

            AdvPlayerHandler handler = AdvPlayerHandler.getInstance();
            AdvEventListener advEventListener = new AdvEventListener();
            handler.setAdvertisementEvent(advEventListener);
        }


    }


    @Override
    public void onTerminate() {


        super.onTerminate();

    }
}
