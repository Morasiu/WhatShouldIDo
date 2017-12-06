package com.example.hubert.whatshouldido;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;


public class MainActivity extends AppCompatActivity {

    private TweenManager tweenManager;
    private boolean isAnimationRunning = true;
    static final private int ALERT_DIALOG_PLAIN = 1;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9534033206643361/6726547939"); //TODO zmienić kod DONE

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Toast.makeText(MainActivity.this, "Thanks for supporting me", Toast.LENGTH_SHORT).show();
                requestNewInterstitial();
            }
        });
        requestNewInterstitial();


        ImageView wheel = (ImageView) findViewById(R.id.wheel_napi);
        wheel.setTag("tag1");

        setTweenEngine();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder
                        .setTitle(R.string.aboutTitle)
                        .setMessage("Messeage")
                        .setPositiveButton("OK", null);
                AlertDialog dialog = alertDialogBuilder.show();
                TextView messageAbout = (TextView)dialog.findViewById(android.R.id.message);
                messageAbout.setGravity(Gravity.CENTER);
                messageAbout.setText(R.string.aboutText);
                messageAbout.setTextSize(17);
                return true;
            case R.id.menu_ad:
                //Toast.makeText(this, "Soon", Toast.LENGTH_SHORT).show();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    }
                return true;

            default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void setTweenEngine() {
        tweenManager = new TweenManager();
        //start animation thread
        setAnimationThread();

        Tween.registerAccessor(ImageView.class, new WheelAccessor());
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                // .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public void StartAnimation(View v){

        final int degree = randInt(1, 51);
        final Button spin_button = (Button) findViewById(R.id.spin_button);
        final Button prev = (Button) findViewById(R.id.prev_button);
        final Button next = (Button) findViewById(R.id.next_button);
        final ImageView wheel = (ImageView) findViewById(R.id.wheel_napi);

        spin_button.setEnabled(false);
        prev.setEnabled(false);
        next.setEnabled(false);

        TweenCallback TweenCall = new TweenCallback() {
            public void onEvent(int type, BaseTween<?> source) {

                spin_button.setEnabled(true);

                prev.setEnabled(true);
                next.setEnabled(true);

                Object tag = wheel.getTag();

                String Text1 = getResources().getString(R.string.Text1);
                String Text2 = getResources().getString(R.string.Text2);
                String Text3 = getResources().getString(R.string.Text3);
                String Text4 = getResources().getString(R.string.Text4);
                String Text5 = getResources().getString(R.string.Text5);
                String Text6 = getResources().getString(R.string.Text6);
                String Text7 = getResources().getString(R.string.Text7);
                String Text8 = getResources().getString(R.string.Text8);

                if(tag == "tag1") { // Toast dla main
                    if (degree < 7)
                        Toast.makeText(MainActivity.this, Text8, Toast.LENGTH_SHORT).show();
                    if (6 < degree && degree < 13)
                        Toast.makeText(MainActivity.this, Text7, Toast.LENGTH_SHORT).show();
                    if (12 < degree && degree < 20)
                        Toast.makeText(MainActivity.this, Text6, Toast.LENGTH_SHORT).show();
                    if (19 < degree && degree < 26)
                        Toast.makeText(MainActivity.this, Text5, Toast.LENGTH_SHORT).show();
                    if (25 < degree && degree < 33)
                        Toast.makeText(MainActivity.this, Text4, Toast.LENGTH_SHORT).show();
                    if (32 < degree && degree < 39)
                        Toast.makeText(MainActivity.this, Text3, Toast.LENGTH_SHORT).show();
                    if (38 < degree && degree < 46)
                        Toast.makeText(MainActivity.this, Text2, Toast.LENGTH_SHORT).show();
                    if (45 < degree && degree < 52)
                        Toast.makeText(MainActivity.this, Text1, Toast.LENGTH_SHORT).show();
                }

                String Yes = getResources().getString(R.string.Yes);
                String No = getResources().getString(R.string.No);

                if(tag == "tag2") { // Toast dla Yes/No
                    if (degree < 7)
                        Toast.makeText(MainActivity.this, Yes, Toast.LENGTH_SHORT).show();
                    if (6 < degree && degree < 13)
                        Toast.makeText(MainActivity.this, No, Toast.LENGTH_SHORT).show();
                    if (12 < degree && degree < 20)
                        Toast.makeText(MainActivity.this, Yes, Toast.LENGTH_SHORT).show();
                    if (19 < degree && degree < 26)
                        Toast.makeText(MainActivity.this, No, Toast.LENGTH_SHORT).show();
                    if (25 < degree && degree < 33)
                        Toast.makeText(MainActivity.this, Yes, Toast.LENGTH_SHORT).show();
                    if (32 < degree && degree < 39)
                        Toast.makeText(MainActivity.this, No, Toast.LENGTH_SHORT).show();
                    if (38 < degree && degree < 46)
                        Toast.makeText(MainActivity.this, Yes, Toast.LENGTH_SHORT).show();
                    if (45 < degree && degree < 52)
                        Toast.makeText(MainActivity.this, No, Toast.LENGTH_SHORT).show();
                }

            }
        };

        Tween.set(wheel, WheelAccessor.ROTATION).target(0).start(tweenManager);
        Tween.to(wheel, WheelAccessor.ROTATION, (float) 3.6).target(2880+degree*7).setCallback(TweenCall).start(tweenManager);

        MediaPlayer wheelMP = MediaPlayer.create(this, R.raw.wheelsound);
        wheelMP.start();

    }

    public void Next(View v){
        ImageView wheel = (ImageView) findViewById(R.id.wheel_napi);
        wheel.setImageResource(R.drawable.wheel_napi2);
        wheel.setTag("tag2");
        /*Object tag2 = wheel.getTag();
        Toast.makeText(MainActivity.this, (CharSequence) tag2, Toast.LENGTH_SHORT).show(); */
    }

    public void Prev(View v){
        ImageView wheel = (ImageView) findViewById(R.id.wheel_napi);
        wheel.setImageResource(R.drawable.wheel_napisy);
         wheel.setTag("tag1");
        /* Object tag1 = wheel.getTag();
        //Toast.makeText(MainActivity.this, (CharSequence) tag1, Toast.LENGTH_SHORT).show(); */
    }

    private void setAnimationThread() {

        new Thread(new Runnable() {
            private long lastMillis = -1;

            @Override public void run() {
                while (isAnimationRunning) {
                    if (lastMillis > 0) {
                        long currentMillis = System.currentTimeMillis();
                        final float delta = (currentMillis - lastMillis) / 1000f;

            /*
            view.post(new Runnable(){
              @Override public void run() {

              }
            });
            */
                        /**
                         * We run all animation in UI thread instead of using post for each elements.
                         */
                        runOnUiThread(new Runnable() {

                            @Override public void run() {
                                tweenManager.update(delta);

                            }
                        });

                        lastMillis = currentMillis;
                    } else {
                        lastMillis = System.currentTimeMillis();
                    }

                    try {
                        Thread.sleep(1000 / 60);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }).start();

    }
    /**
     * Stop animation thread
     */
    private void setAnimationFalse() {
        isAnimationRunning = false;
    }
    /**
     * Make animation thread alive
     */
    private void setAnimationTrue() {isAnimationRunning = true;}

}





