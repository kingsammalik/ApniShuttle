package com.example.sahilgoyal.apnishuttle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.sahilgoyal.apnishuttle.adapters.MyRidesAdapter;
import com.example.sahilgoyal.apnishuttle.common.UtilityMethods;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.DispatchGetResponse;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ErrorDto;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.GetDispatchs;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ResponseTypes;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.DispatchPostResponse;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.MyRidesModel;

import java.util.List;

public class MyRideActivity extends AppCompatActivity implements GetDispatchs {

    RecyclerView rlRidesList;
    ImageButton ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ride);
        rlRidesList = findViewById(R.id.rlHistory);
        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
        getAllRides();
    }

    public void setAdapter(List<MyRidesModel> myRidesModelList) {
        MyRidesAdapter myRidesAdapter = new MyRidesAdapter(MyRideActivity.this, myRidesModelList);
        rlRidesList.setLayoutManager(new LinearLayoutManager(MyRideActivity.this));
        rlRidesList.setAdapter(myRidesAdapter);

    }

    public void getAllRides() {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(MyRideActivity.this, "", "Please Wait");
            DispatchGetResponse.disptatchRequest(this, ResponseTypes.MYRIDES, null, this);

        } else {

            UtilityMethods.showToast(MyRideActivity.this, "No Internet Connection");

        }

    }


    @Override
    public void apiSuccess(Object body, ResponseTypes response) {


        System.out.println(body.toString());

        List<MyRidesModel> myRidesModelList = (List<MyRidesModel>) body;

        setAdapter(myRidesModelList);

    }

    @Override
    public void apiError(ErrorDto error) {

    }

    @Override
    public void error(String body) {

    }
}
