package com.example.sahilgoyal.apnishuttle;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.sahilgoyal.apnishuttle.Constants.ApplicationConstants;
import com.example.sahilgoyal.apnishuttle.Constants.Utils;
import com.example.sahilgoyal.apnishuttle.adapters.BookTimingAdapter;
import com.example.sahilgoyal.apnishuttle.adapters.MyRidesAdapter;
import com.example.sahilgoyal.apnishuttle.common.UtilityMethods;
import com.example.sahilgoyal.apnishuttle.drawroutemap.DrawMarker;
import com.example.sahilgoyal.apnishuttle.drawroutemap.DrawRouteMaps;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.DispatchGetResponse;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ErrorDto;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.GetDispatchs;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.PostDispatchs;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ResponseTypes;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.BookOrderModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.DispatchPostResponse;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.GetTimingModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.MyRidesModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.prefrences.Prefrences;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.sasidhar.smaps.payumoney.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class BookRideActivity extends AppCompatActivity implements PostDispatchs, OnMapReadyCallback, GetDispatchs {


    Button btnReserve;
    TextView tvSource;
    TextView tvDestination;
    private GoogleMap mMap;
    String latitude;
    String longitude;
    String sourcename;
    String destinationLongitude;
    String destnationLatitude;
    String destination, route_id;
    android.support.v7.widget.Toolbar toolbar;

    RecyclerView timingList;
    String date;
    String pickUp;
    String dropId;
    String busSchedule;
    String busId;
    TextView tvFare, tvBookRide;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ride);
        tvSource = findViewById(R.id.tvSource);
        imageButton = findViewById(R.id.ivBackButton);
        tvFare = findViewById(R.id.tvFare);
        tvBookRide = findViewById(R.id.tvBookTime);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Book Ride");
        toolbar.bringToFront();
        timingList = findViewById(R.id.rlTiming);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tvDestination = findViewById(R.id.tvDestination);
        Intent intent = getIntent();

        tvFare.setText("Fare: " + ApplicationConstants.fare);

        latitude = ApplicationConstants.sourceLat;
        longitude = ApplicationConstants.sourceLng;
        sourcename = ApplicationConstants.sourceName;
        destnationLatitude = ApplicationConstants.destinationLat;
        destinationLongitude = ApplicationConstants.destinationLng;
        destination = ApplicationConstants.destinationName;

     /*   latitude = intent.getStringExtra("sourcelatitude");
        longitude = intent.getStringExtra("sourcelongitude");
        sourcename = intent.getStringExtra("sourcename");
        destinationLongitude = intent.getStringExtra("destinationlongitude");
        destnationLatitude = intent.getStringExtra("destinationlatitude");
        destination = intent.getStringExtra("destinationname");*/
        route_id = intent.getStringExtra("route_id");

        Log.e("testinggg", "" + sourcename + " " + destination);
        tvSource.setText(sourcename);
        tvDestination.setText(destination);
        btnReserve = findViewById(R.id.btnReserve);
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(busSchedule)) {

                    UtilityMethods.showToast(BookRideActivity.this, "Select Time First");
                } else {

                    bookRide();

                }


                //startActivity(new Intent(BookRideActivity.this, PaymentActivity.class));

            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
        getTiming();

    }


    public void bookRide() {
    /*    if (TextUtils.isEmpty(pickUp)) {

            UtilityMethods.showToast(BookRideActivity.this, "Enter Pickup");


        } else if (TextUtils.isEmpty(dropId)) {

            UtilityMethods.showToast(BookRideActivity.this, "Enter Drop");

        } else {

            if (UtilityMethods.isNetworkAvailable(BookRideActivity.this)) {

                Date c = Calendar.getInstance().getTime();

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = df.format(c);


                UtilityMethods.showProgressDialog(BookRideActivity.this, "", "Please Wait");
                String values = formattedDate + ";" + ApplicationConstants.pick_up + ";" + route_id + ";" + busSchedule + ";" + busId;
                DispatchPostResponse.disptatchRequest(BookRideActivity.this, ResponseTypes.BOOK_RIDE, values, BookRideActivity.this);

            } else {

                UtilityMethods.showToast(BookRideActivity.this, "No Internet Connection");
            }

        }
*/
        if (UtilityMethods.isNetworkAvailable(BookRideActivity.this)) {

            Date c = Calendar.getInstance().getTime();

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(c);


            UtilityMethods.showProgressDialog(BookRideActivity.this, "", "Please Wait");
            String values = formattedDate + ";" + ApplicationConstants.pick_up + ";" + ApplicationConstants.drop_id + ";" + busSchedule + ";" + busId;
            Log.e("testing ", "" + values);
            DispatchPostResponse.disptatchRequest(BookRideActivity.this, ResponseTypes.BOOK_RIDE, values, BookRideActivity.this);

        } else {

            UtilityMethods.showToast(BookRideActivity.this, "No Internet Connection");
        }

    }

    public void getTiming() {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(BookRideActivity.this, "", "Please Wait");
            DispatchGetResponse.disptatchRequest(this, ResponseTypes.GETTIMING, ApplicationConstants.route_id + ";" + ApplicationConstants.pick_up, this);

        } else {

            UtilityMethods.showToast(BookRideActivity.this, "No Internet Connection");

        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng origin = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        LatLng destination = new LatLng(Double.parseDouble(destnationLatitude), Double.parseDouble(destinationLongitude));
        DrawRouteMaps.getInstance(this)
                .draw(origin, destination, mMap);
        DrawMarker.getInstance(this).draw(mMap, destination, R.drawable.ic_location_on_black_24dp, "Destination Location");
        DrawMarker.getInstance(this).draw(mMap, origin, R.drawable.locpickup, "origin Location");

        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(origin)
                .include(destination).build();
        Point displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, displaySize.x, 250, 30));
    }

    @Override
    public void apiSuccess(Object body, ResponseTypes response) {

        switch (response) {


            case GETTIMING:

                ArrayList<GetTimingModel.Timing> arrayList = new ArrayList();
                List<GetTimingModel> getTimingModelList = (List<GetTimingModel>) body;

                for (int i = 0; i < getTimingModelList.size(); i++) {

                    GetTimingModel getTimingModel = getTimingModelList.get(i);

                    String bus_id = getTimingModel.getBus_id();
                    if (ApplicationConstants.route_id.equalsIgnoreCase(getTimingModel.getRoute_id())) {


                        for (int j = 0; j < getTimingModel.getTimings().size(); j++) {


                            GetTimingModel.Timing timing = getTimingModel.getTimings().get(j);

                            if (timing.getStop_id().equalsIgnoreCase(ApplicationConstants.pick_up)) {

                                timing.setBus_ids(bus_id);
                                arrayList.add(timing);

                            }

                        }

                    }

                }


                if (arrayList.size() > 0) {

                    Collections.sort(arrayList, new Comparator<GetTimingModel.Timing>() {
                        @Override
                        public int compare(GetTimingModel.Timing timing, GetTimingModel.Timing t1) {

                            SimpleDateFormat format = new SimpleDateFormat(
                                    "HH:mm:ss");
                            int compareResult = 0;
                            try {
                                Date arg0Date = format.parse(timing.getPick_up());
                                Date arg1Date = format.parse(t1.getPick_up());
                                compareResult = arg0Date.compareTo(arg1Date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                                Log.e("testing book", "" + e.getCause());

                                //  compareResult = ar.compareTo(arg1);
                            }


                            Log.e("testing book", "" + compareResult);
                            return compareResult;


                        }
                    });


                    setAdapter(arrayList);

                } else {

                    UtilityMethods.showToast(BookRideActivity.this, "No Timing Found");
                }

                break;

            case PAYMENT:
                UtilityMethods.showToast(BookRideActivity.this, "Payment Successfull");
                Intent i = new Intent(BookRideActivity.this, HomeActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
                break;

            default:
                BookOrderModel bookkOrderModel= (BookOrderModel) body;

                startPayment(bookkOrderModel.getTxnid());

                break;
        }


    }

    String txn_id;

    private void startPayment(String txnid) {

        txn_id=txnid;
        HashMap params = new HashMap<>();
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.KEY, "MCVs7yGt"); // Get merchant key from PayU Money Account
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.TXN_ID, txnid);
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.AMOUNT, ApplicationConstants.fare);
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.PRODUCT_INFO, ApplicationConstants.pick_up_name+" - "+ApplicationConstants.destinationName);
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.FIRST_NAME, Prefrences.getInstance().getNAME(BookRideActivity.this));
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.EMAIL, Prefrences.getInstance().getEmail(BookRideActivity.this));
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.PHONE, Prefrences.getInstance().getPHONE(BookRideActivity.this));
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.SURL, "https://www.payumoney.com/mobileapp/payumoney/success.php");
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.FURL, "https://www.payumoney.com/mobileapp/payumoney/failure.php");


// User defined fields are optional (pass empty string)
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.UDF1, "");
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.UDF2, "");
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.UDF3, "");
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.UDF4, "");
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.UDF5, "");
        String hash = Utils.generateHash(params, "rBy6ZSYjVv"); // Get Salt from PayU Money Account
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.HASH, hash);


// SERVICE PROVIDER VALUE IS ALWAYS "payu_paisa".
        params.put(com.sasidhar.smaps.payumoney.PayUMoney_Constants.SERVICE_PROVIDER, "payu_paisa");

        Intent intent = new Intent(this, com.sasidhar.smaps.payumoney.MakePaymentActivity.class);
        intent.putExtra(com.sasidhar.smaps.payumoney.PayUMoney_Constants.ENVIRONMENT, com.sasidhar.smaps.payumoney.PayUMoney_Constants.ENV_PRODUCTION);
        intent.putExtra(com.sasidhar.smaps.payumoney.PayUMoney_Constants.PARAMS, params);

        startActivityForResult(intent, com.sasidhar.smaps.payumoney.PayUMoney_Constants.PAYMENT_REQUEST);
    }

    @Override
    public void apiError(ErrorDto error) {

    }

    @Override
    public void error(String body) {

    }


    ArrayList arrayList = new ArrayList();

    public void setAdapter(ArrayList<GetTimingModel.Timing> myRidesModelList) {



    /*    SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        Date ten = parser.parse("10:00");
        Date eighteen = parser.parse("18:00");

        try {
            Date userDate = parser.parse(someOtherDate);
            if (userDate.after(ten) && userDate.before(eighteen)) {

            }
        } catch (ParseException e) {
            // Invalid date was entered
        }*/

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");


        BookTimingAdapter myRidesAdapter = new BookTimingAdapter(BookRideActivity.this, myRidesModelList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BookRideActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        timingList.setLayoutManager(linearLayoutManager);
        timingList.setAdapter(myRidesAdapter);

    }

    public void itemClicked(String busId, String bus_schedule_id, String time) {

        this.busId = busId;
        this.busSchedule = bus_schedule_id;
        tvBookRide.setText("Selected time " + time);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String paymentresponse = null;

        Log.e("testing", "" + data);
        if (requestCode == PayUMoney_Constants.PAYMENT_REQUEST) {
            switch (resultCode) {
                case RESULT_OK:
                    Log.e("Testing", "" + requestCode + " " + resultCode + " " + data.getStringExtra("result"));
                    Toast.makeText(BookRideActivity.this, "Payment Success.   " + data, Toast.LENGTH_SHORT).show();

                    try {

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("txnid", txn_id);
                        jsonObject.put("mihpayid", data.getStringExtra("result"));
                        jsonObject.put("firstname", Prefrences.getInstance().getNAME(BookRideActivity.this));
                        jsonObject.put("email", Prefrences.getInstance().getEmail(BookRideActivity.this));
                        jsonObject.put("phone", Prefrences.getInstance().getPHONE(BookRideActivity.this));
                        jsonObject.put("amount", ApplicationConstants.fare);
                        jsonObject.put("status", "success");
                        jsonObject.put("unmappedstatus", "captured");

                        sendPaymentResponse(jsonObject.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    break;
                case RESULT_CANCELED:
                    Log.e("Testing", "" + requestCode + " " + resultCode + " " + data.getStringExtra("result"));


                    JSONObject jsonObject1 = new JSONObject();
                    try {
                        jsonObject1.put("txnid", txn_id);
                        jsonObject1.put("mihpayid",data.getStringExtra("result") );
                        jsonObject1.put("firstname", Prefrences.getInstance().getNAME(BookRideActivity.this));
                        jsonObject1.put("email", Prefrences.getInstance().getEmail(BookRideActivity.this));
                        jsonObject1.put("phone",Prefrences.getInstance().getPHONE(BookRideActivity.this) );
                        jsonObject1.put("amount", ApplicationConstants.fare);
                        jsonObject1.put("status", "Failure");
                        jsonObject1.put("unmappedstatus", "userCancelled");
                        sendPaymentResponse(jsonObject1.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    Toast.makeText(BookRideActivity.this, "Payment Cancelled | Failed.", Toast.LENGTH_SHORT).show();
                    break;
            }

        }


    }

    public void sendPaymentResponse(String type) {


        UtilityMethods.showProgressDialog(BookRideActivity.this, "", "Please Wait");

        DispatchPostResponse.disptatchRequest(this, ResponseTypes.PAYMENT, type, BookRideActivity.this);


    }

}

