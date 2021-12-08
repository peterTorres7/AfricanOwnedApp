package com.aaron.cs.africanowned;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class RecyclerViewBusinessAdapter extends RecyclerView.Adapter<RecyclerViewBusinessAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    List<String> businesses;
    static List<String> aboutUs;
    static List<String> categoryNames;
    static List<String> hours;
    static List<String> locationNames;
    static List<String> websiteNames;
    static List<Boolean> verified;

    public RecyclerViewBusinessAdapter(Context context, List<String> businesses, List<String> aboutUs,
                                       List<String> categoryNames, List<String> hours, List<String> locationNames,
                                       List<String> websiteNames, List<Boolean> verified) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.businesses=businesses;
        this.aboutUs = aboutUs;
        this.categoryNames = categoryNames;
        this.hours = hours;
        this.locationNames = locationNames;
        this.websiteNames = websiteNames;
        this.verified = verified;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_frame, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if(verified.get(position))
        {
            holder.businessName.setText(businesses.get(position));


            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), BusinessInformation.class);

                intent.putExtra("BusinessName", businesses.get(position));
                intent.putExtra("AboutUs", aboutUs.get(position));
                intent.putExtra("Category", categoryNames.get(position));
                intent.putExtra("Location", locationNames.get(position));
                intent.putExtra("Hours", hours.get(position));
                intent.putExtra("website", websiteNames.get(position));
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return businesses.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView businessName;
        Context context;

        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            businessName = (TextView)itemView.findViewById(R.id.businessName);
        }
    }
}