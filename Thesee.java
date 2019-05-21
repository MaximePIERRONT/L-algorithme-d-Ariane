/**
 * 
 * La classe Thesee represente l'élement Thésée dans le labyrinthe 
 * 
 * @version 1.0
 * @author Zouave.co
 */

public class Thesee {
    /**
     * Taille de la Grille.
     * 
     * @see Grille#getSize()
     */
    private Byte lsize;

    /**
     * Coordonnée de la sortie.
     * 
     * @see Grille
     */
    private int[] psortie = new int[2];

    /**
     * Coordonnées initiale de Thesee.
     * 
     * @see Thesee#getPositioninit(int)
     * @see Thesee#setPositioninit(int[])
     * @see Thesse#restart()
     */
    private int[] positioninit = new int[2];

    /**
     * Coordonées x de Thesee.
     * 
     * @see Thesee#getPosx()
     * @see Thesee#setPosx(int)
     * @see Thesse#restart()
     */
    private int posx;

    /**
     * Coordonées y de Thesee.
     * 
     * @see Thesee#getPosy()
     * @see Thesee#setPosy(int)
     * @see Thesse#restart()
     */
    private int posy;

    /**
     * Tableau de Cell correspondant au labyrinthe.
     * 
     * @see Thesee#restart()
     * @see Thesee#canMove(int)
     * @see Thesee#Move(int)
     * @see Thesee#BackMove(int)
     */
    private Cell[][] labyrinthe;

    /**
     * Nombre de mouvement.
     * 
     * @see Thesee#getNb_move()
     * @see Thesee#setNb_move(int)
     */
    private int nb_move; 

    /**
     * Nombre de mouvement de fin.
     * 
     * @see Thesee#getEnd_nbmove()
     * @see Thesee#setEnd_nbmove(int)
     */
    private int end_nbmove;

    /**
     * Il s'agit du chiffre représetant le NORD
     */
    private static int NORTH = 1;

    /**
     * Il s'agit du chiffre représetant le EAST
     */
    private static int EAST = 2;

    /**
     *Il s'agit du chiffre représetant le SOUTH
     */
    private static int SOUTH = 3;

    /**
     * Il s'agit du chiffre représetant le WEST
     */
    private static int WEST = 4;

    /**
     * A la construction Thesee,
     * 
     * @param x
     *          Représente la coordonée x de Thesee
     * @param y
     *          Représente la coordonée y de Thesee
     * @param grille
     *          Représente le tableau ude cell du labyrinthe
     * @param pos
     *          Représente les coordonnées de la sortie
     * @param size
     *          Représente la taille du labyrinthe
     */
    public Thesee(Byte x, Byte y, Cell[][] grille,Byte[] pos, Byte size){
        this.posx = x;
        this.posy = y;
        this.positioninit[0] = x;
        this.positioninit[1]= y;
        this.labyrinthe = grille;
        this.end_nbmove = -1;
        this.psortie[0] = pos[0];
        this.psortie[1] = pos[1];
        this.lsize = size;

    }

    
    /**
     * Regarde si Thesee peut bouger dans la labyrinthe dans une certaine direction.
     * 
     * @param direction
     *              Représente la direction dans laquelle il veut aller.
     * @return s'il peut faire le movement.
     */
    public boolean canMove(int direction) { //Méthode pour voir si Thésée peut se déplacer dans la direction souhaiter;
        
        int x = 0;
        int y = 0;
        if(direction == NORTH){
            y--;
        } else if(direction == EAST) {
            x++;
        } else if(direction == SOUTH) {
            y++;
        } else if(direction == WEST) {
            x--;
        }

        if(this.posx+x < 0 || this.posx+x >= this.lsize){ //Si on sort du labyrinthe en x
            return false;
        }
        if(this.posy+y < 0 || this.posy+y >= this.lsize){ //Si on sort du labyrinthe en y
            return false;
        }
        
        if(this.labyrinthe[this.posx+x][this.posy+y].isWall()){ //Vérifie si il y a un mur
            return false;
        } else {
            return true;
        }
    }


    /**
     * Déplace Thesee dans une certaine direction.
     * 
     * @param direction
     *              Repésente la direction du mouvement.
     */
    public void Move(int direction) {
        int x = 0;
        int y = 0;
        if(direction == NORTH){
            y--;
        } else if(direction == EAST) {
            x++;
        } else if(direction == SOUTH) {
            y++;
        } else {
            x--;
        }
        

        if(this.labyrinthe[this.posx][this.posy].isThesee()) {
            this.labyrinthe[this.posx][this.posy].setThesee(false);
            this.posx = this.posx+x;
            this.posy = this.posy+y;
            this.labyrinthe[this.posx][this.posy].setThesee(true);
        }
        this.nb_move++;
        if(isFinish()){
            this.end_nbmove = this.nb_move;
        }

        
    }

    /**
     * 
     * @return si le labyrinthe est fini
     */
    public boolean isFinish() {
        if((this.psortie[0]==this.posx)&&(this.psortie[1]==this.posy)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Déplace Thesee dans une direction opposé à celle donnée.
     * 
     * @param direction
     *              Repésente la direction du mouvement.
     */
    public void BackMove(int direction) {
        int x = 0;
        int y = 0;
        if(direction == 1){
            y++;
        } else if(direction == 2) {
            x--;
        } else if(direction == 3) {
            y--;
        } else {
            x++;
        }
        
        

        if(this.labyrinthe[this.posx][this.posy].isThesee()) {
            this.labyrinthe[this.posx][this.posy].setThesee(false);
            this.posx = this.posx+x;
            this.posy = this.posy+y;
            this.labyrinthe[this.posx][this.posy].setThesee(true);
        }
        this.nb_move++;

        
    }


    /**
     * Remet le labyrinthe et Thesee à zero.
     */
    public void restart() {
        this.labyrinthe[this.posx][this.posy].setThesee(false);
        this.posx = this.positioninit[0];
        this.posy = this.positioninit[1];
        this.labyrinthe[this.posx][this.posy].setThesee(true);
        this.end_nbmove = 0;
        this.nb_move = 0;
    }



    /**
     * @return the labyrinthe
     */
    public Cell[][] getLabyrinthe() {
        return labyrinthe;
    }

    /**
     * @param labyrinthe the labyrinthe to set
     */
    public void setLabyrinthe(Cell[][] labyrinthe) {
        this.labyrinthe = labyrinthe;
    }

    /**
     * @return the nb_move
     */
    public int getNb_move() {
        return nb_move;
    }

    /**
     * @param nb_move the nb_move to set
     */
    public void setNb_move(int nb_move) {
        this.nb_move = nb_move;
    }

    /**
     * @return the positioninit
     */
    public int getPositioninit(int i) {
        return positioninit[i];
    }

    /**
     * @param positioninit the positioninit to set
     */
    public void setPositioninit(int[] positioninit) {
        this.positioninit = positioninit;
    }

    /**
     * @return the end_nbmove
     */
    public int getEnd_nbmove() {
        return end_nbmove;
    }

    /**
     * @param end_nbmove the end_nbmove to set
     */
    public void setEnd_nbmove(int end_nbmove) {
        this.end_nbmove = end_nbmove;
    }

    /**
     * @return the lsize
     */
    public Byte getLsize() {
        return lsize;
    }

    /**
     * @param lsize the lsize to set
     */
    public void setLsize(Byte lsize) {
        this.lsize = lsize;
    }

    /**
     * @return the psortie
     */
    public int[] getPsortie() {
        return psortie;
    }

    /**
     * @param psortie the psortie to set
     */
    public void setPsortie(int[] psortie) {
        this.psortie = psortie;
    }

    /**
     * @return the posx
     */
    public int getPosx() {
        return posx;
    }

    /**
     * @param posx the posx to set
     */
    public void setPosx(int posx) {
        this.posx = posx;
    }

    /**
     * @return the posy
     */
    public int getPosy() {
        return posy;
    }

    /**
     * @param posy the posy to set
     */
    public void setPosy(int posy) {
        this.posy = posy;
    }




    

}