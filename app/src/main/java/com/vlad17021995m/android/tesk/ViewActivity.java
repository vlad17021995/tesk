package com.vlad17021995m.android.tesk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    private TextView mailView;
    private Button logoutButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        mailView = (TextView)findViewById(R.id.email_view);
        logoutButt = (Button)findViewById(R.id.logout_butt);
    }
}
