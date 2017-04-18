package com.mit.readingsdcard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;

import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlayingMusic extends AppCompatActivity implements RecognitionListener{
    TextView txtSongName,txtStartTime,txtFinishTime;
    Button btnPlay,btnPause;
    MediaPlayer mediaPlayer;
    long startTime=0,finishTime=0;
    SeekBar seekBar;
    Handler handler;
    SpeechRecognizer speechRecognizer;
    ProgressDialog progressDialog;

    String pathOfFile;
    String songName;

    void initViews() {
        txtSongName = (TextView) findViewById(R.id.textViewSongName);
        txtStartTime = (TextView) findViewById(R.id.textViewStart);
        txtFinishTime = (TextView) findViewById(R.id.textViewFinish);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPause = (Button) findViewById(R.id.btnPause);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        mediaPlayer = new MediaPlayer();
        handler = new Handler();
        speechRecognizer=SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Listening....");


        Intent data = getIntent();
        pathOfFile = data.getStringExtra("keyPath");
        File file = new File(pathOfFile);
        songName = file.getName();
        txtSongName.setText(songName);

       try {
            mediaPlayer.setDataSource(pathOfFile);

            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
       } catch (IOException e) {
            e.printStackTrace();
        }

      //   playSound();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                startTime=mediaPlayer.getCurrentPosition();
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

                speechRecognizer.startListening(RecognizerIntent.getVoiceDetailsIntent(PlayingMusic.this));

                finishTime = mediaPlayer.getDuration();
                seekBar.setMax((int) finishTime);
                txtFinishTime.setText(String.format("%02d : %02d", (TimeUnit.MILLISECONDS.toMinutes(finishTime)),
                        (TimeUnit.MILLISECONDS.toSeconds(finishTime)) - (TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(finishTime)))));

                startTime = mediaPlayer.getCurrentPosition();
                seekBar.setProgress((int) startTime);
                txtStartTime.setText(String.format("%02d : %02d", (TimeUnit.MILLISECONDS.toMinutes(startTime)),
                        (TimeUnit.MILLISECONDS.toSeconds(startTime)) - (TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(startTime)))));

                btnPlay.setEnabled(false);
                btnPause.setEnabled(true);
                handler.postDelayed(runnable, 1000);

            }
        });


        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                speechRecognizer.startListening(RecognizerIntent.getVoiceDetailsIntent(PlayingMusic.this));
                btnPause.setEnabled(false);
                btnPlay.setEnabled(true);
            }
        });

        ///Call this view's OnClickListener, if it is defined.
        btnPlay.performClick();
    }

    void playSound(){
        finishTime= mediaPlayer.getDuration();
        seekBar.setMax((int) finishTime);
        txtFinishTime.setText(String.format("%02d : %02d", (TimeUnit.MILLISECONDS.toMinutes(finishTime)),
                (TimeUnit.MILLISECONDS.toSeconds(finishTime)) - (TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(finishTime)))));
        txtStartTime.setText("00:00");

        mediaPlayer.start();

        seekBar.setProgress((int) mediaPlayer.getCurrentPosition());
        btnPlay.setEnabled(false);
        btnPause.setEnabled(true);
        handler.postDelayed(runnable, 1000);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing_music);
        initViews();
    }


    Runnable runnable=new Runnable() {
        @Override
        public void run() {
                startTime=mediaPlayer.getCurrentPosition();
            txtStartTime.setText(String.format("%02d : %02d", (TimeUnit.MILLISECONDS.toMinutes(startTime)),
                    (TimeUnit.MILLISECONDS.toSeconds(startTime)) - (TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(startTime)))));
            seekBar.setProgress((int) startTime);
            handler.postDelayed(this, 1000);
            }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();

        speechRecognizer.stopListening();
        mediaPlayer.stop();
        mediaPlayer.release();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {
        progressDialog.show();
    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {
        progressDialog.dismiss();
    }

    @Override
    public void onError(int error) {

    }

    @Override
    public void onResults(Bundle results) {
        List<String> list=results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        for(String s:list){
            if(s.toLowerCase().contains("play")){
                btnPlay.performClick();
            }
            else if(s.toLowerCase().contains("pause")){
                btnPause.performClick();
            }
        }
        speechRecognizer.stopListening();
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
