package test.openerp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.openerp.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    boolean doubleBackToExitPressedOnce = false;
    SharedPreferences sp;
    private final String MY_PREFERENCES = "AUTHENTICATION";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Initializing Toolbar and setting it as the actionbar
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.home));

        //Initializing NavigationView
        navigationView.setItemIconTintList(null);

        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().
//                    replace(R.id.frame, new HomeFragment(), getString(R.string.home)).commit();
        }

        sp = getSharedPreferences(MY_PREFERENCES,MODE_PRIVATE);
        setupNavigationBar();

        loadFragment(0);
        
    }

    private void setupNavigationBar() {
            // setup drawer layout and sync to toolbar
            navigationView.setNavigationItemSelectedListener(this);
            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                    drawerLayout, toolbar, R.string.openDrawer, R.string.closedDrawer) {

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {
                    super.onDrawerSlide(drawerView, slideOffset);
                }
            };
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //Checking if the item is in checked state or not, if not make it in checked state
        if (menuItem.isChecked()) {
            menuItem.setChecked(false);
        } else {
            menuItem.setChecked(true);
        }

        //Closing drawer on item click
        drawerLayout.closeDrawers();

        //Check to see which item was being clicked and perform appropriate action
        switch (menuItem.getItemId()) {
            //Replacing the main content with ContentFragment Which is our Inbox View;
            case R.id.home:
//                HomeFragment fragment1 = new HomeFragment();
//                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager()
//                        .beginTransaction();
//                fragmentTransaction1.replace(R.id.frame, fragment1);
//                fragmentTransaction1.commit();
//                setToolbarTitle(getString(R.string.home));
                return true;

            case R.id.logout:
                logout();
                return true;

            default:
                Toast.makeText(getApplicationContext(), "Somethings Wrong",
                        Toast.LENGTH_SHORT).show();
                return true;
        }
    }

    private void logout() {

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("LOGIN",false);
        editor.commit();

        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();

    }

    public void loadFragment(int position){

        switch(position){

            case 0: //Home

                HomeFragment fragment = new HomeFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame,fragment);
                fragmentTransaction.commit();

                break;
        }
    }

    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                ActivityCompat.finishAffinity(this);
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Tap again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    }

}
