package com.example.cardgames.StartScreens;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cardgames.*;
import com.example.cardgames.GameDiscovery.GameCategory;
import com.example.cardgames.GameDesign.GameDesignLeadInScreen;
import com.example.cardgames.Profile.FriendProfile;
import com.example.cardgames.Profile.UserInfo;
import com.example.cardgames.Profile.profile;
import com.example.cardgames.Settings.AppHelp;
import com.example.cardgames.Settings.AppSettings;
import com.example.cardgames.net_utils.Const;
import com.example.cardgames.net_utils.Search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Colton Hazlett
 * This is the main lobby screen to choose from the to paths of game design or game play
 */

public class Lobby extends ScreenParent implements AdapterView.OnItemSelectedListener{

    private Button play, design;
    private Spinner spin;
    private TextView username, uV, gV;
    private ImageView userimage, searchBTN;
    private String UserName;
    private int ImageID = getRandomIcon();
    private SearchView searchBar;
    private ListView usersList, gamesList;
    private boolean search;
    private Search srch;

    /**
     * Creates the Screen and checks if the buttons have been clicked for the paths
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        srch = new Search();
        search = false;
        searchBTN = findViewById(R.id.search_button);
        searchBar = findViewById(R.id.search_bar);
        usersList = findViewById(R.id.users_list);
        gamesList = findViewById(R.id.games_list);
        uV = new TextView(this);
        uV.setText("Users");
        usersList.addHeaderView(uV);
        gV = new TextView(this);
        gV.setText("Games");
        gamesList.addHeaderView(gV);
        userimage = findViewById(R.id.LOBBY_UsersImg);
        username = findViewById(R.id.LOBBY_UsersName);
        UserName = getIntent().getStringExtra("name");
        if(UserName == null)
            UserName = "User" + (int)(Math.random() * 1000000);
        ImageID = getIntent().getIntExtra("imageID", ImageID);
        username.setText(UserName);
        userimage.setImageDrawable(getDrawable(ImageID));

        play = findViewById(R.id.BTN_play);
        design = findViewById(R.id.BTN_design);
        spin = findViewById(R.id.SPIN_Lobby);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SET_default, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterActivity(GameCategory.class, UserName, ImageID);
            }
        });
        design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterActivity(GameDesignLeadInScreen.class, UserName, ImageID);
            }
        });
        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!search) {
                    search = true;
                    searchBar.setVisibility(View.VISIBLE);
                    //Toast.makeText(Lobby.this, Integer.toString(ImageID), Toast.LENGTH_LONG).show();
                } else {
                    search = false;
                    searchBar.setVisibility(View.GONE);
                    gamesList.setVisibility(View.GONE);
                    usersList.setVisibility(View.GONE);
                }
            }
        });



        searchBar.setSubmitButtonEnabled(true);

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String s) {
                String noRes = "No Results Found";
                ArrayList<String> users = srch.searchUsers(s);
                ArrayList<String> games = srch.searchGames(s);
                boolean resultsU = true;
                boolean resultsG = true;
                if(users.get(0).equals(noRes)) {
                    resultsU = false;
                }
                if(games.get(0).equals(noRes)){
                    resultsG = false;
                }
                setListViews(users, games, resultsU, resultsG);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    public int getRandomIcon(){
        int r = (int)((Math.random() * 10) % 9);
        switch(r){
            case 0:
                return R.drawable.icon1;
            case 1:
                return R.drawable.icon2;
            case 2:
                return R.drawable.icon3;
            case 3:
                return R.drawable.icon4;
            case 5:
                return R.drawable.icon6;
            case 6:
                return R.drawable.icon7;
            case 7:
                return R.drawable.icon8;
            case 8:
                return R.drawable.icon9;
            default:
                return R.drawable.icon5;
        }
    }

    /**
     * Checks if the a settings button has been pressed and then chooses where to go
     */

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
        if(adapterView.getItemAtPosition(index).equals("Profile")){
            enterActivity(profile.class, UserName, ImageID, null);
        }
        else if(adapterView.getItemAtPosition(index).equals("Settings")){
            Intent i = new Intent(this, AppSettings.class);
            startActivity(i);
        }
        else if(adapterView.getItemAtPosition(index).equals("Help")){
            Intent i = new Intent(this, AppHelp.class);
            startActivity(i);
        }
        else if(adapterView.getItemAtPosition(index).equals("Sign Out")){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        else{

        }
    }


    public void setListViews(ArrayList<String> u, ArrayList<String> g, boolean resultsU, boolean resultsG){
        String[] users = new String[u.size()];
        for(int i = 0; i < u.size(); ++i){
            users[i] = u.get(i);
        }
        String[] games = new String[g.size()];
        for(int n = 0; n < g.size(); ++n){
            games[n] = g.get(n);
        }

        final ArrayAdapter<String> arrUsers = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, users);
        final ArrayAdapter<String> arrGames = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, games);

        usersList.setAdapter(arrUsers);
        gamesList.setAdapter(arrGames);

        if(resultsU){
            usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String selected = arrUsers.getItem(i);
                    UserInfo user = new UserInfo(Lobby.this, selected);
                    UserInfo temp = new UserInfo(Lobby.this, UserName);
                    ArrayList<String> list = temp.getFriendsList();
                    boolean friend = false;
                    if(list.contains(selected)){
                        friend = true;
                    }
                    enterActivity(FriendProfile.class, selected, user.getImageID(), friend, UserName);
                }
            });
        }

        if(resultsG){
            gamesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String selected = arrGames.getItem(i);
                    Toast.makeText(Lobby.this, selected, Toast.LENGTH_SHORT).show();
                }
            });
        }

        usersList.setVisibility(View.VISIBLE);
        gamesList.setVisibility(View.VISIBLE);



    }

}
