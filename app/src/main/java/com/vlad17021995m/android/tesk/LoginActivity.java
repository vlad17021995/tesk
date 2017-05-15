package com.vlad17021995m.android.tesk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mailText, passText;
    private Button login_button, register_button;

    public static final String LOGIN_PREFERENCES = "LOGIN_PREFERENCES";
    public static final String KEY_EMAIL = "LOGIN_PREFERENCES";

    public static List<Activity> activities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activities.add(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mailText = (EditText)findViewById(R.id.mailText);
        passText = (EditText)findViewById(R.id.passText2);
        login_button = (Button)findViewById(R.id.login_button);
        register_button = (Button)findViewById(R.id.register_button);
        login_button.setOnClickListener(this);
        register_button.setOnClickListener(this);
        for (int i = 0;i < activities.size();i++){
            Activity a = activities.get(i);
            if (a.equals(this)){
                continue;
            }
            activities.remove(i);
            a.finish();
        }

        LocalStorage data = LocalStorage.getInstance();
        SharedPreferences preferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        String currUser = data.currentUser(preferences, KEY_EMAIL);
        if (!currUser.equals("")){
            Intent intent = new Intent(this, ViewActivity.class);
            intent.putExtra(KEY_EMAIL, currUser);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        LocalStorage data = LocalStorage.getInstance();
        SharedPreferences preferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        switch (v.getId()){
            case R.id.login_button:
                int r = data.loginAccount(preferences, mailText.getText().toString(), passText.getText().toString());
                if (r == 0){
                    data.setCurrentUser(preferences, KEY_EMAIL, mailText.getText().toString());
                    Intent intent = new Intent(this, ViewActivity.class);
                    intent.putExtra(KEY_EMAIL, mailText.getText().toString());
                    startActivity(intent);
                }else if (r == 1){
                    Toast.makeText(this, "пользователь не найден", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "пароль не правильный", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.register_button:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activities.remove(this);
    }
}
