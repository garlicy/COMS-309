package com.example.cardgames;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TabWidget;

import com.example.cardgames.Profile.profile;
import com.example.cardgames.Settings.AppHelp;
import com.example.cardgames.Settings.AppSettings;


import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Colton Hazlett
 *  Class used to as a parent for all other classes
 */
public class ScreenParent extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    public ScreenParent(){}

    /**
     * Used for every screen to go to profile, settings, help, or log out
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
        if(adapterView.getItemAtPosition(index).equals("Profile")){
            Intent i = new Intent(this, profile.class);
            startActivity(i);
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

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * Enters the user into the "activity" activity/screen, adds extras to the activity from the parameters
     * Used primarily to view a friend's profile screen
     * @param activity activity to be entered
     * @param Username username of friend's username
     * @param id image id of the friend or user
     * @param friend true if friend and superuser are friends in database
     * @param superUser username of user logged into app
     */
    public void enterActivity(java.lang.Class activity, String Username, int id, boolean friend, String superUser){
        Intent i = new Intent(this, activity);
        i.putExtra("name", Username);
        i.putExtra("imageID", id);
        i.putExtra("friend", friend);
        i.putExtra("superUser", superUser);
        startActivity(i);
    }

    /**
     * Enters the user into the "activity" activity/screen, adds extras to the activity from parameters
     * @param activity activity to be entered
     * @param Username username of user
     * @param id image id of the user
     */
    public void enterActivity(java.lang.Class activity, String Username, int id){
        Intent i = new Intent(this, activity);
        i.putExtra("name", Username);
        i.putExtra("imageID", id);
        startActivity(i);
    }

    /**
     * Enters the user into the "activity" activity/screen, adds extras to the activity from parameters
     * Used primarily for game categories
     * @param activity activity to be entered
     * @param Username username of user
     * @param id image id of user
     * @param CategoryName name of game category selected by user
     */
    public void enterActivity(java.lang.Class activity, String Username, int id, String CategoryName){
        Intent i = new Intent(this, activity);
        i.putExtra("name", Username);
        i.putExtra("imageID", id);
        i.putExtra("CategoryName", CategoryName);
        startActivity(i);
    }

    /**
     * Enters the user into the "activity" activity/screen, adds extras to the activity from parameters
     * Used primarily game screens
     * @param activity activity to be entered
     * @param Username username of user
     * @param id image id of user
     * @param CategoryName name of category selected by user
     * @param GameName name of game selected by user
     * @param isDefault true if the game is non-user created
     */
    public void enterActivity(java.lang.Class activity, String Username, int id, String CategoryName, String GameName, boolean isDefault){
        Intent i = new Intent(this, activity);
        i.putExtra("name", Username);
        i.putExtra("imageID", id);
        i.putExtra("CategoryName", CategoryName);
        i.putExtra("GameName", GameName);
        i.putExtra("isDefault", isDefault);
        startActivity(i);
    }
}
