package com.example.android_hausbung3;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

interface IMyCallback {
    public void runCallback(ArrayList<MagicCard> magicCard);
}

public class WebRunnable implements Runnable {
    int cardNr = 0;
    int page = 0;
    private IMyCallback iMyCallback;
    ArrayList<MagicCard> magicCardList = new ArrayList<MagicCard>();

    URL url;

    WebRunnable(String url, IMyCallback iMyCallback ) {
        try {
            this.url = new URL(url);
            this.iMyCallback = iMyCallback;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");

            InputStream in = urlConn.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            Handler mainHandler = new Handler(Looper.getMainLooper());

            String out = "";

            if (scanner.hasNext()) {
                JSONObject root = new JSONObject(scanner.next());
                JSONArray cards = root.getJSONArray("cards");

                for (int i = 0; i < cards.length(); i++) {

                    cardNr++;
                    JSONObject card = cards.getJSONObject(i);
                    MagicCard mc = new MagicCard(card,cardNr);

                    magicCardList.add(mc);
                }
                iMyCallback.runCallback(magicCardList);
                Log.d("JSON Obj", root.toString());

/*
                String finalOut = out + "\n\n\n Page" + page;
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //TO-DO fill views
                    }
                });
*/


            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
