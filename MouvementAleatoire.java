import java.util.Random;
/**
 * 
 * La classe MouvementAleatoire est la classe qui répresente tous les types de parcours 
 * avec l'aléatoire comme mode de fonctionnement
 * @version 1.0
 * @author Zouave.co
 */

public class MouvementAleatoire {
    /**
     * Il s'agit du Labyrinthe à résoudre aléatoirement
     */
    private Grille labyrinthe;

    /**
     * Il s'agit Thesee le personnage du Labyrinthe c'est lui qui bouge pendant la résolution
     * du labyrinthe
     */
    private Thesee myhero;


    /**
     * A la construction MouvementAleatoire, on lui associe juste son Labyrinthe.
     * 
     * @param lab
     *          Représente le labyrinthe qui va être résoudre aléatoirement
     */
    public MouvementAleatoire(Grille lab) {
        this.labyrinthe = lab;
        this.myhero = labyrinthe.getHeros();
    }

    /**
     * Il s'agit de faire 100 parties aléatoire, mais si le résultat dépasse un
     * certains nombres élevés de coups alors on le stop et on dit que c'est impossible.
     * 
     * @see MouvementAleatoire#roundAAlea
     * 
     * @return la moyenne des 100 parties jouées aléatoirement.
     */
    public long randhundredParty() {
        
        int nb_parti = 1;
        Moyenne moypas = new Moyenne();
        while (nb_parti <= 100) {
            long result = roundAAlea(myhero);
            moypas.add(result); 
            nb_parti++;
        }
        return moypas.getAverage();
  }

  /**
   * Représente une partie joué aléatoirement automatiquement.
   *            
   * @param hero
   *            Thésée est le pion que l'on bouge pendant la partie Aléatoire Automatique      
   * 
   * @return le nombre de movement finale (si le labyrinthe est terminable)
   */
  public long roundAAlea(Thesee hero){
        while( ( !( hero.isFinish() ) ) && (hero.getNb_move()<=100000)) {
            Random rand = new Random();
            int n = rand.nextInt(4) + 1;
            if(hero.canMove(n)){
                hero.Move(n);
            } else {
                 hero.setNb_move(hero.getNb_move()+1);
    
           }

        }
        long result = hero.getNb_move();
        if(hero.getNb_move()>100000){
            result=-1;
        }
        
        hero.restart();
        return result;
  }


    /**
     * Fait la prédiction du prochain mouvement aléatoirement.
     * 
     * @param left
     *             Représente l'emplacement graphique où la prédiction sera affichée.
     * 
     * @return la prédiction.
     */
    public int predictionalea(LeftPaneDurantPartie left){
        Random rand = new Random();
        int n = rand.nextInt(4) + 1;

        left.prediction(n);
        
        return n;
    }

    /**
     * Fait un mouvement aléatoire manuel
     * 
     * @param gridpane
     *                  Représente la grille graphique a faire évoluer si myhero se déplace.
     * @param left
     *                  Représente la partie graphique qui évolue s'il y a mouvement aujoute un pas au compteur.
     * @param n
     *                  Représente la direction dans laquelle doit aller myhero.
     * 
     * @return true si myhero a terminer labyrinthe sinon false
     */
    public boolean RoundMAlea(GrilleGraphique gridpane, LeftPaneDurantPartie left, int n){ //a lancé si l'utilisateur appui sur la bonne touche 
            if(myhero.canMove(n)){
                gridpane.updategrille(myhero.getPosx(),myhero.getPosy(),n);
                myhero.Move(n);
            } else {
                myhero.setNb_move(myhero.getNb_move()+1);
                left.updatepas();
            }
            return myhero.isFinish();
    }
        
}