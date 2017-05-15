package com.vlad17021995m.android.tesk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mailView;
    private Button logoutButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LoginActivity.activities.add(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        mailView = (TextView)findViewById(R.id.email_view);
        logoutButt = (Button)findViewById(R.id.logout_butt);
        String mail = getIntent().getStringExtra(LoginActivity.KEY_EMAIL);
        mailView.setText(mail);

        logoutButt.setOnClickListener(this);
        for (int i = 0;i < LoginActivity.activities.size();i++){
            Activity a = LoginActivity.activities.get(i);
            if (a.equals(this)){
                continue;
            }
            LoginActivity.activities.remove(i);
            a.finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoginActivity.activities.remove(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logout_butt:
                LocalStorage data = LocalStorage.getInstance();
                SharedPreferences preferences = getSharedPreferences(LoginActivity.LOGIN_PREFERENCES, Context.MODE_PRIVATE);
                data.setCurrentUser(preferences, LoginActivity.KEY_EMAIL, "");
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
