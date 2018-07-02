package com.example.android.musicrandomizer;

import android.widget.TextView;

import java.util.ArrayList;

public class GlobalVars {
    private static GlobalVars globalVars = null;
    private int mCategoryPosition;
    private int mSubCategoryPosition;
    private ArrayList<SongView> mSongList;
    private TextView mFooterSongTitle;
    private TextView mFooterSongArtist;

    /**
     * Class GlobalVars is designed to provide global variables to use throughout the application,
     * to provide data and pointers to various activities and adapters seamlessly.
     */
    private GlobalVars() {
    }

    public static GlobalVars getInstance() {
        if (globalVars == null) {
            globalVars = new GlobalVars();
        }
        return globalVars;
    }

    /**
     * Get Methods
     */
    public int getCategoryPosition() {
        return this.mCategoryPosition;
    }

    /**
     * Set Methods
     */
    public void setCategoryPosition(int newCatPosition) {
        this.mCategoryPosition = newCatPosition;
    }

    public int getSubCategoryPosition() {
        return this.mSubCategoryPosition;
    }

    public void setSubCategoryPosition(int newSubCatPosition) {
        this.mSubCategoryPosition = newSubCatPosition;
    }

    public ArrayList getSongArray() {
        return mSongList;
    }

    public void setSongArray(ArrayList<SongView> songList) {
        if (mSongList != null) {
            mSongList.clear();
        }
        mSongList = (ArrayList<SongView>) songList.clone();
    }

    public TextView getFooterSongTitle() {
        return this.mFooterSongTitle;
    }

    public void setFooterSongTitle(TextView thisFooterSongTitle) {
        this.mFooterSongTitle = thisFooterSongTitle;
    }

    public TextView getFooterSongArtist() {
        return this.mFooterSongArtist;
    }

    public void setFooterSongArtist(TextView thisFooterSongArtist) {
        this.mFooterSongArtist = thisFooterSongArtist;
    }


}
