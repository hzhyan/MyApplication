package com.example.hyan;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Toast;

import com.example.hyan.animation.ActivityAnimation;
import com.example.hyan.customprogressbar.ActivityCustomProgressBar;
import com.example.hyan.jni.JniTest;
import com.example.hyan.opengl.ActivityOpenGL;
import com.example.hyan.openglshader.ActivityShader;
import com.example.hyan.openglstl.ActivityOpenGLSTL;
import com.example.hyan.resourceupdate.ActivityResourceUpdate;
import com.example.hyan.retrofit.ActivityRetrofitTest;
import com.example.hyan.rxjavatest.ActivityRxJavaTest;

import java.util.ArrayList;


/**
 * Created by huangyan on 2018/1/26.
 */

public class ActivityMain extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_test_resource_change).setOnClickListener(this);
        findViewById(R.id.btn_test_rx_java).setOnClickListener(this);
        findViewById(R.id.btn_test_custom_progress_bar).setOnClickListener(this);
        findViewById(R.id.btn_test_retrofit).setOnClickListener(this);
        findViewById(R.id.btn_test_jni).setOnClickListener(this);
        findViewById(R.id.btn_test_opengl).setOnClickListener(this);
        findViewById(R.id.btn_test_opengl_stl).setOnClickListener(this);
        findViewById(R.id.btn_test_opengl_shader).setOnClickListener(this);
        findViewById(R.id.btn_test_animation).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_test_resource_change:
                ActivityResourceUpdate.launch(ActivityMain.this);
                break;
            case R.id.btn_test_rx_java:
                ActivityRxJavaTest.launch(ActivityMain.this);
                break;
            case R.id.btn_test_custom_progress_bar:
                ActivityCustomProgressBar.launch(ActivityMain.this);
                break;
            case R.id.btn_test_retrofit:
                ActivityRetrofitTest.launch(ActivityMain.this);
                break;
            case R.id.btn_test_jni:
                JniTest test = new JniTest();
                int num = test.nativeMain();
                Toast.makeText(ActivityMain.this, "num = " + num, Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_test_opengl:
                ActivityOpenGL.launch(ActivityMain.this);
                break;
            case R.id.btn_test_opengl_stl:
                ActivityOpenGLSTL.launch(ActivityMain.this);
                break;
            case R.id.btn_test_opengl_shader:
                ActivityShader.launch(ActivityMain.this);
                break;
            case R.id.btn_test_animation:
                ActivityAnimation.launch(ActivityMain.this);
                break;
            default:
                break;
        }
    }
}
