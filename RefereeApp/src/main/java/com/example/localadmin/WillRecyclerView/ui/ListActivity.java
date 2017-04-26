package com.example.localadmin.WillRecyclerView.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.localadmin.WillRecyclerView.R;
import com.example.localadmin.WillRecyclerView.adapter.WillAdapter;
import com.example.localadmin.WillRecyclerView.model.ListItem;
import com.example.localadmin.WillRecyclerView.model.WillData;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static com.example.localadmin.WillRecyclerView.R.string.goals;
import static com.example.localadmin.WillRecyclerView.R.string.hasScored;

public class ListActivity extends AppCompatActivity implements WillAdapter.ItemClickCallback {

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";

    //Creation of the basic structure of the recyclerview.
    private RecyclerView recView;
    private WillAdapter adapter;
    private ArrayList listData;

    //Creation of variables used within the class.
    boolean returned = false;
    int player_num = 0;
    int goalsScored;
    int subsLeft = 3;


    //Physical creation of the items in the list.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        listData = (ArrayList) WillData.getListData();


        recView = (RecyclerView)findViewById(R.id.rec_list);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new WillAdapter(WillData.getListData(), this);

        recView.setAdapter(adapter);
        adapter.setItemClickCallback(this);
    }


    //When the physical item in the list is clicked, create an intent expecting a result from DetailActivity.java.
    @Override
    public void onItemClick(int p) {
        ListItem item = (ListItem) listData.get(p);

        if (item.isSentOff()){
            Toast.makeText(getApplicationContext(), "Cannot access data, player has been sent off!", Toast.LENGTH_LONG).show();
        }else {
            Intent i = new Intent(this, DetailActivity.class);
            Bundle extras = new Bundle();
            extras.putInt("goals_scored", item.getGoalsScored());
            extras.putInt("player_number", item.getNumber());
            extras.putString(EXTRA_ATTR, item.getSubName());
            extras.putInt("subsLeft", subsLeft);
            i.putExtra(BUNDLE_EXTRAS, extras);
            startActivityForResult(i, 1);
        }
    }


    //Taking the input from DetailActivity.java and interpreting the results.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                boolean hasScoredReturned = data.getBooleanExtra("hasScoredReturned", false);
                int player_name = data.getIntExtra("player_number", player_num);
                goalsScored = data.getIntExtra("goals_scored", goalsScored);
                System.out.println(player_name);
                returned = true;

                ListItem item = (ListItem)listData.get(player_name-1);
                if (hasScoredReturned) {
                    item.setScored(true);
                } else {
                    item.setScored(false);
                }
                adapter.setListData(listData);
                adapter.notifyDataSetChanged();

                item.setGoalsScored(goalsScored);

            }
            if(resultCode == Activity.RESULT_CANCELED){

            }
        }
    }


    //When the icon of the item is clicked. This can be empty as it does not currently have a purpose in my program.
    public void onIconClick(int p) {

    }


    //When the yellow card is clicked.
    public void onYellowCardClick(int p) {
        ListItem item = (ListItem)listData.get(p);

        if(item.isBooked()){
            item.setBooked(false);
        }else{
            item.setBooked(true);
        }

        adapter.setListData(listData);
        adapter.notifyDataSetChanged();

    }


    //When the red card is clicked.
    public void onRedCardClick(int p) {
        ListItem item = (ListItem) listData.get(p);

        if (item.isSentOff()) {
            item.setSentOff(false);
        } else {
            item.setSentOff(true);
        }
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();

    }

}
