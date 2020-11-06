package com.example.cardgames.GameDesign;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgames.R;
import com.example.cardgames.ScreenParent;

import java.util.ArrayList;

public class GameDesignScreenPlayAreas extends ScreenParent {

    private String url;
    private Bundle gameData;

    private ArrayList<EditText> playAreaNames;
    private ArrayList<Spinner> playAreaVisibility;
    private Spinner spin, visibility;
    private TextView username, playAreaCounter, visibleTo;
    private EditText playAreaName;
    private ImageView userimage;
    private String UserName;
    private int ImageID = R.drawable.icon3;
    private Button addNewButton, done, back, destinations;

    private Integer textViewOneCounter;
    private LinearLayout mainLayout;

    private EditText xCoordinate;
    private EditText yCoordinate;
    private ArrayList<EditText> xCoor, yCoor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_design_screen_play_areas);

        playAreaNames = new ArrayList<>();
        playAreaVisibility = new ArrayList<>();

        url = "";

        playAreaCounter = findViewById(R.id.playAreaCounter);
        visibleTo = findViewById(R.id.visibleTo);

        mainLayout = findViewById(R.id.mainLayout);

        textViewOneCounter = 1;

        gameData = getIntent().getBundleExtra(GameDesignInputReading.gameDataID);
        if(gameData == null)
        {
            gameData = new Bundle();
        }

        userimage = findViewById(R.id.PLAYAREAS_UsersImage);
        username = findViewById(R.id.PLAYAREAS_UsersName);
        UserName = getIntent().getStringExtra("username");
        username.setText(UserName);
        ImageID = getIntent().getIntExtra("imageID", R.drawable.icon3);
        userimage.setImageDrawable(getDrawable(ImageID));


        spin = findViewById(R.id.SPINNER_PlayAreas);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SET_default, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        visibility = findViewById(R.id.visibilitySpinner);
        visibility.setAdapter(setUpSpinner());
        visibility.setBackgroundColor(getResources().getColor(android.R.color.white));

        playAreaName = findViewById(R.id.playArea1);
        playAreaName.setBackgroundColor(getResources().getColor(android.R.color.white));

        Button cards = findViewById(R.id.metaButton);
        Button playArea = findViewById(R.id.playAreasButton);
        Button winning = findViewById(R.id.winningButton);
        Button phases = findViewById(R.id.phasesButton);
        back = findViewById(R.id.backButton);
        done = findViewById(R.id.doneButton);
        addNewButton = findViewById(R.id.addNewButton);
        destinations = findViewById(R.id.destinationsButton);

        playAreaNames.add(playAreaName);
        playAreaVisibility.add(visibility);

        xCoordinate = findViewById(R.id.xCoor);
        xCoordinate.setBackgroundColor(getResources().getColor(android.R.color.white));
        yCoordinate = findViewById(R.id.yCoor);
        yCoordinate.setBackgroundColor(getResources().getColor(android.R.color.white));
        xCoor = new ArrayList<>();
        yCoor = new ArrayList<>();
        xCoor.add(xCoordinate);
        yCoor.add(yCoordinate);

        autofillValues();

        cards.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenPlayAreas.this, GameDesignScreenMeta.class);
                i = setData(i);
                startActivity(i);
            }
        });

        playArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"You're already here, ya fool.", Toast.LENGTH_SHORT).show();
            }
        });

        winning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenPlayAreas.this, GameDesignScreenWinning.class);
                i = setData(i);
                startActivity(i);
            }
        });

        phases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenPlayAreas.this, GameDesignScreenPhases.class);
                i = setData(i);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenPlayAreas.this, GameDesignLeadInScreen.class);

                i.putExtra("username", getIntent().getStringExtra("username"));
                i.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
                i.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));
                startActivity(i);
            }
        });

        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addingNew();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                RequestQueue mainReq = Volley.newRequestQueue(getApplicationContext());

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
                        params.put(radio, "" + testCheck.isChecked());
                        params.put(counter,"" + getIntent().getIntExtra(counter,0));
                        return params;
                    }
                };

                mainReq.add(jsonReq);

                Intent i = new Intent(GameDesignScreenPlayAreas.this, GameDesignLeadInScreen.class);
                startActivity(i);
                 */
                Toast.makeText(getApplicationContext(),"I'm Sorry. We didn't finish writing to the server in time.", Toast.LENGTH_LONG).show();
            }
        });

        destinations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenPlayAreas.this, GameDesignPlayAreaDestinations.class);
                i = setData(i);
                startActivity(i);
            }
        });
    }

    private void addingNew()
    {
        TextView header = new TextView(getApplicationContext());
        header.setTextColor(getResources().getColor(android.R.color.white));
        TextView instruction = new TextView(getApplicationContext());
        instruction.setTextColor(getResources().getColor(android.R.color.white));
        EditText name = new EditText(getApplicationContext());
        name.setBackgroundColor(getResources().getColor(android.R.color.white));
        Spinner visibilityChoice = new Spinner(getApplicationContext());
        visibilityChoice.setBackgroundColor(getResources().getColor(android.R.color.white));

        TextView xText = new TextView(getApplicationContext());
        xText.setText("x coordinate");
        xText.setTextColor(getResources().getColor(android.R.color.white));

        EditText xValue = new EditText(getApplicationContext());
        xValue.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        xValue.setBackgroundColor(getResources().getColor(android.R.color.white));
        xValue.setText("0");

        TextView yText = new TextView(getApplicationContext());
        yText.setText("y coordinate");
        yText.setTextColor(getResources().getColor(android.R.color.white));

        EditText yValue = new EditText(getApplicationContext());
        yValue.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        yValue.setBackgroundColor(getResources().getColor(android.R.color.white));
        yValue.setText("0");


        Integer index = mainLayout.indexOfChild(addNewButton);
        mainLayout.addView(header, index);
        mainLayout.addView(name, index + 1);
        mainLayout.addView(instruction, index + 2);
        mainLayout.addView(visibilityChoice, index + 3);
        mainLayout.addView(xText, index + 4);
        mainLayout.addView(xValue, index + 5);
        mainLayout.addView(yText, index + 6);
        mainLayout.addView(yValue, index + 7);

        textViewOneCounter++;
        header.setText("Play Area " + textViewOneCounter);


        instruction.setText("Visible to players:");

        visibilityChoice.setAdapter(setUpSpinner());

        playAreaNames.add(name);
        playAreaVisibility.add(visibilityChoice);
        xCoor.add(xValue);
        yCoor.add(yValue);
    }

    private ArrayAdapter<String> setUpSpinner()
    {
        String playerCount = "1";
        if (getIntent().getBundleExtra(GameDesignInputReading.gameDataID) != null && getIntent().getBundleExtra(GameDesignInputReading.gameDataID).getString(GameDesignInputReading.numPlayersID) != null) {
            playerCount = getIntent().getBundleExtra(GameDesignInputReading.gameDataID).getString(GameDesignInputReading.numPlayersID);
        }
        ArrayList<String> options = new ArrayList<>();
        options.add("None");
        options.add("All");
        Integer players = Integer.parseInt(playerCount);
        options = recursiveOptions(options,players,"");


        ArrayAdapter<String> retVal = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        retVal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return retVal;
    }

    private Bundle setUpBundle(Bundle b)
    {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> visible = new ArrayList<>();
        ArrayList<Integer> xAxis = new ArrayList<>();
        ArrayList<Integer> yAxis = new ArrayList<>();
        for(int j = 0; j < playAreaNames.size(); j++)
        {
            names.add(playAreaNames.get(j).getText().toString());
        }
        b.putStringArrayList(GameDesignInputReading.playAreaNamesID, names);
        for(int j = 0; j < playAreaVisibility.size(); j++)
        {
            visible.add(setUpSpinner().getItem(playAreaVisibility.get(j).getSelectedItemPosition()));
        }
        b.putStringArrayList(GameDesignInputReading.playAreaVisibilityID, visible);
        for(int j = 0; j < xCoor.size(); j++)
        {
            xAxis.add(Integer.parseInt(xCoor.get(j).getText().toString()));
        }
        b.putIntegerArrayList(GameDesignInputReading.xCoordinateID, xAxis);
        for(int j = 0; j < yCoor.size(); j++)
        {
            yAxis.add(Integer.parseInt(yCoor.get(j).getText().toString()));
        }
        b.putIntegerArrayList(GameDesignInputReading.yCoordinateID, yAxis);

        return b;
    }

    private void autofillValues()
    {
        ArrayList<String> playAreaName = null;
        ArrayList<String> visibileness = null;
        playAreaName = gameData.getStringArrayList(GameDesignInputReading.playAreaNamesID);
        visibileness = gameData.getStringArrayList(GameDesignInputReading.playAreaVisibilityID);
        ArrayList<Integer> xs = gameData.getIntegerArrayList(GameDesignInputReading.xCoordinateID);
        ArrayList<Integer> ys = gameData.getIntegerArrayList(GameDesignInputReading.yCoordinateID);
        if(playAreaName == null)
        {
            ArrayAdapter<String> theAdapter = (ArrayAdapter<String>)visibility.getAdapter();
            visibility.setSelection(theAdapter.getPosition("all")); //NOTE: I do not know how "get position" handles things not in the Adapter, nor how set selection handles index-out-of-bounds stuff.
        }
        else {

            for (int i = 1; i < playAreaName.size(); i++) {
                addingNew();
            }
            for (int i = 0; i < playAreaNames.size(); i++) {
                playAreaNames.get(i).setText(playAreaName.get(i));
            }
            for (int i = 0; i < playAreaVisibility.size(); i++) {
                ArrayAdapter<String> theAdapter = (ArrayAdapter<String>) playAreaVisibility.get(i).getAdapter();
                playAreaVisibility.get(i).setSelection(theAdapter.getPosition(visibileness.get(i)));
            }
            for(int i = 0; i < xCoor.size(); i++)
            {
                xCoor.get(i).setText(xs.get(i).toString());
            }
            for (int i = 0; i < yCoor.size(); i++)
            {
                yCoor.get(i).setText(ys.get(i).toString());
            }
        }
    }

    private Intent setData(Intent i)
    {
        i.putExtra(GameDesignInputReading.gameDataID, setUpBundle(gameData));

        i.putExtra("username", getIntent().getStringExtra("username"));
        i.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
        i.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));
        return i;
    }

    //I'm curious if there is anon recursive way of doing this that isn't insane.

    /**
     * Returns an arrayList of every possible combination of numbers less than or equal to num and greater than 0.
     * @param toAdd The base arrayList we're adding to. Isn't changed.
     * @param num The largest number we want as an option
     * @param base should be whatever you want before the numbers, glitches if its not the empty string and includes the base without the numbers in the list, however.
     * @return ArrayList that includes what was in toAdd, and also every possible combination of numbers less than or equal to num.
     */
    private ArrayList<String> recursiveOptions(ArrayList<String> toAdd, Integer num, String base)
    {
        ArrayList<String> adding = toAdd;
        if(num <= 0)
        {
            if(!base.equals("") && ! adding.contains(base)) {
                adding.add(base);
            }
            return adding;
        }
        else
        {
            adding = recursiveOptions(adding, num - 1, base);
            adding = recursiveOptions(adding, num - 1, base + num + " ");
            return adding;
        }
    }
}
