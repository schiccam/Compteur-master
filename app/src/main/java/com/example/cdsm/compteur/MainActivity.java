package com.example.cdsm.compteur;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button button, boutonSet, boutonRAZ;
    CompteurADAL UnCpt;
    Digit[] Compteur;

    TextView[] textTab;
    TextView text0;
    TextView text1;
    TextView text2;
    TextView text3;

    ImageView image;

    int limite = 0;
    int debut = 0;

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        image = (ImageView)findViewById(R.id.image);
        boutonSet = (Button)findViewById(R.id.button2);
        boutonRAZ = findViewById(R.id.buttonRAZ);


        text0 = (TextView)findViewById(R.id.text0);
        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);

        textTab = new TextView[4];
        textTab[0] = text0;
        textTab[1] = text1;
        textTab[2] = text2;
        textTab[3] = text3;


        UnCpt = new CompteurADAL(4, debut, limite);
        CompteurRAZ();


        boutonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Settings.class);

                startActivityForResult(intent, 1);
            }
        });




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 timer = new CountDownTimer(Long.MAX_VALUE, 10) {
                        public void onTick(long millisUntilFinished) {


                            UnCpt.Incrementer();
                            Compteur = UnCpt.getCompteur();
                            for (int i = 0; i < 4; i++) {
                                textTab[i].setText(String.valueOf(Compteur[i].getValeur()));
                            }

                            if(UnCpt.GetLampe()){

                                timer.cancel();
                                image.setImageResource(R.drawable.allumee);
                            }
                        }
                        public void onFinish(){

                        }

                    }.start();
                }

        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == Activity.RESULT_OK){
            debut = intent.getIntExtra("Debut", 0);
            limite = intent.getIntExtra("Limite",limite);
            UnCpt = new CompteurADAL(4, debut, limite);
            Compteur = UnCpt.getCompteur();
            for (int i = 0; i < 4; i++) {
                textTab[i].setText(String.valueOf(Compteur[i].getValeur()));
            }
            button.setVisibility(View.VISIBLE);
            boutonSet.setVisibility(View.INVISIBLE);
            boutonRAZ.setVisibility(View.VISIBLE);

        }

    }

    public void CompteurRAZ()
    {
        for (int i = 0; i < 4; i++) {
            textTab[i].setText("0");
        }
    }

    public void buttonRAZ_Clicked(View view)
    {
        CompteurRAZ();
        image.setImageResource(R.drawable.eteinte);
        button.setVisibility(View.INVISIBLE);
        boutonSet.setVisibility(View.VISIBLE);
        boutonRAZ.setVisibility(View.INVISIBLE);
        timer.cancel();
    }
}
