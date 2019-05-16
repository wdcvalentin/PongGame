package com.DogPong.pong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;


public class SplashActivity extends AppCompatActivity {


    Intent intentPong;
    Intent intentTeam;

    BottomNavigationView navigationPong;
    BottomNavigationView navigationTeam;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // affiche un écran
        setContentView(R.layout.activity_main);


        // TextMessage = (TextView) findViewById(R.id.message);
        navigationPong = (BottomNavigationView) findViewById(R.id.navigation);
        navigationPong.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigationTeam = (BottomNavigationView) findViewById(R.id.navigation);
        navigationTeam.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        intentPong = new Intent(this, MainActivity.class);
        intentTeam = new Intent(this, TeamActivity.class);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(intentPong);
                    return true;
                case R.id.navigation_dashboard:
                    startActivity(intentTeam);
                    return true;
            }
            return false;
        }
    };



}