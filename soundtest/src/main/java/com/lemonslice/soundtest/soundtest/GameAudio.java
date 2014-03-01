package com.lemonslice.soundtest.soundtest;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import com.musicg.wave.Wave;

import java.util.logging.LogRecord;
import java.io.InputStream;

/**
 * Created by Sam on 01/03/14.
 */
public class GameAudio {
    static MediaPlayer mediaPlayerx = new MediaPlayer();
    static double bpm = 148; //beat per minute
    static double spb = 60.0/bpm; //seconds per beat
    static int intro = 1346;
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
        mediaPlayerx = MediaPlayer.create(c, R.raw.eiawav7);
    }

    public static double plzGetBeatFraction()
    {
        int curPos = mediaPlayerx.getCurrentPosition()-intro ;
        double frac = (curPos % spb*1000.0)/(spb*1000.0);
        return frac;
    }


    public static int plzGetIntenseValue(Context c)
    {

        InputStream is = c.getResources().openRawResource(R.raw.eiawav7);
        Wave wave = new Wave(is);
        Log.d("DEBUGLOG", "jfk " + wave.toString());
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

        double upperThreshold = mean*1.25;
        double upper2Threshold = mean*1.5;
        double upper3Threshold = mean*1.75;
        double upper4Threshold = mean*2.0;
        double lowerThreshold = mean/1.25;
        double lower2Threshold = mean/1.5;
        double lower3Threshold = mean/1.75;
        double lower4Threshold = mean/2.0;

        double curPos = mediaPlayerx.getCurrentPosition()-intro;
        double percent = curPos/(mediaPlayerx.getDuration()-intro);
        int startpoint = (int)(percent * amps.length);
        if (startpoint == amps.length) startpoint = startpoint - 1;
        double amplitude = amps[startpoint];

        if (amplitude < 0) amplitude = amplitude * -1;
        double meanamp = (amps[startpoint] + amps[startpoint + 1])/2.0;
        double amptemp;

        for(int i=2; i<44100; i++)
        {
            if (i + startpoint == amps.length) startpoint = amps.length - 1 - i;
            amptemp = amps[i + startpoint];
            if (amptemp < 0) amptemp *= -1;
            meanamp = (meanamp*(i) + amptemp) / (i+1);
        }
        Log.d("DEBUGSAM","single amplitude = " + amplitude);


        if (meanamp < 0) meanamp = meanamp * -1;
        Log.d("DEBUGSAM","averaged amplitude = " + meanamp);
        amplitude = meanamp;
        if(amplitude < lower4Threshold)
            return 1;
        else if(amplitude < lower3Threshold)
            return 2;
        else if(amplitude < lower2Threshold)
            return 3;
        else if(amplitude < lowerThreshold)
            return 4;
        else if(amplitude < mean)
            return 5;
        else if(amplitude < upperThreshold)
            return 6;
        else if(amplitude < upper2Threshold)
            return 7;
        else if(amplitude < upper3Threshold)
            return 8;
        else if(amplitude < upper4Threshold)
            return 9;
        else
            return 10;
    }

}