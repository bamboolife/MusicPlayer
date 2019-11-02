package com.sundy.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sundy.music.service.MusicService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart, btnStop, btnPause, btnLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btn_start);
        btnPause = findViewById(R.id.btn_pause);
        btnStop = findViewById(R.id.btn_stop);
        btnLoop = findViewById(R.id.btn_loop);
        btnLoop.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
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
}
