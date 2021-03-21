package com.example.android_hausbung3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Handler mainHandler = new Handler(Looper.getMainLooper());
    Context context;
    Button btnLoadCards, btnPage;
    int cardNr = 0;
    int page = 0;
    ArrayList<MagicCard> magicCardList = new ArrayList<MagicCard>();
    private ListAdapter mAdapter;
    private  RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init View
        //btnLoadCards = findViewById(R.id.btn_load);
        context = getApplicationContext();
        list = findViewById(R.id.rv_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        list.setHasFixedSize(true);

        loadWebResult();


/*        btnLoadCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btnLoadCards.setEnabled(false);
                //setAndRestoreButtons();
            }
        });*/

        mAdapter.setOnLIstItemClickListener(new ListAdapter.ListItemClickListener() {
            @Override
            public void onLIstItemClick(MagicCard mc) {
                Toast.makeText(MainActivity.this, mc.getName(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra(DetailActivity.KEY_MAGIC_CARD_TEXT, mc);
                startActivity(i);
            }
        });





    }

    private void setAndRestoreButtons(){
        //btnLoadCards.setEnabled(false);
        //btnLoadCards.setText("Cards loaded");
        String pageNrText = "Page " + page + " - Next Page";
        //btnPage.setText(pageNrText);
        //btnPage.setEnabled(true);
    }

    private void updateGui(){

    }

    private void loadWebResult() {
        WebRunnable webRunnable = new WebRunnable("https://api.magicthegathering.io/v1/cards/?page="+page,  magicCards -> {
            mainHandler.post(() -> {

                addCard(magicCards);

                list.setAdapter(mAdapter);
                Log.d("magicCardList", magicCardList.size()+"");
            });
        });

        Toast.makeText(context,"Loading Cards", Toast.LENGTH_SHORT).show();
        mAdapter = new ListAdapter(magicCardList);
        new Thread(webRunnable).start();

    }

    public void addCard(ArrayList<MagicCard> mcl){
        this.magicCardList.clear();
        this.magicCardList.addAll(mcl);
    }


}