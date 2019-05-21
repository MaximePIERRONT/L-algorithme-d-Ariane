import java.awt.*;
import java.io.*;
import java.lang.Math;
import javax.swing.*;

/**
 *
 * La classe Grille represente l'ensemble du labyrinthe
 *
 * @version 1.0
 * @author Zouave.co
 */


public class Grille {
    /**
     * La taille de la labyrinthe.
     */
    private Byte size;

    /**
     * Le Thesee du labyrinthe.
     */
    private Thesee heros;

    /**
     * Les coordonnées de la sortie.
     */
    private Byte[] sortie = new Byte[2];

    /**
     * La grille du labyrinthe avec un tableau de Cell().
     */
    private Cell[][] grille;



    /**
     * A la construction de Grille,
     *
     * @param sz
     *      Représente la taille du labyrinthe.
     * @param theseex
     *      Représente la coordonnée x de Thésée
     * @param theseey
     *      Représente la coordonnée y de Thésée
     * @param sortiex
     *      Représente la coordonnée x de la sortie
     * @param sortiey
     *      Représente la coordonnée y de la sortie
     * @param g
     *      Représente le tableau des etats des cellules qui va etre copié dans la grille
     */
    public Grille(Byte sz,Byte theseex,Byte theseey ,Byte sortiex,Byte sortiey,int[][] g){
        this.setSize(sz);
        this.sortie[0]=sortiex;
        this.sortie[1]=sortiey;

        Cell[][] grilletmp = new Cell[this.size][this.size];

        for (int a = 0; a < this.size; a++) { //Ligne
            for (int b = 0; b < this.size; b++) { //Colonne

              switch(g[a][b]){
                case 0: grilletmp[b][a]=new Cell();
                        break;
                case 1: grilletmp[b][a]=new Cell(Cell.WALL);
                        break;
                case 2: grilletmp[b][a]=new Cell(Cell.THESEE);
                        break;
                case 3: grilletmp[b][a]=new Cell(Cell.SORTIE);
                        break;
              }

            }
        }

        for (int a = 0; a < this.size; a++) { //Ligne
            for (int b = 0; b < this.size; b++) { //Colonne

            }
        }
      this.grille = grilletmp;
      Thesee prems = new Thesee(theseex,theseey,this.grille,this.sortie,this.size);
      this.heros = prems;
    }





    /**
     * A la construction Grille,
     *
     * @param filename
     *            Répresente le nom du fichier choisi. @see ChoisirGrille.java
     */
    public Grille(String filename){
        Byte tmp_thesee1 = 0;
        Byte tmp_thesee2 = 0;
        try {
            //tableau de string ou sont les 6 composants : tailles coordonnees et

            DataInputStream file = new DataInputStream(new FileInputStream("./map/"+filename));
            try {

                this.size = file.readByte();
                tmp_thesee2 = file.readByte();
                tmp_thesee1 = file.readByte();
                this.sortie[1] = file.readByte();
                          //met la pos_x de la sortie de la grille dans l'attribut
                this.sortie[0] = file.readByte();
                     //met la pos_y de la sortie de la grille dans l'attribut


            } catch (NumberFormatException e1) {
                System.err.println("Erreur problème de fichier!");
            }


            int nb_case = this.size*this.size;
            int r = nb_case % 8;
            int nboctet;
            if(r!=0){
                nboctet = (int) nb_case/8 +1;
            } else {
                nboctet = nb_case/8;
            }

            int[] tmp = new int[nboctet];



            for (int i = 0; i < nboctet ; i++) {
                tmp[i] = file.readByte();

                if(tmp[i] < 0) {
                    tmp[i] = (int)tmp[i]+256;
                }

            }


            int m = 0;
            Byte[] cartemur = new Byte[this.size*this.size+7];
            //Tant que nb_case-8 > 0 alors lecture des 8 bits
            for (int i = 0 ; i < nboctet ; i++) {
                for (int k = 7; k >= 0 ; k--, m++) { //ET bit à bit (Lecture complète des octects)
                    int powtwo = (int) Math.pow(2,k);
                    int cmp = tmp[i] & powtwo;
                    if(cmp > 0){
                        cartemur[m] = 1;

                    } else {
                        cartemur[m] = 0;

                    }
                }

            }


            //00000110 == 6
            //2^7 & 6 = 0 donc on, met 0 dans notre tabl
            //2^6 & 6 = 0 donc on, met 0 dans notre tableau
            //2^5 & 6 = 0 donc on, met 0 dans notre tableau
            //2^4 & 6 = 0 donc on, met 0 dans notre tableau
            //2^3 & 6 = 0 donc on, met 0 dans notre tableau
            //2^2 & 6 = 1 donc on, met 1 dans notre tableau
            //2^1 & 6 = 1 donc on, met 1 dans notre tableau
            //2^0 & 6 = 0 donc on, met 0 dans notre tableau




            Cell[][] grilletmp = new Cell[this.size][this.size];
            int c = 0;
            for (int a = 0; a < this.size; a++) { //Ligne
                for (int b = 0; b < this.size; b++, c++) { //Colonne

                    Byte option = cartemur[c];
                    if (option == 1) {
                        grilletmp[a][b] = new Cell(Cell.WALL);

                    } else {
                        if ((sortie[0]==a)&&(sortie[1]==b)){
                            grilletmp[a][b] = new Cell(Cell.SORTIE);

                        } else if ((tmp_thesee1==a)&&(tmp_thesee2==b)){
                            grilletmp[a][b] = new Cell(Cell.THESEE);

                        }else {
                            grilletmp[a][b] = new Cell();

                        }

                    }

                }
            }



            this.grille = grilletmp;
            Thesee prems = new Thesee(tmp_thesee1,tmp_thesee2,this.grille,this.sortie,this.size);
            this.heros = prems;

            file.close();

        } catch (IOException e) {
            System.err.println("Le fichier n'existe pas !");
        }
    }

    /**
     * Permet de créer un fichier ".lab".
     *
     * @param name
     *          Représente le nom du fichier a créer.
     */
    public void exportGrille(String name) { //Cette fonction permet d'exporter fichier en .lab
        try {
            File namefile = new File("./map/"+ name +".lab"); //on définit l'arborésence
            namefile.createNewFile();
            FileOutputStream writein = new FileOutputStream(namefile);


            writein.write(this.size);
            writein.write(heros.getPosx());
            writein.write(heros.getPosy());
            writein.write(this.sortie[0]);
            writein.write(this.sortie[1]);
            int nb_case = this.size * this.size;
            int nboctet;
            int r = nb_case % 8;
            if(r!=0){
                nboctet = (int) nb_case/8 +1;
            } else {
                nboctet = nb_case/8;
            }
            //remplissage de carte mur pour le transformer en suite de byte
            Byte[] cartemur = new Byte[nb_case+7];
            int c = 0;
            for (int a = 0; a < this.size; a++) { //Ligne
                for (int b = 0; b < this.size; b++, c++) { //Colonne
                    if (grille[a][b].isWall()){
                        cartemur[c] = 1;
                    } else {
                        cartemur[c] = 0;
                    }

                }
            }

            for (int i = 0; i < 7; i++) {
                cartemur[nb_case+i]=0;
            }



            int l = 0;
            for (int i = 0 ; i < nboctet ; i++) {
               int octet = 0;

                for (int k = 0; k < 8 ; k++, l++) { //Création des octets
                    if (!(k==0)){
                        octet = octet << 1;
                    }
                    octet = octet + cartemur[l];
                }


                writein.write(octet);
            }



            //On écrit la dernière partie du fichier qui représente les murs

            writein.close(); //Fermeture du fichier à la fin de traitement


        } catch (IOException e) {
           System.err.println("Erreur !");
        }
    }



    /**
     * Tranforme une grille en tableau de JPanel
     *
     * @param pan
     *          Représente où afficher la grille grahpique.
     *
     * @return le tableau de JPanel
     */
    public JPanel[][] toGrilleGraphique(JPanel pan){
        JPanel[][] grillegraph = new JPanel[size][size];
        heros.restart();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int x = this.grille[j][i].getTypeCell();

                grillegraph[i][j] = new JPanel();
                pan.add(grillegraph[i][j]);
                if (x==0){
                    grillegraph[i][j].setBackground(Color.WHITE);
                } else if (x==1) {
                    grillegraph[i][j].setBackground(Color.BLACK);
                } else if (x==2) {
                    grillegraph[i][j].setBackground(Color.RED);
                } else if (x==3) {
                    grillegraph[i][j].setBackground(Color.GREEN);
                }
            }
        }
        return grillegraph;
    }



    /**
     * @return the size
     */
    public Byte getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Byte size) {
        this.size = size;
    }

    /**
     * @return the heros
     */
    public Thesee getHeros() {
        return heros;
    }

    /**
     * @param heros the heros to set
     */
    public void setHeros(Thesee heros) {
        this.heros = heros;
    }

    /**
     * @return the sortie
     */
    public Byte getSortie(int i) {
        return sortie[i];
    }

    /**
     * @param sortie the sortie to set
     */
    public void setSortie(Byte[] sortie) {
        this.sortie = sortie;
    }

    /**
     * @return the grille
     */
    public Cell[][] getGrille() {
        return grille;
    }

    /**
	 * @param grille the grille to set
	 */
	public void setGrille(Cell[][] grille) {
		this.grille = grille;
    }

}
