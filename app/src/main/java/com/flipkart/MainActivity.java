package com.flipkart;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.flipkart.fragment.CameraFragment;
import com.flipkart.fragment.GalaryFragment;
import com.flipkart.fragment.HomeFragment;
import com.flipkart.fragment.ProductDetailsFragment;
import com.flipkart.fragment.SendFragment;
import com.flipkart.fragment.ShareFragment;
import com.flipkart.fragment.SlideShowFragment;
import com.flipkart.fragment.ToolFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CameraFragment.OnFragmentInteractionListener,
        GalaryFragment.OnFragmentInteractionListener,
        SlideShowFragment.OnFragmentInteractionListener,
        ToolFragment.OnFragmentInteractionListener,
        ShareFragment.OnFragmentInteractionListener,
        SendFragment.OnFragmentInteractionListener,
        ProductDetailsFragment.OnFragmentInteractionListener,HomeFragment.OnFragmentInteractionListener{
    private FrameLayout frameLayout;
    private ImageView imageView;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

         navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        imageView = (ImageView) header.findViewById(R.id.iv_pic);
//
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.raw.god).into(imageViewTarget);

        HomeFragment homeFragment = new HomeFragment();
        setFragment(homeFragment);
        toolbar.setTitle("Home");
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
        getMenuInflater().inflate(R.menu.main, menu);
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
            CameraFragment cameraFragment = new CameraFragment();
            setFragment(cameraFragment);
            toolbar.setTitle("Camera");
            Toast.makeText(this, "Camera", Toast.LENGTH_SHORT).show();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            GalaryFragment galaryFragment = new GalaryFragment();
            setFragment(galaryFragment);
            toolbar.setTitle("Catalogue");
            Toast.makeText(this, "Galary", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_slideshow) {
            SlideShowFragment slideShowFragment = new SlideShowFragment();
            setFragment(slideShowFragment);
            toolbar.setTitle("SlideShow");
            Toast.makeText(this, "SlideShow", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_manage) {
            ToolFragment toolFragment = new ToolFragment();
            setFragment(toolFragment);
            toolbar.setTitle("Navigation");
            Toast.makeText(this, "Tool", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_share) {
            ShareFragment shareFragment = new ShareFragment();
            setFragment(shareFragment);
            toolbar.setTitle("About Us");
            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            SendFragment sendFragment = new SendFragment();
            setFragment(sendFragment);
            toolbar.setTitle("Send Enquiry");
            Toast.makeText(this, "send", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "OnFragmentInteractionListener", Toast.LENGTH_SHORT).show();
    }
}
