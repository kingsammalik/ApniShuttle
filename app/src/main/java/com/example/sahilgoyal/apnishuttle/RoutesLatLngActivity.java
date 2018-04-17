package com.example.sahilgoyal.apnishuttle;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sahilgoyal.apnishuttle.Constants.ApplicationConstants;
import com.example.sahilgoyal.apnishuttle.adapters.RoutesDestinationAdapter;
import com.example.sahilgoyal.apnishuttle.common.UtilityMethods;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.DispatchGetResponse;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ErrorDto;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.GetDispatchs;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ResponseTypes;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.GetStopsModel;

import java.util.ArrayList;
import java.util.List;

public class RoutesLatLngActivity extends AppCompatActivity implements GetDispatchs {

    RecyclerView rlList;
    String sourcename;
    String latitude, longitude;
    String route_id;
    ImageButton ivBack;
    TextView tvTitle;
    TextView tvtexttool;
    ArrayList<GetStopsModel> routesModelss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_routes);
        rlList = findViewById(R.id.rlRoutes);
        tvTitle = findViewById(R.id.tvTitle);
        routesModelss = new ArrayList<>();
        ivBack = findViewById(R.id.backbtn);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
        tvtexttool = findViewById(R.id.texttool);
        tvtexttool.setText("Select Your Drop Point");
        latitude = getIntent().getStringExtra("latitude");
        sourcename = getIntent().getStringExtra("sourcename");
        route_id = getIntent().getStringExtra("route_id");

        longitude = getIntent().getStringExtra("longitude");

        getAllRoutes(latitude, longitude);
    }

    public void getAllRoutes(String source, String destination) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            String vale = source + ";" + destination;

            UtilityMethods.showProgressDialog(RoutesLatLngActivity.this, "", "Please Wait");
            DispatchGetResponse.disptatchRequest(this, ResponseTypes.GETSTOPS, route_id, this);

        } else {

            UtilityMethods.showToast(RoutesLatLngActivity.this, "No Internet Connection");

        }

    }

    ArrayList<GetStopsModel> getStopsModels = new ArrayList<>();

    @Override
    public void apiSuccess(Object body, ResponseTypes response) {

        List<GetStopsModel> routesModels = (List<GetStopsModel>) body;


        try {


            if (routesModels.size() > 0) {


                tvTitle.setVisibility(View.GONE);
                rlList.setVisibility(View.VISIBLE);

                for (GetStopsModel routesModel : routesModels) {


                    String longitude = routesModel.getLongitude();
                    String latitude = routesModel.getLatitude();

                    Log.e("tedstedrfdf", "" + latitude + " " + longitude + " " + ApplicationConstants.destinationLat + " " + ApplicationConstants.destinationLng);

                    if (latitude.isEmpty() || latitude.equalsIgnoreCase(null) || latitude.equalsIgnoreCase("")) {


                        latitude = "0.0";
                    }

                    if (longitude.isEmpty() || longitude.equalsIgnoreCase(null) || longitude.equalsIgnoreCase("")) {


                        longitude = "0.0";
                    }


                    Location source = new Location("diatand");
                    source.setLatitude(Double.parseDouble(latitude));
                    source.setLongitude(Double.parseDouble(longitude));


                    Location destination = new Location("diatand");
                    destination.setLatitude(Double.parseDouble(ApplicationConstants.destinationLat));
                    destination.setLongitude(Double.parseDouble(ApplicationConstants.destinationLng));


                    double distance = source.distanceTo(destination) / 1000;


                    if (distance <= 9) {

                        Log.e("distance", "" + distance);

                        this.routesModelss.add(routesModel);

                    }


                }


                //  routesModelss.add(routesModel);


                setAdapter(this.routesModelss);

            } else {

                tvTitle.setVisibility(View.VISIBLE);
                rlList.setVisibility(View.GONE);
                // setAdapter(this.routesModelss);

            }
        } catch (
                Exception e)

        {

            Log.e("testing eception", "" + e.getCause() + "   " + e);
        }


    }


    private void setAdapter(List<GetStopsModel> routesModels) {


        if (routesModels.size() > 0) {

            rlList.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.GONE);

            RoutesDestinationAdapter allRoutessAdapter = new RoutesDestinationAdapter(RoutesLatLngActivity.this, routesModels, sourcename, latitude, longitude);
            rlList.setAdapter(allRoutessAdapter);
            rlList.setLayoutManager(new LinearLayoutManager(RoutesLatLngActivity.this));


        } else {
            rlList.setVisibility(View.GONE);

            tvTitle.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void apiError(ErrorDto error) {

    }

    @Override
    public void error(String body) {

    }
}
