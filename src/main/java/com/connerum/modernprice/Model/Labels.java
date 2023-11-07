package com.connerum.modernprice.Model;

public class Labels {
    public String size;
    public int quantity;
    public String cash;
    public String credit;

    public Labels(String size, int quantity, String cash) {
        this.size = size;
        this.quantity = quantity;
        this.cash = Convert.formatCurrencyValue(cash);
        this.credit = Convert.cashToCredit(cash);
    }
}
