package com.example.cardgames.GameDiscovery;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cardgames.ScreenParent;
import com.example.cardgames.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.cardgames.net_utils.Const.URL_ECHO_POST_GET_DEFAULT_GAMES;

/**
 * @author Colton Hazlett
 * Lists all the games for the category choosen
 */
public class Category extends ScreenParent{

    private Spinner spin;
    private ImageView back, userimage;
    private TextView username, categoryName;
    private RequestQueue mRequestQueue;
    private String UserName;
    private int imageID;

    /**
     * Creates the screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        SetFrame();
        requestGameInformation();
    }

    /**
     * Makes an android request for the game information
     */
    public void requestGameInformation(){
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,
                URL_ECHO_POST_GET_DEFAULT_GAMES, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(this.getClass().getSimpleName(), response.toString()); //The drawable id for image is 2131165273
                        try {
                            JSONObject ja= (JSONObject)response.get("args");
                            for(int i = 0; i < ja.getInt("total"); i++)
                                CreateGame(ja.getInt("image"+i), ja.getString("name"+i), ja.getString("creator"+i));
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
     * Sets the frame for the screen
     */
    public void SetFrame(){
        mRequestQueue = Volley.newRequestQueue(this);
        username = findViewById(R.id.CAT_UserName);
        userimage = findViewById(R.id.CAT_UserImage);
        categoryName = findViewById(R.id.CAT_NAME);
        UserName = getIntent().getStringExtra("name");
        categoryName.setText(getIntent().getStringExtra("CategoryName"));
        if(categoryName.getText().length() > 14)
            categoryName.setTextSize(40);
        imageID = getIntent().getIntExtra("imageID", -1);
        username.setText(UserName);
        userimage.setImageDrawable(getDrawable(imageID));
        spin = findViewById(R.id.SPIN_CAT);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SET_default, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        back = findViewById(R.id.BACK_CAT);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spin.setOnItemSelectedListener(this);
    }

    /**
     * Creates a option
     * @param iconID the value for the games image
     * @param gameName the name of the game
     * @param gameCreator the creator of the game
     * @return returns the id of the frame
     */
    public int CreateGame(int iconID, String gameName, String gameCreator){
        final String GameName = gameName;
        final String GameCreator = gameCreator;
        final int IconID = iconID;
        LinearLayout lin = findViewById(R.id.CAT_linear);
        FrameLayout game = new FrameLayout(this);
        game.setId(View.generateViewId());
        ImageView img = new ImageView(this);
        int id = View.generateViewId();
        img.setId(View.generateViewId());
        img.setImageDrawable(getDrawable(IconID));
        img.setPadding(60,10,0,0);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Category.this, GameInformation.class);
                i.putExtra("name", UserName);
                i.putExtra("imageID", imageID);
                i.putExtra("gamename", GameName);
                i.putExtra("creator", GameCreator);
                i.putExtra("iconID", IconID);
                startActivity(i);
            }
        });
        TextView name = new TextView(this);
        name.setText(GameName);
        name.setTextSize(20);
        name.setTextColor(getResources().getColor(R.color.colorWhite));
        name.setPadding(440, 80, 0 ,0);
        TextView creator = new TextView(this);
        creator.setText("Creator: " + GameCreator);
        creator.setTextSize(20);
        creator.setTextColor(getResources().getColor(R.color.colorWhite));
        creator.setPadding(440, 180, 0 ,0);
        game.addView(img, 410, 410);
        game.addView(name);
        game.addView(creator);
        lin.addView(game);
        return id;
    }
}
