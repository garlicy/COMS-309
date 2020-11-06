package com.example.cardgames.GameDesign;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgames.R;
import com.example.cardgames.ScreenParent;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * @author Benjamin Goodall
 * @Date 3/29/2020
 *
 * Receives user input to set what will be done at each phase of the game.
 */
public class GameDesignScreenPhases extends ScreenParent {


    /**
     * URL used to contact the server
     */
    private String url;
    /**
     * Stores data about the game, new and old data.
     */
    private Bundle gameData;
    /**
     * holds height data for adding new objects
     */
    private Integer height;
    private Spinner spin;
    /**
     * input for for conditional deck selection for the first phase.
     */
    private Spinner first;
    /**
     * input for second conditional deck selection for the first phase.
     */
    private Spinner second;
    /**
     * input for selection of which deck to move cards from for the first phase.
     */
    private Spinner from;
    /**
     * input for selection of which deck to move cards to for the first phase.
     */
    private Spinner to;
    /**
     * input for which player will choose the card to move in the first phase.
     */
    private Spinner chosenBy;
    /**
     * input for which player will receive points in the first phase.
     */
    private Spinner pointGetter;
    private TextView username;
    /**
     * input for how many points will be given on the first phase.
     */
    private EditText pointVal;
    /**
     * input for how to compare the two conditional decks in the first phase.
     */
    private RadioGroup comparators;
    private ImageView userimage;
    private String UserName;
    private Button back, done, addNew;
    private int ImageID = R.drawable.icon3;
    /**
     * A list of each phase's list of spinners used to make selections in order
     */
    ArrayList<ArrayList<Spinner>> spinnerHold;
    /**
     * a list of each phase's number of points to give in order.
     */
    ArrayList<EditText> pointArray;
    /**
     * a list of each phase's RadioGroup that acts like "comparators"
     */
    ArrayList<RadioGroup> compares;
    /**
     * holds each array list that holds each phases info for storage.
     */
    private Bundle everything;

    /**
     * keeps track of the number of phases.
     */
    private Integer phaseNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_design_screen_phases);

        variableSetup();


        Button cards = findViewById(R.id.metaButton);
        Button playArea = findViewById(R.id.playAreasButton);
        Button winning = findViewById(R.id.winningButton);
        Button phases = findViewById(R.id.phasesButton);
        back = findViewById(R.id.backButton);
        done = findViewById(R.id.doneButton);
        addNew = findViewById(R.id.addNewPhase);

        if(gameData.getBundle("phases") != null)
        {
            initialValues();
        }


        cards.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenPhases.this, GameDesignScreenMeta.class);
                i = setData(i);
                startActivity(i);
            }
        });

        playArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenPhases.this, GameDesignScreenPlayAreas.class);
                i = setData(i);
                startActivity(i);
            }
        });

        winning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenPhases.this, GameDesignScreenWinning.class);
                i = setData(i);
                startActivity(i);
            }
        });

        phases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"You're already here, ya fool.", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameDesignScreenPhases.this, GameDesignLeadInScreen.class);

                i.putExtra("username", getIntent().getStringExtra("username"));
                i.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
                i.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));
                startActivity(i);
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
                        params.put(radio, "" + getIntent().getIntExtra(radio,-1));
                        params.put(counter,"" +getCount(count));
                        return params;
                    }
                };

                mainReq.add(jsonReq);

                Intent i = new Intent(GameDesignScreenPhases.this, GameDesignLeadInScreen.class);

                i.putExtra("name", getIntent().getStringExtra("name"));
                i.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
                i.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));
                startActivity(i);

                 */
                Toast.makeText(getApplicationContext(),"I'm Sorry. We didn't finish writing to the server in time.", Toast.LENGTH_LONG).show();
            }
        });


        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNew();
            }
        });
    }

    /**
     * sets up each newly made spinner to keep track of them and fill them with the correct options.
     * Used for each phase.
     * @param one Spinner that will act like "first"
     * @param two Spinner that will act like "second"
     * @param three Spinner that will act like "from"
     * @param four Spinner that will act like "to"
     * @param five Spinner that will act like "choseBy"
     * @param six Spinner that will act like "pointGetter"
     */
    private void setUpSpinners(Spinner one, Spinner two, Spinner three, Spinner four, Spinner five, Spinner six)
    {
        //Setting up the basic information all spinners will pull from
        ArrayList<String> playAreas = getIntent().getBundleExtra(GameDesignInputReading.gameDataID).getStringArrayList(GameDesignInputReading.playAreaNamesID);
        ArrayList<String> players = new ArrayList<>();
        String numPlayers = getIntent().getBundleExtra(GameDesignInputReading.gameDataID).getString(GameDesignInputReading.numPlayersID);
        for(int i = 1; i <= Integer.parseInt(numPlayers.substring(0,1)); i++)
        {
            players.add("Player " + i);
        }
        //Setting up the first spinner, acts like "first"
        ArrayList<String> oneArray = new ArrayList<>(playAreas);
        oneArray.add("ALWAYS PERFORM");
        ArrayAdapter<String> oneAdapt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, oneArray);
        oneAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        one.setAdapter(oneAdapt);
        one.setSelection(oneAdapt.getPosition("ALWAYS PERFORM"));
        //Setting up the second spinner, acts like "second" uses same list as the first spinner
        ArrayAdapter<String> twoAdapt = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,oneArray);
        twoAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        two.setAdapter(twoAdapt);
        two.setSelection(oneAdapt.getPosition("ALWAYS PERFORM"));
        //setting up the third spinner, acts like "from"
        ArrayList<String> threeArray = new ArrayList<>(playAreas);
        threeArray.add("NO MOVEMENT");
        ArrayAdapter<String> threeAdapt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, threeArray);
        threeAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        three.setAdapter(threeAdapt);
        three.setSelection(threeAdapt.getPosition("NO MOVEMENT"));
        //setting up the fourth spinner, acts like "to" uses same list as the third spinner
        ArrayAdapter<String> fourAdapt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, threeArray);
        fourAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        four.setAdapter(fourAdapt);
        four.setSelection(threeAdapt.getPosition("NO MOVEMENT"));
        //setting up the fifth spinner, acts like "chosenBy"
        ArrayList<String> fiveArray = new ArrayList<>(players);
        fiveArray.add("Choose top card");
        ArrayAdapter<String> fiveAdapt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fiveArray);
        fiveAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        five.setAdapter(fiveAdapt);
        five.setSelection(fiveAdapt.getPosition("Choose top card"));
        // setting up the sixth spinner, acts like "pointGetter"
        ArrayList<String> sixArray = new ArrayList<>(players);
        sixArray.add("Nobody");
        ArrayAdapter<String> sixAdapt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sixArray);
        sixAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        six.setAdapter(sixAdapt);
        six.setSelection(sixAdapt.getPosition("Nobody"));

        ArrayList<Spinner> spinnerData = new ArrayList<>();
        spinnerData.add(one);
        spinnerData.add(two);
        spinnerData.add(three);
        spinnerData.add(four);
        spinnerData.add(five);
        spinnerData.add(six);
        spinnerHold.add(spinnerData);
    }

    /**
     * Sets up the base variables when the screen is first loaded.
     */
    private void variableSetup()
    {
        url = "";

        gameData = getIntent().getBundleExtra(GameDesignInputReading.gameDataID);
        if(gameData == null)
        {
            gameData = new Bundle();
        }

        everything = new Bundle();



        userimage = findViewById(R.id.PHASES_UsersImage);
        username = findViewById(R.id.PHASES_UsersName);
        UserName = getIntent().getStringExtra("username");
        username.setText(UserName);
        ImageID = getIntent().getIntExtra("imageID", R.drawable.icon3);
        userimage.setImageDrawable(getDrawable(ImageID));

        spin = findViewById(R.id.SPINNER_Phases);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SET_default, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        first = findViewById(R.id.first);
        first.setBackgroundColor(getResources().getColor(android.R.color.white));
        second = findViewById(R.id.second);
        second.setBackgroundColor(getResources().getColor(android.R.color.white));
        from = findViewById(R.id.from);
        from.setBackgroundColor(getResources().getColor(android.R.color.white));
        to = findViewById(R.id.to);
        to.setBackgroundColor(getResources().getColor(android.R.color.white));
        chosenBy = findViewById(R.id.chosenBy);
        chosenBy.setBackgroundColor(getResources().getColor(android.R.color.white));
        pointGetter = findViewById(R.id.pointGetter);
        pointGetter.setBackgroundColor(getResources().getColor(android.R.color.white));
        pointVal = findViewById(R.id.pointVal);
        comparators = findViewById(R.id.comparators);

        pointVal.setBackgroundColor(getResources().getColor(android.R.color.white));

        spinnerHold = new ArrayList<>();
        pointArray = new ArrayList<>();
        compares = new ArrayList<>();

        setUpSpinners(first,second,from,to,chosenBy,pointGetter);

        pointArray.add(pointVal);
        compares.add(comparators);
    }

    /**
     * Reads what is input and stores it in "everything".
     */
    private void readDataHold()
    {
        for(int i = 0; i < spinnerHold.size(); i++)
        {
            ArrayList<Spinner> spinny = spinnerHold.get(i);
            Bundle dataPoint = new Bundle();
            dataPoint.putString("first", spinny.get(0).getAdapter().getItem(spinny.get(0).getSelectedItemPosition()).toString());
            dataPoint.putString("second", spinny.get(1).getAdapter().getItem(spinny.get(1).getSelectedItemPosition()).toString());
            dataPoint.putString("from", spinny.get(2).getAdapter().getItem(spinny.get(2).getSelectedItemPosition()).toString());
            dataPoint.putString("to", spinny.get(3).getAdapter().getItem(spinny.get(3).getSelectedItemPosition()).toString());
            dataPoint.putString("chosenBy", spinny.get(4).getAdapter().getItem(spinny.get(4).getSelectedItemPosition()).toString());
            dataPoint.putString("pointGetter", spinny.get(5).getAdapter().getItem(spinny.get(5).getSelectedItemPosition()).toString());
            dataPoint.putString("pointVal", pointArray.get(i).getText().toString());
            RadioButton checked = findViewById(compares.get(i).getCheckedRadioButtonId());
            dataPoint.putString("comparators", checked.getText().toString());
            everything.putBundle("phaseNumber" + i, dataPoint);
        }
    }

    /**
     * creates a new set of spinners to receive the inputs, dynamically. Automatically adjusts to add them in the right spot.
     */
    private void createNew()
    {
        phaseNumber++;
        Spinner a = new Spinner(getApplicationContext());
        Spinner b = new Spinner(getApplicationContext());
        Spinner c = new Spinner(getApplicationContext());
        Spinner d = new Spinner(getApplicationContext());
        Spinner e = new Spinner(getApplicationContext());
        Spinner f = new Spinner(getApplicationContext());
        setUpSpinners(a,b,c,d,e,f);
        a.setBackgroundColor(getResources().getColor(android.R.color.white));
        b.setBackgroundColor(getResources().getColor(android.R.color.white));
        c.setBackgroundColor(getResources().getColor(android.R.color.white));
        d.setBackgroundColor(getResources().getColor(android.R.color.white));
        e.setBackgroundColor(getResources().getColor(android.R.color.white));
        f.setBackgroundColor(getResources().getColor(android.R.color.white));


        EditText g = new EditText(getApplicationContext());
        g.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        g.setBackgroundColor(getResources().getColor(android.R.color.white));

        RadioGroup h = new RadioGroup(getApplicationContext());
        RadioButton i = new RadioButton(getApplicationContext());
        i.setText("less than");
        i.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        i.setTextColor(getResources().getColor(android.R.color.white));
        RadioButton j = new RadioButton(getApplicationContext());
        j.setText("less or equal");
        j.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        j.setTextColor(getResources().getColor(android.R.color.white));
        RadioButton k = new RadioButton(getApplicationContext());
        k.setText("equal");
        k.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        k.setTextColor(getResources().getColor(android.R.color.white));
        RadioButton l = new RadioButton(getApplicationContext());
        l.setText("greater or equal");
        l.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        l.setTextColor(getResources().getColor(android.R.color.white));
        RadioButton m = new RadioButton(getApplicationContext());
        m.setText("greater");
        m.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        m.setTextColor(getResources().getColor(android.R.color.white));
        RadioButton n = new RadioButton(getApplicationContext());
        n.setText("Not equal");
        n.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        n.setTextColor(getResources().getColor(android.R.color.white));
        h.addView(i,0);
        h.addView(j,1);
        h.addView(k,2);
        h.addView(l,3);
        h.addView(m,4);
        h.addView(n,5);
        h.setOrientation(RadioGroup.HORIZONTAL);
        h.check(k.getId());

        TextView one = new TextView(getApplicationContext());
        one.setText("Phase " + phaseNumber);
        one.setTextColor(getResources().getColor(android.R.color.white));

        TextView two = new TextView(getApplicationContext());
        two.setText("If the top card of");
        two.setTextColor(getResources().getColor(android.R.color.white));

        TextView three = new TextView(getApplicationContext());
        three.setText("is");
        three.setTextColor(getResources().getColor(android.R.color.white));

        TextView four = new TextView(getApplicationContext());
        four.setText("the top card of");
        four.setTextColor(getResources().getColor(android.R.color.white));

        TextView five = new TextView(getApplicationContext());
        five.setText("then move a card from");
        five.setTextColor(getResources().getColor(android.R.color.white));

        TextView six = new TextView(getApplicationContext());
        six.setText("to");
        six.setTextColor(getResources().getColor(android.R.color.white));

        TextView seven = new TextView(getApplicationContext());
        seven.setText("chosen by");
        seven.setTextColor(getResources().getColor(android.R.color.white));

        TextView eight = new TextView(getApplicationContext());
        eight.setText("give points");
        eight.setTextColor(getResources().getColor(android.R.color.white));

        TextView nine = new TextView(getApplicationContext());
        nine.setText("to");
        nine.setTextColor(getResources().getColor(android.R.color.white));

        LinearLayout phaseView = findViewById(R.id.phaseView);
        Integer index = phaseView.indexOfChild(addNew);

        phaseView.addView(one,index);
        phaseView.addView(two,index + 1);
        phaseView.addView(a,index+2);
        phaseView.addView(three, index + 3);
        phaseView.addView(h,index + 4);
        phaseView.addView(four, index + 5);
        phaseView.addView(b,index + 6);
        phaseView.addView(five,index +7);
        phaseView.addView(c,index + 8);
        phaseView.addView(six,index + 9);
        phaseView.addView(d,index + 10);
        phaseView.addView(seven, index + 11);
        phaseView.addView(e,index + 12);
        phaseView.addView(eight, index + 13);
        phaseView.addView(g,index + 14);
        phaseView.addView(nine, index + 15);
        phaseView.addView(f,index + 16);



        pointArray.add(g);
        compares.add(h);
    }

    private void initialValues()
    {
        Bundle phases = gameData.getBundle(GameDesignInputReading.phasesID);
        phaseNumber = 1;
        int i = 0;
        while(phases.getBundle("phaseNumber" + i) != null)
        {
            Bundle dataPoint = phases.getBundle("phaseNumber" + i);
            ArrayList<Spinner> spinnersToSet = spinnerHold.get(i);
            ArrayAdapter<String> one = (ArrayAdapter) spinnersToSet.get(0).getAdapter();
            spinnersToSet.get(0).setSelection(one.getPosition(dataPoint.getString("first")));
            ArrayAdapter<String> two = (ArrayAdapter) spinnersToSet.get(1).getAdapter();
            spinnersToSet.get(1).setSelection(two.getPosition(dataPoint.getString("second")));
            ArrayAdapter<String> three = (ArrayAdapter) spinnersToSet.get(2).getAdapter();
            spinnersToSet.get(2).setSelection(three.getPosition(dataPoint.getString("from")));
            ArrayAdapter<String> four = (ArrayAdapter) spinnersToSet.get(3).getAdapter();
            spinnersToSet.get(3).setSelection(four.getPosition(dataPoint.getString("to")));
            ArrayAdapter<String> five = (ArrayAdapter) spinnersToSet.get(4).getAdapter();
            spinnersToSet.get(4).setSelection(five.getPosition(dataPoint.getString("chosenBy")));
            ArrayAdapter<String> six = (ArrayAdapter) spinnersToSet.get(5).getAdapter();
            spinnersToSet.get(5).setSelection(six.getPosition(dataPoint.getString("pointGetter")));
            pointArray.get(i).setText(dataPoint.getString("pointVal"));
            RadioButton toCheck = GameDesignInputReading.getRadioToCheck(dataPoint.getString("comparators"), compares.get(i));
            if(toCheck != null)
            {
                toCheck.setChecked(true);
            }

            i++;
            if(phases.getBundle("phaseNumber" + i) != null)
            {
                createNew();
            }
        }
    }

    /**
     * Sets up the intent, saving the data input on this screen.
     * @param i intent to add the data to.
     * @return the given intent, with the data added.
     */
    private Intent setData(Intent i)
    {
        readDataHold();
        gameData.putBundle("phases", everything);
        i.putExtra(GameDesignInputReading.gameDataID,gameData);


        i.putExtra("username", getIntent().getStringExtra("username"));
        i.putExtra("imageID", getIntent().getIntExtra("imageID",ImageID));
        i.putExtra("CategoryName", getIntent().getStringExtra("CategoryName"));
        return i;
    }
}
