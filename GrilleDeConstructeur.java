import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 *
 * La classe GrilleDeConstructeur represente la grille de boutons du constructeur de grille
 *
 * @version 1.0
 * @author Zouave.co
 */


public class GrilleDeConstructeur extends JPanel{

  private static final long serialVersionUID = 8788982052514062447L;

  /**
   * la valeur des dimensions de la grille
   */
  private int dimension;

  /**
   * le tableau bidimensionnel des cellules de la grille
   */
  private CelluleDeConstructeur[][] test;

  /**
   * un observateur pour les actions de la grille
   */
  private ObservateurConstructeur ev;

  /**
   *  les posisions de thesee et de la sortie qui sont set
   */
  private int tx,ty,sx,sy;


  /**
   *A la creation de la grille,
   *@param t
   *      Représente la taille du labyrinthe.
   */
  public GrilleDeConstructeur(int t){
    this.dimension=t;
    GridLayout lay = new GridLayout(this.dimension, this.dimension);
    this.setLayout(lay);
    test= new CelluleDeConstructeur[t][t];
    for (int i=0;i<this.dimension ;i++ ) {
      for (int j=0;j<this.dimension ;j++ ) {
        test[i][j]=new CelluleDeConstructeur(0);
        ObservateurConstructeur x=new ObservateurConstructeur(test[i][j]);
        test[i][j].addActionListener(x);
        this.add(test[i][j]);
      }
    }
  }


  /**
   * random génère set aleatoirement les murs dans la grille et place thesee et la sortie aleatoirement egalement
   */
  public void random(){
    Random rand = new Random();

    int tmp;
    for (int i=0;i<this.dimension ;i++ ) {
        for (int j=0;j<this.dimension ;j++ ) {
          tmp=rand.nextInt(2);
          test[i][j].setEtat(tmp);
        }
    }
    int x = rand.nextInt(this.dimension);
    int y = rand.nextInt(this.dimension);

    test[x][y].setEtat(2); // met Thesee
    x = rand.nextInt(this.dimension);
    y = rand.nextInt(this.dimension);
    this.tx=x;
    this.ty=y;
    this.setEvT(true);
    while(test[x][y].getEtat()==2){
      x = rand.nextInt(this.dimension);
      y = rand.nextInt(this.dimension);
    }

    test[x][y].setEtat(3); // met Sortie
    this.setEvS(true);
    this.sx=x;
    this.sy=y;
  }

  /**
   * vider met tous les etats a 0
   */
  public void vider(){

      for (int i=0;i<this.dimension ;i++ ) {
          for (int j=0;j<this.dimension ;j++ ) {
            test[i][j].setEtat(0);
            this.setEvT(false);
            this.setEvS(false);
            this.tx=-1;
            this.ty=-1;
            this.sx=-1;
            this.sy=-1;
          }
      }

    }

  /**
   *
   * @param x
   *      Represente la position x de la sortie
   */
  public void setSortiex(int x){
    sx=x;
  }

  /**
   *
   * @param y
   *       Represente la position y de la sortie
   */
  public void setSortiey(int y){
    sy=y;
  }

  /**
   *
   * @param x
   *      Represente la position x de thesee
   */
  public void setTheseex(int x){
    tx=x;
  }

  /**
   *
   * @param y
   *      Represente la position y de thesee
   */
  public void setTheseey(int y){
    ty=y;
  }

  /**
   * @return la position x de la sortie
   */
  public int getSortiex(){
    return sx;
  }

  /**
   * @return la position y de la sortie
   */
  public int getSortiey(){
    return sy;
  }

  /**
   * @return la position x de thesee
   */
  public int getTheseex(){
    return tx;
  }

  /**
   *
   * @return la position y de thesee
   */
  public int getTheseey(){
    return ty;
  }

  /**
   *
   * @param b
   *      Represente le booleen qui definira si il y a une sortie ou pas
   */
  public void setEvS(boolean b){
    ev.setS(b);
  }

  /**
   *
   * @param b
   *      Represente le booleen qui definira si il y a un thesee ou pas
   */
  public void setEvT(boolean b){
    ev.setT(b);
  }

  /**
   *
   * @return si il y a une sortie ou pas
   */
  public boolean getEvS(){
    return ev.getS();
  }

  /**
   *
   * @return si il y a un thesee ou pas
   */
  public boolean getEvT(){
    return ev.getT();
  }

  /**
   * @return l'etat d'une cellule
   */
  public int getCellEtat(int x, int y){
    return test[x][y].getEtat();
  }

}
