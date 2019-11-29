package com.sundy.music;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.sundy.common.base.BaseActivity;
import com.sundy.common.utils.DensityUtils;
import com.sundy.common.utils.DrawableUtils;

import java.util.Random;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {

    @BindView(R2.id.splash_container)
    View container;
    @BindView(R2.id.splash_m)
    TextView splashM;
    @BindView(R2.id.splash_u)
    TextView splashU;
    @BindView(R2.id.splash_s)
    TextView splashS;
    @BindView(R2.id.splash_i)
    TextView splashI;
    @BindView(R2.id.splash_c)
    TextView splashC;
    @BindView(R2.id.splash_o)
    TextView splashO;
    @BindView(R2.id.splash_c1)
    TextView splashC1;
    @BindView(R2.id.splash_o1)
    TextView splashO1;
    @BindView(R2.id.splash_name)
    TextView splashName;
    @BindView(R2.id.splash_logo)
    ImageView splashLogo;
    private TextView[] mTextViews;

    @Override
    protected int getLayoutId() {
        return R.layout.smc_splash_layout;
    }

    @Override
    protected void initViews(Bundle bundle) {
        ImmersionBar.with(this)
                .hideBar(BarHide.FLAG_HIDE_BAR)
                .init();
        DrawableUtils drawableUtils = new DrawableUtils();
        GradientDrawable gd = drawableUtils.getGradient(GradientDrawable.Orientation.TL_BR, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorPrimaryDark));
        container.setBackground(gd);
        mTextViews=new TextView[]{
           splashM,
           splashU,
           splashS,
           splashI,
           splashC,
           splashO,
           splashC1,
           splashO1,
        };
        //此处需要修改  匿名内部类会导致内存泄漏
        mTextViews[0].post(new Runnable() {
            @Override
            public void run() {
                for (TextView t:mTextViews) {
                    t.setVisibility(View.VISIBLE);
                    startTextInAnim(t);
                }
            }
        });
    }
    private void startTextInAnim(TextView t) {
        Random r = new Random();

        int x = r.nextInt(DensityUtils.getScreenWidthPixels(this) * 4 / 3);
        int y = r.nextInt(DensityUtils.getScreenHeightPixels(this)* 4 / 3);
        float s = r.nextFloat() + 4.0f;
        ValueAnimator tranY = ObjectAnimator.ofFloat(t, "translationY", y - t.getY(), 0);
        ValueAnimator tranX = ObjectAnimator.ofFloat(t, "translationX", x - t.getX(), 0);
        ValueAnimator scaleX = ObjectAnimator.ofFloat(t, "scaleX", s, 1.0f);
        ValueAnimator scaleY = ObjectAnimator.ofFloat(t, "scaleY", s, 1.0f);
        ValueAnimator alpha = ObjectAnimator.ofFloat(t, "alpha", 0.0f, 1.0f);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(1800);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.play(tranX).with(tranY).with(scaleX).with(scaleY).with(alpha);
        if (t == findViewById(R.id.splash_o1)) {
            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    startFinalAnim();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
        set.start();
    }

    private void startFinalAnim() {
        final ImageView image = (ImageView) findViewById(R.id.splash_logo);
        final TextView name = (TextView) findViewById(R.id.splash_name);

        ValueAnimator alpha = ObjectAnimator.ofFloat(image, "alpha", 0.0f, 1.0f);
        alpha.setDuration(1000);
        ValueAnimator alphaN = ObjectAnimator.ofFloat(name, "alpha", 0.0f, 1.0f);
        alphaN.setDuration(1000);
        ValueAnimator tranY = ObjectAnimator.ofFloat(image, "translationY", -image.getHeight() / 3, 0);
        tranY.setDuration(1000);
        ValueAnimator wait = ObjectAnimator.ofInt(0, 100);
        wait.setDuration(1000);
        wait.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        if (initComplete) {
//                            startMainActivity();
//                        } else {
//                            animComplete = true;
//                        }
                        ARouter.getInstance().build("/music/main").navigation();
                        //  Intent intent=new Intent(SplashActivity.this,MusicActivity.class);
                        //startActivity(intent);
                    }
                });
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new LinearInterpolator());
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                image.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        set.play(alpha).with(alphaN).with(tranY).before(wait);
        set.start();
    }

}
