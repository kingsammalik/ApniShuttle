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
import com.example.sahilgoyal.apnishuttle.Constants.ApplicationConstants;
import com.example.sahilgoyal.apnishuttle.R;
import com.example.sahilgoyal.apnishuttle.RoutesLatLngActivity;
import com.example.sahilgoyal.apnishuttle.RoutesPickUpPointActivity;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.GetStopsModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.RoutesModel;

import java.util.List;

/**
 * Created by sahil.goyal on 4/6/2018.
 */

public class RoutesPickUpAdapter extends RecyclerView.Adapter<RoutesPickUpAdapter.MyviewHolder> {


    RoutesPickUpPointActivity myRideActivity;
    List<GetStopsModel> myRidesModelLists;

    String route_id;

    public RoutesPickUpAdapter(RoutesPickUpPointActivity myRideActivity, List<GetStopsModel> myRidesModelList, String route_id) {

        this.myRideActivity = myRideActivity;
        myRidesModelLists = myRidesModelList;
        this.route_id=route_id;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(myRideActivity).inflate(R.layout.custom_route, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {

        final GetStopsModel myRidesModel = myRidesModelLists.get(position);
        holder.tvSource.setText(myRidesModel.getName());
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   ApplicationConstants.fare = myRidesModel.getFare();

                int id = myRidesModel.getId();
                ApplicationConstants.pick_up = "" + id;
                ApplicationConstants.sourceName = myRidesModel.getName();
                ApplicationConstants.sourceLng = myRidesModel.getLongitude();
                ApplicationConstants.sourceLat = myRidesModel.getLatitude();
                ApplicationConstants.pick_up_name = myRidesModel.getName();


                myRideActivity.startActivity(new Intent(myRideActivity, RoutesLatLngActivity.class).putExtra("route_id", "" + route_id));

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
