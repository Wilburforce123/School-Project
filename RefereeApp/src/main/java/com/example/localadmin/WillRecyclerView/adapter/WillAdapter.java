package com.example.localadmin.WillRecyclerView.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.localadmin.WillRecyclerView.R;
import com.example.localadmin.WillRecyclerView.model.ListItem;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by localadmin on 17/01/17.
 */

public class WillAdapter extends RecyclerView.Adapter<WillAdapter.WillHolder> {

    private List<ListItem> listData;
    private LayoutInflater inflater;

    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback{
        void onItemClick(int p);
        void onIconClick(int p);

        void onYellowCardClick(int p);

        void onRedCardClick(int p);

    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback){
        this.itemClickCallback = itemClickCallback;
    }

    public WillAdapter (List<ListItem> listData, Context c){
        this.inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    @Override
    public WillHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new WillHolder(view);
    }

    //Setting the images when the results are changed.
    @Override
    public void onBindViewHolder(WillHolder holder, int position) {
        ListItem item = listData.get(position);
        String name = ("Player " + Integer.toString(item.getNumber()));
        holder.name.setText(name);
        holder.subName.setText(item.getSubName());
        if(item.isBooked()){
            holder.yellowCard.setImageResource(R.drawable.ic_yellow_card_given);
        } else{
            holder.yellowCard.setImageResource(R.drawable.ic_no_card_given);
        }
        if(item.isSentOff()){
            holder.redCard.setImageResource(R.drawable.ic_red_card_given);
        } else{
            holder.redCard.setImageResource(R.drawable.ic_no_card_given);
        }
        if(item.hasScored()){
            holder.goal.setImageResource(R.drawable.ic_goal_scored);
        } else{
            holder.goal.setImageResource(R.drawable.ic_no_goal_scored);
        }

    }

    public void setListData(ArrayList<ListItem>exerciseList){
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }

    public void setSubsData(ArrayList<ListItem>exerciseList){
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class WillHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;
        private TextView subName;
        private ImageView thumbnail;
        private ImageView yellowCard;
        private ImageView redCard;
        private ImageView goal;
        private View container;

        public WillHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.lbl_item_text);
            subName = (TextView)itemView.findViewById(R.id.lbl_item_sub_name);
            thumbnail = (ImageView)itemView.findViewById(R.id.im_item_icon);
            goal = (ImageView)itemView.findViewById(R.id.im_item_goal);
            yellowCard = (ImageView)itemView.findViewById(R.id.im_item_icon_secondary);
            yellowCard.setOnClickListener(this);
            redCard = (ImageView)itemView.findViewById(R.id.im_icon_red_card);
            redCard.setOnClickListener(this);
            container = itemView.findViewById(R.id.cont_item_root);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if(v.getId()== R.id.cont_item_root){
                itemClickCallback.onItemClick(getAdapterPosition());
            }else if(v.getId()==R.id.im_item_icon_secondary){
                itemClickCallback.onYellowCardClick(getAdapterPosition());
            }else if (v.getId()==R.id.im_icon_red_card){
                itemClickCallback.onRedCardClick(getAdapterPosition());
            }

        }



    }

}
