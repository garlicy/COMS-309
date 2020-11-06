package com.example.cardgames.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cardgames.R;

/**
 * @author Colton Hazlett
 * Will be used for any of the game settings that will be able to change
 */

public class AppSettings extends AppCompatActivity {

    /**
     * Creates the screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);
    }
}
