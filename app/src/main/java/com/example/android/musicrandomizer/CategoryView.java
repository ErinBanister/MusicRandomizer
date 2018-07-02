package com.example.android.musicrandomizer;

/**
 * {@link CategoryView} represents the home screen categories for the Music Randomizer App
 */

public class CategoryView {
    // Name of the category (All songs, artist, etc)
    private int mCategoryName;

    // Drawable resource ID as relates to the category
    private int mImageResourceId;

    //Class that the grid should link to
    private Class mNewActivity;

    /*
     * Create a new CategoryView object.
     *
     * @param vName is the name of the Android version (e.g. Gingerbread)
     * @param image is drawable reference ID that corresponds to the Android version
     * */
    public CategoryView(int categoryName, int imageResourceId, Class newActivity) {
        mCategoryName = categoryName;
        mImageResourceId = imageResourceId;
        mNewActivity = newActivity;
    }

    /**
     * Get the name of the category for display
     */
    public int getCategoryName() {
        return mCategoryName;
    }

    /**
     * Get the image resource ID
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Get the resource ID to link the grid item to
     */
    public Class getNewActivity() {
        return mNewActivity;
    }
}
