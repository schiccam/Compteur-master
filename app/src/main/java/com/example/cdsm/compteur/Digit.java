package com.example.cdsm.compteur;

import android.widget.TextView;

/**
 * Created by cdsm on 2/14/18.
 */


public class Digit
{
    int Valeur;

    public Digit()
    {
        Valeur = 0;
    }

    public Digit(int Val)
    {
        if(Val < 0 || Val > 9)
            setValeur(0);
        else
            setValeur(Val);
    }

    public void Initialiser(int Val)
    {
        if(Val < 0 || Val > 9)
            setValeur(0);
        else
            setValeur(Val);
    }

    public void AfficherDigit()
    {
        System.out.print("|"+getValeur()+"|");

    }

    public Boolean Incrementer()
    {
        Valeur++;
        if(Valeur == 10)
        {
            Valeur = 0;
            return true;
        }
        else
            return false;
    }

    public int getValeur() {
        return Valeur;
    }

    private void setValeur(int valeur) {
        Valeur = valeur;
    }
}