package com.example.sahilgoyal.apnishuttle.serverrequesthandler.prefrences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sahil.goyal on 3/13/2018.
 */

public class Prefrences {


    public String USER_DETAIL = "user_detail";

    public String USER_CREDENTIAL = "user_credential";

    public String SYNC_DETAIL = "sync_detail";

    public String NAME = "name";
    public String PHONE = "phone";

    public String USER_NAME = "username";
    public String PASSWORD = "password";
    public String SYNC_DATE = "sync_date";

    public String USER_TOKEN = "user_token";

    public String USER_EMAIL = "user_email";

    public static Prefrences getInstance() {
        return new Prefrences();
    }


    public void storeUserData(Context context, String userid, String type, String token,String email) {


        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DETAIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, userid);
        editor.putString(PASSWORD, type);
        editor.putString(USER_TOKEN, token);
        editor.putString(USER_EMAIL, email);
        editor.apply();
    }

    public void storeUserCredentail(Context context, String type, String phone) {


        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_CREDENTIAL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME, type);
        editor.putString(PHONE, phone);
        editor.apply();
    }


    public String getNAME(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_CREDENTIAL, Context.MODE_PRIVATE);

        return sharedPreferences.getString(NAME, "");
    }

    public String getPHONE(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_CREDENTIAL, Context.MODE_PRIVATE);

        return sharedPreferences.getString(PHONE, "");
    }


    public void storeSyncDetail(Context context, String date) {


        SharedPreferences sharedPreferences = context.getSharedPreferences(SYNC_DETAIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SYNC_DATE, date);

        editor.apply();
    }


    public String getSyncDate(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SYNC_DETAIL, Context.MODE_PRIVATE);

        return sharedPreferences.getString(SYNC_DATE, "");
    }


    public String getToken(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DETAIL, Context.MODE_PRIVATE);

        return sharedPreferences.getString(USER_TOKEN, "");
    }

    public String getEmail(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DETAIL, Context.MODE_PRIVATE);

        return sharedPreferences.getString(USER_EMAIL, "");
    }
    public String getUsername(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DETAIL, Context.MODE_PRIVATE);

        return sharedPreferences.getString(USER_NAME, "");
    }

    public String getPassword(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DETAIL, Context.MODE_PRIVATE);

        return sharedPreferences.getString(PASSWORD, "");
    }

    public void clearUserDetail(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DETAIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();


        SharedPreferences sharedPreferences1 = context.getSharedPreferences(SYNC_DETAIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        editor1.clear();
        editor1.apply();

    }


}
