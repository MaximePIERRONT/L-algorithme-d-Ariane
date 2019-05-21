import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.*;

/**
 *
 * La classe ChoisirGrille permet de choisir une grille parmi
 * les fichiers ".lab" qui sont diponibles dans './map/'
 *
 * @version 1.0
 * @author Zouave.co
 */
public class ActionChoisirGrille implements ActionListener {
    /**
     * Correspond à la fenentre de Jeu
     */
    private FenetreJeu window;

    @Override
    public void actionPerformed(ActionEvent evenement) {

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier Labyrinthe", "lab");
        chooser.setFileFilter(filter);
        try {
            File f = new File(new File("./map").getCanonicalPath());
            chooser.setCurrentDirectory(f);
            chooser.showOpenDialog(null);


            String namefile = chooser.getSelectedFile().getName();
            Grille jeu = new Grille(namefile);


            //appel fonction menu de préparti
            //création de menu de preparti prend la JFrame ; la Grille ;
            window.MenuPrePartie(jeu,namefile);
        } catch (IOException e) {
            System.err.println("Erreur dossier introuvable!");
        } catch (NullPointerException e) {
            System.err.println("Erreur : sélectionnez un fichier .lab et ne fermez pas la fenetre !");
        }

        }


    /**
     * A la construction de Choisir, 
     * 
     * @param win
     *          Représente la fenetre de Jeu.
     */
    public ActionChoisirGrille(FenetreJeu win) {
        super();
        this.window = win;

    }


}
