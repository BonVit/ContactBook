package com.vb.contactbook.utils;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by bonar on 6/13/2017.
 */

public class SharedPreferences {
    private static final String USER_ID = "user_id";

    public static String getUserId(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(USER_ID, null);
    }

    public static void setUserId(Context context, String id){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(USER_ID, id)
                .apply();
    }

}
