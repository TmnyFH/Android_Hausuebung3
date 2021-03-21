package com.example.android_hausbung3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    Context context;
    Button loadCards;
    int cardNr = 0;
    int page = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadCards = findViewById(R.id.btn_load);
        context = getApplicationContext();

    }


    private void loadWebResult() {
        WebRunnable webRunnable = new WebRunnable("https://api.magicthegathering.io/v1/cards/?page="+page);
        new Thread(webRunnable).start();
        Toast.makeText(context,"Loading Cards", Toast.LENGTH_SHORT).show();
    }




}