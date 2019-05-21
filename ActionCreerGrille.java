import java.awt.event.*;

/**
 *
 * La classe ActionCreerGrille represente une cellule durant la construction d'une grille.
 *
 * @version 1.0
 * @author Zouave.co
 */

public class ActionCreerGrille implements ActionListener {

    /**
     * Représente la fenetre relié à la classe.
     */
    private FenetreJeu window;
    /**
     * Représente la partie de consturction de la grille.
     */
    private Constructeur c;

    /**
     * A la création d'ActionCreerGrille,
     * 
     * @param win
     *          Représente la fenetre de jeu.
     */
    public ActionCreerGrille(FenetreJeu win) {
      super();
      this.window = win;
      c=new Constructeur(win);
    }


    @Override
    public void actionPerformed(ActionEvent evenement) {


    window.getContentPane().removeAll();
    window.add(c);
    window.validate();

    }



}
