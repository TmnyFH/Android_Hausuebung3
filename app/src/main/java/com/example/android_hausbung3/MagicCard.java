package com.example.android_hausbung3;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MagicCard implements Parcelable {
    String name, type, rarity, imageUrl, text, set, power, toughness;
    JSONArray colors;
    ArrayList<String> colorsAL = new ArrayList<String>();
    int cardNr;

    MagicCard(JSONObject card, int cardNr){
        try {

            this.name = card.getString("name");
            this.type = card.getString("type");
            this.rarity = card.getString("rarity");

            if(!card.isNull("set"))
                this.set = card.getString("set");

            if(!card.isNull("power"))
                this.power = card.getString("power");

            if(!card.isNull("toughness"))
                this.toughness = card.getString("toughness");

            this.cardNr = cardNr;
            if(!card.isNull("imageUrl"))
                this.imageUrl = card.getString("imageUrl");

            if(!card.isNull("text"))
                this.text = card.getString("text");

            this.colors = card.getJSONArray("colors");
            if (this.colors != null) {
                for (int i=0;i<this.colors.length();i++){
                    colorsAL.add(this.colors.getString(i));
                }
            }
            else{
                colorsAL.add("No color");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    protected MagicCard(Parcel in) {
        name = in.readString();
        type = in.readString();
        rarity = in.readString();
        imageUrl = in.readString();
        text = in.readString();
        colorsAL = in.createStringArrayList();
        cardNr = in.readInt();
    }

    public String getType() {
        return type;
    }

    public String getRarity() {
        return rarity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getText() {
        return text;
    }

    public JSONArray getColors() {
        return colors;
    }

    public ArrayList<String> getColorsAL() {
        return colorsAL;
    }

    public int getCardNr() {
        return cardNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(rarity);
        dest.writeString(imageUrl);
        dest.writeString(text);
        dest.writeStringList(colorsAL);
        dest.writeInt(cardNr);
    }
}
