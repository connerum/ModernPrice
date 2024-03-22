package com.connerum.modernprice.Model;

import java.util.prefs.Preferences;

public class LicenseStorage {
    private static final Preferences prefs = Preferences.userNodeForPackage(LicenseValidator.class);

    public static void saveLicenseKey(String licenseKey) {
        prefs.put("licenseKey", licenseKey);
        System.out.println(licenseKey);
    }

    public static String getLicenseKey() {
        return prefs.get("licenseKey", null); // Returns null if not found
    }
}