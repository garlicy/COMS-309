package com.example.cardgames;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button Login, Signup, Skip, viewDB;
    private EditText etName, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login = findViewById(R.id.loginButton);
        Signup = findViewById(R.id.SignupButton);
        Skip = findViewById(R.id.SkipLinButton);
        viewDB = findViewById(R.id.viewDB);

        etName = findViewById(R.id.ETusernameLOGIN);
        etPassword = findViewById(R.id.ETpasswordLOGIN);

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                boolean worked = true;
                try {
                    String name = etName.getText().toString();
                    String password = etPassword.getText().toString();

                    CardGameMaker entry = new CardGameMaker(MainActivity.this);
                    entry.open();
                    if(entry.getName(name) != null) {
                        Intent i = new Intent(MainActivity.this, Lobby.class);
                        entry.close();
                        Dialog d = new Dialog(MainActivity.this);
                        TextView tv = new TextView(MainActivity.this);
                        tv.setText("Logged In");
                        d.setContentView(tv);
                        d.show();
                        startActivity(i);
                    }
                    else
                        entry.close();
                }catch(Exception e){
                    worked = false;
                    Dialog d = new Dialog(MainActivity.this);
                    TextView tv = new TextView(MainActivity.this);
                    tv.setText("No User");
                    d.setContentView(tv);
                    d.show();
                }finally{
                    if(worked){
                        Dialog d = new Dialog(MainActivity.this);
                        TextView tv = new TextView(MainActivity.this);
                        tv.setText("Logged In");
                        d.setContentView(tv);
                        d.show();
                    }
                }
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, GameCategory.class);
                startActivity(i);
            }
        });
        viewDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DatabaseView.class);
                startActivity(intent);
            }
        });
    }
}
