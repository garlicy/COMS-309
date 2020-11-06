package com.example.cardgames;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cardgames.Games.GameScreen;
import com.example.cardgames.StartScreens.*;
import java.util.HashMap;
import java.util.Map;

import static com.example.cardgames.net_utils.Const.URL_LOGIN_USER;

/**
 * @author Colton Hazlett
 * This is the login screen that allows users to log into their account or sign up
 */

public class MainActivity extends AppCompatActivity {

    private Button Login, Signup, skip, game;
    private EditText etName, etPassword;
    private RequestQueue mRequestQueue;
    private int imageID = -1;


    /**
     * Creates the screen displaying
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login = findViewById(R.id.loginButton);
        Signup = findViewById(R.id.SignupButton);
        skip = findViewById(R.id.skip);
        game = findViewById(R.id.game);
        mRequestQueue = Volley.newRequestQueue(this);

        etName = findViewById(R.id.ETusernameLOGIN);
        etPassword = findViewById(R.id.ETpasswordLOGIN);
        etPassword.setTransformationMethod(new AsteriskPasswordTransformationMethod());


        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                requestUserInformation();
                if(imageID != -1) {
                    Intent i = new Intent(MainActivity.this, Lobby.class);
                    i.putExtra("name", etName.getText().toString());
                    i.putExtra("imageID", imageID);
                    startActivity(i);
                }
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Lobby.class);
                startActivity(intent);
            }
        });
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameScreen.class);
                startActivity(intent);
            }
        });
    }
    /**
     * Android Volley function that sends a request to see if user exsists
     */
    public void requestUserInformation() {
        StringRequest putRequest = new StringRequest(Request.Method.POST, URL_LOGIN_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        if(response != null)
                            imageID = Integer.parseInt(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", etName.getText().toString());
                params.put("password", etPassword.getText().toString());

                return params;
            }

        };
        mRequestQueue.add(putRequest);
    }
    /**
     * Private class to help transform text to hidden form
     */
    private class AsteriskPasswordTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }

        private class PasswordCharSequence implements CharSequence {
            private CharSequence mSource;
            public PasswordCharSequence(CharSequence source) {
                mSource = source; // Store char sequence
            }
            public char charAt(int index) {
                return '*'; // This is the important part
            }
            public int length() {
                return mSource.length(); // Return default
            }
            public CharSequence subSequence(int start, int end) {
                return mSource.subSequence(start, end); // Return default
            }
        }
    };
}
