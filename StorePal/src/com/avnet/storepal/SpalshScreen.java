package com.avnet.storepal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SpalshScreen extends Activity {

	private static int SPLASH_TIME_OUT = 3000;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spalsh_screen);
		
		String fontPath = "fonts/texgyreschola-regular.otf";

        // text view label
        TextView txtGhost = (TextView) findViewById(R.id.textView1);

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        // Applying font
        txtGhost.setTypeface(tf);
        
        View circleView = (View)findViewById(R.id.view1);
        Animation animation = AnimationUtils.loadAnimation(this, R.animator.splashscreen_animator);
        animation.setDuration(200);
        circleView.startAnimation(animation);
        
        circleView = (View)findViewById(R.id.view2);
        animation = AnimationUtils.loadAnimation(this, R.animator.splashscreen_animator);
        animation.setDuration(220);
        circleView.startAnimation(animation);
        
        circleView = (View)findViewById(R.id.view3);
        animation = AnimationUtils.loadAnimation(this, R.animator.splashscreen_animator);
        animation.setDuration(240);
        circleView.startAnimation(animation);
        
        circleView = (View)findViewById(R.id.view4);
        animation = AnimationUtils.loadAnimation(this, R.animator.splashscreen_animator);
        animation.setDuration(260);
        circleView.startAnimation(animation);
        
        new Handler().postDelayed(new Runnable() {
        	 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SpalshScreen.this, MainActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
	}

}
