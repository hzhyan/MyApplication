package com.example.hyan.animation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.hyan.R;

/**
 * Created by huangyan on 2018/9/1.
 */
public class ActivityAnimation extends Activity {

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ActivityAnimation.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
    }
}
