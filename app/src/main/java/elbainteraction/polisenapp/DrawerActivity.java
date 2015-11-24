package elbainteraction.polisenapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import elbainteraction.polisenapp.AnmalanPackage.AddWitnessActivity;
import elbainteraction.polisenapp.AnmalanPackage.AnmalanFragment;
import elbainteraction.polisenapp.AnsokanPackage.AnsokanFragment;
import elbainteraction.polisenapp.senastePackage.SenasteFragment;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initiateDrawerActivity();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_anmalan) {
            fragmentManager.beginTransaction().replace(R.id.main, new AnmalanFragment()).commit();

        } else if (id == R.id.nav_ansokan) {
            fragmentManager.beginTransaction().replace(R.id.main, new AnsokanFragment()).commit();
        } else if (id == R.id.nav_aktuellt) {
            fragmentManager.beginTransaction().replace(R.id.main, new SenasteFragment()).commit();
        } else if (id == R.id.nav_installning) {
            fragmentManager.beginTransaction().replace(R.id.main, new SettingsFragment()).commit();
        } else if (id == R.id.nav_login) {
            startActivity(new Intent(this, LoginActivity.class));
        } else if (id == R.id.nav_logout) {

            SharedPreferences mPrefs = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor mEditor = mPrefs.edit();
            mEditor.putBoolean("Logged in", false).commit();

            initiateDrawerActivity();
        }else if (id == R.id.nav_vittne) {
            startActivity(new Intent(this, AddWitnessActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        initiateDrawerActivity();

        if(getIntent().getIntExtra("anmalanFragment", 0) == 1){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main, new AnmalanFragment()).commit();
        }

    }


    private void initiateDrawerActivity(){

        SharedPreferences mPrefs = getSharedPreferences("login", MODE_PRIVATE);
        boolean loggedIn = mPrefs.getBoolean("Logged in", false);

        if (loggedIn){
            setContentView(R.layout.activity_drawer_logged_in);
        } else {
            setContentView(R.layout.activity_drawer_logged_out);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main, new SenasteFragment()).commit();



    }


}
