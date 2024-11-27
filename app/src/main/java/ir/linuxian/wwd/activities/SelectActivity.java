package ir.linuxian.wwd.activities;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import ir.linuxian.wwd.R;

public class SelectActivity extends AppCompatActivity {


    LinearLayoutCompat linear_jostejoo;
    LinearLayoutCompat linear_kalame;
    LinearLayoutCompat linear_tanzimat;
    LinearLayoutCompat linear_ahooee;
    LinearLayoutCompat linear_source;
    AudioManager audioManager;
    MediaPlayer mediaPlayer;
    int current_music_volume = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_select);

        Toast.makeText(this, "chedo"+TimeUnit.MINUTES.toMillis(1), Toast.LENGTH_SHORT).show();


        initiate();

        animate();

        play();


    }

    private void play() {

         mediaPlayer = MediaPlayer.create(this,R.raw.startup);
        current_music_volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)/3,0);
        mediaPlayer.start();

    }

    private void initiate() {

        linear_jostejoo = findViewById(R.id.linear_jostejoo);
        linear_kalame = findViewById(R.id.linear_kalame);
        linear_tanzimat = findViewById(R.id.linear_tanzimat);
        linear_ahooee = findViewById(R.id.linear_linuxian);
        linear_source = findViewById(R.id.linear_source);

         audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        linear_kalame .setOnClickListener(view -> {

            Intent intent = new Intent(SelectActivity.this, FindActivity.class);

            startActivity(intent);
            SelectActivity.this.finish();

        });


        linear_jostejoo.setOnClickListener(view->{

            Intent intent = new Intent(SelectActivity.this, SearchActivity.class);

            startActivity(intent);
            SelectActivity.this.finish();


        });


    }

    private void animate() {

        setAndaze(linear_jostejoo);
        setAndaze(linear_kalame);
        setAndaze(linear_tanzimat);
        setAndaze(linear_ahooee);
        setAndaze(linear_source);
    }


    private void setAndaze(LinearLayoutCompat linearLayout) {

        linearLayout.setScaleX(0.001f);
        linearLayout.setScaleY(0.001f);
        linearLayout.setAlpha(0.1f);


        linearLayout.setTranslationX(400);
        linearLayout.setTranslationX(400);
        linearLayout.animate().translationX(0);
        linearLayout.animate().alpha(1f);
        linearLayout.animate().scaleY(1f).scaleX(1f).setDuration(2500);
        //linearLayout.animate().rotation(360).setDuration(2500);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mediaPlayer!=null){
            if(mediaPlayer.isPlaying())
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        }
        if(audioManager!=null && current_music_volume!=-1)
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,current_music_volume,0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayer!=null){
            if(mediaPlayer.isPlaying())
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if(audioManager!=null && current_music_volume!=-1)
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,current_music_volume,0);
    }
}
