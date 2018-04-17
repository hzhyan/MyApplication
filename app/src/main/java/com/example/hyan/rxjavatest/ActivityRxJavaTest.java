package com.example.hyan.rxjavatest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.hyan.R;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by huangyan on 2018/2/1.
 */

public class ActivityRxJavaTest extends Activity implements View.OnClickListener {

    public static void launch(Context context) {
        Intent intent = new Intent(context, ActivityRxJavaTest.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_test);
        findViewById(R.id.test_rx_java).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_rx_java:
                testRXJava();
                break;
        }
    }

    private void testRXJava() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            synchronized (this) {
                wait(10 * 1000);
            }
            emitter.onNext(100);
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> Toast.makeText(ActivityRxJavaTest.this, "number = " + integer, Toast.LENGTH_LONG).show());
    }
}
