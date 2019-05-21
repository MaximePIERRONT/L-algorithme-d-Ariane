import java.awt.event.*;

/**
 * La classe AttenteToucheAleatoireManuel est la classe qui détecte la touche ENTREE,
 * elle fait avancer la partie d'un mouvement d'une partie Aléatoire Manuelle.
 *
 * @version 1.0
 * @author Zouave.co
 */

public class AttenteToucheAleatoireManuel implements KeyListener {
    /**
     * La module aléatoire pour résoudre le labyrinthe
     */
    private MouvementAleatoire alea;

    /**
     *  La partie graphique représentant la grille du labyrinthe.
     */
    private GrilleGraphique grid;

    /**
     * La partie graphique avec les informations type : pas et prédiction
     */
    private LeftPaneDurantPartie leftpane;

    /**
     * Représente la dernière prédiction
     */
    private int prediction;

    /**
     * La valeur représentant si la labyrinthe est fini.
     */
    private boolean finish;

    /**
     * La fenetre de Jeu.
     */
    private FenetreJeu window;

    /**
     * A la construction d'AttenteToucheAleatoireManuel
     * 
     * @param rand
     *          Représente le module aléatoire associé au labyrinthe choisi.
     * @param gridgraph
     *          Représente la partie graphique de la grille de la partie.
     * @param left
     *          Représente la partie graphique des élements de la partie.
     * @param pdirection
     *          Représente la première prédiction.
     * @param win
     *          Représente la fenetre du programme.
     */
    public AttenteToucheAleatoireManuel(MouvementAleatoire rand, GrilleGraphique gridgraph, LeftPaneDurantPartie left, int pdirection, FenetreJeu win){
        this.alea = rand;
        this.grid = gridgraph;
        this.leftpane = left;
        this.prediction = pdirection;
        this.window = win;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode==10){
            if (finish==false){
                finish = alea.RoundMAlea(grid,leftpane,prediction);
                prediction = alea.predictionalea(leftpane);
            } else {
                window.MenuFinPartie(" en une partie manuelle aleatoire : "+leftpane.getPas());
            }
            
        }    
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * @param prediction the prediction to set
     */
    public void setPrediction(int prediction) {
        this.prediction = prediction;
    }
}