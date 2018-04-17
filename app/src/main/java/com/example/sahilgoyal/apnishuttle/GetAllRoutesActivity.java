package com.example.sahilgoyal.apnishuttle;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sahilgoyal.apnishuttle.adapters.AllRoutessAdapter;
import com.example.sahilgoyal.apnishuttle.adapters.GetAllRoutessAdapter;
import com.example.sahilgoyal.apnishuttle.common.UtilityMethods;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.DispatchGetResponse;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ErrorDto;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.GetDispatchs;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ResponseTypes;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.RoutesModel;

import java.util.ArrayList;
import java.util.List;

public class GetAllRoutesActivity extends AppCompatActivity implements GetDispatchs {

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
        tvToolTitle.setText("All Routes");
        ivBack = findViewById(R.id.backbtn);
        tvTitle = findViewById(R.id.tvTitle);
        latitude = getIntent().getStringExtra("latitude");
        longitude = getIntent().getStringExtra("longitude");
        routesModelss = new ArrayList<>();
        getAllRoutes();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }

    public void getAllRoutes() {

        if (UtilityMethods.isNetworkAvailable(this)) {


            UtilityMethods.showProgressDialog(GetAllRoutesActivity.this, "", "Please Wait");
            DispatchGetResponse.disptatchRequest(this, ResponseTypes.GETALLROUTES, null, this);

        } else {

            UtilityMethods.showToast(GetAllRoutesActivity.this, "No Internet Connection");

        }

    }


    @Override
    public void apiSuccess(Object body, ResponseTypes response) {

        List<RoutesModel> routesModels = (List<RoutesModel>) body;

        if (routesModels.size() > 0) {


            tvTitle.setVisibility(View.GONE);
            rlList.setVisibility(View.VISIBLE);

            for (RoutesModel routesModel : routesModels) {

                String longitude = routesModel.getLongitude();
                String latitude = routesModel.getLatitude();
               routesModelss.add(routesModel);

            }

            setAdapter(this.routesModelss);


        } else {

            tvTitle.setVisibility(View.VISIBLE);
            rlList.setVisibility(View.GONE);
            //setAdapter(this.routesModelss);

        }


    }

    private void setAdapter(List<RoutesModel> routesModels) {

        GetAllRoutessAdapter allRoutessAdapter = new GetAllRoutessAdapter(GetAllRoutesActivity.this, routesModels);
        rlList.setAdapter(allRoutessAdapter);
        rlList.setLayoutManager(new LinearLayoutManager(GetAllRoutesActivity.this));
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
