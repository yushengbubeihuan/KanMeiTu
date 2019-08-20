package com.example.kanmeitu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kanmeitu.MainActivity;
import com.example.kanmeitu.R;
import com.example.kanmeitu.util.Constants;
import com.example.kanmeitu.util.RegexUtil;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        Button bt_login = findViewById(R.id.bt_login);

        bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_login:
                login();
                break;
        }
    }

    private void login() {

        //获取用户输入的用户名和密码，用来校验
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        //判断用户是否输入用户名和密码
        if(!RegexUtil.isEmail(username)){
            Toast.makeText(this, R.string.email_hint, Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, R.string.password_hint, Toast.LENGTH_SHORT).show();
            return;
        }

        //通过正则表达式判断输入的用户名或密码的格式是否正确
        if (!RegexUtil.isEmail(username)) {
            Toast.makeText(this, R.string.email_error, Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length() < 6 || password.length() >15 ){
            Toast.makeText(this, R.string.password_length_error, Toast.LENGTH_SHORT).show();
        }

        //判断用户名和密码是否正确
        //这里是将用户名和密码放到了本地，没有连接服务端的登录接口请求用户名和密码的判断
        if(Constants.USERNAMW.equals(username) && Constants.PASSWORD.equals(password)){
            //登陆后设置一个标志，下次就不用再登陆了
            sp.setLogin(true);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            //关闭当前页面
            finish();
        }else{
            //登录失败，进行提示
            Toast.makeText(this, R.string.username_or_password_error, Toast.LENGTH_SHORT).show();
        }

    }
}
