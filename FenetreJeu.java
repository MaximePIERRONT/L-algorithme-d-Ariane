import javax.swing.*;
import java.awt.*;

/**
 * La classe FenetreJeu est la classe principale du projet, elle ddessine et met à jour la fenetre de jeu.
 *
 * @version 1.0
 * @author Zouave.co
 */

public class FenetreJeu extends JFrame {


    private static final long serialVersionUID = 6436367298811986964L;


    /**
     * namelab correspond au nom du labyrinthe.
     */
    private String namelab;




    /**
     * Constructeur FenetreJeu
     * <p>
     * A la construction d'un objet Fenetre, on attribut  la taille de la fenetre,
     * son titre, comment la fermer, sa localisation et on la rend visible.
     * </p>
     *
     * @param sizex
     *              Taille en largeur de la fenetre
     * @param sizey
     *              Taille en hauteur de la fenetre
     *
     */
    public FenetreJeu(int sizex, int sizey){
         // on configure la fenetre
        this.setSize(sizex, sizey);
        this.setLocation(0, 0);
        this.setTitle("L'algorithme d'Ariane");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(1000,800));
    }

    /**
     * Lance le programme.
     */
    public static void main(String[] args) {
        FenetreJeu fenetre = new FenetreJeu(1000, 800);
        fenetre.menuDepart();
    }


    /**
     * Lance le menu de départ à la FenetreJeu.
     */
    public void menuDepart() {
        getContentPane().removeAll();
        getContentPane().repaint();

        Fond panel = new Fond();
        panel.setLayout(new GridLayout(4,1));
        this.add(panel);

        JLabel etiquette = new JLabel("L'algorithme d'Ariane");
        etiquette.setFont(new Font("truc",Font.BOLD,45));
        etiquette.setHorizontalAlignment(JLabel.CENTER);
        panel.add(etiquette);

        Font fontmenu=new Font("truc",Font.BOLD,25);

        JButton bouton = new JButton("Choisir une grille existante");
        bouton.setFont(fontmenu);
        panel.add(bouton);
        ActionChoisirGrille choix = new ActionChoisirGrille(this);
        bouton.addActionListener(choix);



        JButton bouton2 = new JButton("Construire une grille");
        bouton2.setFont(fontmenu);
        panel.add(bouton2);
        ActionCreerGrille creer = new ActionCreerGrille(this);
        bouton2.addActionListener(creer);

        JButton bouton3 = new JButton("[ Quitter ]");
        bouton3.setFont(fontmenu);
        panel.add(bouton3);
        ActionQuitter quit = new ActionQuitter(this);
        bouton3.addActionListener(quit);



        this.pack();

    }


    /**
     * Lance le menu prévisualisation avant de lancer la partie.
     *
     * @param jeu
     *              Représente le labyrinthe sélectionner par l'utilisateur.
     * @param name
     *              Nom du labyrinthe.
     */
    public void MenuPrePartie(Grille jeu, String name){
        getContentPane().removeAll();
        getContentPane().repaint();
        Fond panel = new Fond();
        GridBagLayout grid = new GridBagLayout();
        panel.setLayout(grid);
        GridBagConstraints c = new GridBagConstraints();
        this.add(panel);
        JLabel etiquette = new JLabel(name);
        etiquette.setHorizontalAlignment(JLabel.CENTER);


        LeftPaneMenuPrePartie left = new LeftPaneMenuPrePartie(this,jeu);

        GrilleGraphique rightpan = new GrilleGraphique(jeu);

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.weightx = 1;
        c.weighty = 0;
        panel.add(etiquette, c);
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.weightx = 0.05;
        c.weighty = 1;
        panel.add(left, c);
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.95;
        panel.add(rightpan, c);
        SwingUtilities.updateComponentTreeUI(this);

    }

    /**
     * Lance le menu de partie en mode manuelle.
     *
     * @param jeu
     *              Représente le labyrinthe sélectionner par l'utilisateur.
     *
     *
     * @return La grille graphique représenter sur le menu partie manuelle.
     *
     */
    public GrilleGraphique MenuPartiManual(Grille jeu){
        getContentPane().removeAll();
        getContentPane().repaint();

        Fond panel = new Fond();
        GridBagLayout grid = new GridBagLayout();
        panel.setLayout(grid);
        GridBagConstraints c = new GridBagConstraints();
        this.add(panel);

        JLabel etiquette = new JLabel(namelab);
        etiquette.setHorizontalAlignment(JLabel.CENTER);

        LeftPaneDurantPartie left = new LeftPaneDurantPartie();

        GrilleGraphique rightpan = new GrilleGraphique(jeu);
        rightpan.setNeighborpane(left);

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.weightx = 1;
        c.weighty = 0;
        panel.add(etiquette, c);
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.weightx = 0.05;
        c.weighty = 1;
        panel.add(left, c);
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.95;
        panel.add(rightpan, c);

        SwingUtilities.updateComponentTreeUI(this);

        return rightpan;
    }


    /**
     * Lance le menu de fin d'une partie sur la fenetre.
     *
     * @param mov
     *              Représente par une string le résultat du labyrinthe.
     *
     */
    public void MenuFinPartie(String mov){
        getContentPane().removeAll();
        getContentPane().repaint();
        Fond panel = new Fond();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(panel);

        panel.add(Box.createRigidArea(new Dimension(200,200)));

        JLabel nbmove = new JLabel("Nombre de coups"+mov);
        nbmove.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel.add(nbmove);
        panel.add(Box.createRigidArea(new Dimension(120,120)));

        JButton bouton = new JButton("Menu");
        bouton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel.add(bouton);
        ActionMenu gom = new ActionMenu(this);
        bouton.addActionListener(gom);
        panel.add(Box.createRigidArea(new Dimension(100,100)));


        JButton bouton2 = new JButton("Quitter");
        bouton2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel.add(bouton2);
        ActionQuitter quit = new ActionQuitter(this);
        bouton2.addActionListener(quit);
        panel.add(Box.createRigidArea(new Dimension(30,30)));

        this.pack();

    }


    /**
     * Lance la partie sur la fenetre.
     *
     * @param choix
     *              Représente quel choix l'utilisateur a fait entre le choix de l'algorithme
     *              et le mode de fonctionnement.
     * @param jeu
     *              Représente le labyrinthe sélectionner par l'utilisateur.
     */
    public void playGame(int choix, Grille jeu){
        getContentPane().removeAll();
        getContentPane().repaint();

        Fond panel = new Fond();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(panel);
        if (choix==5) {
            DeterM(jeu,panel);
        } else if (choix==6) {
            AleaM(jeu,panel);
        } else if (choix==9) {
            DeterA(jeu,panel);
        } else {
            AleaA(jeu,panel);
        }


    }

    /**
     * Mode Aléatoire Manuel.
     *
     * @param jeu
     *              Représente le labyrinthe sélectionner par l'utilisateur.
     * @param panel
     *              Représente le fond où l'on doit faire les modifcations graphiques
     */
    public void AleaM(Grille jeu, JPanel panel){
            MouvementAleatoire alea = new MouvementAleatoire(jeu);
            GrilleGraphique test = MenuPartiManual(jeu);
            LeftPaneDurantPartie left = test.getNeighborpane();

            int x = alea.predictionalea(left);
            AttenteToucheAleatoireManuel touche = new AttenteToucheAleatoireManuel(alea,test,left,x,this);
            //Attente de la touche
            touche.setPrediction(x);
            left.addKeyListener(touche);
            left.setFocusable(true);
            left.requestFocusInWindow();
            test.addKeyListener(touche);
            test.setFocusable(true);
            test.requestFocusInWindow();

    }



     /**
     * Mode Aléatoire Automatique.
     *
     * @param jeu
     *              Représente le labyrinthe sélectionner par l'utilisateur.
     * @param panel
     *              Représente le fond où l'on doit faire les modifcations graphiques
     */
    public void AleaA(Grille jeu, Fond panel){
            MouvementAleatoire alea = new MouvementAleatoire(jeu);
            long result = alea.randhundredParty();
            if(result==-1){
                this.MenuFinPartie(" sur 100 parties aleatoires automatiques : Impossible");
            } else {
                this.MenuFinPartie(" sur 100 parties aleatoires automatiques : "+result);
            }

    }


     /**
     * Mode Déterministe Automatique.
     *
     * @param jeu
     *              Représente le labyrinthe sélectionner par l'utilisateur.
     * @param panel
     *              Représente le fond où l'on doit faire les modifcations graphiques
     */
    public void DeterA(Grille jeu, Fond panel){
            MouvementDeterministe deter = new MouvementDeterministe(jeu);
            long result = deter.Algodeter();
            if(result==0){
                this.MenuFinPartie(" en une partie deterministe automatique : Impossible");
            } else {
                this.MenuFinPartie(" en une partie deterministe automatique : "+result);
            }

    }



     /**
     * Mode Déterministe Manuel.
     *
     * @param jeu
     *              Représente le labyrinthe sélectionner par l'utilisateur.
     * @param panel
     *              Représente le fond où l'on doit faire les modifcations graphiques
     */
    public void DeterM(Grille jeu, Fond panel){
        MouvementDeterministe deter = new MouvementDeterministe(jeu);
        long result = deter.Algodeter();

        int[] deplacement = deter.getDirection();

        GrilleGraphique test = MenuPartiManual(jeu);
        LeftPaneDurantPartie left = test.getNeighborpane();
        left.prediction(deplacement[0]);

        AttenteToucheDeterministeManuel touche = new AttenteToucheDeterministeManuel(jeu,test,left,deplacement,result,this);
        left.addKeyListener(touche);
        left.setFocusable(true);
        left.requestFocusInWindow();
        test.addKeyListener(touche);
        test.setFocusable(true);
        test.requestFocusInWindow();

    }


     /**
     * Fin du programme.
     */
    public void leave(){
        System.exit(0);
    }






}
