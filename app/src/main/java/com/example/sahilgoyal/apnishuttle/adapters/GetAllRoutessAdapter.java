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
import com.example.sahilgoyal.apnishuttle.GetAllRoutesActivity;
import com.example.sahilgoyal.apnishuttle.R;
import com.example.sahilgoyal.apnishuttle.RoutesLatLngActivity;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.RoutesModel;

import java.util.List;

/**
 * Created by sahil.goyal on 4/6/2018.
 */

public class GetAllRoutessAdapter extends RecyclerView.Adapter<GetAllRoutessAdapter.MyviewHolder> {


    GetAllRoutesActivity myRideActivity;
    List<RoutesModel> myRidesModelLists;

    public GetAllRoutessAdapter(GetAllRoutesActivity myRideActivity, List<RoutesModel> myRidesModelList) {

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
