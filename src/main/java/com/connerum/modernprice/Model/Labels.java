package com.connerum.modernprice.Model;

public class Labels {
    public String size;
    public String name;
    public int quantity;
    public float cash;
    public float credit;

    public Labels(String size, String name, int quantity, String cash) {
        this.size = size;
        this.name = name;
        this.quantity = quantity;
        this.cash = Convert.extractFloatValue(cash);
        this.credit = Convert.cashToCredit(cash);
    }
}
