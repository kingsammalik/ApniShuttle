package com.example.sahilgoyal.apnishuttle;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sahilgoyal.apnishuttle.Constants.ApplicationConstants;
import com.example.sahilgoyal.apnishuttle.adapters.AllRoutessAdapter;
import com.example.sahilgoyal.apnishuttle.adapters.RoutesPickUpAdapter;
import com.example.sahilgoyal.apnishuttle.common.UtilityMethods;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.DispatchGetResponse;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ErrorDto;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.GetDispatchs;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ResponseTypes;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.GetStopsModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.RoutesModel;

import java.util.ArrayList;
import java.util.List;

public class RoutesPickUpPointActivity extends AppCompatActivity implements GetDispatchs {

    RecyclerView rlList;
    String latitude, longitude;
    ArrayList<GetStopsModel> routesModelss;
    ImageButton ivBack;
    TextView tvTitle;
    TextView tvToolTitle;
    String route_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_routes);
        rlList = findViewById(R.id.rlRoutes);
        tvToolTitle = findViewById(R.id.texttool);
        tvToolTitle.setText("Select your PickUp Point");
        ivBack = findViewById(R.id.backbtn);
        tvTitle = findViewById(R.id.tvTitle);
        latitude = getIntent().getStringExtra("latitude");
        longitude = getIntent().getStringExtra("longitude");

        route_id = getIntent().getStringExtra("route_id");
        routesModelss = new ArrayList<>();
        getAllRoutes(latitude, longitude);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }

    public void getAllRoutes(String source, String destination) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            String vale = source + ";" + destination;

            UtilityMethods.showProgressDialog(RoutesPickUpPointActivity.this, "", "Please Wait");
            DispatchGetResponse.disptatchRequest(this, ResponseTypes.GETSTOPS, route_id, this);

        } else {

            UtilityMethods.showToast(RoutesPickUpPointActivity.this, "No Internet Connection");

        }

    }


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
                    destination.setLatitude(Double.parseDouble(ApplicationConstants.intialSourceLat));
                    destination.setLongitude(Double.parseDouble(ApplicationConstants.intailSourceLng));


                    double distance = source.distanceTo(destination) / 1000;


                    if (distance <= 5) {

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

            tvTitle.setVisibility(View.GONE);
            rlList.setVisibility(View.VISIBLE);
            RoutesPickUpAdapter allRoutessAdapter = new RoutesPickUpAdapter(RoutesPickUpPointActivity.this, routesModels,route_id);
            rlList.setAdapter(allRoutessAdapter);
            rlList.setLayoutManager(new LinearLayoutManager(RoutesPickUpPointActivity.this));


        } else {


            tvTitle.setVisibility(View.VISIBLE);
            rlList.setVisibility(View.GONE);

        }
    }

    @Override
    public void apiError(ErrorDto error) {

    }

    @Override
    public void error(String body) {

    }

   /* @Override
    public void onBackPressed() {
        Intent intent = new Intent(AllRoutesActivity.this,HomeActivity.class);
        startActivity(intent);
    }*/
}
