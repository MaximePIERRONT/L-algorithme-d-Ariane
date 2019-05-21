import java.awt.event.*;

/**
 * 
 * La classe ActionDemarrePartie est la classe qui prend en compte les paramètres choisis
 * par les JRadioButtons du LeftPaneMPP
 * 
 * @version 1.0
 * @author Zouave.co
 */



public class ActionDemarrePartie implements ActionListener {
    /**
     * Nombre qui représente la valeur du bouton
     */
    private int val_button;

    /**
     * Valeur qui représente le choix de l'algorythme à l'aide des boutons radios
     */
    private static int choix_algo;

    /**
     * Valeur qui représente le choix du fonctionnement à l'aide des boutons radios
     */
    private static int choix_fonction;

    /**
     * Construction de ActionDemarrePartie,
     * 
     * @param nb
     *          Représente la valeur du bouton radio
     */
    public ActionDemarrePartie(int nb) {
        super();
        this.val_button = nb;
    }    

    /**
     * @return la somme des choix si les deux sont ont une options sélectionnées
     */
    public int getResult(){
        if((choix_algo==0)||(choix_fonction==0)){
            return 0;
        } 
        return choix_algo + choix_fonction;
    }

    /**
     * Initialise les choix à zéro.
     */
    public void intialize(){
        choix_algo = 0;
        choix_fonction = 0;
    } 


    @Override
    public void actionPerformed(ActionEvent evenement) {
        if( this.val_button >= 4){
            choix_fonction = this.val_button;
        } else {
            choix_algo = this.val_button;
        }
    }

    
}