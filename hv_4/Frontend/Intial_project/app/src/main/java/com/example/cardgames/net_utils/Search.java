package com.example.cardgames.net_utils;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cardgames.StartScreens.Lobby;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class Search {

    public Search(){

    }

    public ArrayList<String> search(String query){
        ArrayList<String> results = searchUsers(query);
        ArrayList<String> games = searchGames(query);
        for(int i = 0; i < games.size(); ++i){
            results.add(games.get(i));
        }
        Collections.sort(results);
        return results;
    }

    public ArrayList<String> searchUsers(String query){
        final String Tag = Lobby.class.getSimpleName();
        String url = Const.URL_SEARCH_USERS;
        url = url + "?search=" + query;
        final ArrayList<String> aList = new ArrayList<String>();
        //aList.add("jake");
        //aList.add("ben");

        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    int total = response.getInt("Total");
                    if(total > 0){
                        JSONArray ja = response.getJSONArray("array");
                        for(int i = 0; i < total; ++i){
                            aList.add(ja.getString(i));
                        }
                    }
                    else{
                        aList.add("No Results Found");
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Tag, "Error: " + error.getMessage());
            }
        });

        return aList;
    }

    public ArrayList<String> searchGames(String query){
        final String Tag = Lobby.class.getSimpleName();
        String url = Const.URL_SEARCH_GAMES;
        url = url + "?search=" + query;
        final ArrayList<String> list = new ArrayList<>();
        //list.add("Poker");

        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    int total = response.getInt("Total");
                    if(total > 0){
                        JSONArray ja = response.getJSONArray("array");
                        for(int i = 0; i < total; ++i){
                            list.add(ja.getString(i));
                        }
                    }
                    else{
                        list.add("No Results Found");
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Tag, "Error: " + error.getMessage());
            }
        });

        return list;
    }
}

