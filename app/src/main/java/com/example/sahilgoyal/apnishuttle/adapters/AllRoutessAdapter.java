package com.example.sahilgoyal.apnishuttle.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sahilgoyal.apnishuttle.AllRoutesActivity;
import com.example.sahilgoyal.apnishuttle.BookRideActivity;
import com.example.sahilgoyal.apnishuttle.Constants.ApplicationConstants;
import com.example.sahilgoyal.apnishuttle.MyRideActivity;
import com.example.sahilgoyal.apnishuttle.R;
import com.example.sahilgoyal.apnishuttle.RoutesLatLngActivity;
import com.example.sahilgoyal.apnishuttle.RoutesPickUpPointActivity;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.MyRidesModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.RoutesModel;

import java.util.List;

/**
 * Created by sahil.goyal on 4/6/2018.
 */

public class AllRoutessAdapter extends RecyclerView.Adapter<AllRoutessAdapter.MyviewHolder> {


    AllRoutesActivity myRideActivity;
    List<RoutesModel> myRidesModelLists;

    public AllRoutessAdapter(AllRoutesActivity myRideActivity, List<RoutesModel> myRidesModelList) {

        this.myRideActivity = myRideActivity;
        myRidesModelLists = myRidesModelList;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(myRideActivity).inflate(R.layout.custom_route, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {

        final RoutesModel myRidesModel = myRidesModelLists.get(position);
        holder.tvSource.setText(myRidesModel.getName());
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("fare", "" + myRidesModel.getFare());
                ApplicationConstants.fare = myRidesModel.getFare();
                ApplicationConstants.route_id = ""+myRidesModel.getId();
     Log.e("AllRoutes",""+ApplicationConstants.route_id);
                myRideActivity.startActivity(new Intent(myRideActivity, RoutesPickUpPointActivity.class).putExtra("route_id", "" + myRidesModel.getId()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return myRidesModelLists.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        TextView tvSource;
        LinearLayout rootLayout;

        public MyviewHolder(View itemView) {
            super(itemView);
            rootLayout = itemView.findViewById(R.id.rootLayout);
            tvSource = itemView.findViewById(R.id.tvSource);

        }
    }
}
