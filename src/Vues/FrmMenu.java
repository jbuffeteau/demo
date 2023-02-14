package Vues;

import Entities.Trader;
import Entities.Action;
import Tools.ModelJTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FrmMenu extends JFrame
{
    int numAction;
    int numTrader;
    ArrayList<Trader> mesTraders;
    ModelJTable mdlTraders;
    ModelJTable mdlActions;
    private JLabel lblTitre;
    private JLabel lblTrader;
    private JLabel lblAction;
    private JTable tblTraders;
    private JTable tblActions;
    private JScrollPane jspActions;
    private JLabel lblMontantPortefeuille;
    private JLabel lblMessage;
    private JTextField txtQuantiteVendue;
    private JButton btnVendre;
    private JLabel lblQuantiteVendue;
    private JPanel pnlRoot;

    public FrmMenu()
    {
        this.setTitle("TP3 - POO");
        this.setContentPane(pnlRoot);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        mesTraders = new ArrayList<>();
        LoadDatas();
        mdlTraders = new ModelJTable();
        mdlTraders.loadDatasAgents(mesTraders);
        tblTraders.setModel(mdlTraders);

        btnVendre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(tblActions.getSelectedRowCount() == 0)
                {
                    JOptionPane.showMessageDialog(null, "Sélectionner une action", "Choix de l'action", JOptionPane.ERROR_MESSAGE);
                }
                else if(txtQuantiteVendue.getText().compareTo("")==0)
                {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir une quantité", "Erreur quantité", JOptionPane.ERROR_MESSAGE);
                }
                else if(Integer.parseInt(txtQuantiteVendue.getText())>Integer.parseInt(tblActions.getValueAt(tblActions.getSelectedRow(), 4).toString()))
                {
                    JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vendre\nplus que ce que vous possédez", "Quantité vendue", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    Trader letrader = mesTraders.stream().filter(trader-> trader.getIdTrader() == numTrader).collect(Collectors.toList()).get(0);
                    //(mesTraders.stream().findFirst().filter(code->code.getIdTrader()==numTrader))
                    for(Trader trad : mesTraders)
                    {
                        if(trad.getIdTrader() == numTrader)
                        {
                            for(Action act : trad.getLesActionsDuTrader())
                            {
                                if(act.getIdAction() == numAction)
                                {
                                    if(Integer.parseInt(txtQuantiteVendue.getText()) == Integer.parseInt(tblActions.getValueAt(tblActions.getSelectedRow(), 4).toString()))
                                    {
                                        // On supprimel'action
                                        trad.getLesActionsDuTrader().remove(act);
                                    }
                                    else
                                    {
                                        int qte = Integer.parseInt(tblActions.getValueAt(tblActions.getSelectedRow(), 4).toString()) - Integer.parseInt(txtQuantiteVendue.getText());
                                        act.setQuantiteAchetee(qte);
                                    }
                                }
                            }
                        }
                    }
                    lblMessage.setText("");
                    RemplirJTableActions();
                }
            }
        });

        tblTraders.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mdlActions = new ModelJTable();
                numTrader = Integer.parseInt(tblTraders.getValueAt(tblTraders.getSelectedRow(),0).toString());
                RemplirJTableActions();
            }
        });
        tblActions.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                numAction = Integer.parseInt(tblActions.getValueAt(tblActions.getSelectedRow(), 0).toString());
                for(Trader trad : mesTraders)
                {
                    if(trad.getIdTrader() == numTrader)
                    {
                        for(Action act : trad.getLesActionsDuTrader())
                        {
                            if(act.getIdAction() == numAction)
                            {
                                double gain = Math.round((act.getCoursReelAction() * act.getQuantiteAchetee())-(act.getPrixAchatAction() * act.getQuantiteAchetee()));
                                if(gain >=0)
                                {
                                    lblMessage.setText("Vous gagnez de l'argent sur cette action : " +String.valueOf(gain));
                                }
                                else
                                {
                                    lblMessage.setText("Vous perdez de l'argent sur cette action : " +String.valueOf(gain));
                                }
                            }
                        }
                    }
                }
            }
        });
    }



    public void LoadDatas()
    {
        Trader trad1 = new Trader(1,"Enzo");
        Trader trad2 = new Trader(2,"Noa");
        Trader trad3 = new Trader(3,"Lilou");
        Trader trad4 = new Trader(4,"Milo");

        Action act1 = new Action(1,"Twitter",169.15,159,110);
        Action act2 = new Action(2,"Apple",171.89,173,54);
        Action act3 = new Action(3,"Facebook",105.67,98.45,145);
        Action act4 = new Action(4,"Microsoft",110,113.08,32);
        Action act5 = new Action(5,"Dell",56.12,54,78);
        Action act6 = new Action(6,"VMWare",121.56,123.91,43);
        Action act7 = new Action(7,"IBM",42.61,40.98,126);

        trad1.AjouterUneAction(act1);
        trad1.AjouterUneAction(act2);
        trad1.AjouterUneAction(act7);

        trad2.AjouterUneAction(act1);
        trad2.AjouterUneAction(act3);
        trad2.AjouterUneAction(act4);
        trad2.AjouterUneAction(act5);

        trad3.AjouterUneAction(act6);
        trad3.AjouterUneAction(act7);

        trad4.AjouterUneAction(act4);
        trad4.AjouterUneAction(act5);
        trad4.AjouterUneAction(act1);

        mesTraders.add(trad1);mesTraders.add(trad2);
        mesTraders.add(trad3);mesTraders.add(trad4);
    }

    public void RemplirJTableActions()
    {
        double montantReel = 0;
        double montantAchete = 0;
        for(Trader trad : mesTraders)
        {
            if(trad.getIdTrader() == numTrader)
            {
                mdlActions = new ModelJTable();
                mdlActions.loadDatasActions(trad.getLesActionsDuTrader());
                tblActions.setModel(mdlActions);
                for (Action act : trad.getLesActionsDuTrader())
                {
                    montantReel = montantReel + (act.getCoursReelAction() * act.getQuantiteAchetee());
                    montantAchete = montantAchete + (act.getPrixAchatAction() * act.getQuantiteAchetee());
                }
                lblMontantPortefeuille.setText(String.valueOf(Math.round(montantReel - montantAchete)));
                break;
            }
        }
    }
}
