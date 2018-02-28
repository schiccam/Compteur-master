package com.example.cdsm.compteur;

/**
 * Created by cdsm on 2/14/18.
 */


public class CompteurADSL
{
    Digit Tab[];
    int Valeur;
    int Limite;


    public CompteurADSL(int Val)
    {
        if(Val < 1 || Val > 9)
            Tab = new Digit[9];
        else
            Tab = new Digit[Val];

        Valeur = 0;
        Limite = 0;
    }

    public CompteurADSL(int ValDepart, int ValLimite)
    {
        Valeur = ValDepart;
        Limite = ValLimite;
        int TailleTab = String.valueOf(Limite).length();

        Tab = new Digit[TailleTab];

        for(int i = (TailleTab - 1);i > -1;i--)
        {
            Tab[i] = new Digit(Valeur%10);
            Valeur = Valeur/10;
        }

    }

    public void Initialiser(int ValDepart, int ValLimite)
    {
        Valeur = ValDepart;
        Limite = ValLimite;
        int TailleTab = Tab.length;


        for(int i = (TailleTab - 1);i > -1;i--)
        {
            Tab[i] = new Digit(Valeur%10);
            Valeur = Valeur/10;
        }
    }

    public void Incrementer()
    {

        int TailleTab = Tab.length;
        int i = TailleTab-2;
        Boolean Test = false;
        if(ControlCompteur() == false) {


            Test = Tab[TailleTab - 1].Incrementer();
            if (Test == true) {
                while (Test == true) {
                    Test = Tab[i].Incrementer();
                    i--;
                }
            }
        }

    }

    public void Afficher()
    {
        for (Digit ElemTab : Tab)
        {
            ElemTab.AfficherDigit();
        }
        System.out.println();
    }

    public int Compare()
    {
        StringBuilder strNum = new StringBuilder();

        for (Digit num : Tab)
        {
            strNum.append(num.getValeur());
        }

        int ValCpt = Integer.parseInt(strNum.toString());

        return ValCpt-Limite;
    }

    public Digit[] getTab()
    {
        return Tab;
    }


    private Boolean ControlCompteur()
    {
        StringBuilder strNum = new StringBuilder();

        for (Digit num : Tab)
        {
            strNum.append(num.getValeur());
        }

        int ValCpt = Integer.parseInt(strNum.toString());

        if(ValCpt == Limite)
            return true;
        else
            return false;
    }
}