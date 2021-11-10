package com.aaron.cs.africanowned;

import android.content.Context;
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

    public RecyclerViewBusinessAdapter(Context context, List<String> businesses) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.businesses=businesses;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_frame, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.businessName.setText(businesses.get(position));
    }

    @Override
    public int getItemCount() {
        return businesses.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView businessName;

        public MyViewHolder(View itemView) {
            super(itemView);
            businessName = (TextView)itemView.findViewById(R.id.businessName);
        }
    }
}