package com.dhc3800.mp4;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

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
        holder.cost.setText("Cost: " + (purchase.getCost()));
        holder.place.setText("Place: " + purchase.getStoreName());
        holder.description.setText("Description: " +purchase.getDescription());
        holder.date.setText(purchase.getDate());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), PurchaseDetails.class);
                i.putExtra("cost", purchase.getCost());
                i.putExtra("place", purchase.getStoreName());
                i.putExtra("description", purchase.getDescription());
                i.putExtra("date", purchase.getDate());
                v.getContext().startActivity(i);
            }


        });
        holder.v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {

                new AlertDialog.Builder(v.getContext()).setTitle("Delete Entry")
                        .setMessage("Are you sure you want to delete")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Purchase p = purchaseList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, purchaseList.size());
                                Helper helper = new Helper(v.getContext());
                                helper.delete(helper.getPurchase(purchase.getId()));
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
                };
            }

        );


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
        View v;
        public ViewHolder(View view) {
            super(view);
            this.v = view;

            this.cost = view.findViewById(R.id.cos);
            this.date = view.findViewById(R.id.daTE);
            this.description = view.findViewById(R.id.desc);
            this.place = view.findViewById(R.id.place);




        }

    }
    public void setChange(ArrayList<Purchase> list) {
        purchaseList = new ArrayList<>();
        purchaseList.addAll(list);
        notifyDataSetChanged();
    }



}
