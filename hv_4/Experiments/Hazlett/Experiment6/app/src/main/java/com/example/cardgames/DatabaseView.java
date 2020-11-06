package com.example.cardgames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DatabaseView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_view);
        TextView tv = findViewById(R.id.info);
        CardGameMaker info = new CardGameMaker(this);
        info.open();
        String data = info.getData();
        tv.setText(data);
        info.close();

    }
}
