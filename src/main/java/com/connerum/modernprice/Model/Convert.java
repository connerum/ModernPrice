package com.connerum.modernprice.Model;

public class Convert {
    public static float cashToCredit(String cash) {
        float cashValue = extractFloatValue(cash);
        return (float) (cashValue * 1.04);
    }

    public static float extractFloatValue(String str) {
        String cleanedStr = str.replaceAll("[^\\d.]", "");
        return Float.parseFloat(cleanedStr);
    }
}
