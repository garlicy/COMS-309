package com.example.cardgames.GameDesign;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cardgames.R;
import com.example.cardgames.ScreenParent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This screen lets the user set data that describes the game itself, the number of players, and the number of decks to be shuffled together.
 * @author Benjamin Goodall
 * @date 3/29/2020
 */
public class GameDesignScreenMeta extends ScreenParent {

    /**
     * Lets user decide the Game's name.
     */
    private EditText gameName;

    private String url;
    /**
     * Input for Game Instructions
     */
    private EditText instructions;
    /**
     * input for desired Number of Decks
     */
    private EditText numDecks;
    /**
     * input for desired Number of Players
     */
    private EditText numPlayers;
    /**
     * Holds known and new data for the game.
     */
    private Bundle gameData;

    private Spinner spin;
    private TextView username;
    private ImageView userimage;
    private String UserName;
    private int ImageID = R.drawable.icon3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_design_screen_meta);

        url = "http://coms-309-hv-4.cs.iastate.edu:8080/game/add";


        userimage = findViewById(R.id.CARDS_UsersImage);
        username = findViewById(R.id.CARDS_UsersName);
        UserName = getIntent().getStringExtra("username");
        username.setText(UserName);
        ImageID = getIntent().getIntExtra("imageID", R.drawable.icon3);
        userimage.setImageDrawable(getDrawable(ImageID));


        spin = findViewById(R.id.SPINNER_Cards);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SET_default, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);



        Button cards = findViewById(R.id.metaButton);
        Button playArea = findViewById(R.id.playAreasButton);
        Button winning = findViewById(R.id.winningButton);
        Button phases = findViewById(R.id.phasesButton);
        Button back = findViewById(R.id.backButton);
        Button done = findViewById(R.id.doneButton);
        Button edit = findViewById(R.id.cardEditButton);

        gameName = findViewById(R.id.gameNameInput); //TODO: find some way to guarantee gameNames are unique.
        instructions = findViewById(R.id.instructions);
        numDecks = findViewById(R.id.numberOfDecks);
        numPlayers = findViewById(R.id.numberOfPlayers);


        gameData = getIntent().getBundleExtra(GameDesignInputReading.gameDataID);
        if(gameData == null)
        {
            gameData = new Bundle();
        }

        if(getIntent().getBundleExtra(GameDesignInputReading.gameDataID) != null) {
            gameName.setText(getIntent().getBundleExtra(GameDesignInputReading.gameDataID).getString(GameDesignInputReading.gameNameID));
            instructions.setText(getIntent().getBundleExtra(GameDesignInputReading.gameDataID).getString(GameDesignInputReading.instructionsID));
            numDecks.setText(getIntent().getBundleExtra(GameDesignInputReading.gameDataID).getString(GameDesignInputReading.numDecksID));
            numPlayers.setText(getIntent().getBundleExtra(GameDesignInputReading.gameDataID).getString(GameDesignInputReading.numPlayersID));
        }


        cards.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"You're already here, ya fool.", Toast.LENGTH_SHORT).show();
            }
        });

        playArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenMeta.this, GameDesignScreenPlayAreas.class);
                i = setData(i);
                startActivity(i);
            }
        });

        winning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenMeta.this, GameDesignScreenWinning.class);
                i = setData(i);
                startActivity(i);
            }
        });

        phases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenMeta.this, GameDesignScreenPhases.class);
                i = setData(i);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenMeta.this, GameDesignLeadInScreen.class);

                i.putExtra("username", getIntent().getStringExtra("username"));
                i.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
                i.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));
                startActivity(i);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameData.putString(GameDesignInputReading.instructionsID, instructions.getText().toString());
                gameData.putString(GameDesignInputReading.gameNameID, gameName.getText().toString());
                gameData.putString(GameDesignInputReading.numPlayersID, numPlayers.getText().toString());
                gameData.putString(GameDesignInputReading.numDecksID, numDecks.getText().toString());
                JSONObject JSONObjecthere = new JSONObject();
                try {
                    JSONObjecthere.put(GameDesignInputReading.instructionsID, gameData.getString(GameDesignInputReading.instructionsID));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObjecthere.put(GameDesignInputReading.gameNameID, gameData.getString(GameDesignInputReading.gameNameID));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObjecthere.put(GameDesignInputReading.numPlayersID, gameData.getString(GameDesignInputReading.numPlayersID));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObjecthere.put(GameDesignInputReading.numDecksID, gameData.getString(GameDesignInputReading.numDecksID));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObjecthere.put(GameDesignInputReading.totalRoundsID, gameData.getInt(GameDesignInputReading.totalRoundsID));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObjecthere.put(GameDesignInputReading.pointsGoalID, gameData.getInt(GameDesignInputReading.pointsGoalID));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObjecthere.put(GameDesignInputReading.endChoiceID, gameData.getString(GameDesignInputReading.endChoiceID));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ArrayList<String> pAN = gameData.getStringArrayList(GameDesignInputReading.playAreaNamesID);
                JSONArray playAreaNames = new JSONArray();
                for(int i = 0; i < pAN.size(); i++)
                {
                    playAreaNames.put(pAN.get(i));
                }
                try {
                    JSONObjecthere.put(GameDesignInputReading.playAreaNamesID, playAreaNames);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray playAreaVisibility = new JSONArray();
                ArrayList<String> pAV = gameData.getStringArrayList(GameDesignInputReading.playAreaVisibilityID);
                for(int i = 0; i < pAV.size(); i++)
                {
                    playAreaVisibility.put(pAV.get(i));
                }
                try {
                    JSONObjecthere.put(GameDesignInputReading.playAreaVisibilityID, playAreaVisibility);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObjecthere.put(GameDesignInputReading.toExhaustID, gameData.getString(GameDesignInputReading.toExhaustID));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray phases = new JSONArray();
                Bundle allPhases = gameData.getBundle(GameDesignInputReading.phasesID);
                int j = 0;
                while(allPhases.containsKey("phaseNumber" + j))
                {
                    Bundle thePhase = allPhases.getBundle("phaseNumber" + j);
                    JSONObject phaseNumber = new JSONObject();
                    try {
                        phaseNumber.put("phaseNumber", j);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        phaseNumber.put("first", thePhase.getString("first"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        phaseNumber.put("second", thePhase.getString("second"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        phaseNumber.put("sender", thePhase.getString("from"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        phaseNumber.put("receiver", thePhase.getString("to"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        phaseNumber.put("chosenBy", thePhase.getString("chosenBy"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        phaseNumber.put("pointGetter", thePhase.getString("pointGetter"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        phaseNumber.put("pointVal", thePhase.getString("pointVal"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        phaseNumber.put("comparators", thePhase.getString("comparators"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        phases.put(j, phaseNumber);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    j++;
                }
                try {
                    JSONObjecthere.put(GameDesignInputReading.phasesID, phases);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                RequestQueue mainReq = Volley.newRequestQueue(getApplicationContext());


                JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.POST, url, JSONObjecthere,
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
                        return params;
                    }
                };

                mainReq.add(jsonReq);

                Intent i = new Intent(GameDesignScreenMeta.this, GameDesignLeadInScreen.class);

                i.putExtra("name", getIntent().getStringExtra("name"));
                i.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
                i.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));
                startActivity(i);

                Toast.makeText(getApplicationContext(),"I'm Sorry. We didn't finish writing to the server in time.", Toast.LENGTH_LONG).show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenMeta.this, GameDesignCardAttributes.class);
                i= setData(i);
                startActivity(i);
            }
        });
    }

    /**
     * sets up the data that's always used for intents
     * @param i intent to add the data to
     * @return The given intent, with the data added
     */
    private Intent setData(Intent i)
    {
        gameData.putString(GameDesignInputReading.instructionsID, instructions.getText().toString());
        gameData.putString(GameDesignInputReading.gameNameID, gameName.getText().toString());
        gameData.putString(GameDesignInputReading.numPlayersID, numPlayers.getText().toString());
        gameData.putString(GameDesignInputReading.numDecksID, numDecks.getText().toString());
        i.putExtra(GameDesignInputReading.gameDataID, gameData);

        i.putExtra("username", getIntent().getStringExtra("username"));
        i.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
        i.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));
        return i;
    }
}
//Android volley - connects to
//JSON formatter using java class
//GSON
//developer.android.com
//newboston (android) youtube channel
//