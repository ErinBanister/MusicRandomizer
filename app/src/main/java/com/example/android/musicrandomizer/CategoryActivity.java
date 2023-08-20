package com.example.android.musicrandomizer;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list);

        /** Create an array CategoryView with the following elements:
         *  1. Name of Category
         *  2. Drawable Location
         *  3. Class to Run when clicked
         */

        ArrayList<CategoryView> catArray = new ArrayList<>();
        catArray.add(new CategoryView(R.string.allSongs, R.drawable.boombox, SongActivity.class));
        catArray.add(new CategoryView(R.string.category, R.drawable.note, SelectActivity.class));
        catArray.add(new CategoryView(R.string.year, R.drawable.cassette, SelectActivity.class));
        catArray.add(new CategoryView(R.string.rating, R.drawable.sorting, SelectActivity.class));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        categoryAdapter adapter = new categoryAdapter(this, catArray);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        GridView gridview = findViewById(R.id.categoryList);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        gridview.invalidate();
        gridview.setAdapter(adapter);

    }
}
//All Icons Courtesy of BomSymbols from the Noun Project