package com.lzc.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

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
