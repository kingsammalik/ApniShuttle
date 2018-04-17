package com.example.sahilgoyal.apnishuttle.serverrequesthandler.models;

import android.content.Context;
import android.util.Log;

import com.example.sahilgoyal.apnishuttle.Constants.ApplicationConstants;
import com.example.sahilgoyal.apnishuttle.common.UtilityMethods;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.LoginResponse;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.PostDispatchs;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.PostRequestCallback;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.PostRequests;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ResponseTypes;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.prefrences.Prefrences;
import com.google.gson.JsonElement;
import com.payumoney.core.response.UserDetail;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sahil.goyal on 3/13/2018.
 */

public class DispatchPostResponse {


    public static void disptatchRequest(final PostDispatchs postDispatchs, ResponseTypes type, Object object, final Context context) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor);
        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).baseUrl(ApplicationConstants.baseApi).build();
        PostRequests postRequests = retrofit.create(PostRequests.class);

        switch (type) {

            case LOGIN:


                String values[] = object.toString().split(";");

                postRequests.authenticateUser(ApplicationConstants.auth_token, values[0], values[1]).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<LoginResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onNext(Response<LoginResponse> jsonElementResponse) {


                        new PostRequestCallback<>(postDispatchs, jsonElementResponse, ResponseTypes.LOGIN, context).onResponse();

                    }

                });

                break;
            case REGISTER:

                String values1[] = object.toString().split(";");
                postRequests.registerUser(ApplicationConstants.auth_token, values1[0], values1[1], values1[2], values1[3], values1[4]).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<RegisterResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onNext(Response<RegisterResponse> jsonElementResponse) {


                        new PostRequestCallback<>(postDispatchs, jsonElementResponse, ResponseTypes.REGISTER, context).onResponse();

                    }

                });

                break;

            case BOOK_RIDE:

                String values2[] = object.toString().split(";");

                Log.e("bookride",""+values2[0]+" "+values2[1]+" "+values2[2]+" "+values2[3]+" "+values2[4]);
                postRequests.bookShuttle(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context),values2[0], values2[1], values2[2], values2[3], values2[4]).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<BookOrderModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onNext(Response<BookOrderModel> jsonElementResponse) {



                        Log.e("book",""+jsonElementResponse);
                        if (jsonElementResponse.isSuccessful()) {

                            UtilityMethods.showToast(context, "successs");
                        }
                        new PostRequestCallback<>(postDispatchs, jsonElementResponse, ResponseTypes.BOOK_RIDE, context).onResponse();

                    }

                });

                break;

            case PAYMENT:


                postRequests.paymentStore(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context),object.toString()).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<PaymentModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onNext(Response<PaymentModel> jsonElementResponse) {


                        if (jsonElementResponse.isSuccessful()) {

                            UtilityMethods.showToast(context, "successs");
                        }
                        new PostRequestCallback<>(postDispatchs, jsonElementResponse, ResponseTypes.PAYMENT, context).onResponse();

                    }

                });



                break;

        }


    }
}
