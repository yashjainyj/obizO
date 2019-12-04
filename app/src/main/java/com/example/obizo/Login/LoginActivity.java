package com.example.obizo.Login;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.obizo.MainActivity;
import com.example.obizo.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    TextView forgetpass;
    TextInputEditText email,password;
    Button SignIn,signInwithOthers;
    FirebaseAuth mAuth;
    List<AuthUI.IdpConfig> providers;

    public static final int MY_REQUEST_CODE = 1222;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(LoginActivity.this);
        email = findViewById(R.id.username);
        forgetpass = findViewById(R.id.forgetpass);

        password = findViewById(R.id.etPassword);
        SignIn=findViewById(R.id.signin);
        mAuth=FirebaseAuth.getInstance();
        signInwithOthers = findViewById(R.id.signinwithOthers);
        //SignUp=findViewById(R.id.signup);
        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()

        );
//        SignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(LoginActivity.this,SignUp_Activity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(LoginActivity.this, "Please enter email address", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this, "Reset Mail has been send to your email address", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString();
                String Pass=password.getText().toString();
                if(!Pass.equalsIgnoreCase("") && !Email.equalsIgnoreCase(""))
                {
                    mAuth.signInWithEmailAndPassword(Email,Pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure( @Nullable Exception e) {
                            Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                    Toast.makeText(LoginActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
            }
        });
        signInwithOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignInOption();
            }
        });
    }
    private void showSignInOption() {
        startActivityForResult(AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.AppTheme)
                        .build()
                ,MY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==MY_REQUEST_CODE)
        {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(RESULT_OK ==resultCode)
            {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this, firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
            }
            else if (requestCode==RESULT_CANCELED)
            {
               Toast.makeText(LoginActivity.this, response.getError()+"", Toast.LENGTH_SHORT).show();
            }
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null)
        {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finishAffinity();
        }
    }

}
