package com.example.android.musicrandomizer;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class selectAdapter extends ArrayAdapter<SelectView> {
    /* Provides a view for the adapter view
     */

    public selectAdapter(Activity context, ArrayList<SelectView> catArray) {
        super(context, 0, catArray);
    }

    /**
     * overrides the getView method in the ArrayAdapter class, and attaches it to the
     * selectItemView of select_list_item.xml
     */
    @Override
    @NonNull
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.select_list_item, parent, false);
        }
        /** Get the select object's position and populates the xml */
        final SelectView currentSel = getItem(position);

        /**Set the selectName TextView to the current items' category name in array**/
        TextView selectNameTextView = listItemView.findViewById(R.id.selectName);
        selectNameTextView.setText(currentSel.getCategoryName());

        /**set the number of songs to the current items value*/
        TextView selectNumberSongs = listItemView.findViewById(R.id.numSongs);
        selectNumberSongs.setText(currentSel.getNumSongs());

        /**set the total song length to the value in the currentSel array**/
        TextView selectSongLength = listItemView.findViewById(R.id.playTime);
        selectSongLength.setText(currentSel.getSongLength());
        //selectSongLength.setText(Double.toHexString(currentSel.getSongLength()));

        /** Create onClickListener for each category and opens the appropriate activity */
        LinearLayout selItem = listItemView.findViewById(R.id.selectListItem);

        selItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSongs = new Intent(v.getContext(), SongActivity.class);
                GlobalVars gv = GlobalVars.getInstance();
                gv.setSubCategoryPosition(position);
                v.getContext().startActivity(openSongs);
            }
        });

        return listItemView;
    }


}