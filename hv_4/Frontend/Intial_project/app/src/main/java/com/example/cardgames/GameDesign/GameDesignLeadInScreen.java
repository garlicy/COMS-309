package com.example.cardgames.GameDesign;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Benjamin Goodall
 * @date 3/29/2020
 *
 * This screen gives the user the option of creating a new game or loading an already made game to edit.
 */
public class GameDesignLeadInScreen extends ScreenParent {

    /**
     * intent that will hold the premade game, if load is chosen.
     */
    private Intent progress;
    private Spinner spin;
    private TextView username;
    private ImageView userimage;
    private String UserName;
    private int ImageID = R.drawable.icon3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_design_lead_in_screen);

        Button newButton = findViewById(R.id.newButton);
        Button loadButton = findViewById(R.id.loadButton);

        String url = "";



        //Beginning of setting up the frame
        userimage = findViewById(R.id.LEADIN_UsersImage);
        username = findViewById(R.id.LEADIN_UsersName);
        UserName = getIntent().getStringExtra("username");
        username.setText(UserName);
        ImageID = getIntent().getIntExtra("imageID", R.drawable.icon3);
        userimage.setImageDrawable(getDrawable(ImageID));


        spin = findViewById(R.id.SPINNER_LeadIn);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SET_default, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);


        //End of Setting up the Frame

        progress = new Intent(GameDesignLeadInScreen.this, GameDesignScreenMeta.class);
        /*
        RequestQueue mainReq = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Lead In Screen", response.toString());


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Lead In Screen", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),"Oh gosh, a lot failed.",Toast.LENGTH_LONG).show();
            }
        });

        mainReq.add(jsonReq);

         */


        newButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Brings user to the Game Design Meta Screen with default values.
             * @param view
             */
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignLeadInScreen.this, GameDesignScreenMeta.class);
                defaultValues(i);
                i.putExtra("username", getIntent().getStringExtra("username"));
                i.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
                i.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));
                startActivity(i);
            }
        });

        loadButton.setOnClickListener((new View.OnClickListener() {
            /**
             * Brings user to the Game Design Meta Screen with values loaded from the server.
             * @param view
             */
            @Override
            public void onClick(View view) {

                progress.putExtra("username", getIntent().getStringExtra("username"));
                progress.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
                progress.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));

                startActivity(progress);
            }
        }));
    }

    /**
     * Loads the default values into a given Intent
     * @param i the intent the default values will be loaded into.
     */
    private void defaultValues(Intent i)
    {
        i.putExtra(GameDesignInputReading.gameDataID, defaultBundle());
    }

    /**
     *
     * @return a Bundle filled with the default values.
     */
    private static Bundle defaultBundle()
    {
        Bundle defaults = new Bundle();
        defaults.putString(GameDesignInputReading.instructionsID, "");
        defaults.putString(GameDesignInputReading.gameNameID, "");
        defaults.putString(GameDesignInputReading.numPlayersID, "1");
        defaults.putString(GameDesignInputReading.numDecksID, "1");
        defaults.putInt(GameDesignInputReading.totalRoundsID, 1);
        defaults.putInt(GameDesignInputReading.pointsGoalID, 1);
        defaults.putString(GameDesignInputReading.endChoiceID, "Points (Total)");
        ArrayList<String> a = new ArrayList<>();
        a.add("");
        ArrayList<String> b = new ArrayList<>();
        b.add("All");
        defaults.putStringArrayList(GameDesignInputReading.playAreaNamesID, a);
        defaults.putStringArrayList(GameDesignInputReading.playAreaVisibilityID, b);
        Bundle c = new Bundle();
        Bundle d = new Bundle();
        d.putString("first", "ALWAYS PERFORM");
        d.putString("second", "ALWAYS PERFORM");
        d.putString("from", "NO MOVEMENT");
        d.putString("to", "NO MOVEMENT");
        d.putString("chosenBy", "Choose top card");
        d.putString("pointGetter", "Nobody");
        d.putString("pointVal", "1");
        d.putString("comparators", "equal");
        c.putBundle("phaseNumber0", d);
        defaults.putBundle(GameDesignInputReading.phasesID,c);
        defaults.putString(GameDesignInputReading.toExhaustID, "none");
        ArrayList<Integer> coor = new ArrayList<>();
        coor.add(0);
        defaults.putIntegerArrayList(GameDesignInputReading.xCoordinateID, coor);
        defaults.putIntegerArrayList(GameDesignInputReading.yCoordinateID, coor);

        ArrayList<Integer> fiftyTwoOnes = new ArrayList<>();
        for(int i = 0; i < 52; i++)
        {
            fiftyTwoOnes.add(1);
        }

        defaults.putIntegerArrayList(GameDesignInputReading.cardPointListID,fiftyTwoOnes);
        return defaults;
    }
}
