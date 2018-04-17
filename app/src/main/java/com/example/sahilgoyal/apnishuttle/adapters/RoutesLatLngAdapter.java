package com.example.sahilgoyal.apnishuttle.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sahilgoyal.apnishuttle.BookRideActivity;
import com.example.sahilgoyal.apnishuttle.R;
import com.example.sahilgoyal.apnishuttle.RoutesLatLngActivity;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.RoutesModel;

import java.util.List;

/**
 * Created by sahil.goyal on 4/6/2018.
 */

public class RoutesLatLngAdapter extends RecyclerView.Adapter<RoutesLatLngAdapter.MyviewHolder> {


    RoutesLatLngActivity myRideActivity;
    List<RoutesModel> myRidesModelLists;
    String name;
    String latitdue, longitude;

    public RoutesLatLngAdapter(RoutesLatLngActivity myRideActivity, List<RoutesModel> myRidesModelList, String s, String latitude, String longitude) {

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


      //  holder.tvSource.setText(name);
        holder.tvDestination.setText(myRidesModel.getName());

        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRideActivity.startActivity(new Intent(myRideActivity, BookRideActivity.class).putExtra("route_id",myRidesModel.getRoute_id()).putExtra("sourcelatitude", latitdue).putExtra("sourcelongitude", longitude).putExtra("sourcename", name).putExtra("destinationlongitude", myRidesModel.getLongitude()).putExtra("destinationlatitude", myRidesModel.getLatitude()).putExtra("destinationname", myRidesModel.getName()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return myRidesModelLists.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        TextView tvDestination;
        LinearLayout rootLayout;

        public MyviewHolder(View itemView) {
            super(itemView);
            rootLayout = itemView.findViewById(R.id.rootLayout);
            tvDestination = itemView.findViewById(R.id.tvSource);

        }
    }
}
