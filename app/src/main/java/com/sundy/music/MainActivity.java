package com.sundy.music;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sundy.common.base.BaseActivity;
import com.sundy.music.service.MusicService;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.OnClick;
public class MainActivity extends BaseActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle bundle) {
        SimpleDateFormat date =
                new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String logDate = date.format(new Date());
// Applies the date and time to the name of the trace log.
        // Debug.startMethodTracing("sample-" + logDate);
    }

    @OnClick({R.id.btn_start, R.id.btn_pause, R.id.btn_stop, R.id.btn_loop})
    public void musicEvent(View v) {
        int status = 0;
        switch (v.getId()) {
            case R.id.btn_start:
                status = 0;
                break;
            case R.id.btn_pause:
                status = 1;
                break;
            case R.id.btn_stop:
                status = 2;
                break;
            case R.id.btn_loop:
                status = 3;
                break;

        }
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        Bundle bundle = new Bundle();
        bundle.putInt("status", status);
        intent.putExtras(bundle);
        startService(intent);
    }

    @OnClick(R.id.button)
    public void jumpEvent() {
        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // Debug.stopMethodTracing();
    }
}
