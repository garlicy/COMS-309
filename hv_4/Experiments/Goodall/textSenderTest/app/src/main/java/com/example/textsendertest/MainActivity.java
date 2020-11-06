package com.example.textsendertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Date: 1/26/2020
//Name: Benjamin Goodall, but really I most of this is from the FAQ, and I was just using it to become more familiar with Android.
//Sorry.
public class MainActivity extends AppCompatActivity {

    private EditText SMSBody;
    private EditText phoneNumber;
    private Button SMSManagerBttn;
    private Button intentSenderBttn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneNumber = findViewById(R.id.phoneNumberInput);
        SMSBody = findViewById((R.id.smsText));
        SMSManagerBttn = findViewById(R.id.SMSManagerButton);
        intentSenderBttn = findViewById(R.id.SMSIntentButton);

        SMSManagerBttn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                sendSMSByManager();
            }
        });
        intentSenderBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMSByIntent();
            }
        });
    }

    public void sendSMSByManager()
    {
        try{
            SmsManager manager = new SmsManager.getDefault();
            manager.sendTextMessage(phoneNumber.getText().toString(), null, SMSBody.getText().toString(), null,null);
            Toast.makeText(getApplicationContext(),"Wow, that worked?",Toast.LENGTH_LONG).show();
        } catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), "Okay, that failed.", Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}
