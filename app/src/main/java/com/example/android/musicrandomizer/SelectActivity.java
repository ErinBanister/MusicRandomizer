package com.example.android.musicrandomizer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class SelectActivity extends AppCompatActivity {
    GlobalVars gv = GlobalVars.getInstance();
    public int value = gv.getCategoryPosition();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        createSelectAdapterView();
        //set action bar to xml field toolbar
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    public void onResume() {
        super.onResume();
        ListView listview = findViewById(R.id.selectListView);
        listview.invalidate();
        createSelectAdapterView();
        //set action bar to xml field toolbar
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void createSelectAdapterView() {
        /** Create an array SelectView with the following elements:
         *  1. Name of Category
         *  2. Number of Songs in Category
         *  3. Total Length of songs in category
         */
        BufferedReader reader = null;
        ArrayList<SelectView> selList = new ArrayList<>();
        //determine which click sent the intent, create ArrayList based on value
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            value = bundle.getInt("position");
        }
        try {
            /*based on the click value entering the activity, select the correct source material for the SelectView array*/
            if (value == 1) {
                reader = new BufferedReader(
                        new InputStreamReader(getAssets().open("categorylist.txt")));
            } else if (value == 2) {
                reader = new BufferedReader(
                        new InputStreamReader(getAssets().open("decadelist.txt")));
            } else if (value == 3) {
                reader = new BufferedReader(
                        new InputStreamReader(getAssets().open("ratinglist.txt")));
            }

            if (reader != null) {
                String line = reader.readLine();
                while (line != null) {
                    String tokens[] = line.split(",");

                     /*if data is coming from the ratinglist.txt file, prefix the title with "Rating: "
                     for reading clarity*/

                    if (value == 3) {
                        selList.add(new SelectView("Rating: " + tokens[0], tokens[1], tokens[2]));
                    } else {
                        selList.add(new SelectView(tokens[0], tokens[1], tokens[2]));
                    }
                    /*move to next line */
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("PROBLEM ReadingFile : ", e.getMessage());
        }
        // Create an {@link WordAdapter}, whose data source is a list of categories.
        selectAdapter adapter = new selectAdapter(this, selList);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called selectListView, which is declared in the
        // xml layout file.
        final ListView listview = findViewById(R.id.selectListView);

        // Make the {@link ListView} use the adapter created above, so that the
        // {@link ListView} will display list items for each item in the list.
        listview.setAdapter(adapter);
    }
}
//All Icons Courtesy of BomSymbols from the Noun Project