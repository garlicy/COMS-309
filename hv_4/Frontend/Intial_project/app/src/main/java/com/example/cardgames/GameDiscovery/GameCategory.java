package com.example.cardgames.GameDiscovery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.*;
import com.example.cardgames.R;
import com.example.cardgames.ScreenParent;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.cardgames.net_utils.Const.*;

/**
 * Lists all the categories available
 */

public class GameCategory extends ScreenParent implements View.OnClickListener{

    private ArrayList<Button> cats;
    private ArrayList<String> btnNames;
    private Spinner spin;
    private int TotalCategories = 5, UsersImageID;
    private ImageView back, userimage;
    private TextView username;
    private RequestQueue mRequestQueue;
    private String UsersName;

    /**
     * Creates the screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_category);
        SetFrame();
    }

    /**
     * A click listener to check what buttons have been pressed
     * @param view
     */
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.BACK_categories:
                finish();
                break;
            case R.id.Btn_PrevPlayed:
                enterActivity(Category.class, UsersName, UsersImageID, "Previously Played Games");
                break;
            case R.id.Btn_Trending:
                enterActivity(Category.class,UsersName, UsersImageID, "Trending Games");
                break;
            case R.id.Btn_Default:
                enterActivity(Category.class, UsersName, UsersImageID, "Default Games");
                break;
            case R.id.Btn_Cat1:
                enterActivity(Category.class, UsersName, UsersImageID, btnNames.get(0) + " Games");
                break;
            case R.id.Btn_Cat2:
                enterActivity(Category.class, UsersName, UsersImageID, btnNames.get(1) + " Games");
                break;
        }
    }

    /**
     * Requests all the category information
     */
    public void requestCategoryInformation(){
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,
                URL_ECHO_POST_GET_CAT, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(this.getClass().getSimpleName(), response.toString()); //The drawable id for image is 2131165273
                        try {
                            JSONObject ja= (JSONObject)response.get("args");
                            for(int i = 0; i < ja.getInt("total"); i++) {
                                btnNames.add(ja.getString("name" + (i + 1)));
                                cats.get(3 + i).setText(ja.getString("name" + (i + 1)) + "\nGames");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(this.getClass().getSimpleName(), "Error: " + error.getMessage());
            }
        });
        mRequestQueue.add(getRequest);
    }

    /**
     * Sets the frame of the screen
     */
    public void SetFrame(){
        mRequestQueue = Volley.newRequestQueue(this);
        username = findViewById(R.id.CATEGORIES_UserName);
        userimage = findViewById(R.id.CATEGORIES_UserImage);
        UsersImageID = getIntent().getIntExtra("imageID", -1);
        UsersName = getIntent().getStringExtra("name");
        username.setText(UsersName);
        userimage.setImageDrawable(getDrawable(UsersImageID));

        requestCategoryInformation();
        spin = findViewById(R.id.SPIN_gameCat);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SET_default, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
        setUpBTNs(TotalCategories);
        spin.setOnItemSelectedListener(this);

    }

    /**
     * Sets up all the buttons for each of the categories
     * @param numCats the number of categories
     */
    public void setUpBTNs(int numCats){
        cats = new ArrayList<>();
        btnNames = new ArrayList<>();
        back = findViewById(R.id.BACK_categories);
        cats.add((Button)findViewById(R.id.Btn_PrevPlayed));
        cats.add((Button)findViewById(R.id.Btn_Trending));
        cats.add((Button)findViewById(R.id.Btn_Default));
        for(int i = 2; i < numCats; i++) {
            switch (i) {
                case 3:
                    cats.add((Button) findViewById(R.id.Btn_Cat1));
                    cats.get(3).setVisibility(View.VISIBLE);
                    break;
                case 4:
                    cats.add((Button) findViewById(R.id.Btn_Cat2));
                    cats.get(4).setVisibility(View.VISIBLE);
                    break;
            }
        }
        for(int i = 0; i < numCats; i++){
            cats.get(i).setOnClickListener(this);
        }
        back.setOnClickListener(this);
    }
}
