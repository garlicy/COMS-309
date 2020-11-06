package com.example.hello_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScreenTwo extends AppCompatActivity {
    
    Button b3;
    TextView t1;
    boolean info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_two);

        info = getIntent().getBooleanExtra("info", false);
        t1 = findViewById(R.id.didTheyPress);
        b3 = findViewById(R.id.backButton);

        if(info)
        {
            t1.setText("You Pressed the first button.");
        }
        else
        {
            t1.setText("You did not press that first button.");
        }

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScreenTwo.this, MainActivity.class);
                startActivity(i);
            }
        });
    }


}
