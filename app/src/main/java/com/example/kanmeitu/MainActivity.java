package com.example.kanmeitu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kanmeitu.activity.BaseActivity;
import com.example.kanmeitu.activity.ImageDetailActivity;
import com.example.kanmeitu.activity.LoginActivity;
import com.example.kanmeitu.adapter.ImageAdapter;
import com.example.kanmeitu.api.Api;
import com.example.kanmeitu.domain.Image;
import com.example.kanmeitu.domain.response.ListResponse;
import com.example.kanmeitu.util.Constants;
import com.example.kanmeitu.util.ToastUtil;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        //显示两页这里我们实现的宽高都是一样的
        //最好的效果其实是根据图片的宽高来进行所放的
        //因为每一张图片的大小是不一样的

        //这里使用了GridLaoutManager，它会显示类似九宫格布局
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutManager);

        //设置数据
        /*ArrayList<Image> datas = new ArrayList<>();
        for (int i = 1; i < 15; i++) {
            datas.add(new Image(String.format("http://dev-courses-quick.oss-cn-beijing.aliyuncs.com/%d.jpg", i)));
        }*/

        adapter = new ImageAdapter(this);
        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //ToastUtil.shortToast(MainActivity.this,"click:"+position);

                //点击一个图片调用这里
               Image image = adapter.getData(position);

               //跳转到详细界面
                Intent intent = new Intent(MainActivity.this, ImageDetailActivity.class);

                //通过intent将图片的地址传递到详情界面
                intent.putExtra(Constants.ID, image.getUri());

                startActivity(intent);
            }
        });
        rv.setAdapter(adapter);

        //adapter.setDatas(datas);

        fetchData();

    }

    private void fetchData() {
        Api
                .getInstance()
                .images()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListResponse<Image>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListResponse<Image> imageListResponse) {
                        //当数据请求完毕后，它会解析成对象并返回给我们
                        //真实项目中还会进行一系列错误处理

                        adapter.setDatas(imageListResponse.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        //真实项目中会将错误信息，提示给用户，并写入到日志中
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 退出登录
     * @param view
     */
    public void onLogOutClick(View view) {
        sp.setLogin(false);

        //退出后跳转登录界面
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        //关闭当前页面
        finish();

    }
}
