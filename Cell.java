/**
 * La classe Cell est la classe qui représente une cellule du labirynthe
 * Une cellule peut être soit un chemin, un mur, Thésée ou la sortie
 *  
 * @version 1.0
 * @author Zouave.co
 */

public class Cell {
    /**
     * Si la case est un mur
     */
    private boolean wall;

    /**
     * Si la case a Thesee
     */
    private boolean thesee;

    /**
     * Si la case est la sortie
     */
    private boolean sortie;

    /**
     * Fait correspondre la mur à la valeur 1.
     */
    public static int WALL = 1;

    /**
     * Fait correspondre Thesee à la valeur 2.
     */
    public static int THESEE = 2;

    /**
     * Fait correspondre la sortie à la valeur 3.
     */
    public static int SORTIE = 3;

    /**
     * A la construction Cell(), faire une cellule vide.
     */
    public Cell() {
        this.setWall(false);
        this.setThesee(false);
        this.setSortie(false);
    }

    /**
     * A la construction Cell(type), en choisissant le type.
     * 
     * @param type
     *          Représente le type de la Cellule.
     */
    public Cell(int type) {
        if (type==Cell.WALL){
            this.setWall(true);
            this.setThesee(false);
            this.setSortie(false);
        } else if (type ==Cell.THESEE) {
            this.setWall(false);
            this.setThesee(true);
            this.setSortie(false);
        } else if (type ==Cell.SORTIE) {
            this.setWall(false);
            this.setThesee(false);
            this.setSortie(true);
        } else { //chemin
            this.setWall(false);
            this.setThesee(false);
            this.setSortie(false); 
        }
        
    }

    /**
     * @return le type de la cellule
     */
    public int getTypeCell() {
        int x;
        if(this.isWall()){
            x = 1;
        } else {
            if(this.isThesee()){
                x = 2;
            } else if (this.isSortie()){
                x = 3;
            } else {
                x = 0;
            }
        }
        return x;
    }


    /**
     * @return the wall
     */
    public boolean isWall() {
        return wall;
    }

    /**
     * @param wall the wall to set
     */
    public void setWall(boolean wall) {
        this.wall = wall;
    }

    /**
     * @return the sortie
     */
    public boolean isSortie() {
        return sortie;
    }

    /**
     * @param sortie the sortie to set
     */
    public void setSortie(boolean sortie) {
        this.sortie = sortie;
    }

    /**
     * @return the thesee
     */
    public boolean isThesee() {
        return thesee;
    }

    /**
     * @param thesee the these to set
     */
    public void setThesee(boolean thesee) {
        this.thesee = thesee;
    }
}