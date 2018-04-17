package com.example.sahilgoyal.apnishuttle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sahilgoyal.apnishuttle.Constants.ApplicationConstants;
import com.example.sahilgoyal.apnishuttle.common.UtilityMethods;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.DispatchGetResponse;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ErrorDto;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.GetDispatchs;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ResponseTypes;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.DispatchPostResponse;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.GetProfileModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.prefrences.Prefrences;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GetDispatchs {

    DrawerLayout drawer;
    ActionBarDrawerToggle actionBarDrawerToggle;

    EditText etSource, etDestination;
    TextView tvName, tvMobile;

    Button btnFindRoute;
    String destinationLat, destinationLng;
    boolean doubleBackToExitPressedOnce = false;
    boolean value = true;

    TextView tvExplore;

    ImageView ivSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvExplore = findViewById(R.id.tvExplore);
        ivSwipe = findViewById(R.id.ivSwipe);

        ivSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            /*    if (TextUtils.isEmpty(etDestination.getText().toString()) || etDestination.getText().toString().equalsIgnoreCase("")) {


                    UtilityMethods.showToast(HomeActivity.this, "Enter Drop Location");

                } else if (TextUtils.isEmpty(etSource.getText().toString()) || etDestination.getText().toString().equalsIgnoreCase("")) {


                    UtilityMethods.showToast(HomeActivity.this, "Enter PickUp Location");


                } else {


                    String lat = ApplicationConstants.destinationLat;
                    String lnf = ApplicationConstants.destinationLng;

                    ApplicationConstants.destinationLat=ApplicationConstants.sourceLat;
                    ApplicationConstants.destinationLng=ApplicationConstants.sourceLng;
                    ApplicationConstants.in=lat;
                    ApplicationConstants.sourceLng=lnf;
                    String destName=etDestination.getText().toString();
                    String sourName=etSource.getText().toString();

                    etSource.setText(destName);
                    etDestination.setText(sourName);

                }*/

            }
        });
        tvExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this, GetAllRoutesActivity.class));
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");

        setSupportActionBar(toolbar);
        getProfile();
        drawer = findViewById(R.id.drawerLayout);
        // ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawer,R.string.navigation_drawer_open,R.string.navigation_drawer_close);


        etSource = findViewById(R.id.etSource);


        findViewById(R.id.tvExplore);
        etDestination = findViewById(R.id.etDestination);
        etDestination.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (view.hasFocus()) {

                    destinationClick();

                }

            }
        });

        etSource.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (view.hasFocus()) {

                    sourceClick();

                }

            }
        });





/*
        etDestination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                    if (value) {

                        destinationClick();

                    }


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/

        btnFindRoute = findViewById(R.id.btnFind);
        NavigationView navigationView = findViewById(R.id.nav_view);

        tvName = navigationView.getHeaderView(0).findViewById(R.id.tvname);
        tvMobile = navigationView.getHeaderView(0).findViewById(R.id.tvmobile);


        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        btnFindRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(etDestination.getText().toString()) || etDestination.getText().toString().equalsIgnoreCase("")) {


                    UtilityMethods.showToast(HomeActivity.this, "Enter Drop Location");

                } else if (TextUtils.isEmpty(etSource.getText().toString()) || etDestination.getText().toString().equalsIgnoreCase("")) {


                    UtilityMethods.showToast(HomeActivity.this, "Enter PickUp Location");


                } else {

                    startActivity(new Intent(HomeActivity.this, AllRoutesActivity.class).putExtra("latitude", destinationLat).putExtra("longitude", destinationLng));

                }

            }
        });
        etDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                destinationClick();
            }
        });
        etSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sourceClick();
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.nav_item_home) {

            drawer.closeDrawers();
            return true;
        }

        if (item.getItemId() == R.id.nav_item_ride) {

            drawer.closeDrawers();

            startActivity(new Intent(HomeActivity.this, MyRideActivity.class));

            return true;
        }

        if (item.getItemId() == R.id.nav_item_wallet) {

            return true;
        }
        if (item.getItemId() == R.id.nav_item_contact) {

            return true;
        }
        if (item.getItemId() == R.id.nav_routes) {

            startActivity(new Intent(HomeActivity.this, GetAllRoutesActivity.class));
            return true;

        }
        if (item.getItemId() == R.id.logout) {

            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.newicon)
                    .setTitle("Closing Application")
                    .setMessage("Do You want to Logout?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Prefrences.getInstance().clearUserDetail(HomeActivity.this);

                            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();

        }


        return false;
    }

    public void getProfile() {


        if (UtilityMethods.isNetworkAvailable(HomeActivity.this)) {

            UtilityMethods.showProgressDialog(HomeActivity.this, "", "Please Wait");
            DispatchGetResponse.disptatchRequest(this, ResponseTypes.GETPROFILE, null, HomeActivity.this);

        } else {

            UtilityMethods.showToast(HomeActivity.this, "No Internet Connection");
        }

    }


    @Override
    public void apiSuccess(Object body, ResponseTypes response) {

        GetProfileModel getProfileModel = (GetProfileModel) body;

        Prefrences.getInstance().storeUserCredentail(HomeActivity.this,getProfileModel.getName(),getProfileModel.getPhone());

        tvName.setText(getProfileModel.getName().toString());
        tvMobile.setText(getProfileModel.getPhone().toString());
    }

    @Override
    public void apiError(ErrorDto error) {

    }

    @Override
    public void error(String body) {

    }


    public void sourceClick() {
        startActivityForResult(new Intent(HomeActivity.this, SearchLocation.class).putExtra("value", "1"), 1);
    }

    public void destinationClick() {
        value = false;
        startActivityForResult(new Intent(HomeActivity.this, SearchLocation.class).putExtra("value", "2"), 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Log.e("testing", "" + data + " " + requestCode + " " + resultCode);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String place = data.getStringExtra("place");
                Double lat = data.getDoubleExtra("lat", 0);
                Double log = data.getDoubleExtra("long", 0);
                Log.e("Testong", "" + lat + " " + log + " " + place);
                etSource.setText(place);

                ApplicationConstants.intailSourceLng = "" + log;
                ApplicationConstants.intialSourceLat = "" + lat;
                //startActivityForResult(new Intent(HomeActivity.this, RoutesSourceLatLngActivity.class).putExtra("latitude", lat.toString()).putExtra("sourcename", etSource.getText().toString()).putExtra("longitude", log.toString()), 1);


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {

                value = true;
                String place = data.getStringExtra("place");
                Double lat = data.getDoubleExtra("lat", 0);
                Double log = data.getDoubleExtra("long", 0);

                destinationLat = lat.toString();
                destinationLng = log.toString();
                Log.e("Testong", "" + place + " " + lat + " " + log);
                etDestination.setText(place);

                ApplicationConstants.destinationLat = "" + lat;
                ApplicationConstants.destinationLng = "" + log;
                ApplicationConstants.destinationName = place;


                Log.e("Testong", "" + data);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
