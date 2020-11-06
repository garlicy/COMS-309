package com.example.guessgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

import androidx.appcompat.app.AppCompatActivity;

public class Level1 extends AppCompatActivity implements Serializable {

    private GuessGame gg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        Intent i = getIntent();
        gg = (GuessGame) i.getSerializableExtra("gg");
        Button butt1 = findViewById(R.id.button1);
        butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gg.isDead())
                    openLoser();
                if(gg.guess(1)){
                    openCorrect();
                    openLevel2();
                }
                else{
                    openWrong();
                }
            }
        });
        Button butt2 = findViewById(R.id.button2);
        butt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gg.isDead())
                    openLoser();
                if(gg.guess(2)){
                    openCorrect();
                    openLevel2();
                }
                else{
                    openWrong();
                }
            }
        });
    }
    public void openCorrect(){
        Intent i = new Intent(this, Correct.class);
        startActivity(i);
    }
    public void openWrong(){
        Intent i = new Intent(this, Wrong.class);
        startActivity(i);
    }
    public void openLevel2(){

    }
    public void openLoser(){

    }
}
