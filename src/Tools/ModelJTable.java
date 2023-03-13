package Tools;

import Entities.Carte;
import Entities.Menu;
import Entities.Plat;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelJTable extends AbstractTableModel
{
    private String[] columns;
    private Object[][] rows;

    @Override
    public int getRowCount() {
        return rows.length;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public void loadDatasListeCarte (ArrayList<Carte> lesCartes){
        columns = new String[]{"Numéro","Nom"};
        rows = new Object[lesCartes.size()][2];
        int i = 0;
        for (Carte unCarte : lesCartes){
            rows[i][0] = unCarte.getIdCarte();
            rows[i][1] = unCarte.getNomCarte();
            i++;
        }
    }
    public void loadDatasListeMenus (ArrayList<Menu> lesMenus){
        columns = new String[]{"Numéro","Nom"};
        rows = new Object[lesMenus.size()][2];
        int i = 0;
        for (Menu unMenu : lesMenus){
            rows[i][0] = unMenu.getIdMenu();
            rows[i][1] = unMenu.getNomMenu();
            i++;
        }

    }
    public void loadDatasListePlats (ArrayList<Plat> lesPlats){
        columns = new String[]{"Numéro","Nom", "Note"};
        rows = new Object[lesPlats.size()][3];
        int i = 0;
        for (Plat unPlat : lesPlats){
            rows[i][0] = unPlat.getIdPlat();
            rows[i][1] = unPlat.getNomPlat();
            rows[i][2] = unPlat.getNotePlat();
            i++;
        }

    }



    // A compléter ici





}
