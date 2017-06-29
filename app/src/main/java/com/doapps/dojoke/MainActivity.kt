package com.doapps.dojoke

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView

@Suppress("UNUSED_PARAMETER")
class MainActivity : Activity() {
    private lateinit var daDumMp: MediaPlayer
    private lateinit var cricket: MediaPlayer
    private lateinit var aww: MediaPlayer
    private lateinit var fakeLaugh: MediaPlayer

    private lateinit var laughImage: ImageView
    private lateinit var awwImage: ImageView
    private lateinit var cricketImage: ImageView
    private lateinit var daDumTssImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Media players
        daDumMp = MediaPlayer.create(this, R.raw.badum)
        cricket = MediaPlayer.create(this, R.raw.cricket)
        aww = MediaPlayer.create(this, R.raw.aww)
        fakeLaugh = MediaPlayer.create(this, R.raw.laugh)

        // Images
        laughImage = findViewById<View>(R.id.laughImage) as ImageView
        awwImage = findViewById<View>(R.id.awwImage) as ImageView
        daDumTssImage = findViewById<View>(R.id.drumImage) as ImageView
        cricketImage = findViewById<View>(R.id.cricketImage) as ImageView
    }

    fun daDum(view: View) = playMedia(daDumMp, daDumTssImage)
    fun cricket(view: View) = playMedia(cricket, cricketImage)
    fun fakeLaugh(view: View) = playMedia(fakeLaugh, laughImage)
    fun aww(view: View) = playMedia(aww, awwImage)

    companion object {

        private val fadeOut = ScaleAnimation(1f, 1.4f, 1f, 1.4f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        private val fadeIn = ScaleAnimation(1.4f, 1f, 1.4f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)

        fun playMedia(mediaPlayer: MediaPlayer, imageView: ImageView) {
            if (mediaPlayer.isPlaying) {
                makeImageSmaller(imageView)
                mediaPlayer.pause()
            } else {
                makeImageBigger(imageView)
                mediaPlayer.start()
                mediaPlayer.setOnCompletionListener { makeImageSmaller(imageView) }
            }
        }

        fun makeImageBigger(imageView: ImageView) {
            fadeOut.duration = 200
            fadeOut.fillAfter = true
            imageView.startAnimation(fadeOut)
        }

        fun makeImageSmaller(imageView: ImageView) {
            fadeIn.duration = 200
            fadeIn.fillAfter = true
            imageView.startAnimation(fadeIn)
        }
    }

    override fun onDestroy() {
        daDumMp.stop()
        cricket.stop()
        aww.stop()
        fakeLaugh.stop()
        super.onPause()
    }
}
