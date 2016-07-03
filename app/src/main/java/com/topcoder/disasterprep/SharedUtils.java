package com.topcoder.disasterprep;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedUtils {

    private static final String MY_PREFS_NAME = "com_topcoder_disasterprep_prefs";
    public static final String USER_NAME_KEY = "user_name";
    public static final String LOGIN_PASSWORD_KEY = "login_password";
    public static final String KEY_FIRST_NAME = "key_first_name";
    public static final String KEY_LAST_NAME = "key_last_name";
    public static final String KEY_BUSINESS_NAME = "key_business_name";
    public static final String KEY_EMAIL = "key_email";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static String getUsername(Context context) {
        return getSharedPreferences(context).getString(USER_NAME_KEY, "");
    }

    public static String getPassword(Context context) {
        return getSharedPreferences(context).getString(LOGIN_PASSWORD_KEY, "");
    }

    public static String getFullName(Context context) {
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs.getString(KEY_FIRST_NAME, "") + " " + prefs.getString(KEY_LAST_NAME, "");
    }

    public static String getBusinessName(Context context) {
        return getSharedPreferences(context).getString(KEY_BUSINESS_NAME, "");
    }

    public static String getEmail(Context context) {
        return getSharedPreferences(context).getString(KEY_EMAIL, "");
    }

    public static void setUserInfo(Context context, String firstName, String lastName, String businessName, String email, String password) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_FIRST_NAME, firstName)
                .putString(KEY_LAST_NAME, lastName)
                .putString(KEY_BUSINESS_NAME, businessName)
                .putString(KEY_EMAIL, email)
                .putString(USER_NAME_KEY, email)
                .putString(LOGIN_PASSWORD_KEY, password)
                .apply();
    }
}
