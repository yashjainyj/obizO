package com.example.obizo.seller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.obizo.MainActivity;
import com.example.obizo.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Shops_Main extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FloatingActionButton add;
    boolean checked=false;
    ShimmerFrameLayout shimmerFrameLayout;
    RelativeLayout relativeLayout;
    List<Shop_Detais_Modal> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shops_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Shops");
        Window window = this.getWindow();
        list.clear();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        recyclerView = findViewById(R.id.recyclerview);
        shimmerFrameLayout = findViewById(R.id.shimmer);
        relativeLayout = findViewById(R.id.rel1);
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Shopes");
//        if(databaseReference==null)
//        {
//            Intent intent = new Intent(Shops_Main.this,Add_Shop.class);
//            startActivity(intent);
//        }
        add = findViewById(R.id.edit_shop);

    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Shopes");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                if (dataSnapshot.exists())
                {
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        list.add(dataSnapshot1.getValue(Shop_Detais_Modal.class));
                    }
                    for(Shop_Detais_Modal shop_detais_modal : list)
                    {
                        if(shop_detais_modal.getUserId().equalsIgnoreCase(FirebaseAuth.getInstance().getUid()))
                        {
                            checked=true;
                        }
                        else
                            checked=false;
                    }

                    if (checked)
                    {
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Shops_Main.this);
                        recyclerView.setLayoutManager(layoutManager);
                        Shop_Main_Adapter shop_main_adapter = new Shop_Main_Adapter(Shops_Main.this, list);
                        recyclerView.setAdapter(shop_main_adapter);
                        recyclerView.setHasFixedSize(true);
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        relativeLayout.setVisibility(View.VISIBLE);
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Shops_Main.this,Add_Shop.class);
                                intent.putExtra("shopId",list.get(0).getShop_Id());
                                startActivity(intent);
                                finish();
                            }
                        });

                    }
                    else
                    {
                        Intent intent = new Intent(Shops_Main.this,Add_Shop.class);
                        startActivity(intent);
                        finish();
                    }


                }
                else
                {
                    Intent intent = new Intent(Shops_Main.this,Add_Shop.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent  = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmer();
    }

    @Override
    public void onPause() {
        super.onPause();
        shimmerFrameLayout.stopShimmer();
    }
}
