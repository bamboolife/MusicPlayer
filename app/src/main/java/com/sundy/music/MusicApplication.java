package com.sundy.music;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sundy.common.BuildConfig;

import butterknife.ButterKnife;

/**
 * 项目名称：MusicPlayer
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2019-11-29 09:53
 * 描述：
 */
public class MusicApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
initRouter();
    }

    private void initRouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }
}
