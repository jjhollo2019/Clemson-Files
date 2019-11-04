/* Jeremy Holloway
 * CPSC-4150-001
 * Lab01
 * 11/1/2019
 * jjhollo@g.clemson.edu
 */
package com.example.holloway_ktunes

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.*

class MainActivity : AppCompatActivity() {

    //media player for the class
    private lateinit var player: MediaPlayer;
    //TrackList for the class
    private val trackList = TrackList();

    /**
     * This function is the override for the onCreate
     * @pre class must be instantiated
     * @post song is playing and all buttons are properly instantiated
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //make a new player
        player = MediaPlayer.create(this, R.raw.guitar);

        //set the song
        setSong(R.raw.guitar);

        //set the previous button
        val previous = findViewById<ImageView>(R.id.previous);
        previous.setOnClickListener {
            trackList.prevTrack();
            setSong(trackList.currentTrack);
        }

        //set the pause button
        val pause = findViewById<ImageView>(R.id.pause);
        pause.setOnClickListener {
            if(player.isPlaying){
                player.pause();
            } else {
                player.start();
            }
        }

        //set the next button
        val next = findViewById<ImageView>(R.id.next);
        next.setOnClickListener {
            trackList.nextTrack();
            setSong(trackList.currentTrack);
        }
    }

    /**
     * This function will set the current song
     * @pre TrackList constructor must be called
     * @post a song is playing, the title is updated, and
     */
    fun setSong(x: Int) {
        //release MediaPlayer
        player.release();
        //create new MediaPlayer and assign to the MediaPlayer var declared
        player = MediaPlayer.create(this, x);
        //get current tracck
        val track = trackList.currentTrack;
        //get the title
        val title = findViewById<TextView>(R.id.songTitle);
        //set the title text
        title.setText(trackList.getSongTitle(track));
        //set the onCompleteListener to play the next track
        player.setOnCompletionListener {
            trackList.nextTrack();
            setSong(trackList.currentTrack);
        }
        //start the song
        player.start();
    }
}
