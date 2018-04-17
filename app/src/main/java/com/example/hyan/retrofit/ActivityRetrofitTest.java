package com.example.hyan.retrofit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.hyan.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huangyan on 2018/3/20.
 */

public class ActivityRetrofitTest extends Activity implements View.OnClickListener {

    private Button mRepoListBtn;
    private GitHubService mService;


    public static void launch(Context context) {
        Intent intent = new Intent(context, ActivityRetrofitTest.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);
        init();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.get_repo_list_btn) {
            doGetRepoList();
        }
    }

    private void init() {
        mRepoListBtn = findViewById(R.id.get_repo_list_btn);
        mRepoListBtn.setOnClickListener(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = retrofit.create(GitHubService.class);
    }

    private void doGetRepoList() {
        Call<List<Repo>> repos = mService.listRepos("hzhyan");
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
    }
}
