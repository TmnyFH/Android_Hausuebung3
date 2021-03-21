package com.example.android_hausbung3;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {


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
            btn_listItem_showDetails = itemView.findViewById(R.id.btn_listItem_showDetails);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mlistItemClickListener != null){
                int clickIndex = getAdapterPosition();
                MagicCard mc = mcItems.get(clickIndex);
                mlistItemClickListener.onLIstItemClick(mc);
            }
        }

        public void bind(int position) {
            tv_listItem_name.setText(mcItems.get(position).getName());
            tv_listItem_rarity.setText(mcItems.get(position).getRarity());
            //btn_listItem_showDetails.setBackgroundResource(mcItems.get(position).getColor());

        }
    }

}