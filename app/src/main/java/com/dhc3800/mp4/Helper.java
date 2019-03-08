package com.dhc3800.mp4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Helper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "purchases.db";
    public static final String TABLE_NAME = "Purchases";
    public static final String COLUMN_ID = "purchaseID";
    public static final String COLUMN_MERCHANT = "merchant";
    public static final String COLUMN_COST = "cost";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_SECONDS = "seconds";


    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_MERCHANT + " TEXT,"
                    + COLUMN_COST + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_DATE + " TEXT,"
                    + COLUMN_SECONDS + " TIMESTAMP)";
    private static final String DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insert(Purchase purchase) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MERCHANT, purchase.getStoreName());
        values.put(COLUMN_COST, purchase.getCost());
        values.put(COLUMN_DESCRIPTION, purchase.getDescription());
        values.put(COLUMN_DATE, purchase.getDate());
        values.put(COLUMN_SECONDS, Utils.time(purchase.getDate()));
        db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<Purchase> getAll() {
        ArrayList<Purchase> purchases = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " +
                COLUMN_SECONDS + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String merch = cursor.getString(cursor.getColumnIndex(COLUMN_MERCHANT));
                String cost = cursor.getString(cursor.getColumnIndex(COLUMN_COST));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                Purchase purchase = new Purchase(cost, description, merch, date);
                purchase.setID(id);
                purchases.add(purchase);
            } while (cursor.moveToNext());
        }
        db.close();
        return purchases;
    }
    public Purchase getPurchase(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_MERCHANT, COLUMN_COST, COLUMN_DESCRIPTION, COLUMN_DATE, COLUMN_SECONDS},
                COLUMN_ID + " =?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Purchase purchase = new Purchase(
                cursor.getString(cursor.getColumnIndex(COLUMN_COST)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(COLUMN_MERCHANT)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
        purchase.setID(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        cursor.close();
        return purchase;

    }



    public void delete(Purchase purchase) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_COST+" = ? and " + COLUMN_DATE + " = ? and " + COLUMN_DESCRIPTION +
                " = ? and " + COLUMN_MERCHANT + " + ?", new String[]{purchase.getCost(), purchase.getDate(),
        purchase.getDescription(), purchase.getStoreName()});
        db.close();
    }
}

