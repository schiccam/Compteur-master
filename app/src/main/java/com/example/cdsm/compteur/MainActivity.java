package com.example.cdsm.compteur;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    Boolean TestCompteurActif;

    TextView[] textTab;
    TextView text0;
    TextView text1;
    TextView text2;
    TextView text3;

    ImageView image;

    int limite = 0;
    int debut = 0;
    Boolean testRotation = false;


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

        TestCompteurActif = false;


        // Récupération des données après rotation
        if(savedInstanceState != null)
        {
            limite = savedInstanceState.getInt("saveL");
            debut = savedInstanceState.getInt("saveD");
            testRotation = savedInstanceState.getBoolean("saveR");
            // Affichage de la valeur après la rotation
            int temp = debut;
            for(int i = 3;i > -1;i--)
            {
                textTab[i].setText(String.valueOf(temp%10));
                temp = temp/10;
            }

            /*Log.i("Limite =========",String.valueOf(limite));
            Log.i("Debut =========",String.valueOf(debut));*/
        }


        // Initialisation du compteur
        UnCpt = new CompteurADAL(4, debut, limite);

        // Affichage du compteur au 1er démarage de l'application
        if(!testRotation)
            CompteurRAZ();



        // Bouton vers l'activité Settings
        boutonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivityForResult(intent, 1);
            }
        });



        // Bouton Start
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // if pour empêcher le click multiple
                if (TestCompteurActif == false)
                {
                    TestCompteurActif = true;
                    timer = new CountDownTimer(Long.MAX_VALUE, 10) {
                        public void onTick(long millisUntilFinished) {


                            UnCpt.Incrementer();
                            Compteur = UnCpt.getCompteur();
                            for (int i = 0; i < 4; i++) {
                                textTab[i].setText(String.valueOf(Compteur[i].getValeur()));
                            }
                             // Arrêt du compteur quand la limite est atteinte
                             if(UnCpt.GetLampe()){

                                timer.cancel();
                                TestCompteurActif = false;
                                image.setImageResource(R.drawable.allumee);
                            }
                        }
                        public void onFinish(){
                            TestCompteurActif = false;
                        }

                    }.start();
                }

            }



        });
        // Affichage des boutons après la rotation
        if(testRotation)
        {
            button.setVisibility(View.VISIBLE);
            boutonSet.setVisibility(View.INVISIBLE);
            boutonRAZ.setVisibility(View.VISIBLE);
            button.performClick();
        }

    }
    // Récupération des données depuis l'activité Settings
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == Activity.RESULT_OK){
            debut = intent.getIntExtra("Debut", debut);
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
   // Initialisation des 4 TextView à 0
    public void CompteurRAZ()
    {
        for (int i = 0; i < 4; i++) {
            textTab[i].setText("0");
        }
    }

    public void buttonRAZ_Clicked(View view)
    {
        CompteurRAZ();
        TestCompteurActif = false;
        image.setImageResource(R.drawable.eteinte);
        button.setVisibility(View.INVISIBLE);
        boutonSet.setVisibility(View.VISIBLE);
        boutonRAZ.setVisibility(View.INVISIBLE);
        try{
            timer.cancel();
        }
        catch (Exception e){
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (limite != 0 && debut != 0 && TestCompteurActif)
        {
            String saveCompteur = "";

            for (int i = 0; i < 4; i++) {
                saveCompteur = saveCompteur + textTab[i].getText();
            }

            int Conversion = Integer.parseInt(saveCompteur);

            outState.putInt("saveL",limite);
            outState.putInt("saveD",Conversion);
            outState.putBoolean("saveR",true);

        }

    }
}
