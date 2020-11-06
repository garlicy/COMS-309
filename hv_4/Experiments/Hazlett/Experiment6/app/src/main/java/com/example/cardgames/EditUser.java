package com.example.cardgames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditUser extends AppCompatActivity {

    private Button delete, updateSelect, updateAction, viewDB;
    private EditText findUser, eName, eEmail, ePass;
    private TextView tvName, tvEmail, tvPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);



    }
}
