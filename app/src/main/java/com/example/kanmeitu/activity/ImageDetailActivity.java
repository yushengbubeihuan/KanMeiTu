package com.example.kanmeitu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.kanmeitu.R;
import com.example.kanmeitu.util.Constants;
import com.example.kanmeitu.util.ImageUtil;
import com.github.chrisbanes.photoview.PhotoView;

public class ImageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        PhotoView pv = findViewById(R.id.pv);

        //获取传递过来的参数
        String url = getIntent().getStringExtra(Constants.ID);

        //显示图片
        ImageUtil.show(this, pv, url);

    }
}
