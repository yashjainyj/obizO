package com.example.obizo.Login;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.obizo.MainActivity;
import com.example.obizo.R;

public class User_Info extends AppCompatActivity {
    Button save;
    EditText fname,lname,ph,address,bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__info);
        fname=findViewById(R.id.edfirstname);
        lname=findViewById(R.id.edlastname);
        ph=findViewById(R.id.edphoneno);
        address=findViewById(R.id.edaddress);
        bio=findViewById(R.id.edbio);
        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Fname=fname.getText().toString();
                String Lname=lname.getText().toString();
                String Ph=ph.getText().toString();
                String Address=address.getText().toString();
                String Bio=bio.getText().toString();
                if(!Fname.equalsIgnoreCase("") && !Lname.equalsIgnoreCase("") && !Ph.equalsIgnoreCase("") && !Address.equalsIgnoreCase("") && !Bio.equalsIgnoreCase(""))
                {
                    Intent intent=new Intent(User_Info.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(User_Info.this, "Field Can't be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
