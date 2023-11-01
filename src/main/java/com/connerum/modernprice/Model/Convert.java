package com.connerum.modernprice.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Convert {
    public static String cashToCredit(String cash) {
        float cashValue = extractFloatValue(cash);
        BigDecimal creditValue = new BigDecimal(cashValue).multiply(new BigDecimal("1.04"));

        // Round up to the nearest cent
        creditValue = creditValue.setScale(2, RoundingMode.CEILING);

        // Convert to string with 2 decimal places
        return String.format("%.2f", creditValue);
    }

    public static float extractFloatValue(String str) {
        String cleanedStr = str.replaceAll("[^\\d.]", "");
        return Float.parseFloat(cleanedStr);
    }

    public static String formatCashValue(String cash) {
        Float cashStr = extractFloatValue(cash);
        return String.format("%.2f", cashStr);
    }
}
