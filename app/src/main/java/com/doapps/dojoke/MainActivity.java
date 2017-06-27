package com.doapps.dojoke;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private MediaPlayer daDumMp;
    private MediaPlayer cricket;
    private MediaPlayer aww;
    private MediaPlayer fakeLaugh;
    private ImageView laughImage;
    private ImageView awwImage;
    private ImageView cricketImage;
    private ImageView daDumTssImage;

    private static ScaleAnimation fade_out;
    private static ScaleAnimation fade_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Media players
        daDumMp = MediaPlayer.create(this, R.raw.badum);
        cricket = MediaPlayer.create(this, R.raw.cricket);
        aww = MediaPlayer.create(this, R.raw.aww);
        fakeLaugh = MediaPlayer.create(this, R.raw.laugh);

        // Images
        laughImage = (ImageView) findViewById(R.id.laughImage);
        awwImage = (ImageView) findViewById(R.id.awwImage);
        daDumTssImage = (ImageView) findViewById(R.id.drumImage);
        cricketImage = (ImageView) findViewById(R.id.cricketImage);
    }

    public static void playMedia(MediaPlayer mediaPlayer, final ImageView imageView) {
        if (mediaPlayer.isPlaying()) {
            makeImageSmaller(imageView);
            mediaPlayer.pause();
        } else {
            makeImageBigger(imageView);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    makeImageSmaller(imageView);
                }
            });
        }
    }

    public void daDum(View view) {
        playMedia(daDumMp, daDumTssImage);
    }

    public void cricket(View view) {
        playMedia(cricket, cricketImage);
    }

    public void fakeLaugh(View view) {
        playMedia(fakeLaugh, laughImage);
    }

    public void aww(View view) {
        playMedia(aww, awwImage);
    }

    public static void makeImageBigger(ImageView imageView) {

        if (fade_out == null) {
            fade_out = new ScaleAnimation(1f, 1.4f, 1f, 1.4f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            fade_out.setDuration(200);
            fade_out.setFillAfter(true);
        }

        imageView.startAnimation(fade_out);
    }

    public static void makeImageSmaller(ImageView imageView) {

        if (fade_in == null) {
            fade_in = new ScaleAnimation(1.4f, 1f, 1.4f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            fade_in.setDuration(200);
            fade_in.setFillAfter(true);
        }

        imageView.startAnimation(fade_in);
    }
}
