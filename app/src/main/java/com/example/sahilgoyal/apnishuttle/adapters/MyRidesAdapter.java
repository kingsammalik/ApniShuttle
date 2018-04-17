package com.example.sahilgoyal.apnishuttle.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sahilgoyal.apnishuttle.MyRideActivity;
import com.example.sahilgoyal.apnishuttle.R;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.MyRidesModel;

import java.util.List;

/**
 * Created by sahil.goyal on 4/6/2018.
 */

public class MyRidesAdapter extends RecyclerView.Adapter<MyRidesAdapter.MyviewHolder> {


    MyRideActivity myRideActivity;
    List<MyRidesModel> myRidesModelLists;

    public MyRidesAdapter(MyRideActivity myRideActivity, List<MyRidesModel> myRidesModelList) {

        this.myRideActivity = myRideActivity;
        myRidesModelLists = myRidesModelList;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(myRideActivity).inflate(R.layout.custom_history, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {

        MyRidesModel myRidesModel = myRidesModelLists.get(position);
       // holder.tvDestination.setText(myRidesModel.getDrop());
        //holder.tvSource.setText(myRidesModel.getPickup());
    }

    @Override
    public int getItemCount() {
        return myRidesModelLists.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        TextView tvSource;
        TextView tvDestination;


        public MyviewHolder(View itemView) {
            super(itemView);

            tvSource = itemView.findViewById(R.id.tvSource);
            tvDestination = itemView.findViewById(R.id.tvDestination);

        }
    }
}
