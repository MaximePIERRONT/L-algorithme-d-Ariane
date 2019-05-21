/**
 * 
 * La classe Moyenne permet d'effectuer des moyennes quand 
 * on fait les 100 simulations aléatoires.
 * @version 1.0
 * @author Zouave.co
 */

public class Moyenne {
    /**
     * Somme de tous les nombres.
     */
    private long somme;

    /**
     * Nombre de chiffre de la moyenne.
     */
    private int nombre;

    /**
     * Construction de la Moyenne.
     */
    public Moyenne(){
        this.somme=0;
        this.nombre=0;
    }

    /**
     * Ajoute un nombre à la moyenne.
     * 
     * @param a
     *       Représente le nouveau nombre.
     */
    public void add (Number a) {
        somme += a.longValue();
        nombre++;
    }
    
    /**
     * @return la moyenne (maths)
     */
    public long getAverage(){
            long t= (long)somme/nombre;
            return t;
    } 
}