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
import com.example.sahilgoyal.apnishuttle.R;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.GetTimingModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.RoutesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sahil.goyal on 4/6/2018.
 */

public class BookTimingAdapter extends RecyclerView.Adapter<BookTimingAdapter.MyviewHolder> {


    BookRideActivity myRideActivity;
    ArrayList<GetTimingModel.Timing> myRidesModelLists;

    public BookTimingAdapter(BookRideActivity myRideActivity, ArrayList<GetTimingModel.Timing> myRidesModelList) {

        this.myRideActivity = myRideActivity;
        myRidesModelLists = myRidesModelList;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(myRideActivity).inflate(R.layout.custom_fare, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {

        final GetTimingModel.Timing myRidesModel = myRidesModelLists.get(position);
        holder.tvSource.setText(myRidesModel.getPick_up());
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.e("BookTimg",""+myRidesModel.getBus_ids()+" "+myRidesModel.getBus_shedule_id()+" "+myRidesModel.getPick_up());
                myRideActivity.itemClicked(myRidesModel.getBus_ids(),myRidesModel.getBus_shedule_id(),myRidesModel.getPick_up());
                //myRideActivity.startActivity(new Intent(myRideActivity, BookRideActivity.class));

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
