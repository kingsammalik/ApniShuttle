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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sahilgoyal.apnishuttle.Constants.ApplicationConstants;
import com.example.sahilgoyal.apnishuttle.adapters.AllRoutessAdapter;
import com.example.sahilgoyal.apnishuttle.common.UtilityMethods;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.DispatchGetResponse;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ErrorDto;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.GetDispatchs;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ResponseTypes;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.GetStopsModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.GetTimingModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.RoutesModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import okhttp3.Route;

public class AllRoutesActivity extends AppCompatActivity implements GetDispatchs {

    RecyclerView rlList;
    String latitude, longitude;
    ArrayList<RoutesModel> routesModelss;
    ImageButton ivBack;
    TextView tvTitle;
    TextView tvToolTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_routes);
        rlList = findViewById(R.id.rlRoutes);
        tvToolTitle = findViewById(R.id.texttool);
        tvToolTitle.setText("Select your Route");
        ivBack = findViewById(R.id.backbtn);
        tvTitle = findViewById(R.id.tvTitle);
        latitude = getIntent().getStringExtra("latitude");
        longitude = getIntent().getStringExtra("longitude");
        routesModelss = new ArrayList<>();
        getRoute();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }

    public void getAllRoutes() {

        if (UtilityMethods.isNetworkAvailable(this)) {


            UtilityMethods.showProgressDialog(AllRoutesActivity.this, "", "Please Wait");
            DispatchGetResponse.disptatchRequest(this, ResponseTypes.GETALLROUTES, null, this);

        } else {

            UtilityMethods.showToast(AllRoutesActivity.this, "No Internet Connection");

        }

    }


    public void getRoute() {

        if (UtilityMethods.isNetworkAvailable(this)) {

            String vale = ApplicationConstants.destinationLat + ";" + ApplicationConstants.destinationLng;

            UtilityMethods.showProgressDialog(AllRoutesActivity.this, "", "Please Wait");
            DispatchGetResponse.disptatchRequest(this, ResponseTypes.GETROUTE, vale, this);

        } else {

            UtilityMethods.showToast(AllRoutesActivity.this, "No Internet Connection");

        }

    }

    ArrayList<RoutesModel> getStopsModels = new ArrayList<>();
    ArrayList<RoutesModel> getRoutesModelss = new ArrayList<>();

    @Override
    public void apiSuccess(Object body, ResponseTypes response) {


        switch (response) {

            case GETROUTE:

                getStopsModels = (ArrayList<RoutesModel>) body;
                getAllRoutes();
                break;

            default:

                List<RoutesModel> routesModels = (List<RoutesModel>) body;


                for (RoutesModel routesModel : routesModels) {

                    //   RoutesModel routesModel = routesModels.get(s);


                    if (!TextUtils.isEmpty(ApplicationConstants.destinationLat) && !TextUtils.isEmpty(ApplicationConstants.destinationLng)) {

                        Location source1 = new Location("diatand");
                        source1.setLatitude(Double.parseDouble(routesModel.getLatitude()));
                        source1.setLongitude(Double.parseDouble(routesModel.getLongitude()));


                        Location nearDestination1 = new Location("diatand");
                        nearDestination1.setLatitude(Double.parseDouble(ApplicationConstants.destinationLat));
                        nearDestination1.setLongitude(Double.parseDouble(ApplicationConstants.destinationLng));


                        double dist = source1.distanceTo(nearDestination1) / 1000;

                        Log.e("destination", "" + dist + routesModel.getName() + " " + routesModel.getId());
                        if (dist <= 9) {


                            for (int k = 0; k < getStopsModels.size(); k++) {


                                if (getStopsModels.get(k).getRoute_id().equalsIgnoreCase("" + routesModel.getId())) {


                                    if (!TextUtils.isEmpty(getStopsModels.get(k).getLatitude()) && !TextUtils.isEmpty(getStopsModels.get(k).getLongitude())) {


                                        Location source = new Location("diatand");
                                        source.setLatitude(Double.parseDouble(getStopsModels.get(k).getLatitude()));
                                        source.setLongitude(Double.parseDouble(getStopsModels.get(k).getLongitude()));

                                        Location destination = new Location("diatand");
                                        destination.setLatitude(Double.parseDouble(ApplicationConstants.intialSourceLat));
                                        destination.setLongitude(Double.parseDouble(ApplicationConstants.intailSourceLng));

/*

                                Location nearDestination = new Location("diatand");
                                nearDestination.setLatitude(Double.parseDouble(ApplicationConstants.destinationLat));
                                nearDestination.setLongitude(Double.parseDouble(ApplicationConstants.destinationLng));
*/


                                        try {


                                            double distance = source.distanceTo(destination) / 1000;
                                            //      double nearDistance = source.distanceTo(destination) / 1000;


                                            Log.e("distance", "" + distance + "   " + getStopsModels.get(k).getId() + " " + getStopsModels.get(k).getRoute_id());


                                            if (distance <= 5) {


                                                try {


                                                    if (getRoutesModelss.size() > 0) {
                                                        boolean checkValue = true;


                                                        for (int l = 0; l < getRoutesModelss.size(); l++) {

                                                            if ((getRoutesModelss.get(l).getId()) == (routesModel.getId())) {

                                                                checkValue = true;
                                                                break;
                                                            } else {

                                                                checkValue = false;
                                                            }

                                                        }

                                                        if (!checkValue) {

                                                            routesModel.setDrop_point_distance(dist);
                                                            getRoutesModelss.add(routesModel);

                                                        }


                                                    } else {

                                                        getRoutesModelss.add(routesModel);
                                                    }


                                                } catch (Exception e) {


                                                    Log.e("testing exfeption", "" + e);

                                                }
                                            }

                                        } catch (Exception e) {

                                            Log.e("All Rotes", "" + e);
                                        }


//                                for (int j = 0; j < getRoutesModelss.size(); j++) {


                                  /*  if (routesModel.getId()==(getRoutesModelss.get(j).getId())) {

                                        checkValue = true;
                                    } else {


                                        checkValue = false;
                                    }
*/


                                        //32, Sector 43 Service Rd, Sector 44, Gurugram, Haryana 122003, India
                                        //Block C, DLF Colony, Sector 14, Gurugram, Haryana 122001, India

//                                    if (distance <= 5) {


                                        //                                  }


                            /*    if (!checkValue) {



                                 //   getRoutesModelss.add(routesModel);
                                }
*/
                                        //  } else {

                                        //     getRoutesModelss.add(routesModel);

                                        //}


                                    }

                                }


                            }






/*
                try {


                    if (routesModels.size() > 0) {


                        tvTitle.setVisibility(View.GONE);
                        rlList.setVisibility(View.VISIBLE);

                        for (RoutesModel routesModel : routesModels) {


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


                            //    if (distance <= 20) {

                            Log.e("distance", "" + distance);

                            this.routesModelss.add(routesModel);

                            //  }


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
                }*/


                        }
                    }

                    if (getRoutesModelss.size() > 0) {
                        rlList.setVisibility(View.VISIBLE);
                        tvTitle.setVisibility(View.GONE);

                        setAdapter(getRoutesModelss);
                    } else {

                        tvTitle.setVisibility(View.VISIBLE);
                        rlList.setVisibility(View.GONE);
                    }

                }

                break;

        }
    }

    List<RoutesModel> routesModelsss = new ArrayList<>();

    private void setAdapter(List<RoutesModel> routesModels) {


        if (routesModels.size() > 0) {


     /*       for (int i = 0; i < routesModels.size(); i++) {


                Location source = new Location("diatand");
                source.setLatitude(Double.parseDouble(routesModels.get(i).getLatitude()));
                source.setLongitude(Double.parseDouble(routesModels.get(i).getLongitude()));


                Location destination = new Location("diatand");
                destination.setLatitude(Double.parseDouble(ApplicationConstants.destinationLat));
                destination.setLongitude(Double.parseDouble(ApplicationConstants.destinationLng));


                double dis = source.distanceTo(destination) / 1000;
                boolean checkValue = false;
                for (int j = i + 1; j < routesModels.size(); j++) {


                    Location source1 = new Location("diatand");
                    source1.setLatitude(Double.parseDouble(routesModels.get(j).getLatitude()));
                    source1.setLongitude(Double.parseDouble(routesModels.get(j).getLongitude()));


                    Location destination1 = new Location("diatand");
                    destination1.setLatitude(Double.parseDouble(ApplicationConstants.destinationLat));
                    destination1.setLongitude(Double.parseDouble(ApplicationConstants.destinationLng));

                    double dis1 = source1.distanceTo(destination1) / 1000;

                    if (dis <= dis1) {
                        checkValue = true;
                    } else {
                        checkValue = false;
                    }

                }
                if (checkValue) {

                    boolean values = false;
                    if (routesModelsss.size() > 0) {

                        for (int g = 0; g < routesModelsss.size(); g++) {

                            Log.e("iddd",""+routesModels.get(i).getId()+"   "+routesModelsss.get(g).getId());
                            if (routesModels.get(i).getId() != routesModelsss.get(g).getId()) {

                                values = true;
                            } else {
                                values = false;
                            }
                        }
                        if(values){

                            routesModelsss.add(routesModels.get(i));

                        }


                    } else {

                        routesModelsss.add(routesModels.get(i));

                    }


                }

            }*/
            tvTitle.setVisibility(View.GONE);
            rlList.setVisibility(View.VISIBLE);


            Collections.sort(getRoutesModelss, new Comparator<RoutesModel>() {
                @Override
                public int compare(RoutesModel routesModel, RoutesModel routesModel1) {

                    int compareResult = 0;
                    compareResult = ("" + routesModel.getDrop_point_distance()).compareTo(("" + routesModel1.getDrop_point_distance()));


                    Log.e("testing book", "" + compareResult);
                    return compareResult;


                }
            });


            AllRoutessAdapter allRoutessAdapter = new AllRoutessAdapter(AllRoutesActivity.this, routesModels);
            rlList.setAdapter(allRoutessAdapter);
            rlList.setLayoutManager(new LinearLayoutManager(AllRoutesActivity.this));


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
