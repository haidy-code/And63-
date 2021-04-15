package com.omar.and63_;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import static android.Manifest.permission.CALL_PHONE;

public class MainActivity extends AppCompatActivity {

    Button openBtn;
    ImageView pickedPhotoIv;

    private final int REQUEST_CODE = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openBtn = findViewById(R.id.open_btn);
        pickedPhotoIv = findViewById(R.id.photo_iv);

        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE);


                /*// Android 6+   API 23+   ( runtime permission )

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:01146401103"));

                if (ContextCompat.checkSelfPermission(MainActivity.this, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                    Log.d("omar", "permission is previously Granted");

                    startActivity(intent);

                } else {
                    checkForPermission();
                }*/


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {



            Uri selectedImage = data.getData();

            Glide.with(MainActivity.this).load(selectedImage).into(pickedPhotoIv);




        }

    }

    public void checkForPermission() {

        // SDK_INT <<< current device
        if (Build.VERSION.SDK_INT >= 23) {

            if (checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                //

                Log.d("omar", "permission is previously Granted");

            } else {

                Log.d("omar", "permission request");
                ActivityCompat.requestPermissions(this, new String[]{CALL_PHONE}, 1);
            }

        }

    }


}