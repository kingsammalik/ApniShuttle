package com.example.sahilgoyal.apnishuttle.drawroutemap;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.List;


public class DrawRouteMaps {

    private static DrawRouteMaps instance;
    private Context context;
    public List<List<HashMap<String, String>>> routes = null;

    public static DrawRouteMaps getInstance(Context context) {
        instance = new DrawRouteMaps();
        instance.context = context;
        return instance;
    }

    public DrawRouteMaps draw(LatLng origin, LatLng destination, GoogleMap googleMap) {
        String url_route = FetchUrl.getUrl(origin, destination);
        DrawRoute drawRoute = new DrawRoute(googleMap);
        drawRoute.execute(url_route);
        routes = drawRoute.routes;
        return instance;
    }

    public static Context getContext() {
        return instance.context;
    }
}
