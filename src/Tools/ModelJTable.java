package Tools;

import Entities.Action;
import Entities.Trader;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelJTable extends AbstractTableModel
{
    private String[] nomsColonnes;
    private Object[][] lignesDatas;
    @Override
    public int getRowCount() {
        return lignesDatas.length;
    }

    @Override
    public int getColumnCount() {
        return nomsColonnes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return lignesDatas[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return nomsColonnes[column];
    }

    public void loadDatasAgents(ArrayList<Trader> lesTraders) {
        nomsColonnes = new String[]{"Numéro", "Nom"};
        lignesDatas = new Object[lesTraders.size()][3];
        int i = 0;
        for (Trader trader : lesTraders) {
            lignesDatas[i][0] = trader.getIdTrader();
            lignesDatas[i][1] = trader.getNomTrader();
            i++;
        }
        fireTableChanged(null);
    }
    public void loadDatasActions(ArrayList<Action> lesActions) {
        nomsColonnes = new String[]{"Numéro", "Nom","Cours réel","Prix achat","Quantitée achetée"};
        lignesDatas = new Object[lesActions.size()][5];
        int i = 0;
        for (Action action : lesActions) {
            lignesDatas[i][0] = action.getIdAction();
            lignesDatas[i][1] = action.getNomAction();
            lignesDatas[i][2] = action.getCoursReelAction();
            lignesDatas[i][3] = action.getPrixAchatAction();
            lignesDatas[i][4] = action.getQuantiteAchetee();
            i++;
        }
        fireTableChanged(null);
    }
}
