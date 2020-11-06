package com.example.cardgames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class GameCategory extends AppCompatActivity {

    FrameLayout f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_category);

        f = findViewById(R.id.cgm_shell_vert);
        f.setVisibility(View.VISIBLE);

    }
}
