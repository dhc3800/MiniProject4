package com.dhc3800.mp4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Purchase> purchaseArrayList;
    private PurchaseAdapter purchaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recylerView);
        purchaseArrayList = new ArrayList<>();
        generate();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        purchaseAdapter = new PurchaseAdapter(purchaseArrayList);
        recyclerView.setAdapter(purchaseAdapter);



    }

    public void generate() {
        purchaseArrayList.add(new Purchase("2", "Cookie", "Ralph's", "12/1/2019"));
        purchaseArrayList.add(new Purchase("3", "Gummy Worms", "Walgreens", "11/1/2019"));
        purchaseArrayList.add(new Purchase("5", "Ice Cream", "Trader Joes", "10/1/2019"));
        purchaseArrayList.add(new Purchase("2.50", "Izze", "Bear Mart", "9/1/2019"));
        purchaseArrayList.add(new Purchase("12.14", "Sandwich", "Ike's", "8/1/2019"));
        purchaseArrayList.add(new Purchase("12.25", "Pad Thai", "Imm Thai", "7/1/2019"));
    }
}
