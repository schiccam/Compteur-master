package com.example.cdsm.compteur;

/**
 * Created by cdsm on 2/14/18.
 */


public class CompteurADAL
{
    Lampe lampe;
    CompteurADSL cptADSL;

    public CompteurADAL(int Taille, int Debut,int Limite)
    {
        lampe = new Lampe();
        cptADSL = new CompteurADSL(Taille);
        cptADSL.Initialiser(Debut,Limite);

    }

    public void Incrementer()
    {
        cptADSL.Incrementer();
        if(cptADSL.Compare() == 0)
        {
            lampe.AllumeLampe();
        }

    }

    public void Afficher()
    {
        cptADSL.Afficher();
        Boolean EtatLampe = lampe.DonneEtat();

        if(EtatLampe == true)
        {
            System.out.println("La lampe est allumé");
        }
        else
            System.out.println("La lampe est éteinte");
    }

    public Digit[] getCompteur()
    {
        return cptADSL.getTab();
    }

    public boolean GetLampe(){
        return lampe.EtatLampe;
    }
}