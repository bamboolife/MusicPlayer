package com.sundy.music.ui.aty;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sundy.common.base.BaseActivity;
import com.sundy.music.R;
@Route(path = "/music/web")
public class WebActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.smc_web_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }
}
