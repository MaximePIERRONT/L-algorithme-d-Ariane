import java.awt.*;
import java.awt.event.*;

/**
 *
 * La classe ObservateurConstructeur cree un observateur qui agit sur le constructeur de grille
 *
 * @version 1.0
 * @author Zouave.co
 */
public class ObservateurConstructeur implements ActionListener {
  /**
   * une cellule de constructeur
   */
  private CelluleDeConstructeur mbut;

  /**
   * un flag pour savoir si un thesee est place
   */
  private static boolean yathesee=false;

  /**
   * un flag pour savoir si une sortie est placee
   */
  private static boolean yasortie=false;

  /**
   *
   * @param but
   *      Represente le bouton associe a l'observateur
   */
    public ObservateurConstructeur(CelluleDeConstructeur but){
      this.mbut=new CelluleDeConstructeur(0);
      this.mbut=but;
      mbut.setBackground(Color.WHITE);

    }


    @Override
    public void actionPerformed(ActionEvent evt){
      switch(mbut.getEtat()) {
        case 0: mbut.setEtat(1);
                break;
        case 1: if (yathesee&&yasortie) {
                  mbut.setEtat(0);
                }
                if (yathesee&&!yasortie) {
                  mbut.setEtat(3);
                  yasortie=true;
                }
                if (!yathesee) {
                  mbut.setEtat(2);
                  yathesee=true;
                }
                break;
        case 2: if (yasortie) {
                  mbut.setEtat(0);
                  yathesee=false;
                }
                if (!yasortie) {
                  mbut.setEtat(3);
                  yasortie=true;
                  yathesee=false;
                }
                break;
        case 3: mbut.setEtat(0);
                yasortie=false;
                break;
      }
    }

    /**
     * set le flag yathesee
     * 
     * @param b 
     *        est la valeur du booleen
     */
    public static void setT(boolean b){
      yathesee=b;
    }

    /**
     * set le flag yasortie
     * 
     * @param b 
     *        est la valeur du booleen
     */
    public static void setS(boolean b){
      yasortie=b;
    }

    /**
     *
     * @return la valeur du booleen yathesee
     */
    public static boolean getT(){
      return yathesee;
    }

    /**
     *
     * @return la valeur du booleen yasortie
     */
    public static boolean getS(){
      return yasortie;
    }


}
