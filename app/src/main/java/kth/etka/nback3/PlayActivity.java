package kth.etka.nback3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    private Grid grid;
    private Logic logic;
    private SettingsActivity settingsActivity;
    private SharedPreferences shared;


    //private String sharedPref = "kth.etka.nback3.sharedpref";
    //SharedPreferences sharedPreferences = getSharedPreferences("sharedPref",MODE_PRIVATE);
    //shared = getSharedPreferences(sharedPref, MODE_PRIVATE);

    private boolean clickedPos = false;
    private boolean clickedAud = false;

    private boolean Pos, Aud;
    private int secs, nVal;
    private int score = 0;

    Button buttonPos, buttonAud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        grid = findViewById(R.id.Grid);
        logic = Logic.getInstance();

        buttonPos = findViewById(R.id.positionButton);
        buttonPos.setOnClickListener(this::didPos);

        buttonAud = findViewById(R.id.audioButton);
        buttonAud.setOnClickListener(this::didAud);

        Settings();
    }

    private void play() {
        if (Pos && !Aud){
            playPos();
        }
        else if (!Pos && Aud){
            playAud();
        }
        else if (Pos && Aud){
            playPosAud();
        }
        else System.out.println("No game type chosen");
    }

    private void playPosAud() {
        for (int i = 0; i<randomArr().length; i++){
            Draw(randomArr()[i]);
            if (i >= nVal){
                if (clickedPos) {
                    if (randomArr()[i] == randomArr()[i-nVal) {
                        score += 1;
                    }
                }
                else{score -= 1;}
            }
            // Bæta inn fylki fyrir audio spilara
            if (clickedAud){
                if (randomArr()[i] == randomArr()[i-nVal]){
                    score += 1;
                }
            }
            else{score -= 1;}
        }
    }

    private void playAud() {
        for (int i = 0; i< randomArr().length; i++){
            Draw(randomArr()[i]);
            if (i >= nVal){
                if (clickedAud){
                    if (randomArr()[i] == randomArr()[i-nVal]){
                        score += 1;
                    }
                }
                else{score -= 1;}
            }
        }
    }

    private void playPos() {
        for (int i = 1; i<= randomArr().length; i++){
            Draw(randomArr()[i]);
            if (i >= nVal){
                if (clickedPos) {
                    if (randomArr()[i] == randomArr()[i-nVal]) {
                        score += 1;
                    }
                }
                else{score -= 1;}
            }
        }
    }


    public void Settings(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("sharedpref",MODE_PRIVATE);
        nVal = sharedPreferences.getInt("CounterValue",1);
        secs = sharedPreferences.getInt("CounterSeconds", 1);
        Pos = sharedPreferences.getBoolean("PosIsChecked", true);
        Aud = sharedPreferences.getBoolean("AudIsChecked", false);

        Log.v("Pos",Boolean.toString(Pos));
        play();
    }

    private void didAud(View view) {
        clickedAud = true;
    }

    private void didPos(View view) {
        clickedPos = true;
    }

    //Define an array of 20 random numbers from 1-9
    private int[] randomArr(){
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(9) + 1; // storing random integers in an array
            System.out.println(arr[i]); // printing each array element
        }
        return arr;
    }

    private void Draw(int random){
        int row = 0;
        int col = 0;
        if (random == 2){
            col = 1;
        }
        if (random == 3){
            col = 2;
        }
        if (random == 4){
            row = 1;
        }
        if (random == 5){
            row = 1;
            col = 1;
        }
        if (random == 6){
            row = 1;
            col = 2;
        }
        if (random == 7){
            row = 2;
        }
        if (random == 8){
            row = 2;
            col = 1;
        }
        if (random == 9){
            row = 2;
            col = 2;
        }
        grid.fadeInDrawable();
    }

    /*private boolean shouldPos(boolean should){
        if (randomArr()[i] == randomArr()[i+1]){
            should = true;
        }
    }*/

    /*
    private boolean shouldPos(boolean should) {
        int[] arr = randomArr();
        should = false;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                should = true;
            }
        }
        return should;
    }*/

  /*  private boolean shouldAud(boolean should) {
        int[] arr = randomArr();
        should = false;
        //for (int i = 1; i < arr.length; i++) {
            if (arr[index] == arr[i - 1]) {
                should = true;
            }
        //}
        return should;
    }*/
}