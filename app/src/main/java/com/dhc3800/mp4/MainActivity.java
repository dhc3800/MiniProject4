package com.dhc3800.mp4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Purchase> purchaseArrayList;
    private ArrayList<Purchase> masterList;
    private PurchaseAdapter purchaseAdapter;
    private Helper helper;
    private android.widget.SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recylerView);
        purchaseArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        helper = new Helper(this);
        purchaseArrayList = helper.getAll();
        purchaseAdapter = new PurchaseAdapter(purchaseArrayList);
        recyclerView.setAdapter(purchaseAdapter);
        masterList = purchaseArrayList;
        findViewById(R.id.addPurchase).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.addPurchase:
                startActivity(new Intent(MainActivity.this, AddPurchase.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        final MenuItem searchItem = menu.findItem(R.id.hehexd);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Description");
        SharedPreferences sPref = this.getPreferences(Context.MODE_PRIVATE);
        String search = sPref.getString("search", "");
        if (search != "") {
            searchView.setQuery(search, false);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                query = query.toLowerCase();
                filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();

                filter(newText);
                return false;
            }
        });
        return true;

    }

    public void filter(String query) {
        ArrayList<Purchase> newList = new ArrayList<>();
        for (Purchase p: masterList) {
            String pDesc = p.getDescription().toLowerCase();
            if (pDesc.contains(query)) {
                newList.add(p);
            }

        }
        SharedPreferences sPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("search", query);
        editor.commit();

        purchaseAdapter.setChange(newList);


    }



//    public void generate() {
//        purchaseArrayList.add(new Purchase("2", "Cookie", "Ralph's", "12/1/2019"));
//        purchaseArrayList.add(new Purchase("3", "Gummy Worms", "Walgreens", "11/1/2019"));
//        purchaseArrayList.add(new Purchase("5", "Ice Cream", "Trader Joes", "10/1/2019"));
//        purchaseArrayList.add(new Purchase("2.50", "Izze", "Bear Mart", "9/1/2019"));
//        purchaseArrayList.add(new Purchase("12.14", "Sandwich", "Ike's", "8/1/2019"));
//        purchaseArrayList.add(new Purchase("12.25", "Pad Thai", "Imm Thai", "7/1/2019"));
//    }
}
