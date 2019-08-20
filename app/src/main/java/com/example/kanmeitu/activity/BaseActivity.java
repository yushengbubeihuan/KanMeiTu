package com.example.kanmeitu.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kanmeitu.util.SharedPreferencesUtil;

public class BaseActivity extends AppCompatActivity {

    //访问修饰符改为protected
    protected SharedPreferencesUtil sp;

    /**
     * 因为在于类中调用了setContentView设置布局
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //配置文件
        sp = SharedPreferencesUtil.getInstance(getApplicationContext());
    }
}
