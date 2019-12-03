package com.example.obizo.NavigationView;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import com.example.obizo.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class Navigation_logout_Fragment extends Fragment {


    public Navigation_logout_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.feedback, container, false);
        Button send,call;
        final RatingBar ratingBar;
        final TextInputEditText textInputEditText;
        send = view.findViewById(R.id.btnSubmit);
        call = view.findViewById(R.id.calll);
        textInputEditText = view.findViewById(R.id.feedback);
        ratingBar = view.findViewById(R.id.ratingBar);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"sagar.singh9759@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT,"Query");
                intent.putExtra(Intent.EXTRA_TEXT,textInputEditText.getText().toString() + "\nRaiting " +  ratingBar.getRating());
                startActivity(intent.createChooser(intent,"Click One of this to Send"));

            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8368149754"));
                startActivity(intent);
            }
        });

        return view;
    }

}
