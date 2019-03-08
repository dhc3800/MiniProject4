package com.dhc3800.mp4;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddPurchase extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener{

    private int year = -1;
    private int month = -1;
    private int day = -1;
    private String merchant;
    private String cost;
    private String description;
    private String date;
    private boolean dateSelected = false;
    private Button submit;
    private EditText Cost;
    private EditText Merchant;
    private EditText Description;
    private Helper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase);

        findViewById(R.id.setD).setOnClickListener(this);
        findViewById(R.id.submit).setOnClickListener(this);
        Merchant = findViewById(R.id.Merchant);
        Description = findViewById(R.id.daTE);
        Cost = findViewById(R.id.cos);
        helper = new Helper(this);



    }

    public void upload(Purchase purchase) {
        helper.insert(purchase);
        startActivity(new Intent(AddPurchase.this, MainActivity.class));

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.submit:
                if (!dateSelected) {
                    Toast.makeText(this, "Set a date", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(Cost.getText()) || TextUtils.isEmpty(Description.getText()) || TextUtils.isEmpty(Merchant.getText())) {
                    Toast.makeText(this, "Fill out the form", Toast.LENGTH_LONG).show();
                    return;
                }

                cost = Cost.getText().toString();
                description = Description.getText().toString();
                merchant = Merchant.getText().toString();

                Purchase purchase = new Purchase(cost, description, merchant, date);
                upload(purchase);
                break;

            case R.id.setD:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        AddPurchase.this,
                        R.style.Theme_AppCompat_Light_Dialog_Alert,
                        this,
                        year, month, day);
                dialog.show();
                break;

        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = Utils.dateAdd0(year, month, dayOfMonth);
        this.year = year;
        this.month = month + 1;
        this.day = dayOfMonth;
        this.dateSelected = true;
    }
}
