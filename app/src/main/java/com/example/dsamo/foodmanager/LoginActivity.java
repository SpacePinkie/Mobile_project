package com.example.dsamo.foodmanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    Fragment authFragment;
    Fragment regFragment;
    Fragment atThisTimeFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fragmentManager = getSupportFragmentManager();
        authFragment = fragmentManager.findFragmentById(R.id.login_container);
        if(authFragment == null) {
            authFragment = new AuthorizationFragment();
            regFragment = new RegistrationFragment();
            atThisTimeFragment = authFragment;
            fragmentManager.beginTransaction()
                    .add(R.id.login_container, authFragment)
                    .commit();
        }


    }
}
