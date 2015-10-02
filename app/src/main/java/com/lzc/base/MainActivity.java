package com.lzc.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //腾讯bug
        CrashReport.initCrashReport(this.getBaseContext(), "900010042", false);
        //测试bug
//        CrashReport.testJavaCrash();

        //推送通知umeng
        PushAgent mPushAgent = PushAgent.getInstance(this.getBaseContext());
        mPushAgent.enable();
        PushAgent.getInstance(this.getBaseContext()).onAppStart();

        //容云
//        String Token = "d6bCQsXiupB/4OyGkh+TOrI6ZiT8q7s0UEaMPWY0lMxmHdi1v/AAJxOma4aYXyaivfPIJjNHdE+FMH9kV/Jrxg==";//test
//        /**
//         * IMKit SDK调用第二步
//         *
//         * 建立与服务器的连接
//         *
//         */
//        RongIM.connect(Token, new RongIMClient.ConnectCallback() {
//            @Override
//            public void onTokenIncorrect() {
//                //Connect Token 失效的状态处理，需要重新获取 Token
//            }
//
//            @Override
//            public void onSuccess(String userId) {
//                Log.e("MainActivity", "——onSuccess— -"+userId);
//            }
//
//            @Override
//            public void onError(RongIMClient.ErrorCode errorCode) {
//                Log.e("MainActivity", "——onError— -"+errorCode);
//            }
//        });

    }


    //Umeng统计
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


}
