package com.example.android.musicrandomizer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class SongActivity extends AppCompatActivity {
    public int catPosition;
    public int subCatPosition;
    public ListView listview;
    public View playPause;
    public View stop;
    public View forward;
    public TextView songArtist;
    public TextView songTitle;
    public String lastPressed = "Stop";
    public ImageView playImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalVars gv = GlobalVars.getInstance();
        catPosition = gv.getCategoryPosition();
        subCatPosition = gv.getSubCategoryPosition();
        playImage = findViewById(R.id.play);
        setContentView(R.layout.song);
        gv.setFooterSongArtist((TextView) findViewById(R.id.footerSongArtist));
        gv.setFooterSongTitle((TextView) findViewById(R.id.footerSongTitle));
        listview = findViewById(R.id.songListView);
        createSongAdapterView();

        //set action bar to xml field toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.songToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        buttonClick();
    }

    public void onResume() {
        super.onResume();
        listview = findViewById(R.id.songListView);
        listview.invalidate();
        setContentView(R.layout.song);
        GlobalVars gv = GlobalVars.getInstance();
        catPosition = gv.getCategoryPosition();
        subCatPosition = gv.getSubCategoryPosition();
        Log.i("FILENAME REDUX:", "CatPosition :" + catPosition + " subCatPosition: " + subCatPosition);
        listview.setAdapter(null);
        createSongAdapterView();
        buttonClick();

        //set action bar to xml field toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.songToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void createSongAdapterView() {
        /** Create an array SelectView with the following elements:
         *  1. Name of Category
         *  2. Number of Songs in Category
         *  3. Total Length of songs in category
         */
        BufferedReader reader;
        ArrayList<SongView> songList = new ArrayList<>();
        songList.clear();
        try {
            //determine which click sent the intent, create ArrayList based on value
            String fileName = getAssetFile();
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(fileName)));
            if (reader != null) {
                String line = reader.readLine();
                while (line != null) {
                    String tokens[] = line.split(",");
                    songList.add(new SongView(tokens[0], tokens[1], tokens[2], tokens[3]));
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("PROBLEM ReadingFile : ", e.getMessage());
        }
        //save songList array to global variable for future use
        GlobalVars gv = GlobalVars.getInstance();
        gv.setSongArray(songList);
        listview = findViewById(R.id.songListView);
        songAdapter adapter = new songAdapter(this, songList);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * getAssetFile method determines which category was clicked via the Select ListView screen
     * and determines the text data file used to populate the array
     *
     * @return String is the name of the txt data file to import
     */
    public String getAssetFile() {
        String assetFile = "";
        //catPosition 1: All Songs
        if (catPosition == 0) {
            assetFile = "allsongs";
        }
        //catPosition 2: Styles
        if (catPosition == 1) {
            assetFile = "category";
            if (subCatPosition == 0) {
                assetFile = assetFile + "pop";
            } else if (subCatPosition == 1) {
                assetFile = assetFile + "rock";
            } else if (subCatPosition == 2) {
                assetFile = assetFile + "rap";
            } else if (subCatPosition == 3) {
                assetFile = assetFile + "alt";
            }

            //catPosition 3: Years
        } else if (catPosition == 2) {
            assetFile = "decade";
            if (subCatPosition == 0) {
                assetFile = assetFile + "70s";
            } else if (subCatPosition == 1) {
                assetFile = assetFile + "80s";
            } else if (subCatPosition == 2) {
                assetFile = assetFile + "90s";
            } else if (subCatPosition == 3) {
                assetFile = assetFile + "00s";
            } else if (subCatPosition == 4) {
                assetFile = assetFile + "10s";
            }


            //catPosition 4: Ratings
        } else if (catPosition == 3) {
            assetFile = "rating";
            if (subCatPosition == 0) {
                assetFile = assetFile + "1";
            } else if (subCatPosition == 1) {
                assetFile = assetFile + "2";
            } else if (subCatPosition == 2) {
                assetFile = assetFile + "3";
            } else if (subCatPosition == 3) {
                assetFile = assetFile + "4";
            } else if (subCatPosition == 4) {
                assetFile = assetFile + "5";
            }
        }

        assetFile = assetFile + ".txt";
        return assetFile;
    }

    public void buttonClick() {
        playPause = findViewById(R.id.play);
        stop = findViewById(R.id.stop);
        forward = findViewById(R.id.forward);
        songArtist = findViewById(R.id.footerSongArtist);
        songTitle = findViewById(R.id.footerSongTitle);

        playPause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                footerButtonClicked("playPause");
                ImageView playButton = (ImageView) playPause;
                String playButtonDrawable = String.valueOf(playButton.getTag());
                if (playButtonDrawable.equals("Play")) {
                    playButton.setImageResource(R.drawable.pause);
                    playButton.setTag("Pause");

                } else {
                    playButton.setImageResource(R.drawable.play);
                    playButton.setTag("Play");
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                footerButtonClicked("stop");
            }

        });
        forward.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                footerButtonClicked("forward");
            }

        });
    }

    /**
     * footerButtonClicked provides the steps used when the onClickListener determines the play, pause,
     * or stop button is clicked in the footer of the Song ListView
     *
     * @param button - the specific button pressed to get to this method
     */
    private void footerButtonClicked(String button) {
        TextView nowPlaying = songTitle; //(R.id.footerSongTitle);
        TextView nowPlayingArtist = songArtist; //(R.id.footerSongArtist);
        ImageView playImage = (ImageView) playPause;
        String playPauseImage = playImage.getTag().toString();
        if (button == "playPause") {
            switch (playPauseImage) {
                case "Play":
                    //if the last button pressed was pause, then only update the button
                    //and continue playing the song. Otherwise, randomize next song selection.
                    playImage.setImageResource(R.drawable.pause);
                    ((ImageView) playPause).setImageResource(R.drawable.pause);
                    if (lastPressed.equals("Pause")) {
                        lastPressed = "Play";
                        playImage.setTag(lastPressed);
                        break;
                    } else if (lastPressed.equals("Stop")) {
                        randomizeNextSong();
                        lastPressed = "Play";
                        playImage.setTag(lastPressed);
                        break;
                    } else if (lastPressed.equals("Play")) {
                        lastPressed = "Play";
                        playImage.setTag(lastPressed);
                    }
                case "Pause":
                    playImage.setImageResource(R.drawable.play);
                    lastPressed = "Pause";
                    break;
            }
        } else if (button == "stop") {
            nowPlaying.setText("");
            nowPlayingArtist.setText("");
            playImage.setImageResource(R.drawable.play);
            lastPressed = "Stop";
            playImage.setTag(lastPressed);

        } else if (button == "forward") {
            randomizeNextSong();
            playImage.setImageResource(R.drawable.pause);
            lastPressed = "Forward";
            playImage.setTag(lastPressed);
        }
    }

    /**
     * randomizeNextSong method determines which song is currently playing, and randomizes the next
     * selection to not include the current song playing
     */
    public void randomizeNextSong() {
        ImageView playImage = (ImageView) playPause;
        ArrayList<SongView> songList = GlobalVars.getInstance().getSongArray();
        playImage.setImageResource(R.drawable.pause);

        int listLength = songList.size();
        Random r = new Random();
        int nextSong = r.nextInt(listLength);
        /**ensure randomizer won't play same song twice in a row*/
        if (songTitle.getText() == songList.get(nextSong).getSongTitle()) {
            nextSong = r.nextInt(listLength);
            updateSongFooter(songList.get(nextSong).getSongTitle(), songList.get(nextSong).getSongArtist(), false);
            playImage.setImageResource(R.drawable.pause);
        } else {
            updateSongFooter(songList.get(nextSong).getSongTitle(), songList.get(nextSong).getSongArtist(), false);
            playImage.setImageResource(R.drawable.pause);
        }
    }

    public void updateSongFooter(String footerSongTitle, String footerArtist, boolean fromAdapter) {
        songTitle.setText(footerSongTitle);
        songArtist.setText(footerArtist);
        if (fromAdapter) {
            playImage = (ImageView) playPause;
            playImage.setImageResource(R.drawable.pause);
        }
    }

}
//All Icons Courtesy of BomSymbols from the Noun Project