package com.example.administrator.myapplication.module;

import com.example.administrator.myapplication.net.API;
import com.example.administrator.myapplication.net.LoginApi;
import com.example.administrator.myapplication.net.LoginApiService;
import com.example.administrator.myapplication.net.MyInterceptor;
import com.example.administrator.myapplication.net.ProjectApi;
import com.example.administrator.myapplication.net.ProjectApiService;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/8 0008.
 */
@Module
public class HttpModule {

    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS);
    }

    @Provides
    LoginApi provideLoginApi( OkHttpClient.Builder builder){

       /* OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();*/

        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        LoginApiService loginApiService = retrofit.create(LoginApiService.class);
        return LoginApi.getLoginApi(loginApiService);


    }


    @Provides
    ProjectApi provideProjectApi(OkHttpClient.Builder builder){


        builder.addInterceptor(new MyInterceptor());
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        ProjectApiService projectApiService = retrofit.create(ProjectApiService.class);
        return ProjectApi.getProjectApi(projectApiService);


    }
}
