package com.padc.beauty.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;


import com.padc.beauty.data.vos.TipVO;

import com.padc.beauty.data.vos.BeautySaloonVO;
import com.padc.beauty.data.vos.TipVO;
import com.padc.beauty.fragments.BookmarkFragment;
import com.padc.beauty.fragments.DressingPagerFragment;

import com.padc.beauty.fragments.FitnessAndHealthBKFragments;
import com.padc.beauty.fragments.FitnessAndHealthFragment;
import com.padc.beauty.fragments.PersonalityListFragment;
import com.padc.beauty.fragments.SaloonandFashionshopFragment;
import com.padc.beauty.fragments.TipsPagerFragment;
import com.padc.beauty.fragments.TutorialsFragment;
import com.padc.beauty.fragments.WorkoutDetailFragment;
import com.padc.beauty.utils.MMFontUtils;
import com.padc.beauty.views.holders.DressingViewHolder;
import com.padc.beauty.views.holders.FitnessandhealthViewHolder;
import com.padc.beauty.views.holders.PersonalityViewHolder;
import com.padc.beauty.views.holders.BeautySaloonViewHolder;
import com.padc.beauty.views.holders.FitnessandhealthViewHolder;
import com.padc.beauty.views.holders.TipViewHolder;
import com.padc.beauty.views.holders.PersonalityViewHolder;

public class HomeActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener,
        MenuItemCompat.OnActionExpandListener,
        PersonalityViewHolder.ControllerPersonalityItem,
        BeautySaloonViewHolder.ControllerBeautysalonItem,
        FitnessandhealthViewHolder.ControllerFitnessandHealth,
        DressingViewHolder.ControllerDressing{


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        Menu leftMenu = navigationView.getMenu();
        MMFontUtils.applyMMFontToMenu(leftMenu);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            navigateToBeautyTips();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.beauty_tips:
                navigateToBeautyTips();
                return true;
            case R.id.fitness:
                navigateToFitness();
                return true;
            case R.id.saloon_and_fashionshop:
                navigateToSaloonandFashionshop();
                return true;
            case R.id.beauty_tutorial:
                navigateToBeautyTutorial();
                return true;
            case R.id.beauty_dressing:
                navigatetoDressing();
                return true;
            case R.id.personality:
                navigatetoPersonality();
                return true;
            case R.id.bookmarks:
                navigatetoBookmark();
                return true;
        }
        return false;
    }
    public void navigateToBeautyTips(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, TipsPagerFragment.newInstance())
                .commit();
    }
    public void navigateToFitness(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, FitnessAndHealthFragment.newInstance())
                .commit();
    }
    public void navigateToSaloonandFashionshop(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, SaloonandFashionshopFragment.newInstance())
                .commit();
//        Intent intent = SalonandFashionshopActivity.newIntent();
//        startActivity(intent);
    }
    public void navigateToBeautyTutorial(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, TutorialsFragment.newInstance())
                .commit();
    }


    private void navigatetoDressing() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, DressingPagerFragment.newInstance())
                .commit();
    }

    private  void  navigatetoBookmark(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, BookmarkFragment.newInstance())
                .commit();
    }
    private void navigatetoPersonality(){

//        Intent intent = PersonalityListActivity.newIntent();
//        startActivity(intent);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, PersonalityListFragment.newInstance())
                .commit();

    }

    @Override
    public void onTapBeautysalon(BeautySaloonVO beautysalon, ImageView ivbeautysaloon) {
        Intent intent = BeautysalonDetailActivity.newIntent(beautysalon.getsaloonid(), beautysalon.getPhoto(),
                beautysalon.getsaloonname(),beautysalon.getfulladdr());
        startActivity(intent);
    }

    @Override
    public void onTapPersonality(TipVO tip) {
        Intent intent=PersonalityDetailActivity.newIntent(tip.getTipid(),tip.getTitle());
        startActivity(intent);
    }

    @Override
    public void onTapHealth(TipVO healthrelatedTips) {
        Log.d(BeautyApp.TAG, "Tip id" + healthrelatedTips.getTipid());
        Intent intent = WorkoutDetailActivity.newIntent(healthrelatedTips.getTipid());
        startActivity(intent);
    }

    @Override
    public void onTabshare(String imageurl,String Price,String ShopName,String ShopDirection) {
        Toast.makeText(BeautyApp.getContext(), "get activiyt", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT,imageurl+"\r\n" + "Price : " +Price+"\r\n" + "Shop : " +ShopName +"\r\n"  +ShopDirection );


        startActivity(Intent.createChooser(intent, "Share Image"));
    }
}
