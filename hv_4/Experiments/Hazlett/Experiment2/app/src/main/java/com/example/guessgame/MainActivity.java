package com.example.guessgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Serializable {

    private Button playButton;
    private GuessGame gg = new GuessGame();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLevel1();
            }
        });
    }
    public void openLevel1(){
        Intent i = new Intent(this, Level1.class);
        //i.putExtra("gg", gg);
        startActivity(i);
    }
}
