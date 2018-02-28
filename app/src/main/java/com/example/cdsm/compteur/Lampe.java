package com.example.cdsm.compteur;

/**
 * Created by cdsm on 2/14/18.
 */

public class Lampe
{
    Boolean EtatLampe;

    public Lampe()
    {
        EtatLampe = false;
    }
    public void AllumeLampe()
    {
        EtatLampe = true;
    }
    public void EteintLampe()
    {
        EtatLampe = false;
    }
    public Boolean DonneEtat()
    {
        return EtatLampe;
    }

}