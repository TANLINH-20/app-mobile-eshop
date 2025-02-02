package com.example.nguyentanlinh_2122110398.api;

import com.example.nguyentanlinh_2122110398.model.LoginResponse;
import com.example.nguyentanlinh_2122110398.model.VerItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory (GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginResponse> login(@Field("username") String username, @Field("password") String password);
    @GET("products")
    Call<List<VerItem>> getProduct();
}


