package com.example.usuario.inventoryfragment.ui.inventory;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.usuario.inventoryfragment.R;
import com.example.usuario.inventoryfragment.data.prefs.AppPreferencesHelper;

/**
 * Clase que muestra el activity de Inventory.
 */
public class InventoryActivity extends Application {

    private AppPreferencesHelper appPreferencesHelper;
    public static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        appPreferencesHelper = AppPreferencesHelper.getInstance();
    }

    public AppPreferencesHelper getAppPreferencesHelper(){
        return  appPreferencesHelper;
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return super.getSharedPreferences(name, mode);
    }
}
