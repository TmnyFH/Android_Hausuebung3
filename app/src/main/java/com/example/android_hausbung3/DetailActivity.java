package com.example.android_hausbung3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class DetailActivity extends AppCompatActivity {

    public static final String KEY_MAGIC_CARD_TEXT = "TEXT";
        ImageView image;
        Bitmap bmp;
        Handler mainHaindler;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail);
            image = findViewById(R.id.card_img);

            MagicCard mc = getIntent().getParcelableExtra(KEY_MAGIC_CARD_TEXT);
            Toast.makeText(DetailActivity.this, mc.getName(), Toast.LENGTH_SHORT).show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream in = new java.net.URL("https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130550&type=card").openStream();
                        bmp = BitmapFactory.decodeStream(in);

                        mainHaindler = new Handler(Looper.getMainLooper());
                        mainHaindler.post( () -> image.setImageBitmap(bmp) );

                    } catch (IOException e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }
                }
            }).start();





    }
}