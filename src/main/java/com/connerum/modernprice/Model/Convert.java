package com.connerum.modernprice.Model;

public class Convert {
    public static float cashToCredit(String cash) {
        float cashValue = extractFloatValue(cash);
        float creditValue = (float) (cashValue * 1.04);

        // Round up to the nearest cent
        creditValue = (float) (Math.ceil(creditValue * 100) / 100.0);

        return creditValue;
    }

    public static float extractFloatValue(String str) {
        String cleanedStr = str.replaceAll("[^\\d.]", "");
        return Float.parseFloat(cleanedStr);
    }
}
