package com.example.dsamo.foodmanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class FridgeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragmentFridge;
    Fragment fragmentList;
    Fragment atThisTimeFragment;
    FragmentManager fragmentManager;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentFridge = fragmentManager.findFragmentById(R.id.main_container);
        if(fragmentFridge == null) {
            fragmentFridge = new FridgeFragment();
            fragmentList = new ListFragment();
            atThisTimeFragment = fragmentFridge;
            fragmentManager.beginTransaction()
                    .add(R.id.main_container, atThisTimeFragment)
                    .commit();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Добавление нового продукта", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    fragmentManager = getSupportFragmentManager();
                    CreateProductFragment dialog = new CreateProductFragment();
                    dialog.show(fragmentManager, "me");
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fridge, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            if(atThisTimeFragment != fragmentFridge) {
                atThisTimeFragment = fragmentFridge;
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, atThisTimeFragment)
                        .addToBackStack(null)
                        .commit();
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view, "Добавление нового продукта", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        fragmentManager = getSupportFragmentManager();
                        CreateProductFragment dialog = new CreateProductFragment();
                        dialog.show(fragmentManager, "me");
                    }
                });
            }
        } else if (id == R.id.nav_gallery){
            if(atThisTimeFragment != fragmentList) {
                atThisTimeFragment = fragmentList;
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragmentList)
                        .addToBackStack(null)
                        .commit();
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view, "Добавление нового продукта в список", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        fragmentManager = getSupportFragmentManager();
                        CreateListItemFragment dialog = new CreateListItemFragment();
                        dialog.show(fragmentManager, "me");
                    }
                });
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}