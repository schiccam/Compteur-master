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
import android.widget.Toast;

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

            // Contrôle de la valeur pour début
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (bar1.getProgress()>=bar2.getProgress())
                {
                    bar1.setProgress(bar2.getProgress()-1);
                    Toast.makeText(getApplicationContext(),"Erreur : Début plus grand ou égal à Limite",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            //Affichage de la valeur dans un TextView
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                ValDeb.setText(String.valueOf(bar1.getProgress()));
            }
        });

        bar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            // Contrôle de la valeur pour limite
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (bar2.getProgress()<=bar1.getProgress())
                {
                    bar2.setProgress(bar1.getProgress()+1);
                    Toast.makeText(getApplicationContext(),"Erreur : Limite plus petit ou égal à Début",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            //Affichage de la valeur dans un TextView
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                ValLim.setText(String.valueOf(bar2.getProgress()));
            }
        });

        // Retour à l'activité principale
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
