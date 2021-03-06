package com.example.obizo.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;


import com.example.obizo.MainActivity;
import com.example.obizo.UserAccount.Address_DataModel;
import com.example.obizo.R;
import com.example.obizo.seller.Shops_Main;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrder extends AppCompatActivity {
    TextInputEditText textInputEditText;
    RecyclerView recyclerView;
    TextInputLayout textInputLayout;
    DatabaseReference databaseReference;
    List<OrderModel> list;
    ShimmerFrameLayout shimmerFrameLayout;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shops_items);
        textInputEditText = findViewById(R.id.add_item);
        textInputEditText.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        shimmerFrameLayout = findViewById(R.id.shimmer);
        relativeLayout = findViewById(R.id.rel1);
        list = new ArrayList<>();
        textInputLayout = findViewById(R.id.add_address1);
        textInputLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(firebaseAuth.getCurrentUser().getUid()).child("MyOrder");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    list.add(dsp.getValue(OrderModel.class));
                }

                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MyOrder.this,2);
                recyclerView.setLayoutManager(layoutManager);
                MyOrderAdapter myOrderAdapter = new MyOrderAdapter(MyOrder.this,list);
                recyclerView.setAdapter(myOrderAdapter);
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
