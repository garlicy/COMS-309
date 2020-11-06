package com.example.cardgames.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cardgames.R;
import com.example.cardgames.ScreenParent;
import com.example.cardgames.net_utils.Const;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


/**
 * @author rradtke
 */
public class EditProfile extends ScreenParent implements AdapterView.OnItemSelectedListener{

    private ImageView back, userimage;
    private Button submit;
    private TextView user;
    private int imageID;
    private Spinner spinner;
    private RequestQueue mRequestQueue;
    private EditText oldPass, newPass, newConf;
    private String password, urlRec, urlSend, Tag, oPassword, nPassword, cPassword, username;
    private UserInfo mainUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        SetFrame();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toastMsg = "Error";
                oPassword = oldPass.getText().toString();
                nPassword = newPass.getText().toString();
                cPassword = newConf.getText().toString();

                boolean oldMatch = false;
                boolean newMatch = false;

                if(oPassword.equals(password)){
                    oldMatch = true;
                    if(nPassword.equals(cPassword)){
                        newMatch = true;
                    }
                }

                if(oldMatch == false){
                    toastMsg = "Old Password Incorrect";
                }

                else if(newMatch == false){
                    toastMsg = "Passwords Don't Match";
                }

                else{
                    mainUser.changePassword(nPassword);
                    toastMsg = "Password Change Successful";
                    Toast.makeText(EditProfile.this, toastMsg, Toast.LENGTH_SHORT).show();
                    oldPass.setText("Old Password");
                    newPass.setText("New Password");
                    newConf.setText("Confirm New Password");
                }
            }
        });


    }


    public void SetFrame() {
        user = findViewById(R.id.editProfile_username);
        username = getIntent().getStringExtra("name");
        user.setText(username);
        userimage = findViewById(R.id.editProfile_userImage);
        imageID = getIntent().getIntExtra("imageID", -1);
        userimage.setImageDrawable(getDrawable(imageID));
        mainUser = new UserInfo(this, username);
        password = "pass";//mainUser.getPassword();

        spinner = findViewById(R.id.SPIN_editprofile);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SET_default, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        back = findViewById(R.id.BACK_editprofile);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        oldPass = findViewById(R.id.old_password);
        newPass = findViewById(R.id.new_password);
        newConf = findViewById(R.id.confirm_new_password);
        submit = findViewById(R.id.submit_password);
        urlRec = Const.URL_GET_USER_INFO;
        urlSend = Const.URL_CHANGE_PASSWORD;
        Tag = EditProfile.class.getSimpleName();

        mRequestQueue = Volley.newRequestQueue(this);
    }


}