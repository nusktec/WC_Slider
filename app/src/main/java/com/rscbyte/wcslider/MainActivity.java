package com.rscbyte.wcslider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> imageFiles = new ArrayList<>();
    private int _interval = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        //apply shared preference
        _interval = getSharedPreferences(Settings._sPreference, MODE_PRIVATE).getInt(Settings._sPreference, 5000);
        //fetch images
        String path = Splash.imageFolder;
        File directory = new File(path);
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            imageFiles.add(file.getAbsolutePath());
        }
        //fire function to load images
        sliderHere();
        init_component();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            this.setTurnScreenOn(true);
        } else {
            final Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        }

    }

    private int _start = 0;
    private int _end = 0;
    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (imageFiles.size() > 0) {
                _end = imageFiles.size() - 1;

                File imgFile = new File(imageFiles.get(_start));

                if (imgFile.exists()) {

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                    ImageView myImage = findViewById(R.id.img);

                    myImage.setImageBitmap(myBitmap);

                }
                _start++;
                if (_start > _end) {
                    _start = 0;
                }
                handler.postDelayed(runnable, _interval);
            }
        }
    };

    //loop images
    void sliderHere() {
        handler.postDelayed(runnable, 1000);
    }

    //main controller
    void init_component() {
        findViewById(R.id.btn_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Settings.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }
}
