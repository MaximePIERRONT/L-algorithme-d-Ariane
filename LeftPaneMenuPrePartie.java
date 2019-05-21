import javax.swing.*;
import java.awt.*;
/**
 * 
 * La classe LeftPaneMenuPrePartie représente la parti gauche du menu de pré-parti
 * 
 * @version 1.0
 * @author Zouave.co
 */


public class LeftPaneMenuPrePartie extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -2484922216685968350L;
    
    /**
     * Bouton pour passer à la suite
     */
    private ConfigurationParametresPartie lancement;

    /**
     * A la construction LeftPaneMenuPrePartie,
     * 
     * @param win
     *          Représente la fenetre de jeu actuel.
     * @param jeu
     *          Représente le labyrinthe actuel.
     */
    public LeftPaneMenuPrePartie(FenetreJeu win, Grille jeu) {
        super();
        this.setLayout(new BoxLayout((this), BoxLayout.Y_AXIS));

        ButtonGroup algorithme = new ButtonGroup();
        ButtonGroup fonctionenment = new ButtonGroup();

        JLabel titre1 = new JLabel("Algorithme :");
        JRadioButton deterministe = new JRadioButton("Deterministe");
        ActionDemarrePartie wfdeter = new ActionDemarrePartie(1);
        deterministe.addActionListener(wfdeter);
        JRadioButton aleatoire = new JRadioButton("Aleatoire");
        ActionDemarrePartie wfalea = new ActionDemarrePartie(2);
        aleatoire.addActionListener(wfalea);

        
        JLabel titre2 = new JLabel("Fonctionnement :");
        JRadioButton manuel = new JRadioButton("Manuel");
        ActionDemarrePartie wfonct = new ActionDemarrePartie(4);
        manuel.addActionListener(wfonct);
        JRadioButton automatique = new JRadioButton("Automatique");
        ActionDemarrePartie wfauto = new ActionDemarrePartie(8);
        automatique.addActionListener(wfauto);

        JButton start = new JButton("START");
        lancement = new ConfigurationParametresPartie(win,wfauto,jeu);
        start.addActionListener(lancement);


        start.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        titre1.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        titre2.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        
        this.add(Box.createRigidArea(new Dimension(10,100)));
        this.add(titre1);
        this.add(deterministe);
        this.add(aleatoire);
        this.add(Box.createRigidArea(new Dimension(30,100)));
        this.add(titre2);
        this.add(manuel);
        this.add(automatique);
        this.add(Box.createRigidArea(new Dimension(100,100)));
        this.add(start);

        algorithme.add(deterministe);
        algorithme.add(aleatoire);
        fonctionenment.add(manuel);
        fonctionenment.add(automatique);
    }

    /**
     * @return the lancement
     */
    public ConfigurationParametresPartie getLancement() {
        return lancement;
    }

    /**
     * @param lancement the lancement to set
     */
    public void setLancement(ConfigurationParametresPartie lancement) {
        this.lancement = lancement;
    }
   

}