import java.awt.event.*;

/**
 * 
 * La classe ActionQuitter permet de quiiter le programme
 * 
 * @version 1.0
 * @author Zouave.co
 */



public class ActionQuitter implements ActionListener {
    /**
     * Correspond à la fenetre de jeu.
     */
    private FenetreJeu window;

    @Override
    public void actionPerformed(ActionEvent evenement) {
        window.leave();
    }

        
    /**
     * A la construction ActionQuitter,
     * 
     * @param win
     *          Rerpésente la fenetre acutelle de jeu.
     */
    public ActionQuitter(FenetreJeu win) {
        super();
        this.window = win;
        
    }

    
}