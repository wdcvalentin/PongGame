package com.DogPong.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import android.telephony.gsm.SmsManager;
import android.os.Vibrator;



public class PongView extends View implements View.OnTouchListener {

    public com.DogPong.pong.Sound sound;

    float circleX;
    float circleY;
    float xspeed;
    float yspeed;
    int fingerX;
    int width;
    int height;
    int losescore;
    int bouncecount;
    int count;
    String num;
    String msg;
    String[] toppings;
    String message;
    Vibrator v;
    Paint paintballe = new Paint();
    Paint paintrect = new Paint();
    Paint textpaint;
    Paint myPaint;

    public PongView(Context context) {
        super(context);

        circleX = 0;
        circleY = 0;
        xspeed = 15f;
        yspeed = 16f;
        losescore = 10;
        bouncecount = 0;
        count = 0;

        myPaint = new Paint();
        textpaint = new Paint();
        textpaint.setColor(Color.GRAY);
        textpaint.setTextSize(120);

        num = "0651849546";
        msg = "Bon Chien";

        toppings = new String[20];
        toppings[0] = "Nice !";
        toppings[1] = "WoooOOoOOWWW !!!";
        toppings[2] = "MOOOOONSTEEER !!";
        toppings[3] = "OMG";
        toppings[4] = "IMPOSSIBLE MAN";
        toppings[5] = "really?";
        toppings[6] = "undefined level";
        toppings[7] = "it's too much for us";
        toppings[8] = "lyer";
        toppings[9] = "fucking dog";
        toppings[10] = "labrador";
        toppings[11] = "global elite";
        toppings[12] = "u sold ur soul";

        v = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        paintballe.setStyle(Paint.Style.FILL);
        paintballe.setColor(Color.GRAY);
        paintrect.setStyle(Paint.Style.STROKE);
        paintrect.setColor(Color.BLACK);

        this.setOnTouchListener(this);

        //sound = new Sound(getContext());
        sound = new Sound(context);

    }


    @Override
    public    void    onDraw(Canvas canvas)    {
        canvas.drawCircle(circleX,    circleY,    (float)    25,    myPaint);
        canvas.drawRect(fingerX - 200, height - 300, fingerX + 200, height - 250 , paintballe);
        canvas.drawText(String.valueOf("Life = " + losescore), 100, 500, textpaint);
        canvas.drawText(String.valueOf("score = " + bouncecount), 100, 900, textpaint);
        if (message != null) {
            canvas.drawText(String.valueOf(message), 300, 1500, textpaint);
        }
        moveBall();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        fingerX=(int) event.getX();

        return true;
    }


    public void moveBall() {
        circleX += xspeed;
        circleY += yspeed;


        if ((circleX < 0) || (circleX > width)) {
            sound.playBounceSound();
            xspeed *= -1;
        }

        if (circleY < 0) {
            sound.playBounceSound();
            yspeed *= -1;
        }

        if (  (Math.abs(fingerX - circleX) < 200) && (circleY > height - 300)  && (circleY < height - 250)     ){

            v.vibrate(100);
            bouncecount++;
            sound.playHitSound();
            if (bouncecount % 2 == 0) {
                this.message = toppings[this.count];
                this.count++;
                if (xspeed > 0)
                    xspeed += 3;
                else xspeed -= 3;

                if (yspeed > 0)
                    yspeed += 4;
                else yspeed -= 4;

            }

            yspeed = -Math.abs(yspeed);
        }

        if (circleY > height) {
            sound.playLoseSound();
            Toast.makeText(getContext(), "dommage ^^", Toast.LENGTH_SHORT).show();
            losescore--;
            circleX = (float)Math.random() * 700;
            circleY = (float)Math.random() * 200;
            if (losescore == 0) {
                Toast.makeText
                        (getContext(), "tu pues^^", Toast.LENGTH_SHORT).show();
                losescore = 10;
                circleX = 300;
                circleY = 300;
                xspeed = 15;
                yspeed = 16;
                bouncecount = 0;
                SmsManager.getDefault().sendTextMessage
                        (num, null, msg, null, null);
                count = 0;
                SystemClock.sleep(1000);
            }

        }


        invalidate();

    }


}
