package com.example.android.musicrandomizer;

/**
 * {@link SongView} represents the Songs for the Music Randomizer App
 */

public class SongView {

    private String mSongTitle;
    private String mSongArtist;
    private String mSongAlbum;
    private String mSongLength;


    /**
     * Create a new SongView object
     *
     * @param songTitle  - the song title
     * @param songArtist - the song artist name
     * @param songAlbum  - the song album
     * @param songLength - the song length
     */
    public SongView(String songTitle, String songArtist, String songAlbum, String songLength) {
        mSongTitle = songTitle;
        mSongArtist = songArtist;
        mSongAlbum = songAlbum;
        mSongLength = songLength;
    }

    /**
     * Get the name of the song for display
     */
    public String getSongTitle() {
        return mSongTitle;
    }

    /**
     * Get the song artist
     */
    public String getSongArtist() {
        return mSongArtist;
    }

    /**
     * Get the song album
     */
    public String getSongAlbum() {
        return mSongAlbum;
    }

    /**
     * Get the song length
     */
    public String getSongLength() {
        return mSongLength;
    }
}
