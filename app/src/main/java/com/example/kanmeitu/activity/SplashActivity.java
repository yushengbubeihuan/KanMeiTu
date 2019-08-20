package com.example.kanmeitu.activity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.example.kanmeitu.MainActivity;
import com.example.kanmeitu.R;

/**
 * 启动界面
 *
 * 3秒钟后进入登录界面
 *
 * 启动界面根据苹果开发者文档可以理解为是用来让用户加快启动的
 *而不是在上边显示你的广告和商标的（Android的开发我们暂时没有找到相关的定义）
 *
 */
public class SplashActivity extends BaseActivity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            next();
        }
    };

    private void next() {
        Intent intent = null;
        if(sp.isLogin()){
            //已经登录跳转到首页
            intent = new Intent(this, MainActivity.class);
        }else{
            //否则跳转到登陆界面
            intent = new Intent(this, LoginActivity.class);
        }

        startActivity(intent);

        //关闭当前页面
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //3秒钟后调用这里
                //
                //这里调用是在子线程，不能直接操作UI，需要使用handler切换到主线程
                //用多个线程的目的，解决如果有耗时任务，那么就会卡界面
                //用了多线程后，将耗时任务放到子线程，主线程（UI线程）就不会卡住
                handler.sendEmptyMessage(0);
            }
        },3000);
    }
}
