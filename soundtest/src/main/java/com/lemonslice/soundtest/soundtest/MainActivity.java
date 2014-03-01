package com.lemonslice.soundtest.soundtest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends Activity {

    Button btnStart;
    Button btnStop;
    Button btnFraction;
    Button btnJames;
    Long mStartTime;
    Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                GameAudio.stopMedia(getApplicationContext());
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
                int intensity = GameAudio.plzGetIntenseValue(getApplicationContext());
                TextView tv = (TextView)findViewById(R.id.txtvIntensity);
                tv.setText(String.valueOf(intensity));
            }
        });
    }
}
