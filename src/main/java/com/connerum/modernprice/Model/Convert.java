package com.connerum.modernprice.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class Convert {
    public static String rate;
    public static String cashToCredit(String cash) {
        float cashValue = extractFloatValue(cash);
        BigDecimal creditValue = new BigDecimal(cashValue).multiply(new BigDecimal(rate));

        // Round up to the nearest cent
        creditValue = creditValue.setScale(2, RoundingMode.CEILING);

        // Format the BigDecimal as a string with 2 decimal places and commas
        return formatCurrencyValue(creditValue.toString());
    }

    public static float extractFloatValue(String str) {
        String cleanedStr = str.replaceAll("[^\\d.]", "");
        return Float.parseFloat(cleanedStr);
    }

    public static String formatCurrencyValue(String currencyAmount) {
        Float currencyValue = extractFloatValue(currencyAmount);
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        return formatter.format(currencyValue);
    }
}
