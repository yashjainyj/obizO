package com.example.obizo.NavigationView;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.obizo.Main.RecyclerViewAdapter;
import com.example.obizo.Main.SliderAdapterExample;
import com.example.obizo.MainActivity;
import com.example.obizo.R;
import com.example.obizo.seller.Item_data_model;
import com.example.obizo.seller.Show_Item;
import com.example.obizo.seller.Show_Item_Adapter;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Navigation_Home_Fragment extends Fragment {


    public Navigation_Home_Fragment() {
        // Required empty public constructor
    }

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private CollectionReference collectionReference ;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    ArrayList<Item_data_model> arrayList;
    ShimmerFrameLayout shimmerFrameLayout;
    RecyclerView recyclerView1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_navigation__home_, container, false);
        SliderView sliderView = view.findViewById(R.id.imageSlider);
    shimmerFrameLayout = view.findViewById(R.id.shimmer);
        SliderAdapterExample adapter1 = new SliderAdapterExample(getActivity());
        getImages();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        sliderView.setSliderAdapter(adapter1);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();


        recyclerView1 = view.findViewById(R.id.item_recycler_view);
//        List<Item_data_model> lstItm ;
//        lstItm = new ArrayList<>();
//        Item_data_model item_data_model = new Item_data_model("1","2","Cotton Fabirc Shirt","12","120","1000","Fabric And Cotton ","Mens","https://i.pinimg.com/originals/48/bd/92/48bd92db0155df8ab67ee273919056e3.jpg", "");
//        lstItm.add(item_data_model);
//        lstItm.add(item_data_model);
//        lstItm.add(item_data_model);
//        lstItm.add(item_data_model);
//        lstItm.add(item_data_model);
//        lstItm.add(item_data_model);
//        lstItm.add(item_data_model);
//        lstItm.add(item_data_model);


        return view;

    }
    private void getImages(){

        mImageUrls.add("https://martjackstorage.blob.core.windows.net/in-resources/db45a459-67f3-4061-9bf2-b70783e2d522/Wedding%20lehenga.jpg");
        mNames.add("Trending");

        mImageUrls.add("https://i.pinimg.com/originals/48/bd/92/48bd92db0155df8ab67ee273919056e3.jpg");
        mNames.add("Mens");

        mImageUrls.add("https://stylesatlife.com/wp-content/uploads/2018/03/Zardosi-set-with-belt.jpg");
        mNames.add("Womens");

        mImageUrls.add("https://g3fashion.com/blog/wp-content/uploads/2014/07/Indiakidsfashionweek2-1.jpg");
        mNames.add("Kids");


//
//        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
//        mNames.add("Mahahual");
//
//        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
//        mNames.add("Frozen Lake");
//
//
//        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
//        mNames.add("White Sands Desert");
//
//        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
//        mNames.add("Austrailia");
//
//        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
//        mNames.add("Washington");




    }

    @Override
    public void onStart() {
        super.onStart();

        arrayList = new ArrayList<>();
        recyclerView1.setHasFixedSize(true);
        collectionReference = firebaseFirestore.collection("Items");
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                arrayList = new ArrayList<>();
                for(QueryDocumentSnapshot queryDocumentSnapshots1 : queryDocumentSnapshots)
                {
                    Item_data_model item_data_model = queryDocumentSnapshots1.toObject(Item_data_model.class);
                    if(!item_data_model.getUserId().equalsIgnoreCase(FirebaseAuth.getInstance().getUid()))
                    {
                        arrayList.add(item_data_model);
                    }
                }
               Show_Item_Adapter myAdapter = new Show_Item_Adapter(getActivity(),arrayList);
                recyclerView1.setLayoutManager(new GridLayoutManager(getContext(),2));
                recyclerView1.setAdapter(myAdapter);
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerView1.setVisibility(View.VISIBLE);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                Log.i("msl;fdmslf", "onFailure: ----------------------------- Fail");
            }
        });

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
