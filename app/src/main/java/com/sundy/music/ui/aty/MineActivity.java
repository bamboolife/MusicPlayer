package com.sundy.music.ui.aty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sundy.common.base.BaseActivity;
import com.sundy.music.R;
@Route(path = "/music/mine")
public class MineActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.smc_mine_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }
}
