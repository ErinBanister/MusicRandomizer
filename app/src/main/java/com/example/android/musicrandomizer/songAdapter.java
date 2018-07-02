package com.example.android.musicrandomizer;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class songAdapter extends ArrayAdapter<SongView> {
    /* Provides a view for the adapter view
     */
    private Context mContext;

    public songAdapter(Activity context, ArrayList<SongView> songArray) {
        super(context, 0, songArray);
        mContext = context;
    }

    /**
     * overrides the getView method in the ArrayAdapter class, and attaches it to the
     * selectItemView of select_list_item.xml
     */
    @Override
    @NonNull
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.song_list_item, parent, false);
        }


        /** Get the select object's position and populates the xml */
        final SongView currentSong = getItem(position);

        /**Set the selectName TextView to the current items' category name in array**/
        TextView songTitleTextView = listItemView.findViewById(R.id.songTitle);
        songTitleTextView.setText(currentSong.getSongTitle());

        /**set artist value */
        TextView songArtistTextView = listItemView.findViewById(R.id.songArtist);
        songArtistTextView.setText(currentSong.getSongArtist());

        /**set length value**/
        TextView songLengthTextView = listItemView.findViewById(R.id.songLength);
        songLengthTextView.setText(currentSong.getSongLength());

        /** Create onClickListener for each category and opens the appropriate activity */
        ConstraintLayout songItem = listItemView.findViewById(R.id.songListMain);

        songItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when an item is clicked, play the song, update the footer with song artist/title
                if (mContext instanceof SongActivity) {
                    ((SongActivity) mContext).updateSongFooter(currentSong.getSongTitle(), currentSong.getSongArtist(), true);

                }
            }
        });
        return listItemView;
    }
}

