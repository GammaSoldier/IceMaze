package de.joekoperski.icemaze;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static int getNextLevelToPlay(Context context) {
        SharedPreferences pref = context.getSharedPreferences("Preferences", context.MODE_PRIVATE); // 0 - for private mode
        return pref.getInt("NextLevel", 1);    //default is level 1
    }// getNextLevelToPlay


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static void setNextLevelToPlay(Context context, int level) {
        SharedPreferences pref = context.getSharedPreferences("Preferences", context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt("NextLevel", level); // Storing integer
        editor.apply();
    }// setNextLevelToPlay

}
