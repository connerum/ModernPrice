package com.connerum.modernprice.Model;

public class Labels {
    public String size;
    public int quantity;
    public float cash;
    public float credit;

    public Labels(String size, int quantity, String cash) {
        this.size = size;
        this.quantity = quantity;
        this.cash = Convert.extractFloatValue(cash);
        this.credit = Convert.cashToCredit(cash);
    }
}
