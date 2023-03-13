package Entities;

import java.util.ArrayList;

public class Menu
{
    private int idMenu;
    private String nomMenu;
    private ArrayList<Plat> lesPlats;

    public Menu(int unId, String unNom)
    {
        idMenu = unId;
        nomMenu = unNom;
        lesPlats = new ArrayList<>();
    }

    public int getIdMenu() {
        return idMenu;
    }

    public String getNomMenu() {
        return nomMenu;
    }

    public ArrayList<Plat> getLesPlats() {
        return lesPlats;
    }

    public void AjouterPlat(Plat nouveauPlat)
    {
        getLesPlats().add(nouveauPlat);
    }

    // Cette méthode permet de calculer la note d'un menu
    // En cumulant chaque note de tous les plats du menu
    public int CalculerNote()
    {
        int note = 0;
        // A compléter ici
        for (Plat plt : getLesPlats()){
            note = note + plt.getNotePlat();
        }

        return note;
    }

    // Cette méthode permet de récupérer le plat le mieux noté
    // C'est à dire celui qui a la note la plus grande
    // Cette méthode retourne un objet de type Plat
    public Plat getBestPlat()
    {
        Plat bestPlat = null;
        int noteMax = 0;
        for(Plat p : lesPlats)
        {
            if(p.getNotePlat() >= noteMax)
            {
                noteMax = p.getNotePlat();
                bestPlat = p;
            }
        }
        return bestPlat;
    }
}
