package com.aaron.cs.africanowned;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RecyclerViewBusinessAdminAdapter extends RecyclerView.Adapter<RecyclerViewBusinessAdminAdapter.MyAdminViewHolder>{

    DatabaseReference mBase;
    Task<Void> yourRef;
    private LayoutInflater inflater;
    Context context;
    List<String> businesses;
    List<Boolean> verified;

    public RecyclerViewBusinessAdminAdapter(Context context, List<String> businesses, List<Boolean> verified) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.businesses=businesses;
        this.verified = verified;
    }

    @Override
    public MyAdminViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.admin_recycler_view_item, parent, false);
        MyAdminViewHolder holder = new MyAdminViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdminViewHolder holder, int position) {
        holder.businessName.setText(businesses.get(position));

        if(verified.get(position))
        {
            holder.verified.setText("Verified");
        }
        else
        {
            holder.verified.setText("Not Verified");
        }

        holder.verified.setOnClickListener(view -> {
            mBase = FirebaseDatabase.getInstance().getReference();
            if(verified.get(position))
            {
                yourRef = mBase.child("companies/"+businesses.get(position)+"/verified").setValue("false");
                verified.set(position, false);
                holder.verified.setText("Not Verified");
                Toast.makeText(context, "Updated Business Status!", Toast.LENGTH_LONG).show();
            }
            else
            {
                yourRef = mBase.child("companies/"+businesses.get(position)+"/verified").setValue("true");
                verified.set(position, true);
                holder.verified.setText("Verified");
                Toast.makeText(context, "Updated Business Status!", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return businesses.size();
    }

    class MyAdminViewHolder extends RecyclerView.ViewHolder
    {
        TextView businessName;
        Button verified;
        Context context;

        public MyAdminViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            businessName = (TextView)itemView.findViewById(R.id.businessName);
            verified = itemView.findViewById(R.id.verified);


        }
    }
}