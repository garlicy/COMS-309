package com.example.cardgames.Profile;

import android.content.Context;
import android.icu.text.IDNA;
import android.nfc.Tag;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cardgames.R;
import com.example.cardgames.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rradtke
 */
public class UserInfo {

    private ArrayList<String> friendsList;

    private ArrayList<Integer> friendsImageIDList;

    private String url, username, friendUsername, password, email;

    private int imageID;

    private RequestQueue requestQueue;

    private String Tag;

    private boolean InfoReceived, noFriends;


    public UserInfo(Context context, String username) {
        this.username = username;
        Tag = UserInfo.class.getSimpleName();
        InfoReceived = false;
        //friendsList.clear();
        //friendsImageIDList.clear();
        requestQueue = Volley.newRequestQueue(context);
    }

    /**
     * Gets the Image ID for the user logged into the app.
     *
     * @return An int for the Image ID for user
     */
    public int getImageID(){
        if(!InfoReceived){
            this.getUserInfo();
        }
        return imageID;
    }

    public String getPassword(){
        if(!InfoReceived){
            this.getUserInfo();
        }
        return password;
    }

    public String getEmail(){
        if(!InfoReceived){
            this.getUserInfo();
        }
        return email;
    }


    public ArrayList<String> getFriendsList(){
        getUserInfo();
        return friendsList;
    }

    public ArrayList<Integer> getFriendsImageIDList(){
        if(!InfoReceived) {
            getUserInfo();
        }
        return friendsImageIDList;
    }

    public boolean hasNoFriends(){
        return noFriends;
    }

    /**
     * Returns an arraylist of the user's friends
     *
     * @return An string arraylist of the user's friends
     */
    public void getFriendsInfo() {

        url = Const.URL_GET_FRIENDS;
        url = url + "?username=" + username;

        JsonObjectRequest r = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(Tag, response.toString());
                try {
                    int total = response.getInt("Total");
                    if(total > 0){
                        JSONArray ja = response.getJSONArray("array");
                        for(int i = 0; i < total; ++i){
                            noFriends = false;
                            JSONObject jo = ja.getJSONObject(i);
                            friendsList.add(jo.getString("username"));
                            int x = Integer.parseInt(jo.getString("usericon"));
                            friendsImageIDList.add(x);
                        }
                    }
                    else{
                        noFriends = true;
                        friendsList.add("Error");
                        friendsImageIDList.add(0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Tag, "Error: " + error.getMessage());
            }
        });
        InfoReceived = true;
        requestQueue.add(r);
    }


    /**
     * Adds "friendName" as a friend of the user in the database
     * @param friendName the name of the friend to be added
     */
    public void addFriend(String friendName){

        url = Const.URL_ADD_FRIEND;
        friendUsername = friendName;

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("friendName", friendUsername);

        JSONObject obj = new JSONObject(params);


        JsonObjectRequest req2 = new JsonObjectRequest(Request.Method.POST, url, obj,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(Tag, response.toString());
                        try {
                            JSONObject jsonObj = (JSONObject) response.get("args");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error: ", error.getMessage());
                VolleyLog.d(this.getClass().getSimpleName(), "Error: " + error.getMessage());
            }
        });
        requestQueue.add(req2);

    }

    /**
     * Removes "friendName" from the friends list of the user in the database
     * @param friendName the name of the friend to be removed
     */
    public void removeFriend(String friendName){
        url = Const.URL_REMOVE_FRIEND;
        friendUsername = friendName;

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("friendName", friendUsername);

        JSONObject obj = new JSONObject(params);

        JsonObjectRequest req3 = new JsonObjectRequest(Request.Method.POST, url, obj,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(Tag, response.toString());
                        try {
                            JSONObject jsonObj = (JSONObject) response.get("args");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error: ", error.getMessage());
                VolleyLog.d(this.getClass().getSimpleName(), "Error: " + error.getMessage());
            }
        });
        requestQueue.add(req3);
    }


    public void changePassword(String newPass){

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("newPassword", newPass);

        JSONObject obj = new JSONObject(params);

        JsonObjectRequest req4 = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(Tag, response.toString());
                try {
                    JSONObject jsonObj = (JSONObject) response.get("args");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
                @Override
                public void onErrorResponse (VolleyError error){
                Log.d("Error: ", error.getMessage());
                VolleyLog.d(this.getClass().getSimpleName(), "Error: " + error.getMessage());
            }
        });
        requestQueue.add(req4);
    }

    public void getUserInfo(){
        url = Const.URL_USER_INFO;

        Map<String, String> params = new HashMap<>();
        params.put("username", username);

        JSONObject jobj = new JSONObject(params);

        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.POST, url, jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(Tag, response.toString());
                try{
                    password = response.getString("password");
                    email = response.getString("email");
                    imageID = response.getInt("usericon");
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error: ", error.getMessage());
                VolleyLog.d(this.getClass().getSimpleName(), "Error: " + error.getMessage());
            }
        });
        InfoReceived = true;
        requestQueue.add(jr);
    }

}
