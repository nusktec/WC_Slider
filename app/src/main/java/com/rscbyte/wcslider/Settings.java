package com.rscbyte.wcslider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    public static String _sPreference = "_interval";
    private int _interval = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //get default from share preference
        _interval = getBaseContext().getSharedPreferences(_sPreference, MODE_PRIVATE).getInt(_sPreference, 5000);
        ((EditText) findViewById(R.id.txt_interval)).setText(String.valueOf(_interval));
        initializer();
    }

    void initializer() {
        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_int = ((EditText) findViewById(R.id.txt_interval)).getText().toString();
                if (!TextUtils.isEmpty(txt_int)) {
                    //compare it
                    if (Integer.parseInt(txt_int) >= 5000) {
                        _interval = Integer.parseInt(txt_int);
                        getBaseContext().getSharedPreferences(_sPreference, MODE_PRIVATE).edit().putInt(_sPreference, _interval).apply();
                        Toast.makeText(getBaseContext(), "Settings saved...", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getBaseContext(), "interval too small", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
