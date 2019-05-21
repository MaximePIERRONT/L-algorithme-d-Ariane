/**
 * 
 * La classe Vertex représente un sommet pour l'algorithme déterminste 
 * 
 * 
 * @version 1.0
 * @author Zouave.co
 */

public class Vertex {
    private int[] coord = new int[2];

    /**
     * Construction du premier Vertex.
     */
    public Vertex(){ //Construction du premier sommet de l'algo déterministe
        this.coord[0] = 0;
        this.coord[1] = 0;
    }

    /**
     * Construction d'un Vertex avec 
     * @param post
     *          Vertex qui est adjacent au nouveau 
     * @param direction
     *          direction dans laquelle est le nouveau Vertex par rapport à post.
     */
    public Vertex(Vertex post, int direction){ //pour créer un sommet on a besoin de savoir d'où il vient et ou il est allé
        int[] tmpcoord = post.newCoord(direction);
        this.coord[0]=tmpcoord[0];
        this.coord[1]=tmpcoord[1];
    }


    /**
     * Donne les nouvelles coordonnées avec un Vertex en origine et une direction.
     * 
     * @param direction
     *              direction dans laquelle est le Vertex est par rapport a ce Vertex.
     * 
     * @return les nouvelles coordonnées
     */
    public int[] newCoord(int direction) { //Calcul les coordonnées d'un sommet avec un sommet et la direction dans laquelle il va
        int[] pos = this.getCoord();
        int x = pos[0];
        int y = pos[1];
        if(direction == 1){
            y++;
        } else if(direction == 2) {
            x++;
        } else if(direction == 3) {
            y--;
        } else {
            x--;
        }
        int[] lala = {x,y};
        return lala;
    }

    /**
     * @return the coord
     */
    public int[] getCoord() {
        return coord;
    }

    /**
     * @param coord the coord to set
     */
    public void setCoord(int[] coord) {
        this.coord = coord;
    }
}