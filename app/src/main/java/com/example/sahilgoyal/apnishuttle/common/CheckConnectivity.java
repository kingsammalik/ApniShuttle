package com.example.sahilgoyal.apnishuttle.common;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Sahil Goyal on 3/9/2018.
 */

public class CheckConnectivity  {


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


/*    private static final String TAG = "MyTaskService";

    public static final String TASK_TAG_WIFI = "wifi_task";
    public static final String TASK_TAG_CHARGING = "charging_task";
    public static final String TASK_TAG_PERIODIC = "periodic_task";
    public static final String ACTION_DONE = "GcmTaskService#ACTION_DONE";
    public static final String EXTRA_TAG = "extra_tag";
    public static final String EXTRA_RESULT = "extra_result";

    @Override
    public void onInitializeTasks() {
        super.onInitializeTasks();
    }

    @Override
    public int onRunTask(TaskParams taskParams) {

        String tag = taskParams.getTag();
        int result = GcmNetworkManager.RESULT_SUCCESS;

        // Choose method based on the tag.

        Log.e("tesitng",""+tag);

        if (TASK_TAG_WIFI.equals(tag)) {
            result = doWifiTask();
        }


        Log.e("tesitng",""+result);
        // Create Intent to broadcast the task information.
        Intent intent = new Intent();
        intent.setAction(ACTION_DONE);
        intent.putExtra(EXTRA_TAG, tag);
        intent.putExtra(EXTRA_RESULT, result);

        // Send local broadcast, running Activities will be notified about the task.
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.sendBroadcast(intent);

        // Return one of RESULT_SUCCESS, RESULT_FAILURE, or RESULT_RESCHEDULE
        return result;

    }

    public int doWifiTask() {

        Toast.makeText(getApplicationContext(), "coonected", Toast.LENGTH_LONG).show();

        return 1;
    }*/

}
