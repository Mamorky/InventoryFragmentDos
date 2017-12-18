package com.example.usuario.inventoryfragment.data.prefs;

import android.content.SharedPreferences;

import com.example.usuario.inventoryfragment.ui.inventory.InventoryActivity;
import com.example.usuario.inventoryfragment.ui.utils.AppConstant;

/**
 * Created by mamorky on 4/12/17.
 */

public class AppPreferencesHelper implements AccountPreferencesHelper,GeneralPreferenceHelper {
    /**
     * Se definen todas las keys posibles del fichero de preferencias
     * */



    //2. Objeto para editar las preferencias
    private final SharedPreferences preferences;
    private static AppPreferencesHelper instance;

    private AppPreferencesHelper() {
        //Si es el fichero por defecto de las preferencias
        this.preferences = InventoryActivity.preferences;
    }

    /**
     * Método de acceso
     * */
    public static AppPreferencesHelper getInstance(){
        if(instance == null)
            instance = new AppPreferencesHelper();
        return instance;
    }

    public long getCurrentUserId() {
        long id = preferences.getLong(PREF_KEY_CURRENT_USER_ID,AppConstant.NULL_INDEX);
        return id;
    }

    public String getCurrentUserName() {
        String name = preferences.getString(PREF_KEY_CURRENT_USER_ID,null);
        return name;
    }

    public String getCurrentUserPassword() {
        String password = preferences.getString(PREF_KEY_CURRENT_USER_ID,null);
        return password;
    }

    public boolean getCurrentUserRemember() {
        Boolean remeber = preferences.getBoolean(PREF_KEY_CURRENT_USER_ID,false);
        return remeber;
    }

    public void setCurrentUserId(long id) {
        preferences.edit().putLong(PREF_KEY_CURRENT_USER_ID,id).apply();
    }

    public void setCurrentUserName(String name) {
        preferences.edit().putString(PREF_KEY_CURRENT_USER_NAME,name).apply();
    }

    public void setCurrentUserPassword(String pass) {
        preferences.edit().putString(PREF_KEY_CURRENT_USER_PASSWORD,pass).apply();
    }

    public void setCurrentUserRemember(boolean remember) {
        preferences.edit().putBoolean(PREF_KEY_CURRENT_USER_REMEMBER,remember).apply();
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }
}
