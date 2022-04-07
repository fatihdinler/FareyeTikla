package com.example.fareyiyakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class kolay extends AppCompatActivity {

    TextView timeText, scoreText;
    int score = 0;
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    ImageView[] imageArray = new ImageView[9];
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolay);

        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        img9 = findViewById(R.id.img9);

        imageArray = new ImageView[] {img1, img2, img3, img4, img5, img6, img7, img8, img9};

        hideImages();


        score = 0;

        new CountDownTimer(30000,1000) {

            @Override
            public void onTick(long l) {
                timeText.setText("Zaman: " + l/1000);
            }

            @Override
            public void onFinish() {

                timeText.setText("Zaman Bitti");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(kolay.this);
                alert.setMessage("Skorunuz : " + score + "\n"
                        + "Oyun'a tekrardan başlamak ister misiniz?");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(kolay.this, "Yeni oyun için giriş ekranına dönün.", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();

    }

    public void increaseScore (View view) {
        score++;
        //score = score + 1;
        scoreText.setText("Skor: " + score);
    }

    public void hideImages() {

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);

            }
        };


        handler.post(runnable);


    }

}