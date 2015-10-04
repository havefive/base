package com.lzc.base;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by lizhaocai on 15/10/2.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化融云
         */
        RongIM.init(this);
    }
}
