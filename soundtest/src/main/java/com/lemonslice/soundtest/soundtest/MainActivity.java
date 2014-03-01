package com.lemonslice.soundtest.soundtest;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    MediaPlayer mediaPlayerx = new MediaPlayer();
    Button btnStart;
    Button btnStop;
    Button btnFraction;
    double bpm = 148.003; //beat per minute
    double spb = 60.0/bpm; //seconds per beat
    int intro = 1346;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayerx = MediaPlayer.create(this, R.raw.eia);
        int length = mediaPlayerx.getCurrentPosition();
        Double bps = 60.0/bpm;

        //Toast.makeText(getApplicationContext(), Integer.toString(intro), Toast.LENGTH_SHORT).show();
        btnStart = (Button)findViewById(R.id.btnStartMusic);
        btnStop = (Button)findViewById(R.id.btnStopMusic);
        btnFraction = (Button) findViewById(R.id.btnFraction);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                   mediaPlayerx.start();

                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Error starting music", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerx.stop();
                mediaPlayerx.release();
                mediaPlayerx = MediaPlayer.create(MainActivity.this, R.raw.eia);
            }
        });

        btnFraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plzGetFraction();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Double plzGetFraction()
    {
        int curPos = mediaPlayerx.getCurrentPosition()-intro ;
        double frac = (curPos % spb*1000.0)/(spb*1000.0);

        Toast.makeText(getApplicationContext(),
                "CurPos: "+Integer.toString(curPos)+" frac: "+Double.toString(frac),
                Toast.LENGTH_SHORT).show();

        return frac;
    }

}
