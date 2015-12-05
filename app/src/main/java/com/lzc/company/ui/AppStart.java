package com.lzc.company.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.lzc.company.MainActivity;
import com.lzc.company.R;

public class AppStart extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 防止第三方跳转时出现双实例
//        Activity aty = AppManager.getActivity(MainActivity.class);
//        if (aty != null && !aty.isFinishing()) {
//            finish();
//        }
        // SystemTool.gc(this); //针对性能好的手机使用，加快应用相应速度

        final View view = View.inflate(this, R.layout.app_start, null);
        setContentView(view);
        // 渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
        aa.setDuration(800);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationStart(Animation animation) {}
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        int cacheVersion = PreferenceHelper.readInt(this, "first_install",
//                "first_install", -1);
//        int currentVersion = TDevice.getVersionCode();
//        if (cacheVersion < currentVersion) {
//            PreferenceHelper.write(this, "first_install", "first_install",
//                    currentVersion);
//            cleanImageCache();
//        }
//    }
//
//    private void cleanImageCache() {
//        final File folder = FileUtils.getSaveFolder("OSChina/imagecache");
//        KJAsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                for (File file : folder.listFiles()) {
//                    file.delete();
//                }
//            }
//        });
//    }

    /**
     * 跳转到...
     */
    private void redirectTo() {
//        Intent uploadLog = new Intent(this, LogUploadService.class);
//        startService(uploadLog);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}


