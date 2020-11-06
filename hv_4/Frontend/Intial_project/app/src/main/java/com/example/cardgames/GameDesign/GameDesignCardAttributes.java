package com.example.cardgames.GameDesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cardgames.Games.HelpingClasses.Game;
import com.example.cardgames.R;

import java.util.ArrayList;

public class GameDesignCardAttributes extends AppCompatActivity {

    private LinearLayout screen;
    private ArrayList<String> deck;
    private Integer height;
    //Note on card order: Diamond, then Club, then Heart, then Spade, for Ace to King.
    private ArrayList<EditText> cardValueInputs;

    private Button finishedButtton;
    private Button undoButton;
    private Bundle gameData;

    private int ImageID = R.drawable.icon3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_design_card_attributes);


        setUp();

        finishedButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignCardAttributes.this, GameDesignScreenMeta.class);
                gameData.putIntegerArrayList(GameDesignInputReading.cardPointListID, readData());
                i.putExtra(GameDesignInputReading.gameDataID, gameData);

                i.putExtra("username", getIntent().getStringExtra("username"));
                i.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
                i.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));
                startActivity(i);
            }
        });

        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignCardAttributes.this, GameDesignScreenMeta.class);
                i.putExtra(GameDesignInputReading.gameDataID, gameData);

                i.putExtra("username", getIntent().getStringExtra("username"));
                i.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
                i.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));
                startActivity(i);
            }
        });
    }

    private void setUp()
    {
        gameData = getIntent().getBundleExtra(GameDesignInputReading.gameDataID);
        ImageID = getIntent().getIntExtra("imageID", R.drawable.icon3);
        cardValueInputs = new ArrayList<>();
        screen = findViewById(R.id.primaryView);
        deck = GameDesignInputReading.cardNames();
        height = 1;
        for(int i = 0; i < deck.size(); i++)
        {
            TextView cardName = new TextView(getApplicationContext());
            cardName.setTextColor(getResources().getColor(android.R.color.white));
            cardName.setText(deck.get(i));
            screen.addView(cardName,height);
            height++;
            EditText pointEditor = new EditText(getApplicationContext());
            pointEditor.setBackgroundColor(getResources().getColor(android.R.color.white));
            pointEditor.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
            pointEditor.setText(gameData.getIntegerArrayList(GameDesignInputReading.cardPointListID).get(i).toString());
            screen.addView(pointEditor,height);
            cardValueInputs.add(pointEditor);
            height++;
        }

        finishedButtton = findViewById(R.id.finishButton);
        undoButton = findViewById(R.id.undoButton);
    }

    private ArrayList<Integer> readData()
    {
        ArrayList<Integer> retVal = new ArrayList<>();
        for(int i = 0; i < cardValueInputs.size(); i++)
        {
            retVal.add(Integer.parseInt(cardValueInputs.get(i).getText().toString()));
        }
        return retVal;
    }
}
