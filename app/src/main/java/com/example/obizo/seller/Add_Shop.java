package com.example.obizo.seller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.obizo.MainActivity;
import com.example.obizo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class Add_Shop extends AppCompatActivity {
    private CircleImageView circleImageView;
    ImageView camera;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    Button add;
    Uri profileUrl;
    TextInputEditText[] textInputEditTexts;
    private Uri uriProfileImage;
    ProgressDialog progressDialog;
    String shopId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_shop);

        textInputEditTexts = new TextInputEditText[]{findViewById(R.id.shop_name), findViewById(R.id.shop_address), findViewById(R.id.min), findViewById(R.id.raiting), findViewById(R.id.shop_phone), findViewById(R.id.upi), findViewById(R.id.email)};
        setTitle("Add Shop");
        circleImageView = findViewById(R.id.circleImageView);
        storageReference = FirebaseStorage.getInstance().getReference();

        add= findViewById(R.id.submit);
        camera= findViewById(R.id.camera);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();
            }
        });

        Intent intent = getIntent();
        shopId = intent.getStringExtra("shopId");
        if(shopId!=null)
        {
            editShop();
        }
        else
            addShop();


    }

    private void editShop() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Shopes").child(shopId);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {

                        Shop_Detais_Modal shop_detais_modal = dataSnapshot.getValue(Shop_Detais_Modal.class);
                        textInputEditTexts[0].setText(shop_detais_modal.getShop_Name());
                        textInputEditTexts[1].setText(shop_detais_modal.getShop_Address());
                        textInputEditTexts[2].setText(shop_detais_modal.getMin());
                        textInputEditTexts[3].setText(shop_detais_modal.getShop_rating());
                        textInputEditTexts[4].setText(shop_detais_modal.getContact_number());
                        textInputEditTexts[5].setText(shop_detais_modal.getUpi());
                    textInputEditTexts[6].setText(shop_detais_modal.getEmailid());

                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            boolean checked=false;
                            for (int i=0;i<7;i++)
                            {
                                if(textInputEditTexts[i].getText().toString().equalsIgnoreCase(""))
                                {
                                    textInputEditTexts[i].setError("Required");
                                    checked =false;
                                }
                                else
                                {
                                    if(uriProfileImage!=null)
                                        checked=true;
                                    else
                                        Toast.makeText(Add_Shop.this, "Select Photo", Toast.LENGTH_SHORT).show();
                                }

                            }
                            if (checked)
                            {
                                progressDialog.setTitle("Please wait a while");
                                progressDialog.show();
                                StorageReference d = FirebaseStorage.getInstance().getReference().child("images/Shopes/"+textInputEditTexts[4].getText().toString()+".jpg");
                                d.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                                        while(!uri.isComplete());
                                        profileUrl = uri.getResult();
                                        //profileUrl =task.getResult().getDownloadUrl();
                                        //profileUrl = taskSnapshot.getStorage().getDownloadUrl().getResult();

                                        databaseReference = FirebaseDatabase.getInstance().getReference().child("Shopes").child(shopId);
                                        Shop_Detais_Modal shop_detais_modal1 = new Shop_Detais_Modal(shopId,textInputEditTexts[0].getText().toString(),textInputEditTexts[1].getText().toString(),textInputEditTexts[3].getText().toString(),textInputEditTexts[2].getText().toString(),profileUrl.toString(),textInputEditTexts[4].getText().toString(),textInputEditTexts[5].getText().toString(),FirebaseAuth.getInstance().getUid(), textInputEditTexts[6].getText().toString());
                                        databaseReference.setValue(shop_detais_modal1);
                                        Toast.makeText(Add_Shop.this, "Shop Added", Toast.LENGTH_SHORT).show();
                                        progressDialog.cancel();
                                        Intent intent = new Intent(Add_Shop.this,Shops_Main.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Add_Shop.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.i("Fail", "onFailure:------------------> " + e.getMessage());
                                    }
                                });


                            }

                        }
                    });


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public  void addShop()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean checked=false;
                for (int i=0;i<7;i++)
                {
                    if(textInputEditTexts[i].getText().toString().equalsIgnoreCase(""))
                    {
                        textInputEditTexts[i].setError("Required");
                        checked =false;
                    }
                    else
                    {
                        if(uriProfileImage!=null)
                            checked=true;
                        else
                            Toast.makeText(Add_Shop.this, "Select Photo", Toast.LENGTH_SHORT).show();
                    }

                }
                if (checked)
                {
                    progressDialog.setTitle("Please wait a while");
                    progressDialog.show();
                    StorageReference d = FirebaseStorage.getInstance().getReference().child("images/Shopes/"+textInputEditTexts[4].getText().toString()+".jpg");
                    d.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            profileUrl = uri.getResult();
                            //profileUrl =task.getResult().getDownloadUrl();
                            //profileUrl = taskSnapshot.getStorage().getDownloadUrl().getResult();
                            databaseReference = FirebaseDatabase.getInstance().getReference().child("Shopes");
                            DatabaseReference databaseReference1 = databaseReference.push();
                            String s1 = databaseReference1.getKey();
                            databaseReference = FirebaseDatabase.getInstance().getReference().child("Shopes").child(s1);
                            Shop_Detais_Modal shop_detais_modal1 = new Shop_Detais_Modal(s1,textInputEditTexts[0].getText().toString(),textInputEditTexts[1].getText().toString(),textInputEditTexts[3].getText().toString(),textInputEditTexts[2].getText().toString(),profileUrl.toString(),textInputEditTexts[4].getText().toString(),textInputEditTexts[5].getText().toString(),FirebaseAuth.getInstance().getUid(), textInputEditTexts[6].getText().toString());
                            databaseReference1.setValue(shop_detais_modal1);
                            Toast.makeText(Add_Shop.this, "Shop Added", Toast.LENGTH_SHORT).show();
                            progressDialog.cancel();
                            Intent intent = new Intent(Add_Shop.this,Shops_Main.class);
                            startActivity(intent);
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Add_Shop.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.i("Fail", "onFailure:------------------> " + e.getMessage());
                        }
                    });


                }

            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropImage.ActivityResult result = CropImage.getActivityResult(data);
        if (resultCode == RESULT_OK) {
            Uri resultUri = result.getUri();
            uriProfileImage = result.getUri();
            circleImageView.setImageURI(resultUri);
            camera.setVisibility(View.GONE);
        } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
            Exception error = result.getError();
        }
    }
    private void showImageChooser() {
        CropImage.activity().start(Add_Shop.this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent  = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
