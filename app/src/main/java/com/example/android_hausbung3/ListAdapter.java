package com.example.android_hausbung3;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.telephony.IccOpenLogicalChannelResponse;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {
    LinearLayout ll_item;

    interface ListItemClickListener {
        void onLIstItemClick(MagicCard mc);
    }

    private Context mContext;
    private ListItemClickListener mlistItemClickListener;
    private List<MagicCard> mcItems;

    public ListAdapter(List<MagicCard> mcl) {
        this.mcItems = mcl;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        this.mContext = context;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item, parent, false);


        return new ItemViewHolder(view);
    }

    //Binded beim scrollen
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(position);
        Log.d("BINDLOG", String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return (mcItems == null) ? 0 : mcItems.size();
    }

    public void setOnLIstItemClickListener(ListItemClickListener listItemClickListener) {
        mlistItemClickListener = listItemClickListener;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_listItem_name, tv_listItem_rarity;
        Button btn_listItem_showDetails;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_listItem_name = itemView.findViewById(R.id.tv_listItem_name);
            tv_listItem_rarity = itemView.findViewById(R.id.tv_listItem_rarity);
            ll_item = itemView.findViewById(R.id.ll_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mlistItemClickListener != null) {
                int clickIndex = getAdapterPosition();
                MagicCard mc = mcItems.get(clickIndex);
                mlistItemClickListener.onLIstItemClick(mc);


            }
        }

        public void bind(int position) {
            tv_listItem_name.setText(mcItems.get(position).getName());
            tv_listItem_rarity.setText(mcItems.get(position).getRarity());
            //btn_listItem_showDetails.setBackgroundResource(mcItems.get(position).getColor());


            int colorFromCard = 0;
            switch (mcItems.get(position).colorsAL.get(0))
            {
                case "White":
                    colorFromCard = R.color.White;
                    break;
                case "Black":
                    colorFromCard = R.color.Black;
                    break;
                case "Blue":
                    colorFromCard = R.color.Blue;
                    break;
                case "Red":
                    colorFromCard = R.color.Red;
                    break;
                case "Green":
                    colorFromCard = R.color.Green;
                    break;
                default:
                    colorFromCard = R.color.Violet;
                    break;
            }
            ll_item.setBackgroundResource(colorFromCard);

        }

        private void setRandomColors(){
            ll_item = itemView.findViewById(R.id.ll_item);
            Random rnd = new Random(System.currentTimeMillis());

            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            ll_item.setBackgroundColor(color);

            color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            tv_listItem_name.setBackgroundColor(color);

            color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            tv_listItem_name.setTextColor(color);
        }
    }

}