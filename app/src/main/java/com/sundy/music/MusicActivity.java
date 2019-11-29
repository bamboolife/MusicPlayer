package com.sundy.music;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.navigation.NavigationView;
import com.sundy.common.base.BaseActivity;
import com.sundy.music.controller.LeftNavigationController;

import butterknife.BindView;

@Route(path = "/music/main")
public class MusicActivity extends BaseActivity {
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.activity_main_toolbar)
    Toolbar mToolbar;
    private ActionBarDrawerToggle toggle;
    private Menu menu;
    LeftNavigationController mLeftNavigationController;

    @Override
    protected int getLayoutId() {
        return R.layout.smc_music_layout;
    }

    @Override
    protected void initViews(Bundle bundle) {
        initToolbar();
        mLeftNavigationController = new LeftNavigationController(this,mNavigationView,mDrawerLayout);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        toggle=new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        this.menu = menu;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_main_search:

                break;
            case android.R.id.home:

                break;
            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
