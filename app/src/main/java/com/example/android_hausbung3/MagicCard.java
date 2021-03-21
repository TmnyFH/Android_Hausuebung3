package com.example.android_hausbung3;

import org.json.JSONException;
import org.json.JSONObject;

public class MagicCard {
    String name, type, rarity, colors;
    int cardNr;

    MagicCard(JSONObject card, int cardNr){
        try {
            this.name = card.getString("name");
            this.type = card.getString("type");
            this.rarity = card.getString("rarity");
            this.colors = card.getString("colors");
            this.cardNr = cardNr;
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String getMessageData() {
        String msg =
                "card number : " + this.cardNr + "\n" +
                        "name: " + this.name + "\n" +
                        "type: " + this.type + "\n" +
                        "rarity: " + this.rarity + "\n" +
                        "colors: " + this.colors + "\n" +
                        "\n\n";

        return  msg;
    }
}
