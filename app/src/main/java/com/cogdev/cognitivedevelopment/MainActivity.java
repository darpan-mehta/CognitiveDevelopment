package com.cogdev.cognitivedevelopment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;
    int[] soundsr = {R.raw.rightdoor1,R.raw.running1,R.raw.rdoorsound1};
    int[] soundsg = {R.raw.rightdoor1,R.raw.running1,R.raw.gdoorsound1};
    int[] soundsb = {R.raw.rightdoor1,R.raw.running1,R.raw.bdoorsound1};

    Random r = new Random();
    final int Low = 0;
    final int High = 2;
    int rndm = r.nextInt((High - Low)+1) + Low;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        Button rdoor = (Button) findViewById(R.id.rdoor);
        mp = MediaPlayer.create(getApplicationContext(), soundsr[rndm]);
        rdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        rndm = r.nextInt((High - Low) + 1) + Low;
                        mp = MediaPlayer.create(getApplicationContext(), soundsr[rndm]);
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button gdoor = (Button) findViewById(R.id.gdoor);
        mp = MediaPlayer.create(getApplicationContext(), soundsg[rndm]);
        gdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        rndm = r.nextInt((High - Low)+1) + Low;
                        mp = MediaPlayer.create(getApplicationContext(), soundsg[rndm]);
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button bdoor = (Button) findViewById(R.id.bdoor);
        mp = MediaPlayer.create(getApplicationContext(), soundsb[rndm]);
        bdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        rndm = r.nextInt((High - Low)+1) + Low;
                        mp = MediaPlayer.create(getApplicationContext(), soundsb[rndm]);
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToTrace(View view) {
        Intent intent = new Intent(this, Trace.class);
        startActivity(intent);
    }

    public void goToDoor(View view) {
        Intent intent = new Intent(this, DoorScreen.class);
        startActivity(intent);
    }
}
