package com.example.sahilgoyal.apnishuttle.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sahilgoyal.apnishuttle.Constants.ApplicationConstants;
import com.example.sahilgoyal.apnishuttle.R;
import com.example.sahilgoyal.apnishuttle.RoutesSourceLatLngActivity;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.RoutesModel;

import java.util.List;

/**
 * Created by sahil.goyal on 4/6/2018.
 */

public class RoutesSourceAdapter extends RecyclerView.Adapter<RoutesSourceAdapter.MyviewHolder> {


    RoutesSourceLatLngActivity myRideActivity;
    List<RoutesModel> myRidesModelLists;
    String name;
    String latitdue, longitude;

    public RoutesSourceAdapter(RoutesSourceLatLngActivity myRideActivity, List<RoutesModel> myRidesModelList, String s, String latitude, String longitude) {

        this.myRideActivity = myRideActivity;
        myRidesModelLists = myRidesModelList;
        this.latitdue = latitude;
        this.longitude = longitude;
        name = s;
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

      //  holder.tvDestination.setText(myRidesModel.getName());

        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = ""+myRidesModel.getId();
                ApplicationConstants.pick_up = id;
                ApplicationConstants.sourceName=myRidesModel.getName();
                ApplicationConstants.sourceLng=myRidesModel.getLongitude();
                ApplicationConstants.sourceLat=myRidesModel.getLatitude();
                ApplicationConstants.pick_up_name = myRidesModel.getName();
                myRideActivity.finish();
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
         //   tvDestination = itemView.findViewById(R.id.tvDestination);

        }
    }
}
