package com.example.obizo;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTabHost;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.obizo.NavigationView.Navigation_Home_Fragment;
import com.example.obizo.NavigationView.Navigation_Timeline_Fragment;
import com.example.obizo.NavigationView.Navigation_logout_Fragment;
import com.example.obizo.NavigationView.Navigation_school_Fragment;
import com.example.obizo.NavigationView.Navigation_settings_Fragment;
import com.example.obizo.NavigationView.Navigation_work_Fragement;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        mDrawerLayout=findViewById(R.id.drawerLayout);
        mDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView=findViewById( R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Navigation_Home_Fragment fragment=new Navigation_Home_Fragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment,"Home");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();
        if(id==R.id.home){
            Navigation_Home_Fragment fragment=new Navigation_Home_Fragment();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout,fragment,"Home");
            fragmentTransaction.commit();

        }
        else if(id==R.id.work){
            Navigation_work_Fragement fragment=new Navigation_work_Fragement();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout,fragment,"Work");
            fragmentTransaction.commit();
        }
        else if(id==R.id.school){
            Navigation_school_Fragment fragment=new Navigation_school_Fragment();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout,fragment,"School");
            fragmentTransaction.commit();
        }
        else if(id==R.id.timeline){
            Navigation_Timeline_Fragment fragment=new Navigation_Timeline_Fragment();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout,fragment,"Timeline");
            fragmentTransaction.commit();
        }
        else if(id==R.id.setting)
        {
            Navigation_settings_Fragment fragment=new Navigation_settings_Fragment();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout,fragment,"Setting");
            fragmentTransaction.commit();
        }
        else if(id==R.id.logout){
            Navigation_logout_Fragment fragment=new Navigation_logout_Fragment();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout,fragment,"Logout");
            fragmentTransaction.commit();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }
}
