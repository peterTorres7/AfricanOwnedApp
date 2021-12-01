package com.aaron.cs.africanowned;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView view;
    private final Context context;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        view = itemView.findViewById(R.id.businessName);
    }

    public TextView getView(){
        return view;
    }
}