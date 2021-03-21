package com.example.android_hausbung3;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class MagicCard implements Parcelable {
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

    protected MagicCard(Parcel in) {
        name = in.readString();
        type = in.readString();
        rarity = in.readString();
        colors = in.readString();
        cardNr = in.readInt();
    }

    public static final Creator<MagicCard> CREATOR = new Creator<MagicCard>() {
        @Override
        public MagicCard createFromParcel(Parcel in) {
            return new MagicCard(in);
        }

        @Override
        public MagicCard[] newArray(int size) {
            return new MagicCard[size];
        }
    };

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

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public int getCardNr() {
        return cardNr;
    }

    public void setCardNr(int cardNr) {
        this.cardNr = cardNr;
    }

    public int getColor() {
        return 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
    }
}
