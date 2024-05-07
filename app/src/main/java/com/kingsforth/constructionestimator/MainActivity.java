package com.kingsforth.constructionestimator;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.alfadroid.constructionestimator.R;
import com.kingsforth.constructionestimator.Sub_Structure.Sub_structure;
import com.kingsforth.constructionestimator.Super_Structure.Select_in_super_structure;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//    private StartAppAd startAppAd = new StartAppAd(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Select_Structure fragment  = new Select_Structure();;
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

//

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.nav_home) {

            Select_Structure fragment  = new Select_Structure();;
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        }
        if(id==R.id.nav_super){

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, new Select_in_super_structure())
                    .commit();
        }if(id==R.id.nav_sub){

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, new Sub_structure())
                    .commit();
        }if(id==R.id.nav_configuration){

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, new Configuration_setting())
                    .commit();
        }if (id == R.id.nav_save) {
            dialog_save_all();
        }if (id == R.id.nav_about) {

            Toast.makeText(this, "In progress", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void dialog_save_all(){



        final Dialog dialog = new Dialog(MainActivity.this);


        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_save_all);

//            dialog.setTitle("Are You Sure to Delete");


        //***********************     Bridging to alert layout         **************************************



        Button btn_cross = (Button) dialog.findViewById(R.id.cross);
        Button btn_ok    = (Button) dialog.findViewById(R.id.ok);

        //***********************   End   Bridging to alert layout         **************************************







        btn_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.dismiss();
            }

        });




        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });



        dialog.show();


    }
}
