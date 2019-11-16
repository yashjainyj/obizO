package com.example.obizo;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTabHost;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.obizo.Login.LoginActivity;
import com.example.obizo.Main.MyOrder;
import com.example.obizo.NavigationView.Navigation_Home_Fragment;
import com.example.obizo.NavigationView.Navigation_Timeline_Fragment;
import com.example.obizo.NavigationView.Navigation_logout_Fragment;
import com.example.obizo.NavigationView.Navigation_school_Fragment;
import com.example.obizo.NavigationView.Navigation_settings_Fragment;
import com.example.obizo.NavigationView.Navigation_work_Fragement;
import com.example.obizo.UserAccount.Address_Detail;
import com.example.obizo.UserAccount.Cart_Main;
import com.example.obizo.seller.Item_data_model;
import com.example.obizo.seller.Shops_Main;
import com.example.obizo.seller.Show_Item;
import com.example.obizo.seller.Show_Item_Adapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    NavigationView navigationView;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        mDrawerLayout=findViewById(R.id.drawerLayout);
        mDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        navigationView=findViewById( R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Navigation_Home_Fragment fragment=new Navigation_Home_Fragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment,"Home");
        fragmentTransaction.commit();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null)
        {

            View view = getLayoutInflater().inflate(R.layout.navigation_header,null);
            TextView name = view.findViewById(R.id.name);
            TextView number = view.findViewById(R.id.email);
            number.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
            String s[] = FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@");
            name.setText(s[0]);
            navigationView.addHeaderView(view);
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();
        if(id==R.id.seller){
            Intent intent = new Intent(MainActivity.this, Shops_Main.class);
            startActivity(intent);
            finish();

        }
        else if(id==R.id.userLogin){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else if(id==R.id.address){
            Intent intent = new Intent(MainActivity.this, Address_Detail.class);
            startActivity(intent);
            finish();
        }
        else if(id==R.id.order){
            Intent intent = new Intent(MainActivity.this, MyOrder.class);
            startActivity(intent);
            finish();
        }
        else if(id==R.id.cart)
        {
            Intent intent = new Intent(MainActivity.this, Cart_Main.class);
            startActivity(intent);
            finish();
        }
        else if(id==R.id.nav_share){
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
    protected void onStart() {
        super.onStart();


    }
}
