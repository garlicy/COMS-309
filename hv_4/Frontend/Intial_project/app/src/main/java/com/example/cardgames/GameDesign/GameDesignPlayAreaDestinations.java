package com.example.cardgames.GameDesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cardgames.R;

import java.util.ArrayList;

public class GameDesignPlayAreaDestinations extends AppCompatActivity {

    private Bundle gameData;
    private ArrayList<String> pileNames;
    private LinearLayout mainLayout;
    private Integer height;
    private ArrayAdapter<String> selectionAdapter;
    private ArrayList<Spinner> selectors;
    private Button saveButton;
    private Button leaveButton;
    private int ImageID = R.drawable.icon3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_design_play_area_destinations);


        setUp();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> choices = readData();
                gameData.putStringArrayList(GameDesignInputReading.playAreaDestinationsID, choices);
                Intent i = new Intent(GameDesignPlayAreaDestinations.this, GameDesignScreenPlayAreas.class);
                i.putExtra(GameDesignInputReading.gameDataID, gameData);

                i.putExtra("username", getIntent().getStringExtra("username"));
                i.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
                i.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));
                startActivity(i);
            }
        });

        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignPlayAreaDestinations.this, GameDesignScreenPlayAreas.class);
                i.putExtra(GameDesignInputReading.gameDataID, gameData);

                i.putExtra("username", getIntent().getStringExtra("username"));
                i.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
                i.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));
                startActivity(i);
            }
        });
    }

    private static ArrayList<String> pileSpinnerMaker(ArrayList<String> addTo, ArrayList<String> names, Integer start, String prefix)
    {
        ArrayList<String> retVal = addTo;
        if(start >= names.size())
        {
            if(addTo.contains(prefix))
            {
                return retVal;
            }
            else
            {
                retVal.add(prefix);
                return retVal;
            }
        }
        else
        {
            retVal = pileSpinnerMaker(retVal, names, start + 1, prefix);
            String addition = prefix + " " + names.get(start);
            retVal = pileSpinnerMaker(retVal, names, start + 1, addition);
            return retVal;
        }
    }

    private void setUp()
    {
        selectors = new ArrayList<>();
        height = 2;
        gameData = getIntent().getBundleExtra(GameDesignInputReading.gameDataID);
        pileNames = gameData.getStringArrayList(GameDesignInputReading.playAreaNamesID);
        mainLayout = findViewById(R.id.linearLayoutDestinations);
        saveButton = findViewById(R.id.saveFinishButton);
        leaveButton = findViewById(R.id.leaveButton);
        ImageID = getIntent().getIntExtra("imageID", R.drawable.icon3);

        ArrayList pileCombinations = new ArrayList();
        pileCombinations = pileSpinnerMaker(pileCombinations, pileNames,0, "");
        selectionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pileCombinations);
        selectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        for(int i = 0; i < pileNames.size(); i++)
        {
            TextView title = new TextView(getApplicationContext());
            title.setText("Play area " + pileNames.get(i) + " can receive from:");
            title.setTextColor(getResources().getColor(android.R.color.white));
            mainLayout.addView(title,height);
            height++;
            Spinner selection = new Spinner(getApplicationContext());
            selection.setAdapter(selectionAdapter);
            selection.setBackgroundColor(getResources().getColor(android.R.color.white));
            mainLayout.addView(selection,height);
            height++;
            selectors.add(selection);
        }
    }

    private ArrayList<String> readData()
    {
        ArrayList<String> retVal = new ArrayList<>();
        for(int i = 0; i < selectors.size(); i++)
        {
            retVal.add(selectionAdapter.getItem(selectors.get(i).getSelectedItemPosition()));
        }
        return retVal;
    }
}
