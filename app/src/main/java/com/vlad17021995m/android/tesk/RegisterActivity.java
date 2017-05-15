package com.vlad17021995m.android.tesk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText regMailText, regMailPass, regConfirmPass;
    private Button regButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regMailText = (EditText)findViewById(R.id.regmailText);
        regMailPass = (EditText)findViewById(R.id.regpassText2);
        regConfirmPass = (EditText)findViewById(R.id.regconfirmpassText3);
        regButt = (Button) findViewById(R.id.register_butt);
    }
}
