package com.example.cdsm.compteur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    SeekBar bar1;
    SeekBar bar2;
    Button bouton;
    TextView ValDeb, ValLim;
    int debut;
    int limite;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        bar1 = (SeekBar)findViewById(R.id.seekBar);
        bar2 = (SeekBar)findViewById(R.id.seekBar2);
        bouton = (Button)findViewById(R.id.button3);
        ValDeb = (TextView)findViewById(R.id.tvValeurDeb);
        ValLim = (TextView)findViewById(R.id.tvValeurLim);

        ValDeb.setText(String.valueOf(bar1.getProgress()));
        ValLim.setText(String.valueOf(bar2.getProgress()));

        bar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub

                ValDeb.setText(String.valueOf(bar1.getProgress()));


            }
        });

        bar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub

                ValLim.setText(String.valueOf(bar2.getProgress()));


            }
        });

        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                debut = bar1.getProgress();
                limite = bar2.getProgress();
                Log.i("    Debut la !!!!! :   ", String.valueOf(debut));
                Log.i("   Limite la !!!!! :   ", String.valueOf(limite));

                Intent intent = new Intent();
                intent.putExtra("Debut", debut);
                intent.putExtra("Limite", limite);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
