package com.example.mytextrecapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView imgView;
    private TextView txtView;
    private Button selectImgBtn, DetectTxtBtn;
    private Bitmap imgBitmap;

    private static final int RESULT_LOAD_IMG=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectImgBtn=findViewById(R.id.btnSelect);
        DetectTxtBtn=findViewById(R.id.btnDetect);
        imgView=findViewById(R.id.imageView);
        txtView=findViewById(R.id.textViewResult);

        selectImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImg(v);
                txtView.setText("");
            }
        });

        DetectTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                analizeTxtFromImg();
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            final Uri imgUri = data.getData();
            final InputStream isImg;
            try {
                isImg = getContentResolver().openInputStream(imgUri);
                final Bitmap selectImgBm = BitmapFactory.decodeStream(isImg);
                imgView.setImageBitmap(selectImgBm);
            } catch (FileNotFoundException e) {
                Toast.makeText(this, "you haven't pic an image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void analizeTxtFromImg()
    {
        imgBitmap = ((BitmapDrawable) imgView.getDrawable()).getBitmap();
        Bitmap resize = Bitmap.createScaledBitmap(imgBitmap,224, 224, true);

        FirebaseVision vision = FirebaseVision.getInstance();
        FirebaseVisionTextDetector detector = vision.getInstance().getVisionTextDetector();
        FirebaseVisionImage fbVisionImg=FirebaseVisionImage.fromBitmap(imgBitmap);

        detector.detectInImage(fbVisionImg).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {

            @Override
            public void onSuccess(FirebaseVisionText fbVText) {
                Log.d(MainActivity.this.toString(), "/135) success!");
                displayResult(fbVText);
            }
        }).addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error: ", e.getMessage());
            }
        });

    }

    public void selectImg(View view) {

        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker, RESULT_LOAD_IMG);
    }


    public void displayResult(FirebaseVisionText fbVText) {

        List<FirebaseVisionText.Block> blockList = fbVText.getBlocks();
        if (blockList.size() == 0) {
            Toast.makeText(this, "no Text was found", Toast.LENGTH_SHORT).show();
        } else {
            for ( FirebaseVisionText.Block block : fbVText.getBlocks() ) {

                txtView.append(block.getText() + "\n");
            }
        }
    }

}