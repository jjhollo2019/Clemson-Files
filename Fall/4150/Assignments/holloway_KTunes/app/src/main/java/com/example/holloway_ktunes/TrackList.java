/* Jeremy Holloway
 * CPSC-4150-001
 * Lab01
 * 11/1/2019
 * jjhollo@g.clemson.edu
 */
package com.example.holloway_ktunes;

public class TrackList {
    // Private data members
    //declare index
    private int index = 0;
    //declare song array
    private int[] songs = new int[3];

    /**
     * This is the class constructor that populates the songs array
     * @pre class constructor must be called
     * @post songs has all tracks loaded into the array
     */
    public TrackList() {
        //add guitar song
        songs[0] = R.raw.guitar;
        //add marimba song
        songs[1] = R.raw.marimba;
        //add piano song
        songs[2] = R.raw.piano;
    }

    /**
     * This function gets the currently indexed track
     * @pre class constructor must be called
     * @post the current song is returned
     * @return the currently index track resource ID
     */
    public int getCurrentTrack() {
        return songs[index % songs.length];
    }

    /**
     * This function changes the current track to the next track
     * @pre class must be properly instantiated
     * @post the index is increased by one or reset to zero
     */
    public void nextTrack() {
        //check index out of bounds
        if(index == songs.length) {
            //reset
            index = 0;
        } else {
            //increment
            index++;
        }
    }

    /**
     * This function changes the current track to the previous track
     * @pre class must be properly instantiated
     * @post the index is decremented by one or set to the last element
     */
    public void prevTrack() {
        //check if index is zero
        if(index == 0) {
            //reset to end
            index = (songs.length - 1);
        } else {
            //decrement
            index--;
        }
    }

    /**
     * This fucntion will get a string to display the title of the song
     * @pre the class must be properly instantiated
     * @post none
     * @param ID is the resource ID of the track
     * @return a string
     */
    public String getSongTitle(int ID) {
        //if the guitar song
        if(ID == songs[0]) {
            return "Guitar";
        }
        //if the marimba song
        else if(ID == songs[1]) {
            return "Marimba";
        }
        //else piano song
        else {
            return "Piano";
        }
    }
}
