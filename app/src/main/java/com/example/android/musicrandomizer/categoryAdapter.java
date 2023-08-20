package com.example.android.musicrandomizer;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class categoryAdapter extends ArrayAdapter<CategoryView> {
    public categoryAdapter(Activity context, ArrayList<CategoryView> catArray) {
        super(context, 0, catArray);
    }

    /**
     * overrides the getView method in the ArrayAdapter class, and attaches it to the
     * gridItemView of cat_list_item.xml
     */
    @Override
    @NonNull
    public View getView(final int position, View convertView, ViewGroup parent) {
        View gridItemView = convertView;
        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(
                    getContext()).inflate(
                    R.layout.cat_list_item, parent, false);
        }
        /* Get the category object's position and populates the xml */
        final CategoryView currentCat = getItem(position);

        /*Set the catLayoutTxt TextView to the current items category name in array**/
        TextView categoryNameTextView = gridItemView.findViewById(R.id.catLayoutTxt);
        categoryNameTextView.setText(currentCat.getCategoryName());

        /*set teh CatImageView ImageView to the current items' category image in array**/
        ImageView catImageView = gridItemView.findViewById(R.id.catLayoutImg);
        catImageView.setImageResource(currentCat.getImageResourceId());

        /*
          Create onClickListener for each category and opens the appropriate activity
          */
        LinearLayout catButt = gridItemView.findViewById(R.id.catLayoutMain);
        catButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCat = new Intent(v.getContext(), currentCat.getNewActivity());
                GlobalVars gv = GlobalVars.getInstance();
                gv.setCategoryPosition(position);
                v.getContext().startActivity(openCat);

            }
        });
        return gridItemView;
    }

}