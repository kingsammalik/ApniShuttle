package com.example.sahilgoyal.apnishuttle.serverrequesthandler;


import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.BookOrderModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.PaymentModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.RegisterResponse;
import com.google.gson.JsonElement;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sahil.goyal on 3/13/2018.
 */

public interface PostRequests {

    @POST("login")
    @FormUrlEncoded
    public Observable<Response<LoginResponse>> authenticateUser(@Header("X-Authorization") String token, @Field("login") String login, @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    public Observable<Response<RegisterResponse>> registerUser(@Header("X-Authorization") String token, @Field("name") String name, @Field("email") String email,@Field("phone") String phone,@Field("password") String password,@Field("password_confirmation") String password_confirmation);

    @POST("shuttle-book")
    public Observable<Response<BookOrderModel>> bookShuttle(@Header("X-Authorization") String token, @Query("token") String authToken, @Query("date") String date, @Query("pickup_id") String pickUp, @Query("drop_id") String drop_id, @Query("bus_shedule_id") String bus_schedule_id, @Query("bus_id") String bus_id);


    @POST("payment-store")
    @FormUrlEncoded
    public Observable<Response<PaymentModel>> paymentStore(@Header("X-Authorization") String token, @Field("token") String authToken, @Field("data") String date);



}
