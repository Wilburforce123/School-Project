package com.example.localadmin.WillRecyclerView.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.localadmin.WillRecyclerView.R;
import com.example.localadmin.WillRecyclerView.adapter.WillAdapter;
import com.example.localadmin.WillRecyclerView.model.WillData;

import java.util.ArrayList;

import static com.example.localadmin.WillRecyclerView.R.id.lbl_player;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";

    //Variables are established.
    int goalsScored;
    boolean hasScored = false;
    int player_num;
    int subsLeft;
    private RecyclerView recView;
    private WillAdapter adapter;
    private ArrayList listData;

    //Data of the intent is imported into the class to be used.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);


        player_num = extras.getInt("player_number");
        System.out.println(player_num);
        String playerName = ("Player " + String.valueOf(player_num));
        goalsScored = extras.getInt("goals_scored");
        subsLeft = extras.getInt("subsLeft");
        ((TextView)findViewById(R.id.goals_scored_text)).setText(String.valueOf(goalsScored));
        ((TextView)findViewById(R.id.lbl_player)).setText(playerName);
        ((TextView)findViewById(R.id.lbl_position)).setText(extras.getString(EXTRA_ATTR));


    }

    //Method for when plus button is pressed. Number in textview will plus one when pressed.
    public void addOne(View v){
        goalsScored += 1;
        EditText goalsScoredVariable = (EditText) findViewById(R.id.goals_scored_text);
        goalsScoredVariable.setText(String.valueOf(goalsScored));
        hasScored = true;

    }

    //Method for when minus button is pressed. Number in textview will minus one when value >= 0.
    public void minusOne(View v){
        if (goalsScored > 0) {
            goalsScored -= 1;
            EditText goalsScoredVariable = (EditText) findViewById(R.id.goals_scored_text);
            goalsScoredVariable.setText(String.valueOf(goalsScored));
            if (goalsScored == 0){
                hasScored = false;
            }
        }
    }

    //Method for when the save button is pressed.
    public void onSave(View v){
        if(goalsScored > 0){
            hasScored = true;
        }
        Intent i = new Intent();
        i.putExtra("player_number", player_num);
        i.putExtra("hasScoredReturned", hasScored);
        i.putExtra("goals_scored", goalsScored);
        setResult(RESULT_OK, i);
        finish();
    }

    //Method for when the cancel button is pressed.
    public void onCancel(View v){
        Intent i = new Intent();
        setResult(Activity.RESULT_CANCELED, i);
        finish();
    }

    //Method for when a player is substituted.
    public void onSub(View v){
        if (subsLeft < 1){
            Toast.makeText(getApplicationContext(), "Max. Number of subs used!", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(this, ListActivity.class);
            setContentView(R.layout.activity_list);


            listData = (ArrayList) WillData.getSubsData();


            recView = (RecyclerView)findViewById(R.id.rec_list);
            recView.setHasFixedSize(true);
            recView.setLayoutManager(new LinearLayoutManager(this));


            adapter = new WillAdapter(WillData.getSubsData(), this);

            recView.setAdapter(adapter);
            adapter.setItemClickCallback((WillAdapter.ItemClickCallback) this);



        }
    }


    @Override
    public void onClick(View v) {

    }



}
