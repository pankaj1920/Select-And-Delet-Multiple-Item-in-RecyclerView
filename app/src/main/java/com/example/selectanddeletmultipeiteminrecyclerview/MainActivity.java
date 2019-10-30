package com.example.selectanddeletmultipeiteminrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    androidx.appcompat.widget.Toolbar toolbar;
    Context context;
    TextView CounterTextView;
    boolean is_in_Action_mode = false;
    ItemRecyclerAdapter adapter;
    ArrayList<Item_Model_Class> itemList;

    ArrayList<Item_Model_Class> selectedItem = new ArrayList<>();
    int Count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CounterTextView = (TextView) findViewById(R.id.CounterTextView);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //Adding item in Recycler View
        itemList = new ArrayList<>();
        itemList.add(new Item_Model_Class(R.drawable.profile, "The Monk Who Sold His Ferrari", "Robin Sharma"));
        itemList.add(new Item_Model_Class(R.drawable.profile, "Who Will Cry When You Die?", "Robin Sharma"));
        itemList.add(new Item_Model_Class(R.drawable.profile, "The 5 AM Club", "Robin Sharma"));
        itemList.add(new Item_Model_Class(R.drawable.profile, "The Greatness Guide", "Robin Sharma"));
        itemList.add(new Item_Model_Class(R.drawable.profile, "The Greatness Guide 2", "Robin Sharma"));
        itemList.add(new Item_Model_Class(R.drawable.profile, "Little Black Book for Stunning Success", "Robin Sharma"));
        itemList.add(new Item_Model_Class(R.drawable.profile, "The Leader Who Had No Title", "Robin Sharma"));
        itemList.add(new Item_Model_Class(R.drawable.profile, "Family Wisdom From The Monk Who ", "Robin Sharma"));
        itemList.add(new Item_Model_Class(R.drawable.profile, "The Saint, The Surfer & The CEO", "Robin Sharma"));
        itemList.add(new Item_Model_Class(R.drawable.profile, "Leadership Wisdom From The Monk Who Sold His Ferrari", "Robin Sharma"));
        itemList.add(new Item_Model_Class(R.drawable.profile, "Discover Your Destiny With The Monk Who Sold His Ferrari", "Robin Sharma"));
        itemList.add(new Item_Model_Class(R.drawable.profile, "Daily Wisdom From The Monk Who Sold His Ferrari", "Robin Sharma"));

        adapter = new ItemRecyclerAdapter(itemList, MainActivity.this, context);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.normal_menu, menu);
        return true;
    }


    @Override
    public boolean onLongClick(View v) {

        // here we are removing the menu from toolbar
        toolbar.getMenu().clear();

        // add contextual menu in toolbar
        toolbar.inflateMenu(R.menu.contextual_menu);
        CounterTextView.setVisibility(View.VISIBLE);

        is_in_Action_mode = true;

        adapter.notifyDataSetChanged();

        // setting back button on toolbar when Items are long Clicked
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return true;
    }


    public void prepareSelection(View view, int position) {

        // here if user select the checkbox we have to store n arrayList and
        // if user unChecked the Checkbox the we have to remove item from array lsit

        // here we have to check wheateher user select or unSelect hte check box
        if (((CheckBox) view).isChecked()) {

            //if user select the checkBox in that case we have to save the selection to arrayList
            selectedItem.add(itemList.get(position));
            Count = Count + 1;
            updateCounter(Count);

        } else {
            // if user unSelect the item from checkbox we have to remove item from array list
            selectedItem.remove(itemList.get(position));

            // now we have to update Count
            Count = Count - 1;
            updateCounter(Count);
        }
    }

    public void updateCounter(int count) {

        if (Count == 0) {

            CounterTextView.setText("0 item is Selected");
        } else {

            CounterTextView.setText(Count + " item Selected");
        }
    }


    // when user click icon on toolbar this method will invoke
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (item.getItemId() == R.id.delet) {


            //now we have to remove selected item from adapter
            ItemRecyclerAdapter itemRecyclerAdapter = (ItemRecyclerAdapter) adapter;

            itemRecyclerAdapter.updateAdapter(selectedItem);

            clearActionMode();

        } else if (item.getItemId() == android.R.id.home) { // back button on toolbar is pressed

            clearActionMode();
            adapter.notifyDataSetChanged();
        }

        return true;
    }

    public void clearActionMode() {

        // here first we have to remove the checkbox selectiun
        is_in_Action_mode = false;

        // here we removing menu from toolbarr
        toolbar.getMenu().clear();

        // now we have to inflate the normal toolbar
        toolbar.inflateMenu(R.menu.normal_menu);

        // now we have to remove back button fromtoolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //now we have to hide TextView From Toolbar
        CounterTextView.setVisibility(View.GONE);

        // now we have to update the text on text View

        CounterTextView.setText("0 item selected");

        // we we have to clear the counter variable so next time again it start from 0

        Count = 0;

        // now we have to clear the Selection list
        selectedItem.clear();
    }

    @Override
    public void onBackPressed() {

        // if Contextual action mode is enable and back is pressed then Contetual ActionMode is closed
        if (is_in_Action_mode) {

            clearActionMode();
            adapter.notifyDataSetChanged();

        } else {

            // if Contextual action bar is not enable and back is pressed then app will exit
            super.onBackPressed();
        }
    }
}
