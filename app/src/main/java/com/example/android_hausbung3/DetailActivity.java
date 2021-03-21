package com.example.android_hausbung3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;

public class DetailActivity extends AppCompatActivity {

    public static final String KEY_MAGIC_CARD_TEXT = "TEXT";
        ImageView image;
        Bitmap bmp;
        Handler mainHaindler;
        TextView rarity, text, color, set, power, toughness;
        Button btnDetails;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail);
            image = findViewById(R.id.card_img);
            rarity = findViewById(R.id.tv_rarity);
            text = findViewById(R.id.tv_text);
            btnDetails = findViewById(R.id.btn_Details);
            color = findViewById(R.id.tv_color);
            set = findViewById(R.id.tv_set);
            power = findViewById(R.id.tv_power);
            toughness = findViewById(R.id.tv_toughness);


            MagicCard mc = getIntent().getParcelableExtra(KEY_MAGIC_CARD_TEXT);



            rarity.setText("Rarity: " + mc.rarity + "\n");
            text.setText("Text: " + mc.text);
            color.setText("Color: " + mc.colorsAL.get(0));
            set.setText("Set: " + mc.set);
            power.setText("Power: " + mc.power);
            toughness.setText("Toughness: " + mc.toughness);


            btnDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(DetailActivity.this, "Typ: " +  mc.getType(), Toast.LENGTH_SHORT).show();
                }
            });


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String imgLinkHTTP = mc.imageUrl;
                        String imgLink = imgLinkHTTP.replace("http", "https");
                        InputStream in = new java.net.URL(imgLink).openStream();

                        bmp = BitmapFactory.decodeStream(in);

                        mainHaindler = new Handler(Looper.getMainLooper());
                        mainHaindler.post( () -> image.setImageBitmap(bmp) );

                    } catch (IOException e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }
                    catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }
                }
            }).start();





    }
}