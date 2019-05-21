import java.awt.*;
import javax.swing.*;

/**
 *
 * La classe GrilleGraphique represente graphique le labyrinthe
 *
 * @version 1.0
 * @author Zouave.co
 */

public class GrilleGraphique extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 5207464491033347499L;

    /**
     * Correspond à la grille au niveau graphique.
     */
    private JPanel[][] gridppanels;

    /**
     * Correspond à l'autre partie graphique de la fenetre.
     */
    private LeftPaneDurantPartie neighborpane;

    /**
     * A la construction GrilleGraphique,
     * 
     * @param jeu
     *          Représente le labyrinthe de jeu.
     */
    public GrilleGraphique(Grille jeu){
        super();
        int size = jeu.getSize(); 
        GridLayout grid = new GridLayout(size,size);
        this.setLayout(grid);
        this.gridppanels = jeu.toGrilleGraphique(this);
    }

    /**
     * Actualise la grille
     * 
     * @param posx
     *           Position x de Thesée.
     * @param posy
     *           Position y de Thesée.
     * @param direction
     *           Direction dans laquelle va Thésée.
     */
    public void updategrille(int posx, int posy, int direction){//Fait une mise à jour de la grille 
        int x = 0;
        int y = 0;
        if(direction == 1){
            y--;
        } else if(direction == 2) {
            x++;
        } else if(direction == 3) {
            y++;
        } else if(direction == 4) {
            x--;
        }

        this.gridppanels[posy][posx].setBackground(Color.ORANGE); //CHECK POUR POSX ET POSY SI BON SENS
        posx = posx + x;
        posy = posy + y;
        this.gridppanels[posy][posx].setBackground(Color.RED); //CHECK POUR POSX ET POSY SI BON SENS
        this.validate();
        this.repaint();
        neighborpane.updatepas();
    }

    /**
     * @return the neighborpane
     */
    public LeftPaneDurantPartie getNeighborpane() {
        return neighborpane;
    }

    /**
     * @param neighborpane the neighborpane to set
     */
    public void setNeighborpane(LeftPaneDurantPartie neighborpane) {
        this.neighborpane = neighborpane;
    }
}