package com.lzc.company;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.feedback.PgyFeedbackShakeManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.umeng.update.UmengUpdateAgent;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

//import io.rong.imkit.RongIM;
//import io.rong.imlib.RongIMClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //腾讯bug
        CrashReport.initCrashReport(this.getBaseContext(), "900014444", false);
        //测试bug
        CrashReport.testJavaCrash();

        //推送通知umeng
        PushAgent mPushAgent = PushAgent.getInstance(this.getBaseContext());
        mPushAgent.enable();
        PushAgent.getInstance(this.getBaseContext()).onAppStart();

        //蒲公英反馈sdk
        PgyCrashManager.register(this);

        //Umeng 更新sdk
        UmengUpdateAgent.update(this);


        //获取wifi服务
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            String ip = getLocalIpAddress();
            Log.i("GPRS ip",ip);
        }else{
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            String ip = intToIp(ipAddress);
            Log.i("WIFI ip",ip);
        }

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DialogPlus dialog = DialogPlus.newDialog(MainActivity.this)
//                        .setAdapter(adapter)
                        .setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                            }
                        })
                        .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                dialog.show();
                //do something
            }
        });






//        EditText et = (EditText)findViewById(R.id.EditText01);
//        et.setText(ip);


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

    //返回ip地址
    private String intToIp(int i) {

        return (i & 0xFF ) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ( i >> 24 & 0xFF) ;
    }
    //GPRS返回ip地址
    public String getLocalIpAddress()
    {
        try
        {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
            {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress())
                    {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        catch (SocketException ex)
        {
//            Log.e("WifiPreference IpAddress", ex.toString());
        }
        return null;
    }



    //Umeng统计
    public void onResume() {
        PgyFeedbackShakeManager.register(MainActivity.this, false);
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        PgyFeedbackShakeManager.unregister();
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
