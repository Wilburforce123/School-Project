package com.example.localadmin.WillRecyclerView.model;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by localadmin on 17/01/17.
 */

public class WillData {

    private static final int[] numbers ={1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
    private static final String[] subNames={"Goalkeeper","Defender","Defender","Defender","Defender", "Midfielder", "Midfielder", "Midfielder", "Midfielder", "Striker", "Striker", "Substitute", "Substitute", "Substitute","Substitute", "Substitute", "Substitute", "Substitute"};
    private static final int[] icons = {android.R.drawable.ic_popup_reminder, android.R.drawable.ic_menu_add, android.R.drawable.ic_menu_delete};

    public static List<ListItem> getListData(){
        List<ListItem> data = new ArrayList<>();

        for (int x = 0; x < 18; x++){
            ListItem item = new ListItem();
            item.setImageResId(icons[1]);
            item.setNumber(numbers[x]);
            item.setSubName(subNames[x]);
            data.add(item);
        }

        return data;
    }

    public static List<ListItem> getSubsData(){
        List<ListItem> subsData = new ArrayList<>();

        for(int x = 12; x < 18; x++){
            ListItem item = new ListItem();
            item.setImageResId(icons[1]);
            item.setNumber(numbers[x]);
            item.setSubName(subNames[x]);
            subsData.add(item);
        }
        return subsData;
    }

}
