package com.example.sahilgoyal.apnishuttle.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sahilgoyal.apnishuttle.AllRoutesActivity;
import com.example.sahilgoyal.apnishuttle.BookRideActivity;
import com.example.sahilgoyal.apnishuttle.R;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.GetTimingModel;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.RoutesModel;

import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sahil.goyal on 4/6/2018.
 */

public class BookTimingAdapter extends RecyclerView.Adapter<BookTimingAdapter.MyviewHolder> {

    private int mSelectedItem = -1;
    BookRideActivity myRideActivity;
    ArrayList<GetTimingModel.Timing> myRidesModelLists;
    private Context context;
    private RadioGroup lastCheckedRadioGroup = null;

    public BookTimingAdapter(BookRideActivity myRideActivity, ArrayList<GetTimingModel.Timing> myRidesModelList,Context context) {

        this.myRideActivity = myRideActivity;
        myRidesModelLists = myRidesModelList;
        this.context=context;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(myRideActivity).inflate(R.layout.custom_fare, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyviewHolder holder, int position) {

        final GetTimingModel.Timing myRidesModel = myRidesModelLists.get(position);
        holder.radioButton.setText(myRidesModel.getPick_up());

        if(new LocalTime(myRidesModel.getPick_up()).isBefore(new LocalTime())){
            holder.radioButton.setEnabled(false);
            holder.radioButton.setClickable(false);
        }
        else{
            holder.radioButton.setEnabled(true);
            holder.radioButton.setClickable(true);
            holder.radioButton.setChecked(holder.getAdapterPosition() == mSelectedItem);
            Log.e("tag","checkpos "+mSelectedItem+", "+holder.getAdapterPosition());
            if (holder.radioButton.isChecked())
                holder.radioButton.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.orange_background));
            else holder.radioButton.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.white_background));
        }
        //int id = (position+1)*100;
       /* RadioButton rb = new RadioButton(BookTimingAdapter.this.context);
        rb.setId(holder.getAdapterPosition());
        rb.setText(myRidesModel.getPick_up());
        holder.radioGroup.addView(rb);*/
       /* holder.tvSource.setText(myRidesModel.getPick_up());
        if(new LocalTime(myRidesModel.getPick_up()).isBefore(new LocalTime())){
            holder.tvSource.setEnabled(false);
            holder.tvSource.setClickable(false);
        }
        else{

            holder.rootLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Log.e("BookTimg",""+myRidesModel.getBus_ids()+" "+myRidesModel.getBus_shedule_id()+" "+myRidesModel.getPick_up());
                    myRideActivity.itemClicked(myRidesModel.getBus_ids(),myRidesModel.getBus_shedule_id(),myRidesModel.getPick_up());
                    //myRideActivity.startActivity(new Intent(myRideActivity, BookRideActivity.class));

                }
            });
        }*/

       /*rb.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(context,""+holder.getAdapterPosition(),Toast.LENGTH_SHORT).show();
           }
       });*/

    }

    @Override
    public int getItemCount() {
        return myRidesModelLists.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        //TextView tvSource;
        LinearLayout rootLayout;
       // RadioGroup radioGroup;
        RadioButton radioButton;

        public MyviewHolder(View itemView) {
            super(itemView);
            rootLayout = itemView.findViewById(R.id.rootLayout);
            radioButton = itemView.findViewById(R.id.timegrp);
            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    myRideActivity.itemClicked(myRidesModelLists.get(getAdapterPosition()).getBus_ids(),myRidesModelLists.get(getAdapterPosition()).getBus_shedule_id(),myRidesModelLists.get(getAdapterPosition()).getPick_up());
                    notifyDataSetChanged();
                }
            };
            itemView.setOnClickListener(clickListener);
            radioButton.setOnClickListener(clickListener);

            /*radioGroup = itemView.findViewById(R.id.timegrp);
            //tvSource = itemView.findViewById(R.id.tvSource);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    //since only one package is allowed to be selected
                    //this logic clears previous selection
                    //it checks state of last radiogroup and
                    // clears it if it meets conditions
                    if (lastCheckedRadioGroup != null
                            && lastCheckedRadioGroup.getCheckedRadioButtonId()
                            != radioGroup.getCheckedRadioButtonId()
                            && lastCheckedRadioGroup.getCheckedRadioButtonId() != -1) {
                        lastCheckedRadioGroup.clearCheck();

                        *//*Toast.makeText(context,
                                "Radio button clicked " + radioGroup.getCheckedRadioButtonId()+","+i,
                                Toast.LENGTH_SHORT).show();*//*

                    }
                    lastCheckedRadioGroup = radioGroup;

                }
            });*/
        }
    }
}
