package com.dhc3800.mp4;

public class Purchase {
    private String cost;
    private String description;
    private String storeName;
    private String date;
    private int id;

    public Purchase(String cost, String description, String storeName, String date) {
        this.cost = cost;
        this.description = description;
        this.storeName = storeName;
        this.date = date;
    }


    public void setID(int id) {
        this.id = id;
    }
    public int getId() { return id;}

    public String getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getStoreName() {
        return storeName;
    }
    public String getDate() {
        return date;
    }
}
