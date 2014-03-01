package com.lemonslice.soundtest.soundtest;

import android.content.Context;
import android.media.MediaPlayer;

import com.musicg.wave.Wave;

import java.io.InputStream;

/**
 * Created by Sam on 01/03/14.
 */
public class GameAudio {
    static MediaPlayer mediaPlayerx = new MediaPlayer();
    static double bpm = 104.993; //beat per minute
    static double spb = 60.0/bpm; //seconds per beat
    static int intro = 250;
    static double mean = 0;

    public static void startMedia()
    {
        mediaPlayerx.start();
    }

    public static void pauseMedia()
    {
        mediaPlayerx.pause();
    }

    public static void stopMedia(Context c)
    {
        mediaPlayerx.stop();
        mediaPlayerx.release();
        mediaPlayerx = MediaPlayer.create(c, R.raw.eiawav);
    }

    public static double plzGetBeatFraction()
    {
        int curPos = mediaPlayerx.getCurrentPosition()-intro ;
        double frac = (curPos % spb*1000.0)/(spb*1000.0);
        return frac;
    }


    public static int plzGetIntenseValue(Context c)
    {

        InputStream is = c.getResources().openRawResource(R.raw.eiawav);
        Wave wave = new Wave(is);

        short[] amps = wave.getSampleAmplitudes();

        if(mean == 0)
        {
            mean = (amps[0] + amps[1])/2.0;
            for(int i=2; i<amps.length; i++)
            {
                if (amps[i] < 0) amps[i] *= -1;
                mean = (mean*(i) + amps[i]) / (i+1);
            }
        }

        double upperThreshold = mean*2.0;
        double lowerThreshold = mean/2.0;

        double curPos = mediaPlayerx.getCurrentPosition()-intro;
        double percent = curPos/(mediaPlayerx.getDuration()-intro);
        double amplitude = amps[(int)(percent * amps.length)];


        if(amplitude < lowerThreshold)
            return 1;
        else if(amplitude < mean)
            return 2;
        else if(amplitude < upperThreshold)
            return 3;
        else
            return 4;
    }

}