package com.example.cardgames.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.example.cardgames.MainActivity;
import com.example.cardgames.R;
import com.example.cardgames.ScreenParent;
import com.example.cardgames.StartScreens.Lobby;

import java.util.ArrayList;


/**
 * @author rradtke
 */
public class profile extends ScreenParent {

    private ImageView back, userimage;

    private Button account;

    private String username;

    private ArrayList<String> friendsList;

    private ArrayList<Integer> friendsImageIDList;

    private Spinner spinner;

    private TextView user;

    private int imageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SetFrame();

    }

    /**
     * Sets the visual frame for the screen
     * Contains spinner, CGM logo, border and user's name and image
     */
    public void SetFrame() {
        friendsList = new ArrayList<String>();
        friendsImageIDList = new ArrayList<Integer>();
        user = findViewById(R.id.profile_username);
        username = getIntent().getStringExtra("name");
        user.setText(username);
        userimage = findViewById(R.id.profile_UserImage);
        imageID = getIntent().getIntExtra("imageID", -1);
        userimage.setImageDrawable(getResources().getDrawable(imageID));

        spinner = findViewById(R.id.SPIN_profile);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SET_default, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        back = findViewById(R.id.BACK_profile);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        account = findViewById(R.id.account_button);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(profile.this, EditProfile.class);
                i.putExtra("name", username);
                i.putExtra("imageID", imageID);
                startActivity(i);
            }
        });

        UserInfo u = new UserInfo(this, username);
        //friendsList = u.getFriendsList();
        //friendsImageIDList = u.getFriendsImageIDList();
        friendsList.add("Jake");
        friendsImageIDList.add(2131230863);
        boolean noFriends = false;//u.hasNoFriends();

        if(!noFriends){
            for(int x = 0; x < friendsList.size(); ++x){
                String friend = friendsList.get(x);
                int imgIDFriend = friendsImageIDList.get(x);
                createFriend(friend, imgIDFriend);
            }
        }


    }

    /**
     * Creates a FrameView for user's friend, adds view to the screen for user interaction
     * @param friendname name of user's friend
     * @param imgID image id of user's friend
     * @return id of the View
     */
    public int createFriend(final String friendname, final int imgID){

        LinearLayout linlay = findViewById(R.id.profile_linear);
        FrameLayout friend = new FrameLayout(this);
        friend.setId(View.generateViewId());

        ImageView image = new ImageView(this);
        image.setId(View.generateViewId());
        image.setImageDrawable(getDrawable(imgID));
        image.setPadding(30,0,0,0);
        TextView name = new TextView(this);
        name.setText(friendname);
        name.setTextSize(20);
        name.setTextColor(getResources().getColor(R.color.colorWhite));
        name.setPadding(120,0,0,0);
        friend.addView(image, 100, 100);
        friend.addView(name);
        linlay.addView(friend);
        int id = View.generateViewId();

        friend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(profile.this, FriendProfile.class);
                i.putExtra("name", friendname);
                i.putExtra("imageID", imgID);
                i.putExtra("friend", true);
                i.putExtra("superUser", username);
                startActivity(i);
                //enterActivity(FriendProfile.class, friendname, imgID, true, username);
            }
        });

        return id;
    }

}
