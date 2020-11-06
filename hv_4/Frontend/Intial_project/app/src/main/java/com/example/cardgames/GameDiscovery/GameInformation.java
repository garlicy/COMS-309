package com.example.cardgames.GameDiscovery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cardgames.Games.GameScreen;
import com.example.cardgames.R;
import com.example.cardgames.ScreenParent;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.cardgames.net_utils.Const.URL_ECHO_POST_GET_GOLF_INFO;
import static com.example.cardgames.net_utils.Const.URL_ECHO_POST_GET_WAR_INFO;

/**
 * @author Colton Hazlett
 * Shows all the games information, the overview, rules, scoring, game name and author, and any other information
 */
public class GameInformation extends ScreenParent {

    private ImageView back, userimage, GameImage;
    private Spinner spin;
    private TextView username, GameName, creator;
    private RequestQueue mRequestQueue;
    private String STR_username, STR_gamename, STR_creator;
    private String overview, rules, scoring, extraInfo;
    private int imageID;
    private boolean isVertical = true, isDefaultGame;

    /**
     * Creates the screen
     * @param savedInstanceState the last saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_information);
        SetFrame();
    }

    /**
     * Requests the the game information that is needed using android volley
     */
    public void requestGameInformation(){
        String url;
        if(STR_gamename.equals("War"))
            url = URL_ECHO_POST_GET_WAR_INFO;
        else
            url = URL_ECHO_POST_GET_GOLF_INFO;
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(this.getClass().getSimpleName(), response.toString()); //The drawable id for image is 2131165273
                        try {
                            JSONObject ja= (JSONObject)response.get("args");
                            overview = ja.getString("overview");
                            rules = ja.getString("rules");
                            scoring = ja.getString("scoring");
                            extraInfo = ja.getString("extrainfo");
                            isDefaultGame = ja.getBoolean("isDefault");
                            addParagraph("Overview:", overview);
                            addParagraph("Rules:", rules);
                            addParagraph("Scoring:", scoring);
                            addParagraph("Extra Info:", extraInfo);
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
     * Adds a paragraph to the screen
     * @param title title of the paragraph
     * @param body information of the paragraph
     */
    public void addParagraph(String title, String body){
        addText(title, 20, 80);
        addText(body, 15, 140);
    }

    /**
     * Sets the frame of the screen and the click listener
     */
    public void SetFrame(){
        mRequestQueue = Volley.newRequestQueue(this);
        username = findViewById(R.id.GI_UserName);
        userimage = findViewById(R.id.GI_UserImage);
        GameName = findViewById(R.id.GI_Game_Name);
        GameImage = findViewById(R.id.GI_ICON);
        creator = findViewById(R.id.GI_creator);

        STR_username = getIntent().getStringExtra("name");
        imageID = getIntent().getIntExtra("imageID", -1);
        STR_gamename = getIntent().getStringExtra("gamename");
        STR_creator = getIntent().getStringExtra("creator");
        GameImage.setImageDrawable(getDrawable(getIntent().getIntExtra("iconID", -1)));
        username.setText(STR_username);
        userimage.setImageDrawable(getDrawable(imageID));
        GameName.setText(STR_gamename);
        creator.setText("Creator: " + STR_creator);

        requestGameInformation();

        spin = findViewById(R.id.SPIN_gi);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SET_default, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
        back = findViewById(R.id.BACK_gi);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        GameImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameInformation.this, GameScreen.class);
                i.putExtra("name", STR_username);
                i.putExtra("imageID", imageID);
                i.putExtra("GameName", STR_gamename);
                i.putExtra("isDefault", isDefaultGame);
                i.putExtra("creator", STR_creator);
                i.putExtra("overview", overview);
                i.putExtra("rules", rules);
                i.putExtra("scoring", scoring);
                i.putExtra("extraInfo", extraInfo);
                startActivity(i);
            }
        });
    }

    /**
     * Adds text to the screen
     * @param txt the string to be added
     * @param txtSize the size of the text
     * @param tabLeft how far to the left the text should be
     */
    public void addText(String txt, int txtSize, int tabLeft) {
        LinearLayout lin = findViewById(R.id.GI_linear);
        FrameLayout Frame = new FrameLayout(this);
        TextView text = new TextView(this);
        text.setText(txt);
        text.setTextColor(getResources().getColor(R.color.colorWhite));
        text.setTextSize(txtSize);
        text.setPadding(tabLeft, 10, 0, 0);
        Frame.addView(text);
        lin.addView(Frame);
    }
}
