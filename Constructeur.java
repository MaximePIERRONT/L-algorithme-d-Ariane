import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * La classe Constructeur cree le constructeur de grille
 *
 * @version 1.0
 * @author Zouave.co
 */

public class Constructeur extends JPanel implements ActionListener{

  private static final long serialVersionUID = -3030468642635448542L;

  /**
   * Les boutons de l'interface
   */
  private JButton bVid, bRnd, bVal, bMoins, bPlus, bValTaille,ok;

  /**
   * le champ de texte pour sauvegarder le nom
   */
  private JTextField champNom;

  /**
   *  la taille affichee
   */
  private JLabel texte;

  /**
   *  la grille visible dans le constructeur
   */
  private GrilleDeConstructeur grille;

  /**
   * la valeur de la taille de grille
   */
  private int taille;

  /**
   * la contrainte de layout
   */
  private GridBagConstraints c= new GridBagConstraints();

  /**
   * la grille cree a l'exportation
   */
  private Grille grilleVal;

  /**
   * le conteneur
   */
  private JPanel emplacement=new JPanel();

  /**
   * le panneau contenant la grille
   */
  private JPanel gridPan = new JPanel();

  /**
   * le nom donne a la grille
   */
  private String nom;

  /**
   * la fenetre associee
   */
  private FenetreJeu t;

  /**
   * la popup en cas d'oubli
   */
  private JFrame pop;

  /**
   *
   * @param fen
   *      Represente la fenetre
   */
  Constructeur(FenetreJeu fen){
    super();
    this.t=fen;
    this.taille=10;
    this.setSize(750,750);
    choisirDimensions();
  }

  @Override
  public  void actionPerformed(ActionEvent e){
        Object  source=e.getSource();
        if  (source==bVid){
            vider();
        }else if (source==bRnd){
            randomgen();
        }else if (source==bVal){
            validerLaGrille();
        }else if (source==bMoins&&this.taille>2){
            this.taille--;
            texte.setText(Integer.toString(taille));
        }else if (source==bPlus&&this.taille<120){
          this.taille++;
          texte.setText(Integer.toString(taille));
        }else if (source==bValTaille){
          allerAuDessin();
        }else if(source==champNom){
          String tv1 = new String(champNom.getText());
          tv1 = tv1.replaceAll("[^\\w]","");
          tv1 = tv1.replaceAll("[\\s]","");
          this.nom= tv1;
          if (this.nom==null) {
            this.nom="std";
          }
          grilleVal.exportGrille(this.nom);
          Grille jeu = new Grille(this.nom+".lab");
          t.MenuPrePartie(jeu,this.nom);
        }else if(source==ok){
          this.pop.dispose();
        }
  }

  /**
   * Permet de declencher l'interface de selection de taille
   */
  public void choisirDimensions(){

    Font font=new Font("Arial",Font.PLAIN,50);
    this.setLayout(new GridLayout(1,1));
    emplacement.setLayout(new GridBagLayout());
    c.weightx = 1;
    c.weighty = 1;
    c.fill=GridBagConstraints.BOTH;


    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth=1;
    c.gridheight=1;
    bValTaille = new JButton("Valider");
    bValTaille.setPreferredSize(new Dimension(10,10));
    emplacement.add(bValTaille,c);
    bValTaille.addActionListener(this);
    this.add(emplacement);
    bValTaille.setFont(font);


    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth=1;
    c.gridheight=1;
    bMoins = new JButton("-");
    emplacement.add(bMoins,c);
    bMoins.addActionListener(this);
    bMoins.setFont(font);


    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth=1;
    c.gridheight=1;
    bPlus = new JButton("+");
    emplacement.add(bPlus,c);
    bPlus.addActionListener(this);
    this.add(emplacement);
    bPlus.setFont(font);
    c.weightx = 3;
    c.gridx = 2;
    c.gridy = 0;
    c.gridwidth=1;
    c.gridheight=3;
    texte=new JLabel(Integer.toString(taille),JLabel.CENTER);
    texte.setFont(font);
    emplacement.add(texte,c);
  }

  /**
   * permet d'aller a la configuration de l'algo
   */
  public void allerAuDessin(){
    c.weightx = 1;
    c.weighty = 1;
    c.fill=GridBagConstraints.BOTH;
    emplacement.removeAll();
    emplacement.setLayout(new GridBagLayout());
    bVid = new JButton("Vider");
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth=1;
    c.gridheight=1;
    emplacement.add(bVid, c);
    bVid.addActionListener(this);

    bRnd = new JButton("Random");

    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth=1;
    c.gridheight=1;
    emplacement.add(bRnd, c);
    bRnd.addActionListener(this);

    bVal = new JButton("Valider");
    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth=1;
    c.gridheight=1;
    emplacement.add(bVal, c);
    bVal.addActionListener(this);
    c.weightx = 3;
    c.gridx = 2;
    c.gridy = 0;
    c.gridwidth=4;
    c.gridheight=3;

    gridPan.setLayout(new GridLayout(1,1));
    gridPan.setSize(750,500);
    grille = new GrilleDeConstructeur(this.taille);
    grille.vider();
    gridPan.add(grille);
    gridPan.setBackground(Color.GRAY);
    emplacement.add(gridPan,c);
    SwingUtilities.updateComponentTreeUI(this);
  }

  /**
   * permet de vider la grille
   */
  public void vider(){
    grille.vider();
  }

  /**
   * permet de generer aleatoirement une grille
   */
  public void randomgen(){
    grille.random();
  }

  /**
   * check et valide la grille
   */
  public void validerLaGrille(){
    int[][] g=new int[this.taille][this.taille];
    for (int i=0;i<this.taille ;i++ ) {
        for (int j=0;j<this.taille ;j++ ) {
            g[i][j]=grille.getCellEtat(i,j);
            if (g[i][j]==2) {
              grille.setTheseex(i);
              grille.setTheseey(j);
              grille.setEvT(true);
            }
            if (g[i][j]==3) {
              grille.setSortiex(i);
              grille.setSortiey(j);
              grille.setEvS(true);
            }
        }
    }
    String tx= new String(grille.getTheseex()+"");
    byte ixet=Byte.parseByte(tx);
    String ty= new String(grille.getTheseey()+"");
    byte igrect=Byte.parseByte(ty);
    String sx= new String(grille.getSortiex()+"");
    byte ixe=Byte.parseByte(sx);
    String sy= new String(grille.getSortiey()+"");
    byte igrec=Byte.parseByte(sy);
    String taye=new String(this.taille+"");
    byte rtaye=Byte.parseByte(taye);
    if (ixet==-1||igrect==-1||ixe==-1||igrec==-1||((ixet==ixe)&&(igrect==igrec))||grille.getEvT()==false||grille.getEvS()==false){
      String msg= new String("Il faut placer un thesee et une sortie !");
      this.Popup(msg);
    }else{
      this.grilleVal= new Grille(rtaye,ixet,igrect, ixe, igrec ,g);
      choisirNom();
    }
  }

  /**
   * permet de lancer l'interface de selection du nom
   */
  public void choisirNom(){
    emplacement.removeAll();
    emplacement.setLayout(new GridLayout(13,1));
    this.champNom = new JTextField("Nom");
    emplacement.add(new JPanel());
    emplacement.add(new JPanel());
    emplacement.add(new JPanel());
    emplacement.add(new JPanel());
    emplacement.add(new JPanel());
    emplacement.add(new JLabel("Entrez le nom sous lequel vous voulez enregistrer votre grille :"));
    emplacement.add(champNom);
    champNom.addActionListener(this);
    SwingUtilities.updateComponentTreeUI(this);

  }

  /**
   *
   * @param t
   *      represente le message a faire pop
   */
  public void Popup(String t){
    JLabel text;
    pop=new JFrame();
    pop.setTitle("Erreur");
    pop.setLocation(200,100);
    pop.setSize(400,100);
    pop.setLayout(new GridLayout(2,1));
    text=new JLabel(t);
    text.setHorizontalAlignment(JLabel.CENTER);
    pop.add(text);
    pop.setVisible(true);
    ok =new JButton("OK");
    ok.setBackground(Color.green);
    ok.addActionListener(this);
    pop.add(ok);
  }
}
