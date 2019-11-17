package com.rscbyte.wcslider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class Splash extends AppCompatActivity {

    public static String imageFolder = Environment.getExternalStorageDirectory() +
            File.separator + "wc_slider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //start new class after 5secs
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //create folder
                File folder = new File(imageFolder);
                boolean success = true;
                if (!folder.exists()) {
                    success = folder.mkdirs();
                }
                if (success) {
                    // Do something on success
                } else {
                    // Do something else on failure
                }
                startActivity(new Intent(Splash.this, MainActivity.class));
            }
        }, 4000);
    }
}
