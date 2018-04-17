package com.example.sahilgoyal.apnishuttle.serverrequesthandler;


import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.GetProfileModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.GetStopsModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.GetTimingModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.MyRidesModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.RoutesModel;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sahil.goyal on 3/13/2018.
 */

public interface GetRequests {

    @GET("shuttle-history")
    public Observable<Response<List<MyRidesModel>>> getMyRides(@Header("X-Authorization") String authorization, @Query("token") String token);


    @GET("profile")
    public Observable<Response<GetProfileModel>> getProfile(@Header("X-Authorization") String authorization, @Query("token") String token);


    @GET("routes")
    public Observable<Response<List<RoutesModel>>> getAllRoutes(@Header("X-Authorization") String authorization);

    @GET("search/latlong")
    public Observable<Response<List<RoutesModel>>> getRoute(@Header("X-Authorization") String authorization , @Query("token") String token, @Query("latitude")String latitude, @Query("longitude")String longitude, @Query("distance")String distance);


    @GET("bus/timing")
    public Observable<Response<List<GetTimingModel>>> getTiming(@Header("X-Authorization") String authorization , @Query("token") String token, @Query("bus_shedule_id")String schedule_id);

    @GET("stops/{route_id}")
    public Observable<Response<List<GetStopsModel>>> getStops(@Header("X-Authorization") String authorization , @Path("route_id") String route_id, @Query("token") String token);

}
