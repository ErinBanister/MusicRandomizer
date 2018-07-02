package com.example.android.musicrandomizer;

/**
 * {@link SelectView} represents the category list for the Music Randomizer App
 */

public class SelectView {
    // Name of the category (All songs, artist, etc)
    private String mCategoryName;

    // Number of songs in the category
    private String mNumSongs;

    //Total runtime of all songs in category
    private String mSongLength;

    /**
     * Create a new SelectView object.
     *
     * @param categoryName is the name the categoty (e.g. Gingerbread)
     * @param numSongs     is the number of songs in the category
     * @param songLength   is the total length of the songs in the category
     */
    public SelectView(String categoryName, String numSongs, String songLength) {
        mCategoryName = categoryName;
        mNumSongs = numSongs;
        mSongLength = songLength;
    }

    /**
     * Get the name of the category for display
     */
    public String getCategoryName() {
        return mCategoryName;
    }

    /**
     * Get the image resource ID
     */
    public String getNumSongs() {
        return mNumSongs;
    }

    /**
     * Get the resource ID to link the grid item to
     */
    public String getSongLength() {
        return mSongLength;
    }

}
