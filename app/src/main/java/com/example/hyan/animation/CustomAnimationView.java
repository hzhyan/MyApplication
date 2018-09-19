package com.example.hyan.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hyan.R;

/**
 * Created by huangyan on 2018/9/1.
 */
public class CustomAnimationView extends LinearLayout {

    private TextView mUserNameTv;
//    private TextView mByTv;
    private ImageView mLogoIv;

    public CustomAnimationView(Context context) {
        this(context, null);
    }

    public CustomAnimationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createView();
    }

    private void createView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_custom_animation, this);
        mUserNameTv = findViewById(R.id.user_name_tv);
//        mByTv = findViewById(R.id.by_tv);
        mLogoIv = findViewById(R.id.logo_iv);
        initAnimator();
    }

    private void initAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setDuration(740);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                mUserNameTv.setLetterSpacing(2 - currentValue * 1.9f);
//                mUserNameTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16 - currentValue * 2);
//                mUserNameTv.setAlpha((currentValue - 0.3f) / 0.7f);
//                mByTv.setLetterSpacing(2 - currentValue * 2);
//                mByTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16 - currentValue * 2);
//                mByTv.setAlpha((currentValue - 0.3f) / 0.7f);

                setAlpha(currentValue * 5);
            }
        });
        animator.start();


        Animation logoAnimator = AnimationUtils.loadAnimation(getContext(), R.anim.player_end_logo);
        mLogoIv.startAnimation(logoAnimator);
    }
}
