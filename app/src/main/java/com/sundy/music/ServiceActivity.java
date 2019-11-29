package com.sundy.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sundy.music.service.BackgroundService;
import com.sundy.music.service.GrayService;
import com.sundy.music.service.WhiteService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceActivity extends AppCompatActivity {
    /**
     * 黑色唤醒广播的action
     */
    private final static String BLACK_WAKE_ACTION = "com.sundy.wake.black";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
    }

    @OnClick({ R.id.btn_white,R.id.btn_gray,R.id.btn_black,R.id.btn_background})
    public void onclickEvent(View view){
        switch (view.getId()){
            case R.id.btn_white:// //系统正常的前台Service，白色保活手段
                Intent whiteIntent = new Intent(getApplicationContext(), WhiteService.class);
                startService(whiteIntent);
                break;
            case R.id.btn_gray://利用系统漏洞，灰色保活手段（API < 18 和 API >= 18 两种情况）
                Intent grayIntent = new Intent(getApplicationContext(), GrayService.class);
                startService(grayIntent);
                break;
            case R.id.btn_black://拉帮结派，黑色保活手段，利用广播唤醒
                Intent blackIntent = new Intent();
                blackIntent.setAction(BLACK_WAKE_ACTION);
                sendBroadcast(blackIntent);
                break;
            case R.id.btn_background:////普通的后台进程
                Intent bgIntent = new Intent(getApplicationContext(), BackgroundService.class);
                startService(bgIntent);

                break;
        }
    }
}
