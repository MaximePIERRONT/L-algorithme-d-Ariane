import javax.swing.*;
import java.awt.*;

/**
 *
 * La classe CelluleDeConstructeur represente une cellule durant la construction d'une grille.
 *
 * @version 1.0
 * @author Zouave.co
 */

public class CelluleDeConstructeur extends JButton{

  /**
   *
   */
  private static final long serialVersionUID = 636285461719111635L;

  /**
   * Correspond à l'état d'une cellule sous forme de int.
   */
  private int etat;

  /**
   * A la construction d'une CelluleDeConstructeur on attribue son etat.
   * 
   * @param state
   *          Représente l'état à donner à la cellule
   */
  public CelluleDeConstructeur(int state){
    super();
    this.etat=state;
    this.setMinimumSize(new Dimension(5, 5));
  }

  /**
   * @return the etat
   */
  public int getEtat() {
    return this.etat;
  }

  /**
   * @param etat the etat to set
   */
  public void setEtat(int etat) {
  	this.etat = etat;
    switch(this.etat) {
      case 0: this.setBackground(Color.WHITE);
              break;
      case 1: this.setBackground(Color.BLACK);
              break;
      case 2: this.setBackground(Color.RED);
              break;
      case 3: this.setBackground(Color.GREEN);
              break;
    }
  }


}
