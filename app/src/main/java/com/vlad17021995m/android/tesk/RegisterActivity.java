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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText regMailText, regMailPass, regConfirmPass;
    private Button regButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LoginActivity.activities.add(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regMailText = (EditText)findViewById(R.id.regmailText);
        regMailPass = (EditText)findViewById(R.id.regpassText2);
        regConfirmPass = (EditText)findViewById(R.id.regconfirmpassText3);
        regButt = (Button) findViewById(R.id.register_butt);

        regButt.setOnClickListener(this);

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
        LocalStorage data = LocalStorage.getInstance();
        SharedPreferences preferences = getSharedPreferences(LoginActivity.LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        switch (v.getId()){
            case R.id.register_butt:
                if (regMailText.getText().toString().length() < 6){
                    Toast.makeText(this, "почтовый адрес должен быть больше 6 символов", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (regMailPass.getText().toString().length() < 4){
                    Toast.makeText(this, "пароль должен быть больше 4 символов", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!regMailPass.getText().toString().equals(regConfirmPass.getText().toString())){
                    Toast.makeText(this, "пароли должны совпадать", Toast.LENGTH_SHORT).show();
                    return;
                }
                int r = data.registerAccount(preferences, regMailText.getText().toString(), regMailPass.getText().toString());
                if (r == 0){
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }else if (r == 1){
                    Toast.makeText(this, "пользователь с такой почтой уже существует", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
