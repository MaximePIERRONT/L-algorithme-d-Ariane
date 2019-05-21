import java.awt.event.*;

/**
 * La classe ConfigurationParametresPartie est reliée au JButton 'START' qui 
 * s'enclenche si les deux ButtonGroup ont une option de prise, 
 * et lance la parti avec les bonnes options. 
 * 
 * @version 1.0
 * @author Zouave.co
 */

public class ConfigurationParametresPartie implements ActionListener {
    /**
     * Correspond à la liaison des JRadioButton pour récuperer les choix
     * 
     * @see ActionDemarrePartie#getResult()
     */
    private ActionDemarrePartie key;

    /**
     * Correspond à la Fenetre de jeu.
     */
    private FenetreJeu window;

    /**
     * Correspond au labyrinthe de la parite.
     */
    private Grille grid;


    @Override
    public void actionPerformed(ActionEvent evenement) {
        int x = key.getResult();
        if(x > 4) { //Si les deux options ont été sélectionnées
            window.playGame(x,grid);
        }
    }

    /**
     * A la construction de ConfigurationParametresPartie,
     * 
     * @param win
     *          Représente la fenetre du jeu actuel.
     * @param l
     *          Représente les RadioButtons.
     * @param g
     *          Représente la grille du labyrithne du jeu.
     */
    public ConfigurationParametresPartie(FenetreJeu win, ActionDemarrePartie l, Grille g) {
        super();
        this.window = win; 
        this.key = l;  
        this.grid = g;     
    }


    
}