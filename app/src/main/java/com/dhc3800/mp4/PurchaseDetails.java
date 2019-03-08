package com.dhc3800.mp4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PurchaseDetails extends AppCompatActivity {
    private String cost;
    private String merchant;
    private String date;
    private String description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_details);

        Bundle b = getIntent().getExtras();
        cost = b.getString("cost");
        merchant = b.getString("place");
        date = b.getString("date");
        description = b.getString("description");
        TextView merch = findViewById(R.id.merchantString);
        merch.setText(merchant);
        TextView dat = findViewById(R.id.dateString);
        dat.setText(date);
        TextView desc = findViewById(R.id.descriptionString);
        desc.setText(description);
        TextView co = findViewById(R.id.costString);
        co.setText(cost);





    }
}
