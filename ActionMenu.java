import java.awt.event.*;

/**
 * 
 * La classe ActionMenu permet de lancer le menu
 * 
 * @version 1.0
 * @author Zouave.co
 */


public class ActionMenu implements ActionListener {
    /**
     * Correspond à la fenetre de jeu.
     */
    private FenetreJeu window;

    @Override
    public void actionPerformed(ActionEvent evenement) {
        window.menuDepart();
    }

        
    /**
     * A la construction de ActionMenu,
     * 
     * @param win
     *          Représente la fenetre de jeu actuel.
     */
    public ActionMenu(FenetreJeu win) {
        super();
        this.window = win;
    }

    
}