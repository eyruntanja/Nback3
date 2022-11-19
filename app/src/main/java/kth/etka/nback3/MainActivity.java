package kth.etka.nback3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button buttonPlay, buttonSettings;


    //SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlay = findViewById(R.id.play);
        buttonPlay.setOnClickListener(this::play);

        buttonSettings = findViewById(R.id.settings);
        buttonSettings.setOnClickListener(this::settings);
    }

    @Override
    protected void onResume() {
        // Fetching the stored data
        // from the SharedPreference
        super.onResume();
        //SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        //int a = sh.getInt(counterValue);

        // Setting the fetched data
        // in the EditTexts
        // name.setText(s1);
        // age.setText(String.valueOf(a));
    }

    private void settings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    private void play(View view) {
        startActivity(new Intent(this, PlayActivity.class));
    }

}