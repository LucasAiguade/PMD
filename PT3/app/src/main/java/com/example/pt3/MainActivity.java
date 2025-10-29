package com.example.pt3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int onCreateCounter = 0;
    private int onStartCounter = 0;
    private int onRestartCounter = 0;
    private int onResumeCounter = 0;
    private int onPauseCounter = 0;
    private int onStopCounter = 0;
    private int onDestroyCounter = 0;

    private TextView textView_onCreateCounter;
    private TextView textView_onStartCounter;
    private TextView textView_onRestartCounter;
    private TextView textView_onResumeCounter;
    private TextView textView_onPauseCounter;
    private TextView textView_onStopCounter;
    private TextView textView_onDestroyCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_onCreateCounter = findViewById(R.id.textView_onCreateCounter);
        textView_onStartCounter = findViewById(R.id.textView_onStartCounter);
        textView_onRestartCounter = findViewById(R.id.textView_onRestartCounter);
        textView_onResumeCounter = findViewById(R.id.textView_onResumeCounter);
        textView_onPauseCounter = findViewById(R.id.textView_onPauseCounter);
        textView_onStopCounter = findViewById(R.id.textView_onStopCounter);
        textView_onDestroyCounter = findViewById(R.id.textView_onDestroyCounter);

        // Recuperar los contadores de SharedPreferences
        SharedPreferences prefs = getSharedPreferences("CicloVidaPrefs", MODE_PRIVATE);
        onCreateCounter = prefs.getInt("onCreateCounter", 0);
        onStartCounter = prefs.getInt("onStartCounter", 0);
        onRestartCounter = prefs.getInt("onRestartCounter", 0);
        onResumeCounter = prefs.getInt("onResumeCounter", 0);
        onPauseCounter = prefs.getInt("onPauseCounter", 0);
        onStopCounter = prefs.getInt("onStopCounter", 0);
        onDestroyCounter = prefs.getInt("onDestroyCounter", 0);

        // Incrementar el contador y actualizar la UI en onCreate
        onCreateCounter++;
        textView_onCreateCounter.setText(String.valueOf(onCreateCounter));
    }

    @Override
    protected void onStart() {
        super.onStart();
        onStartCounter++;
        textView_onStartCounter.setText(String.valueOf(onStartCounter));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onRestartCounter++;
        textView_onRestartCounter.setText(String.valueOf(onRestartCounter));
    }

    @Override
    protected void onResume() {
        super.onResume();
        onResumeCounter++;
        textView_onResumeCounter.setText(String.valueOf(onResumeCounter));
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPauseCounter++;
        textView_onPauseCounter.setText(String.valueOf(onPauseCounter));
    }

    @Override
    protected void onStop() {
        super.onStop();
        onStopCounter++;
        textView_onStopCounter.setText(String.valueOf(onStopCounter));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyCounter++;
        textView_onDestroyCounter.setText(String.valueOf(onDestroyCounter));

        // Guardar los contadores en SharedPreferences antes de destruir la actividad
        SharedPreferences prefs = getSharedPreferences("CicloVidaPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("onCreateCounter", onCreateCounter);
        editor.putInt("onStartCounter", onStartCounter);
        editor.putInt("onRestartCounter", onRestartCounter);
        editor.putInt("onResumeCounter", onResumeCounter);
        editor.putInt("onPauseCounter", onPauseCounter);
        editor.putInt("onStopCounter", onStopCounter);
        editor.putInt("onDestroyCounter", onDestroyCounter);
        editor.apply();
    }
}
