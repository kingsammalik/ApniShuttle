package com.example.sahilgoyal.apnishuttle.serverrequesthandler;

import android.content.Context;
import android.util.Log;


import com.example.sahilgoyal.apnishuttle.Constants.ApplicationConstants;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.GetProfileModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.GetStopsModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.GetTimingModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.MyRidesModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.RoutesModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.prefrences.Prefrences;

import java.util.List;

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

public class DispatchGetResponse {


    public static void disptatchRequest(final GetDispatchs getDispatch, ResponseTypes type, Object object, final Context context) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor);
        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).baseUrl(ApplicationConstants.baseApi).build();
        GetRequests getRequest = retrofit.create(GetRequests.class);

        switch (type) {

            case MYRIDES:

                getRequest.getMyRides(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<MyRidesModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<List<MyRidesModel>> listResponse) {

                        Log.e("tesitng responsee", "" + listResponse);
                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.MYRIDES, context).onResponse();

                    }
                });
                break;

            case GETPROFILE:

                getRequest.getProfile(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<GetProfileModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<GetProfileModel> listResponse) {

                        Log.e("tesitng responsee", "" + listResponse);
                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.GETPROFILE, context).onResponse();

                    }
                });


                break;


            case GETALLROUTES:


                getRequest.getAllRoutes(ApplicationConstants.auth_token).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<RoutesModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<List<RoutesModel>> listResponse) {

                        Log.e("tesitng responsee", "" + listResponse);
                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.GETALLROUTES, context).onResponse();

                    }
                });


                break;


            case GETROUTE:

                String value = object.toString();
                String data[] = value.split(";");
                getRequest.getRoute(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context), data[0], data[1], "12").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<RoutesModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<List<RoutesModel>> listResponse) {

                        Log.e("tesitng responsee", "" + listResponse);
                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.GETROUTE, context).onResponse();

                    }
                });


                break;
            case GETTIMING:

                Log.e("getTiming",""+object);
                String values[]=object.toString().split(";");

                getRequest.getTiming(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context), values[0]).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<GetTimingModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<List<GetTimingModel>> listResponse) {

                        Log.e("tesitng responsee", "" + listResponse);
                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.GETTIMING, context).onResponse();

                    }
                });
                break;

            case GETSTOPS:
                getRequest.getStops(ApplicationConstants.auth_token, object.toString(),Prefrences.getInstance().getToken(context)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<GetStopsModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<List<GetStopsModel>> listResponse) {

                        Log.e("tesitng responsee", "" + listResponse);
                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.GETSTOPS, context).onResponse();

                    }
                });
                break;

        }


    }
}
