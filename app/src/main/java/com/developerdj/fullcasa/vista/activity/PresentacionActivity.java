package com.developerdj.fullcasa.vista.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.developerdj.fullcasa.MainActivity;
import com.developerdj.fullcasa.R;

/**
 * Created by hp on 9/5/2017.
 */

public class PresentacionActivity extends AppCompatActivity implements AnimationListener {

    private ImageView imageView;

    // Animation
    Animation animFadein;

    // Animation
    Animation animFadeOut;

    public static void createInstance(Context context) {
        Intent intent = getLaunchIntent(context);

        context.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context) {
        Intent intent = new Intent(context, PresentacionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);

        imageView =(ImageView) findViewById(R.id.iv_imagen_presentacion);

        // load the animation
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        // set animation listener
        animFadein.setAnimationListener(this);

        // load the animation
        animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        // set animation listener
        animFadeOut.setAnimationListener(this);

        imageView.startAnimation(animFadein);



    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        // check for fade in animation
        if (animation == animFadein){
            imageView.startAnimation(animFadeOut);
        }
            //
        if (animation == animFadeOut){
            MainActivity.createInstance(this);
            finish();
        }
            //



    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
