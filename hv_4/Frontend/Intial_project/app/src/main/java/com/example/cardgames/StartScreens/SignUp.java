package com.example.cardgames.StartScreens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cardgames.R;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.cardgames.net_utils.Const.URL_ADD_USER;

/**
 * @author Colton Hazlett
 * Allows the users to create an account
 */
public class SignUp extends AppCompatActivity implements SignUpImageSelection.OnInputListener{

    private Button create, icon;
    private EditText ETname, ETemail, ETpassword, ETpassword2;
    private RequestQueue mRequestQueue;
    private ImageView selectedIcon;
    private int imageID = R.drawable.icon6;

    /**
     * Creates the screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        create = findViewById(R.id.BTN_CreateAccount);
        icon = findViewById(R.id.BTN_ImageSelect);
        selectedIcon = findViewById(R.id.SelectedIcon);
        ETname = findViewById(R.id.ET_UserName);
        ETemail = findViewById(R.id.ET_emailSignUp);
        ETpassword = findViewById(R.id.ET_passwordSignUp);
        ETpassword2 = findViewById(R.id.ET_passwordSignUp_ReEnter);
        mRequestQueue = Volley.newRequestQueue(this);

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("On Click", "Opening Dialog");
                SignUpImageSelection dialog = new SignUpImageSelection();
                dialog.show(getSupportFragmentManager(), "SignUpImageSelect");
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validInformation()) {
                    addUser();
                    Intent i = new Intent(SignUp.this, Lobby.class);
                    i.putExtra("name", ETname.getText().toString());
                    i.putExtra("imageID", imageID);
                    startActivity(i);
                }
            }
        });

    }

    /**
     * Sends an Android Volley Request to the server to create a user
     */
    public void addUser() {

        StringRequest putRequest = new StringRequest(Request.Method.POST, URL_ADD_USER,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("username", ETname.getText().toString());
                params.put("email", ETemail.getText().toString());
                params.put("password", ETpassword.getText().toString());
                params.put("usericon", "" + imageID);

                return params;
            }

        };
        mRequestQueue.add(putRequest);
    }
    /**
     * Method to get the image icon id for use
     */
    @Override
    public void sendID(int id) {
        Log.d("Send", "sendID:  " + id);
        selectedIcon.setImageDrawable(getDrawable(id));
        imageID = id;
    }
    /**
     * Method that checks that all the information gathered is valid
     */
    public boolean validInformation(){
        String[] str = new String[3];
        str[0] = ETname.getText().toString();
        str[1] = ETemail.getText().toString();
        str[2] = ETpassword.getText().toString();
        for(int i = 0; i < str.length; i++){
            if(str[i].length() == 0){
                Toast.makeText(this, "Fill in all the information", Toast.LENGTH_SHORT).show();
                return false;
            }
            else{
                for(int j = 0; j < str[i].length(); j++){
                    if(Character.isSpaceChar(str[i].charAt(j))){
                        Toast.makeText(this, "No spaces allowed", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
        }
        if(!Character.isAlphabetic(str[2].charAt(0))){
            Toast.makeText(this, "Password needs a character to start", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!ETpassword.getText().toString().equals(ETpassword2.getText().toString())){
            Toast.makeText(this, "Passwords are not the same", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
         return true;
    }
}
