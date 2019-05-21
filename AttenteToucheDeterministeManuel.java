import java.awt.event.*;
 
/**
 * La classe AttenteToucheDeterministeManuel est la classe qui détecte la touche ENTREE,
 * elle fait avancer la partie d'un mouvement en partie Déterministe Manuelle.
 *
 * @version 1.0
 * @author Zouave.co
 */

public class AttenteToucheDeterministeManuel implements KeyListener {
    /**
     * Le numéro d'étape du parcours déterministe.
     */
    private int numetape;

    /**
     * La partie graphique représentant la grille du labyrinthe.
     */
    private GrilleGraphique grid;

    /**
     * La partie graphique avec les informations type : pas et prédiction.
     */
    private LeftPaneDurantPartie leftpane;

    /**
     * Correspond à la liste de tous les mouvements.
     * 
     * @see MovDeter#getDirection()
     */
    private int prediction[];

    /**
     * La valeur représentant si la labyrinthe est fini.
     */
    private boolean finish;

    /**
     * La valeur dit si le labyrinthe est interminale.
     */
    private boolean interminable;

    /**
     * Représente le Thésée du labyrinthe (le pion du labyrinthe).
     */
    private Thesee thes;

    /**
     * Représente la fenetre principale de jeu.
     */
    private FenetreJeu window;

    /**
     * A la construction AttenteToucheDeterministeManuel,
     * 
     * 
     * @param jeu
     *          Représente le labyrinthe de la partie.
     * @param gridgraph
     *          Représente la partie graphique de la grille de la partie.
     * @param left
     *          Représente la partie graphique des élements de la partie.
     * @param depla
     *          Représente tous les déplacements fait par le déterministe avant.
     * @param result
     *          Représente le nombre de mouvements du déterministe.
     * @param win
     *          Représente la fenetre de Jeu.
     */
    public AttenteToucheDeterministeManuel(Grille jeu, GrilleGraphique gridgraph, LeftPaneDurantPartie left, int[] depla, long result, FenetreJeu win){
        this.thes = jeu.getHeros();
        this.grid = gridgraph;
        this.window = win;
        this.leftpane = left;
        this.prediction = depla;
        this.thes.restart();
        this.numetape = 0;
        if (result == 0){
            this.interminable = true;
        } else{
            this.interminable = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode==10){
            if (finish == false) {
                RoundDeter();
                if((numetape == prediction.length-1)&&(this.interminable)){
                    finish = true;
                    leftpane.updatepasimp();
                }
                if(finish == false){
                    leftpane.prediction(prediction[numetape]);
                }
                
                
            } else {
                window.MenuFinPartie(" en Deterministe : "+leftpane.getPas());
            }
            

        }    
    }

    @Override
    public void keyReleased(KeyEvent e) {
       

    }

    /**
     * Fait un mouvement déterministe.
     */
    public void RoundDeter(){
        if(thes.canMove(prediction[numetape])){
            grid.updategrille(thes.getPosx(), thes.getPosy(), prediction[numetape]);
            thes.Move(prediction[numetape]);
            numetape++;
        } else {
            thes.setNb_move(thes.getNb_move()+1);
            leftpane.updatepas();     
            numetape++;
        }
        if(thes.isFinish()){
            finish = true;
        }
    }
}