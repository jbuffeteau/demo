package Entities;

import java.util.ArrayList;

public class Trader
{
    private int idTrader;
    private String nomTrader;
    private ArrayList<Action> lesActionsDuTrader;

    public Trader(int unId, String unNom)
    {
        idTrader = unId;
        nomTrader = unNom;
        lesActionsDuTrader = new ArrayList<>();
    }

    public int getIdTrader() {
        return idTrader;
    }

    public String getNomTrader() {
        return nomTrader;
    }

    public ArrayList<Action> getLesActionsDuTrader() {
        return lesActionsDuTrader;
    }

    public void AjouterUneAction(Action uneAction)
    {
        lesActionsDuTrader.add(uneAction);
    }
}
