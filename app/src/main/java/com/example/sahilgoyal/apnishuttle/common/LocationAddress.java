package com.example.sahilgoyal.apnishuttle.common;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by C4U015 on 15-02-2016.
 */
public class LocationAddress {
    String result;
    private static final String TAG = "LocationAddress";
    String address1,address2,completeadd;
    public String getAddressFromLocation(final double latitude, final double longitude,
                                         final Context context) {

        //    Thread thread = new Thread() {
        //   @Override
        //    public void run() {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        try {
            List<Address> addressList = geocoder.getFromLocation(
                    latitude, longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                      /*  StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                            sb.append(address.getAddressLine(i)).append("\n");
                            Log.e("address line "+i, " " + sb.toString());
                        }
                        sb.append(address.getLocality()).append("\n");
                        Log.e("locality", " " + sb.toString());
                        sb.append(address.getPostalCode()).append("\n");
                        Log.e("postal code", " " + sb.toString());
                        sb.append(address.getCountryName());
                        Log.e("country", " " + sb.toString());
                        result = sb.toString();*/
                // Log.e("geo result"," "+result);
                result="Latitude:"+latitude+","+"Longitude:"+longitude+","
                        +"Region:"+address.getAddressLine(1)+","+"City:"
                        +address.getLocality()+","+"ZIP:"+address.getPostalCode()+","
                        +"COUNTRY:"+address.getCountryName();

                address1=address.getAddressLine(1);
                address2=address.getLocality()+","+address.getPostalCode()+","
                        +address.getCountryName();
                completeadd=address.getAddressLine(0);

            }
        } catch (IOException e) {

            Log.e(TAG, "Unable connect to Geocoder", e);
        }/* finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if (result != null) {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        result = "Latitude: " + latitude + " Longitude: " + longitude +
                                "\n\nAddress:\n" +result;
                        bundle.putString("address", result);
                        message.setData(bundle);
                    } else {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        result = "Latitude: " + latitude + " Longitude: " + longitude +
                                "\n Unable to get address for this lat-long.";
                        bundle.putString("address", result);
                        message.setData(bundle);
                    }
                    message.sendToTarget();
                }*/
        //    }
        // };
        //  thread.start();
        //Log.e("result", " " + result);
        return completeadd;


    }

    public String getCompleteadd() {
        return completeadd;
    }

    public String getAddress1(){
        return address1;

    }

    public String getAddress2(){
        return address2;
    }



}
