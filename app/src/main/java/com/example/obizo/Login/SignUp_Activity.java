package com.example.obizo.Login;

import android.content.Intent;
import android.os.Build;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.obizo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp_Activity extends AppCompatActivity {
    TextView SignIn;
    Toolbar toolbar;
    EditText username,email,password,cpassword;
    Button register;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);
        username=findViewById(R.id.eduser);
        email=findViewById(R.id.edemail);
        password=findViewById(R.id.edpassword);
        cpassword=findViewById(R.id.edcnfrmpass);
        register=findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User=username.getText().toString();
                String Email=email.getText().toString();
                String Pass=password.getText().toString();
                String Cpass=cpassword.getText().toString();
                if(!Pass.equalsIgnoreCase("") && !Cpass.equalsIgnoreCase("")&& !User.equalsIgnoreCase("") && !Email.equalsIgnoreCase(""))
                {
                    if(Pass.equals(Cpass))
                    {
                        mAuth.createUserWithEmailAndPassword(Email,Pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(SignUp_Activity.this, "Signup successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(SignUp_Activity.this,User_Info.class);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUp_Activity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else
                        Toast.makeText(SignUp_Activity.this, "Password dosen't match", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SignUp_Activity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });
        toolbar=findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(SignUp_Activity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            });
        }
        SignIn=findViewById(R.id.signin);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp_Activity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
