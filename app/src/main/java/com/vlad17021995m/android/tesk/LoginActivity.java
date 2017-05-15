package com.vlad17021995m.android.tesk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText mailText, passText;
    private Button login_button, register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mailText = (EditText)findViewById(R.id.mailText);
        passText = (EditText)findViewById(R.id.passText2);
        login_button = (Button)findViewById(R.id.login_button);
        register_button = (Button)findViewById(R.id.register_button);
    }
}
