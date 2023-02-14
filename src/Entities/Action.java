package Entities;

public class Action
{
    private int idAction;
    private String nomAction;
    private double coursReelAction;
    private double prixAchatAction;
    private int quantiteAchetee;

    public Action(int unId, String unNom,double unCours, double unPrix,int uneQuantite)
    {
        idAction = unId;
        nomAction = unNom;
        coursReelAction = unCours;
        prixAchatAction = unPrix;
        quantiteAchetee = uneQuantite;
    }

    public int getIdAction() {
        return idAction;
    }

    public String getNomAction() {
        return nomAction;
    }

    public double getCoursReelAction() {
        return coursReelAction;
    }

    public double getPrixAchatAction() {
        return prixAchatAction;
    }

    public int getQuantiteAchetee() {
        return quantiteAchetee;
    }
    public void setQuantiteAchetee(int quantiteAchetee) {
        this.quantiteAchetee = quantiteAchetee;
    }
}
