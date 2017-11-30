package com.example.usuario.inventoryfragment.ui.prefs;

import android.preference.PreferenceActivity;
import android.os.Bundle;

import com.example.usuario.inventoryfragment.R;

public class GeneralSettingActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.general_settings);
    }
}
