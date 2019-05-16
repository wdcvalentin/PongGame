package com.DogPong.pong;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.DogPong.pong.R;

public class Sound {

    private static SoundPool soundPool;
    //private static int soundtrack;
    private static int hitSound;
    //private static int overSound;
    private static int bounce;
    private static int lose;


    public Sound(Context context) {

        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        //soundtrack = soundPool.load(context, R.raw.opening, 1);
        hitSound = soundPool.load(context, R.raw.bark, 1);
        //overSound = soundPool.load(context, R.raw.cry, 1);
        bounce = soundPool.load(context, R.raw.bounce, 1);
        lose = soundPool.load(context, R.raw.bounce, 1);

    }

    public void playHitSound() {
        soundPool.play
                (hitSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }


    public void playBounceSound() {
        soundPool.play
                (bounce, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playLoseSound() {
        soundPool.play
                (lose, 1.0f, 1.0f, 1, 0, 1.0f);
    }

//    public void playOverSound() {
//        soundPool.play
//                (overSound, 1.0f, 1.0f, 1, 0, 1.0f);
//    }

//    public void playSoundTrack() {
//        soundPool.play
//                (soundtrack, 1.0f, 1.0f, 1, 0, 1.0f);
//    }

}
