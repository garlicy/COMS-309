package com.example.lobby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class profile_screen extends AppCompatActivity {

    Button back, account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        back = findViewById(R.id.backButtonProfile);
        //account = findViewById(R.id.)

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(profile_screen.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}









