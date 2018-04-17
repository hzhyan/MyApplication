package com.example.hyan.resourceupdate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hyan.R;
import com.netease.skinswitch.SkinManager;
import com.netease.skinswitch.SkinManagerConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * Created by huangyan on 2018/1/26.
 */

public class ActivityResourceUpdate extends Activity implements View.OnClickListener {

    private Resources mResources;
    private static final String TAG = "ActivityResourceUpdate";


    public static void launch(Context context) {
        Intent intent = new Intent(context, ActivityResourceUpdate.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_update);
        findViewById(R.id.btn_show_toast).setOnClickListener(this);
        findViewById(R.id.btn_change_resource).setOnClickListener(this);
        mResources = getResources();
    }

    @Override
    public void onClick(View view) {
        if (view == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_show_toast:
                Toast.makeText(ActivityResourceUpdate.this, getToastString(), Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_change_resource:
                replaceResource();
                break;
        }
    }


    private void replaceResource() {


        String skinPath = getFilesDir() + File.separator +"skin";
        File dir = new File(skinPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File skinFile = new File(skinPath, "res.zip");
//        SkinManagerConfig skinManagerConfig = new SkinManagerConfig(skinPath, "purple_skin.zip", "purple_skin");
//        SkinManager.getInstance().initConfig(getApplicationContext(), skinManagerConfig, true);
        copyToFile(getResources().openRawResource(R.raw.res), skinFile);

        AssetManager assetManager = null;
        try {
            assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, skinFile.getAbsolutePath());
        } catch (InstantiationException e) {
            Log.e(TAG, e.getMessage());
        } catch (IllegalAccessException e) {
            Log.e(TAG, e.getMessage());
        } catch (NoSuchMethodException e) {
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } catch (Error e) {
            Log.e(TAG, e.getMessage());
        }
        Resources superRes = getResources();
        mResources = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
        if (mResources == null) {
            return;
        }
    }

    private String getToastString() {
        int stringId = mResources.getIdentifier("toast_text", "string", getPackageName());
        return mResources.getString(stringId);
    }

    public static boolean copyToFile(InputStream inputStream, File destFile) {
        try {
            if (destFile.exists()) {
                destFile.delete();
            }
            OutputStream out = new FileOutputStream(destFile);
            try {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) >= 0) {
                    out.write(buffer, 0, bytesRead);
                }
            } finally {
                out.close();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
