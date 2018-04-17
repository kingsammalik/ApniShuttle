package com.example.sahilgoyal.apnishuttle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sahilgoyal.apnishuttle.adapters.RoutesSourceAdapter;
import com.example.sahilgoyal.apnishuttle.common.UtilityMethods;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.DispatchGetResponse;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ErrorDto;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.GetDispatchs;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ResponseTypes;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.RoutesModel;

import java.util.List;

import okhttp3.Route;

public class RoutesSourceLatLngActivity extends AppCompatActivity implements GetDispatchs {

    RecyclerView rlList;
    String sourcename;
    String latitude, longitude;
    TextView texttool;

    TextView tvTitle;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_routes);
        rlList = findViewById(R.id.rlRoutes);
        imageButton = findViewById(R.id.backbtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
        texttool = findViewById(R.id.texttool);
        tvTitle = findViewById(R.id.tvTitle);
        texttool.setText("Select Your Nearest PickUp Point");
        latitude = getIntent().getStringExtra("latitude");
        sourcename = getIntent().getStringExtra("sourcename");

        longitude = getIntent().getStringExtra("longitude");

        getAllRoutes(latitude, longitude);
    }

    public void getAllRoutes(String source, String destination) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            String vale = source + ";" + destination;

            UtilityMethods.showProgressDialog(RoutesSourceLatLngActivity.this, "", "Please Wait");
            DispatchGetResponse.disptatchRequest(this, ResponseTypes.GETROUTE, vale, this);

        } else {

            UtilityMethods.showToast(RoutesSourceLatLngActivity.this, "No Internet Connection");

        }

    }


    @Override
    public void apiSuccess(Object body, ResponseTypes response) {

        List<RoutesModel> routesModels = (List<RoutesModel>) body;

        if (routesModels != null) {

            if (routesModels.size() > 0) {

                rlList.setVisibility(View.VISIBLE);
                tvTitle.setVisibility(View.GONE);
                setAdapter(routesModels);

            } else {

                rlList.setVisibility(View.GONE);
                tvTitle.setVisibility(View.VISIBLE);
                System.out.println(Toast.makeText(RoutesSourceLatLngActivity.this, "No Route Found", Toast.LENGTH_LONG));
            }

        }


    }

    private void setAdapter(List<RoutesModel> routesModels) {

        RoutesSourceAdapter allRoutessAdapter = new RoutesSourceAdapter(RoutesSourceLatLngActivity.this, routesModels, sourcename, latitude, longitude);
        rlList.setAdapter(allRoutessAdapter);
        rlList.setLayoutManager(new LinearLayoutManager(RoutesSourceLatLngActivity.this));
    }

    @Override
    public void apiError(ErrorDto error) {

    }

    @Override
    public void error(String body) {

    }
}
