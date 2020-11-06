package com.example.cardgames.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.cardgames.R;
import com.example.cardgames.ScreenParent;

import java.util.ArrayList;

/**
 * @author rradtke
 */
public class FriendProfile extends ScreenParent {

    private ImageView back, userimage;

    private String username, superUser;

    private Button add;

    private ArrayList<String> friendsList;

    private ArrayList<Integer> friendsImageIdList;

    private Spinner spinner;

    private TextView user;

    private int imageID;

    private boolean friend;

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
    }

    /**
     * Sets the visual frame for the screen
     * Contains spinner, CGM logo, border and user's name and image
     */
    public void SetFrame() {
        friendsList = new ArrayList<String>();
        friendsImageIdList = new ArrayList<Integer>();
        friend = getIntent().getBooleanExtra("friend", false);
        superUser = getIntent().getStringExtra("superUser");
        setFriendButton();

        user = findViewById(R.id.friend_profile_username);
        username = getIntent().getStringExtra("name");
        user.setText(username);
        userimage = findViewById(R.id.friend_profile_UserImage);
        imageID = getIntent().getIntExtra("imageID", -1);
        userimage.setImageDrawable(getDrawable(imageID));

        spinner = findViewById(R.id.SPIN_friend_profile);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SET_default, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        back = findViewById(R.id.BACK_friend_profile);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        add = findViewById(R.id.add_friend_button);
        setFriendButton();
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(friend){
                    friend = false;
                    UserInfo u = new UserInfo(context, superUser);
                    u.removeFriend(username);
                }
                else if(!friend){
                    friend = true;
                    UserInfo u = new UserInfo(context, superUser);
                    u.addFriend(username);
                }
                setFriendButton();
            }
        });

        UserInfo u = new UserInfo(context, username);
        //friendsList = u.getFriendsList();
        //friendsImageIdList = u.getFriendsImageIDList();
        friendsList.add("Alex");
        friendsImageIdList.add(2131230863);

        for(int x = 0; x < friendsList.size() - 1; ++x){
            String friend = friendsList.get(x);
            int id = friendsImageIdList.get(x);
            createFriend(friend, id);
        }

    }

    /**
     * Creates a FrameView for user's friend, adds view to the screen for user interaction
     * @param friendname name of user's friend
     * @param imgID image id of user's friend
     * @return id of the View
     */
    public int createFriend(final String friendname, final int imgID){

        LinearLayout linlay = findViewById(R.id.friend_profile_linear);
        FrameLayout friend = new FrameLayout(this);
        friend.setId(View.generateViewId());

        ImageView image = new ImageView(this);
        image.setId(View.generateViewId());
        image.setImageDrawable(getDrawable(imgID));
        image.setPadding(30,0,0,0);
        TextView name = new TextView(this);
        name.setText(friendname);
        name.setTextSize(30);
        name.setTextColor(getResources().getColor(R.color.colorWhite));
        name.setPadding(90,0,0,0);
        friend.addView(image, 200, 200);
        friend.addView(name);
        linlay.addView(friend);
        int id = View.generateViewId();

        return id;
    }

    /**
     * Sets the friend button on the friend;s profile screen to represent friendship status
     */
    public void setFriendButton(){
        if(friend){
            add.setText("Friends");
            add.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        }
        else if(!friend){
            add.setText("Add Friend+");
            add.setBackgroundColor(getResources().getColor(R.color.colorDarkBlue));
        }
    }
}
