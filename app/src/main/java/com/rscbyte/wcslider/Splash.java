package com.rscbyte.wcslider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
                    success = folder.mkdir();
                }
                if (success) {
                    // Do something on success
                    startActivity(new Intent(Splash.this, MainActivity.class));
                    finish();
                } else {
                    // Do something else on failure
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Splash.this);
                    dialog.setTitle("Warning !");
                    dialog.setMessage("Unable to create root folder 'wc_slider'\nMake sure you have grant it's permission and try again !");
                    dialog.setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.create().show();
                }
            }
        }, 4000);
    }
}
