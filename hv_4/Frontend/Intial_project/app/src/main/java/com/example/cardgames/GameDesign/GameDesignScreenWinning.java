package com.example.cardgames.GameDesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgames.R;
import com.example.cardgames.ScreenParent;

import java.util.ArrayList;

/**
 * @author Benjamin Goodall
 * @date 3/29/2020
 *
 * Screen that receives and stores user input on the game's win conditions.
 */
public class GameDesignScreenWinning extends ScreenParent {


    /**
     * Holds the url that contacts the server
     */
    private String url;

    /**
     * Bundle that keeps track of all new and known choices for the game.
     */
    private Bundle gameData;

    /**
     * RadioGroup the user uses to choose and end condition.
     */
    private RadioGroup endChoice;

    /**
     * Allows the user to input the amount of points needed for the relevant end goals.
     */
    private EditText pointGoal;
    /**
     * Allows the user to input the total number of rounds needed for the relevant end goal.
     */
    private EditText totalRounds;
    /**
     * Holds the data for the play areas the user set on a previous screen.
     */
    private ArrayList<String> playAreas;

    private Spinner spin;
    /**
     * Allows user to select which play area should exhaust, for relevant end goals.
     */
    private Spinner deckToExhaust;
    private TextView username;
    private ImageView userimage;
    private String UserName;
    private int ImageID = R.drawable.icon3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_design_screen_winning);

        url = "";


        gameData = getIntent().getBundleExtra(GameDesignInputReading.gameDataID);
        if(gameData == null)
        {
            gameData = new Bundle();
        }

        Button meta = findViewById(R.id.metaButton);
        Button playArea = findViewById(R.id.playAreasButton);
        Button winning = findViewById(R.id.winningButton);
        Button phases = findViewById(R.id.phasesButton);
        Button back = findViewById(R.id.backButton);
        Button done = findViewById(R.id.doneButton);

        endChoice = findViewById(R.id.endingRadios);

        pointGoal = findViewById(R.id.pointGoal);
        totalRounds = findViewById(R.id.totalRounds);

        userimage = findViewById(R.id.WINNING_UsersImage);
        username = findViewById(R.id.WINNING_UsersName);
        UserName = getIntent().getStringExtra("username");
        username.setText(UserName);
        ImageID = getIntent().getIntExtra("imageID", R.drawable.icon3);
        userimage.setImageDrawable(getDrawable(ImageID));

        spin = findViewById(R.id.SPINNER_Winning);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SET_default, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        playAreas = new ArrayList<>();
        playAreas.add("none");


        deckToExhaust = findViewById(R.id.deckExhaustChoice);
        ArrayAdapter<String> decks = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, playAreas);
        decks.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deckToExhaust.setAdapter(decks);
        deckToExhaust.setBackgroundColor(getResources().getColor(android.R.color.white));




        if(getIntent().getBundleExtra(GameDesignInputReading.gameDataID) != null && getIntent().getBundleExtra(GameDesignInputReading.gameDataID).containsKey(GameDesignInputReading.playAreaNamesID)) {
            playAreas.addAll(getIntent().getBundleExtra(GameDesignInputReading.gameDataID).getStringArrayList(GameDesignInputReading.playAreaNamesID));
            pointGoal.setText(getIntent().getBundleExtra(GameDesignInputReading.gameDataID).getInt(GameDesignInputReading.pointsGoalID) + "");
            totalRounds.setText(getIntent().getBundleExtra(GameDesignInputReading.gameDataID).getInt(GameDesignInputReading.totalRoundsID) + "");
            RadioButton toCheck = GameDesignInputReading.getRadioToCheck(getIntent().getBundleExtra(GameDesignInputReading.gameDataID).getString(GameDesignInputReading.endChoiceID), endChoice);
            if(toCheck != null)
            {
                toCheck.setChecked(true);
            }
            deckToExhaust.setSelection(decks.getPosition(getIntent().getBundleExtra(GameDesignInputReading.gameDataID).getString(GameDesignInputReading.toExhaustID)));
        }

        meta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenWinning.this, GameDesignScreenMeta.class);
                i = setData(i);
                startActivity(i);
            }
        });

        playArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenWinning.this, GameDesignScreenPlayAreas.class);
                i = setData(i);
                startActivity(i);
            }
        });

        winning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"You're already here, ya fool.", Toast.LENGTH_SHORT).show();
            }
        });

        phases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenWinning.this, GameDesignScreenPhases.class);
                i = setData(i);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenWinning.this, GameDesignLeadInScreen.class);
                startActivity(i);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*RequestQueue mainReq = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.POST, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("Lead In Screen", response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Lead In Screen", "Error: " + error.getMessage());
                    }
                }) {
                    @Override
                    protected Map<String,String> getParams()
                    {
                        Map<String,String> params = new HashMap<>();
                        params.put(text, getIntent().getStringExtra(text));
                        params.put(check, "" + getIntent().getBooleanExtra(check, false));
                        params.put(radio, "" + endingRadios.getCheckedRadioButtonId());
                        params.put(counter,"" + getIntent().getIntExtra(counter,0));
                        return params;
                    }
                };

                mainReq.add(jsonReq);

                Intent i = new Intent(GameDesignScreenWinning.this, GameDesignLeadInScreen.class);

                i.putExtra("name", getIntent().getStringExtra("name"));
                i.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
                i.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));
                startActivity(i);

                 */
                Toast.makeText(getApplicationContext(),"I'm Sorry. We didn't finish writing to the server in time.", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Fills and returns a given intent with data that is always used.
     * @param i intent to be filled with general data
     * @return the given intent filled with general data
     */
    private Intent setData(Intent i)
    {
        RadioButton choice = findViewById(endChoice.getCheckedRadioButtonId());
        gameData.putString(GameDesignInputReading.endChoiceID, choice.getText().toString());
        if(totalRounds.getText().toString().length() >= 1) {
            gameData.putInt(GameDesignInputReading.totalRoundsID, Integer.parseInt(totalRounds.getText().toString()));
        }
        else
        {
            gameData.putInt(GameDesignInputReading.totalRoundsID, 1);
        }
        if(pointGoal.getText().toString().length() >= 1) {
            gameData.putInt(GameDesignInputReading.pointsGoalID, Integer.parseInt(pointGoal.getText().toString()));
        }
        else
        {
            gameData.putInt(GameDesignInputReading.pointsGoalID, 1);
        }
        gameData.putString(GameDesignInputReading.toExhaustID, deckToExhaust.getSelectedItem().toString());
        i.putExtra(GameDesignInputReading.gameDataID, gameData);


        i.putExtra("username", getIntent().getStringExtra("username"));
        i.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
        i.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));
        return i;
    }
}
