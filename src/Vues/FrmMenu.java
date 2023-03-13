package Vues;

import Entities.Carte;
import Entities.Menu;
import Entities.Plat;
import Tools.ModelJTable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrmMenu extends JFrame {
    private JPanel pnlRoot;
    private JLabel lblTitre;
    private JLabel lblCartes;
    private JLabel lblPlats;
    private JTable tblCartes;
    private JTable tblPlats;
    private JTable tblMenus;
    private JSlider sldNotes;
    private JButton btnNoter;
    private JLabel lblMenus;
    private JTextField txtNoteMeilleurPlat;
    private JLabel lblNomMeilleurPlat;
    private JTextField txtNomMeilleurPlat;
    private JLabel lblNoteMenu;
    private JLabel lblPhotoPlat;

    ArrayList<Carte> mesCartes;
    ModelJTable mdl;
    Carte carteSelectionner;

    Menu menuSelectionner;

    public FrmMenu()
    {
        this.setTitle("Devoir - Restaurant");
        this.setContentPane(pnlRoot);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        mesCartes = new ArrayList<>();
        loadDatas();

        mdl = new ModelJTable();
        mdl.loadDatasListeCarte(mesCartes);
        tblCartes.setModel(mdl);

        // A compléter ici



        btnNoter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tblPlats.getSelectedRowCount()==0)
                {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un plat","Choix du plat",JOptionPane.WARNING_MESSAGE);

                }
                else
                {

                    int numPlat= Integer.parseInt(tblPlats.getValueAt(tblPlats.getSelectedRow(), 0).toString());

                    for (Plat plt : menuSelectionner.getLesPlats())
                    {
                        if (plt.getIdPlat() == numPlat) {
                            plt.CalculerNotePlat(sldNotes.getValue());
                            lblNoteMenu.setText(String.valueOf(menuSelectionner.CalculerNote()));
                            mdl = new ModelJTable();
                            mdl.loadDatasListePlats(menuSelectionner.getLesPlats());
                            tblPlats.setModel(mdl);
                        }
                        txtNomMeilleurPlat.setText(menuSelectionner.getBestPlat().getNomPlat());
                        txtNoteMeilleurPlat.setText(String.valueOf(menuSelectionner.getBestPlat().getNotePlat()));
                    }

                }
                // A compléter ici

            }
        });

        tblCartes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String uneCarte = tblCartes.getValueAt(tblCartes.getSelectedRow(),1).toString();
                for (Carte crt : mesCartes)
                {
                    if(crt.getNomCarte().equals(uneCarte))
                    {
                        mdl = new ModelJTable();
                        mdl.loadDatasListeMenus(crt.getLesMenus());
                        tblMenus.setModel(mdl);

                        carteSelectionner = crt;

                        break;

                    }
                }
                // A compléter ici

            }
        });

        tblMenus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String unMenu = tblMenus.getValueAt(tblMenus.getSelectedRow(),1).toString();
                for (Menu mnu : carteSelectionner.getLesMenus())
                {
                    if (mnu.getNomMenu().equals(unMenu)){
                        mdl = new ModelJTable();
                        mdl.loadDatasListePlats(mnu.getLesPlats());
                        menuSelectionner = mnu;
                        tblPlats.setModel(mdl);
                        Plat meilleurPlat = mnu.getBestPlat();
                        if (meilleurPlat != null ){
                            int meilleurNote = meilleurPlat.getNotePlat();
                            String nomPlat = meilleurPlat.getNomPlat();
                            txtNomMeilleurPlat.setText(nomPlat);
                            txtNoteMeilleurPlat.setText(String.valueOf(meilleurNote));
                        }
                        else {
                            lblNoteMenu.setText("0");
                            txtNoteMeilleurPlat.setText("0");
                            int derniereLigne =  tblPlats.getRowCount()-1;
                            txtNomMeilleurPlat.setText(String.valueOf(tblPlats.getValueAt(derniereLigne,1)));
                        }
                    }
                }
                // A compléter ici
            }
        });

        tblPlats.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // NE PAS MODIFIER CE CODE
                // IL PERMET D'AFFICHER L'IMAGE DU PLAT SELECTIONNE
                Image img1;
                try {
                    img1 = ImageIO.read(this.getClass().getResource(mesCartes.get(tblCartes.getSelectedRow()).getLesMenus().get(tblMenus.getSelectedRow()).getLesPlats().get(tblPlats.getSelectedRow()).getImagePlat()));
                    Image img2 = img1.getScaledInstance(200,150, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(img2);
                    lblPhotoPlat.setIcon(icon);
                } catch (IOException ex) {
                    Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public void loadDatas()
    {
        Carte cartePrintemps = new Carte(1, "Carte de printemps");
        Carte carteHiver = new Carte(2, "Carte d'hiver");

        Menu menuBio = new Menu(1, "Menu Bio");
        Menu menuVIP = new Menu(2, "Menu VIP");
        Menu menuGourmand = new Menu(3, "Menu gourmand");

        Plat entree1 = new Plat(1, "Avocado toast oeuf poché", 0, "/Images/Avocado.jpg");
        Plat entree2 = new Plat(2, "Burrata à la tomate", 0, "/Images/Burrata.jpg");
        Plat entree3 = new Plat(3, "Shakshouka", 0, "/Images/Shakshouka.jpg");
        Plat plat1 = new Plat(4, "Ballottine de volaille", 0, "/Images/Ballottine.jpg");
        Plat plat2 = new Plat(5, "Cabillaud en croûte", 0, "/Images/Cabillaud.jpg");
        Plat plat3 = new Plat(6, "Brochettes de crevettes", 0, "/Images/Brochettes.jpg");
        Plat dessert1 = new Plat(7, "Pancake", 0, "/Images/Pancake.jpg");
        Plat dessert2 = new Plat(8, "Fromage Blanc", 0, "/Images/FromageBlanc.jpg");
        Plat dessert3 = new Plat(9, "Brownie", 0, "/Images/Brownie.jpg");
        Plat dessert4 = new Plat(10, "Gauffre", 0, "/Images/Gauffre.jpg");

        menuBio.AjouterPlat(entree1); menuBio.AjouterPlat(plat2); menuBio.AjouterPlat(dessert2);

        menuVIP.AjouterPlat(entree1); menuVIP.AjouterPlat(entree2); menuVIP.AjouterPlat(plat1);
        menuVIP.AjouterPlat(dessert1); menuVIP.AjouterPlat(dessert4);

        menuGourmand.AjouterPlat(entree3); menuGourmand.AjouterPlat(plat3); menuGourmand.AjouterPlat(dessert3);

        carteHiver.AjouterMenu(menuGourmand); carteHiver.AjouterMenu(menuVIP);
        cartePrintemps.AjouterMenu(menuBio);

        mesCartes.add(cartePrintemps);mesCartes.add(carteHiver);
    }
}
