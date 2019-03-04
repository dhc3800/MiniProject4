package com.dhc3800.mp4;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolder> {
    private ArrayList<Purchase> purchaseList;

    @Override
    public PurchaseAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purhcase_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Purchase purchase = purchaseList.get(position);
        holder.cost.setText((purchase.getCost()));
        holder.place.setText(purchase.getStoreName());
        holder.description.setText(purchase.getDescription());
        holder.date.setText(purchase.getDate());
    }

    @Override
    public int getItemCount() {
        if (purchaseList== null) {
            return 0;
        }
        return purchaseList.size();
    }
    public PurchaseAdapter(ArrayList<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cost;
        TextView date;
        TextView description;
        TextView place;
        public ViewHolder(View view) {
            super(view);
            this.cost = view.findViewById(R.id.cost);
            this.date = view.findViewById(R.id.date);
            this.description = view.findViewById(R.id.description);
            this.place = view.findViewById(R.id.place);


        }

    }



}
