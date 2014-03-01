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
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    MediaPlayer mediaPlayer = new MediaPlayer();
    Button btnStart;
    Button btnStop;
    ContentResolver contentResolver;
    Uri muisicUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button)findViewById(R.id.btnStartMusic);
        btnStop = (Button)findViewById(R.id.btnStopMusic);



        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    Cursor cursor = contentResolver.query(muisicUri, null, null, null, null);

                    if (cursor == null)
                    {
                        // query failed, handle error.
                    }
                    else if (!cursor.moveToFirst())
                    {
                        // no media on the device
                    }
                    else
                    {
                        int titleColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
                        int idColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
                        do {
                            mediaPlayer.reset();
                            long thisId = cursor.getLong(idColumn);
                            String thisTitle = cursor.getString(titleColumn);
                            Toast.makeText(getApplicationContext(), "got: "+thisTitle, Toast.LENGTH_SHORT).show();
                                try
                                {
                                    mediaPlayer.setDataSource(AudioUriPath);

                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "no music: "+e+" "+thisTitle, Toast.LENGTH_LONG).show();
                                }

                            mediaPlayer.prepare();
                            mediaPlayer.start();


                        } while (cursor.moveToNext());
                    }


                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Error starting music: "+e, Toast.LENGTH_LONG).show();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
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

}
