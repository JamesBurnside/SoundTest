package com.lemonslice.soundtest.soundtest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button btnStart;
    Button btnStop;
    Button btnFraction;
<<<<<<< HEAD
    Button btnJames;
=======
    double bpm = 148.003; //beat per minute
    double spb = 60.0/bpm; //seconds per beat
    int intro = 1346;

>>>>>>> 6b9b13e6d39c46b4c1cf1dec8aa304307dfefb08

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
=======
        mediaPlayerx = MediaPlayer.create(this, R.raw.eia);
        int length = mediaPlayerx.getCurrentPosition();
        Double bps = 60.0/bpm;

        //Toast.makeText(getApplicationContext(), Integer.toString(intro), Toast.LENGTH_SHORT).show();
>>>>>>> 6b9b13e6d39c46b4c1cf1dec8aa304307dfefb08
        btnStart = (Button)findViewById(R.id.btnStartMusic);
        btnStop = (Button)findViewById(R.id.btnStopMusic);
        btnJames = (Button)findViewById(R.id.btnJames);
        btnFraction = (Button) findViewById(R.id.btnFraction);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameAudio.startMedia();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
                GameAudio.stopMedia(getApplicationContext());
=======
                mediaPlayerx.stop();
                mediaPlayerx.release();
                mediaPlayerx = MediaPlayer.create(MainActivity.this, R.raw.eia);
>>>>>>> 6b9b13e6d39c46b4c1cf1dec8aa304307dfefb08
            }
        });

        btnFraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double rval = GameAudio.plzGetBeatFraction();
                Toast.makeText(getApplicationContext(), "Frac: "+rval, Toast.LENGTH_SHORT).show();
            }
        });

        btnJames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rval = GameAudio.plzGetIntenseValue(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Intensity: "+rval, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
