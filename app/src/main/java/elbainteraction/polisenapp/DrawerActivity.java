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
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
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
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_anmalan) {
            fragmentManager.beginTransaction().replace(R.id.main, new AnmalanFragment()).commit();
        } else if (id == R.id.nav_ansokan) {
            fragmentManager.beginTransaction().replace(R.id.main, new AnsokanFragment()).commit();
        } else if (id == R.id.nav_aktuellt) {
            fragmentManager.beginTransaction().replace(R.id.main, new SenasteFragment()).commit();
        } else if (id == R.id.nav_installning) {
            fragmentManager.beginTransaction().replace(R.id.main, new SettingsFragment()).commit();
        } else if (id == R.id.nav_logout) {
            //currently logging in instead of out, fix later
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("caller", "LoginActivity");
            startActivity(intent);
        } else if (id == R.id.nav_vittne) {
            fragmentManager.beginTransaction().replace(R.id.main, new VittneFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






    @Override
    public void onResume(){
        super.onResume();

        initiateDrawerActivity();
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
