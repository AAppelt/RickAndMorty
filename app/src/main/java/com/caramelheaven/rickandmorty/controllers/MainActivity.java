package com.caramelheaven.rickandmorty.controllers;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.Toast;

import com.caramelheaven.rickandmorty.R;
import com.caramelheaven.rickandmorty.controllers.ui.characters.CharactersFragment;
import com.caramelheaven.rickandmorty.controllers.ui.locations.LocationsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigationView)
    NavigationView navigationView;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    String string = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_locations:
                    item.setChecked(true);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, LocationsFragment.newInstance())
                            .commit();
                    drawerLayout.closeDrawers();
                    break;
                case R.id.nav_persons:
                    item.setChecked(true);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, CharactersFragment.newInstance())
                            .commit();
                    drawerLayout.closeDrawers();
                    break;
            }
            drawerLayout.closeDrawers();
            return true;
        });
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
