import java.awt.*;
import javax.swing.*;

/**
 * 
 * La classe LeftPaneDurantPartie représente la parti gauche du menu de durant la parti
 * 
 * @version 1.0
 * @author Zouave.co
 */


public class LeftPaneDurantPartie extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -7414547711046468457L;
    
    /**
     * Nombre de mouvement
     */
    private JLabel nb_mov;

    /**
     * La prédiction de mouvement
     */
    private JLabel prediction;


    /**
     * Construction LeftPaneDurantPartie
     */
    public LeftPaneDurantPartie(){
        this.setLayout(new BoxLayout((this), BoxLayout.Y_AXIS));

        JLabel nb_movtxt = new JLabel("Nombre de déplacement(s) :");
        nb_movtxt.setHorizontalAlignment(JLabel.CENTER);
        

        this.nb_mov = new JLabel("0");
        this.nb_mov.setHorizontalAlignment(JLabel.CENTER);
        

        JLabel prediction_txt = new JLabel("Le prochain déplacement est :");
        prediction_txt.setHorizontalAlignment(JLabel.CENTER);
        
        this.prediction = new JLabel("SOUTH");
        this.prediction.setHorizontalAlignment(JLabel.CENTER);
        

        JLabel instrcution = new JLabel("Appuyer sur [ENTREE] pour continuer");
        instrcution.setHorizontalAlignment(JLabel.CENTER);

        this.add(Box.createRigidArea(new Dimension(30,200)));
        this.add(nb_movtxt);
        this.add(nb_mov);
        this.add(Box.createRigidArea(new Dimension(30,100)));
        this.add(prediction_txt);
        this.add(prediction);
        this.add(Box.createRigidArea(new Dimension(30,100)));
        this.add(instrcution);
        
    }

    /**
     * @return le nb_mov
     */
    public JLabel getNb_movJLabel(){
        return nb_mov;
    }

    /**
     * @return la predition du JLabel
     */
    public JLabel getPredictionJLabel(){
        return prediction;
    }

    /**
     * Modifie la prédiction sur le JLabel prediction.
     * 
     * @param direction
     *              Représente la direction pour renouveler l'affichage.
     *          
     */
    public void prediction(int direction){ //Affiche la prediction sur la fenetre
        String message = "";
        if(direction == 1){
            message = "NORD";
        } else if(direction == 2) {
            message = "EST";
        } else if(direction == 3) {
            message = "SUD";
        } else if(direction == 4) {
            message = "OUEST";
        }
        prediction.setText(message);
        this.validate();
        this.repaint();
    }

    /**
     * Actualise le nombre de pas
     */
    public void updatepas(){//Augmente le nombre de pas sur le graphique
        int n = Integer.parseInt(this.nb_mov.getText());
        n++;
        this.nb_mov.setText(n+"");
        this.revalidate();
        this.repaint();
    }

    /**
     * Actualise le nombre de pas à impossible
     */
    public void updatepasimp(){//Augmente le nombre de pas sur le graphique
        this.nb_mov.setText("Impossible");
        this.revalidate();
        this.repaint();
    }

    /**
     * 
     * @return nb_mov (JLabel)
     */
    public String getPas(){
        return this.nb_mov.getText();
    }

 }