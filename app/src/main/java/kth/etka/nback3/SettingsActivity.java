package kth.etka.nback3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    Integer counterValue = 1;
    Integer counterSeconds = 1;

    TextView counterPos, counterSec;

    SharedPreferences sharedPreferences;
    private SharedPreferences shared;
    String sharedPref = "kth.etka.nback3.sharedpref";

    CheckBox checkPos, checkAud;
    Button secPlus, secMinus, valPlus, valMinus;
    Boolean PosIsChecked, AudIsChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        counterPos = findViewById(R.id.valueCounter);
        counterSec = findViewById(R.id.secCounter);

        shared = getSharedPreferences(sharedPref, MODE_PRIVATE);

        secMinus = findViewById(R.id.minusSecButton);
        secMinus.setOnClickListener(this::onMinSec);

        secPlus = findViewById(R.id.plusSecButton);
        secPlus.setOnClickListener(this::onPlusSec);

        valMinus = findViewById(R.id.minusButton);
        valMinus.setOnClickListener(this::onMinVal);

        valPlus = findViewById(R.id.plusButton);
        valPlus.setOnClickListener(this::onPlusVal);

        checkPos = findViewById(R.id.checkPosition);
        PosIsChecked = checkPos.isChecked();

        checkAud = findViewById(R.id.checkAudio);
        AudIsChecked = checkAud.isChecked();
    }
    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor preferencesEditor = shared.edit();

        preferencesEditor.putInt("CounterValue", counterValue);
        preferencesEditor.putInt("CounterSeconds", counterSeconds);
        preferencesEditor.putBoolean("PosIsChecked", PosIsChecked);
        preferencesEditor.putBoolean("AudIsChecked", AudIsChecked);

        preferencesEditor.apply();
    }

    public Boolean getPosIsChecked(){return PosIsChecked;}
    public Boolean getAudIsChecked(){return AudIsChecked;}

    public Integer getCounterValue() {return counterValue;}
    public Integer getCounterSeconds() {return counterSeconds;}

    public void onAudClicked(View view){
        AudIsChecked = true;
        //String yup = shared.getString("CounterValue","1");
        //Log.d("counterValue",yup);
    }

    public void onPosClicked(View view){
        PosIsChecked = true;
    }

    private void onPlusVal(View view) {
        counterValue += 1;
        counterPos.setText(String.valueOf(counterValue));
        Log.d("counter Value hér",Integer.toString(counterValue));
    }
    private void onMinVal(View view) {
        if (counterValue > 1){
            counterValue -= 1;}
        counterPos.setText(String.valueOf(counterValue));
    }
    private void onPlusSec(View view) {
        counterSeconds += 1;
        counterSec.setText(String.valueOf(counterSeconds));
    }
    private void onMinSec(View view) {
        if (counterSeconds > 1){
            counterSeconds -= 1;
        }
        counterSec.setText(String.valueOf(counterSeconds));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Creating an Editor object to edit(write to the file)
        //SharedPreferences.Editor myEdit = sharedPreferences.edit();
        SharedPreferences.Editor myEdit = shared.edit();

        // Storing the key and its value as the data fetched from edittext
        myEdit.putInt("CounterValue", counterValue);
        myEdit.putInt("CounterSeconds", counterSeconds);
        myEdit.putBoolean("PosIsChecked", PosIsChecked);
        myEdit.putBoolean("AudIsChecked", AudIsChecked);
        //save stuff

        // Once the changes have been made,
        // we need to commit to apply those changes made,
        // otherwise, it will throw an error
        myEdit.apply();
    }
}