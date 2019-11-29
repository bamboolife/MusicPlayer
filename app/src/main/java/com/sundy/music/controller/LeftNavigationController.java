package com.sundy.music.controller;

import android.app.Activity;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.navigation.NavigationView;
import com.sundy.music.R;

/**
 * 项目名称：MusicPlayer
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2019-11-29 18:30
 * 描述：左部控制器
 */
public class LeftNavigationController implements NavigationView.OnNavigationItemSelectedListener {
    private final Activity mActivity;
    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;

    public LeftNavigationController(Activity activity, NavigationView mNavigationView, DrawerLayout mDrawerLayout) {
        this.mActivity = activity;
        this.mNavigationView = mNavigationView;
        this.mDrawerLayout = mDrawerLayout;
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.setting_scan:// 歌曲扫描
              //  ARouter.getInstance().build("").navigation();
                break;
            case R.id.setting_sleep://定时停止播放
                ARouter.getInstance().build("/music/time").navigation();
                break;
            case R.id.setting_image_wall://图片墙
                ARouter.getInstance().build("/music/imagewall").navigation();
                break;
            case R.id.setting_play_ui://播放界面风格
                ARouter.getInstance().build("/music/theme").navigation();
                break;
            case R.id.setting_theme_color_custom://主题颜色定制
            //    ARouter.getInstance().build("/music/theme").navigation();
                break;
            case R.id.setting_night_mode://夜间模式
              //  ARouter.getInstance().build("/music/imagewall").navigation();
                break;
            case R.id.setting_user_guide://用户指南
                ARouter.getInstance().build("/music/web").navigation();
                break;
            case R.id.setting_set:
                ARouter.getInstance().build("/music/setting").navigation();
                break;
            case R.id.setting_quit:

                break;

            default:

                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}
