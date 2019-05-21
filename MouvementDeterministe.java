import java.util.LinkedList;
import java.util.Stack;


public class MouvementDeterministe {
    /**
     * La pile des Sommets a aller découvrir
     * 
     * @see MouvementDeterministe#decouverte(Vertex)
     */
    private Stack<Vertex> vPile = new Stack<Vertex>();

    /**
     * Liste des déplacements : Avec que des sommets.
     */
    private LinkedList<Vertex> deplaFile = new LinkedList<Vertex>();
    
    /**
     * La liste des Sommet dans l'ordre de 0 à n.
     */
    private LinkedList<Vertex> list = new LinkedList<Vertex>(); //Liste de tous les sommets

    /**
     * Liste de toute les directions qu'a fait Thesee.
     */
    private LinkedList<Integer> direction = new LinkedList<Integer>(); //Liste de tous les déplacements

    /**
     * La labyrithe du jeu.
     */
    private Grille labyrinthe;

    /**
     * Le Thesee de la partie en cours.
     */
    private Thesee myhero;

    /**
     * Le dernier mouvement de Thesee.
     */
    private int lastmove;

    /**
     * Si le labyrinthe est terminé.
     */
    private boolean fin;



    /**
     * A la construction de MouvementDeterministe,
     * 
     * @param lab
     *          Représente le labyrinthe de la partie actuel.
     */
    public MouvementDeterministe(Grille lab) { //Constructeur
        this.labyrinthe = lab;
        this.myhero = labyrinthe.getHeros();
        this.lastmove = -1;
    }

    /**
     * Inverse le mouvement.
     * Exemple : si on donne nord on obtient sud.
     * 
     * @param i
     *      Représente le mouvement à inverser
     * 
     * @return le mouvement inversé
     */
    public int invertMove(int i) { //Iverse la direction qu'on donne à cette fonction
        if (i > 2){
            i-=2;

        } else {
            i+=2;
        }
        return i;
    }


    /**
     * Fait la résolution du labyrinthe en déterministe.
     * 
     * @return le nombre de déplacement final
     */
    public long Algodeter() { //algorithme détermniste sans pause
        Vertex A = new Vertex(); //On créer le point de départ du graph
        list.addLast(A); //on ajoute le sommet à la liste des sommets
        deplaFile.addLast(A); //on ajoute le sommet à liste des déplacment
        vPile.push(A); // on ajoute à la pile A
        do { //Boucle tant que soit Thésée est tous découvert sans trouvé la sortie ou que Thésée est trouvé la sortie
            int x = list.indexOf(vPile.pop()); //On enlève le somment en haut de la pile pour découvrir cette case       
            deplacement(list.get(x));
            decouverte(list.get(x)); //On découvre la case au top de la vpile
        } while(!(vPile.isEmpty())&&(fin==false));

        return myhero.getEnd_nbmove(); //retourne le nombre de mouvement de thésée
    }


    /**
     * Découvre les Vecteur si autour du Sommet il y en a des nouveaux.
     * 
     * @param V
     *          Représente le sommet autour duquel on checher les nouveaux.
     */
    public void decouverte(Vertex V){       //Cette méthode permet à Thésée de visité toutes les possiblilité autour de lui dans une case précise
        int lastcase = lastmove;
        for (int i = 1; i < 5; i++) {       //Fait toute les direction sauf celle d'où il vient
            if(fin == true) { 
                i = 5;
                myhero.setEnd_nbmove(myhero.getNb_move());
            }else {
                if (deplaFile.getLast()!=V){//
                    myhero.BackMove(lastmove); //Thésee recule dans la case qu'il doit encore découvrir complétement
                    
                    this.lastmove = invertMove(lastmove); //Il retient d'où il vient
                    direction.addLast(lastmove);
                
                    deplaFile.addLast(V); //On ajoute le déplacment dans la liste de déplacement
                } 
                if((i == invertMove(lastcase))&&(myhero.getNb_move()>0)){
                    
                    //corriger last move correspond à l'inverse du dernier deplacement
                    //on fait rien car cela correspond à la case d'où il vient
                } else if(myhero.canMove(i)){   //S'il peut aller dans cette direction
                    
                    this.lastmove = i;
                    int a = Existed(V,i);    //On vérifie s'il est allé dans un case qu'il connait déjà
                    if(a > -1){                //Si elle existe déjà alors
                        //on ne bouge pas vu que cette case a déjà été trouvé
                    } else {                  //Sinon
                        myhero.Move(i);             //Alors il avance dans la direction i
                        direction.addLast(i);
                        list.addLast(new Vertex(V,i));//On créer un new sommet représant une case juste visité
                        deplaFile.addLast(list.getLast()); //On ajoute le déplacment dans la liste de déplacement
                        if(!(myhero.isFinish())){
                            vPile.push(list.getLast());//Puis on ajoute ce sommet à la pile des case non découverte()  
                        } else {
                            fin=true;
                        } 
                    }  
                } else {
                    direction.addLast(i);
                    myhero.setNb_move(myhero.getNb_move()+1);
                }
            }
            
        }

    }

    
    /**
     * Regarde si un Sommet existe déjà.
     * 
     * @param post
     *          Représente le Sommet d'où viens Thesee
     * @param direction
     *          Représente la direction où est aller Thesee
     * 
     * @return -1 s'il n'esxiste pas, sinon renvoie quel sommet c'est dans la liste des Sommets.
     */
    public int Existed(Vertex post, int direction){ //Vérifie s'il s'agit d'un sommet déjà visité 
        int tmpcoord[] = post.newCoord(direction);
        for (int i = 0 ; i < list.size() ; i++) {
            int[] coord = list.get(i).getCoord();
            if((tmpcoord[0]==coord[0])&&(tmpcoord[1]==coord[1])){
                return i;
            } 
        }
        return -1;
    }


    /**
     * Déplce Thesee de sa positon au Sommet donner en paramètre.
     * 
     * @param V
     *          Représente le Vecteur on veut que Thesee aille.
     */
    public void deplacement(Vertex V){//A completer
        LinkedList<Integer> go = new LinkedList<Integer>(); //Liste de toutes les directions
        if(deplaFile.peekLast()==V){
            //on est déjà sur la bonne case
        } else {
            int positionlist = deplaFile.lastIndexOf(V);
            int size = deplaFile.size();
            for (int i = size-1; i > positionlist ; i--) {
                int x, y, direction;
                Vertex b = deplaFile.get(i);
                Vertex a = deplaFile.get(i-1);
                int[] dif = diffCoord(a, b);
                x = dif[0];
                y = dif[1];
                if(y == 1){
                    direction = 1;
                } else if(x == 1) {
                    direction = 2;
                } else if(y == -1) {
                    direction = 3;
                } else {
                    direction = 4;
                }

                if(go.isEmpty()){ //Si premiere itération de la boucle
                    go.addLast(direction);  
                } else { // A partir de la deuxième itération de la boucle
                    int lastmov = go.peekLast(); 
                    int test = direction - lastmov;
                    if ((test == 2)||(test == -2)){ //S'il fait un aller retour on annule les deux dernier déplacements
                        go.removeLast();
                    } else { //Si c'est un nouveau déplacement
                        go.addLast(direction);
                    }
                }
            }
            Vertex tmp = deplaFile.peekLast();
            for (int i = 0; i < go.size() ; i++) {
                myhero.Move(go.get(i));
                direction.addLast(go.get(i));
                int a = Existed(tmp, go.get(i));
                deplaFile.addLast(list.get(a));
                lastmove = go.get(i);
                tmp = list.get(a);
            }

            
        }
    }
    


    /**
     * Fait la différence entre deux Sommets pour obtenir une direction {x,y}.
     * 
     * @param a
     *          Rerpésente le Sommet où on veut arriver.
     * @param b
     *          Représente le Sommet d'où l'on veut partir.
     * 
     * @return la direction {x,y}
     * 
     */
    public int[] diffCoord(Vertex a,Vertex b){
        int[] diff = new int[2];
        int[] acoord = a.getCoord();
        int[] bcoord = b.getCoord();
        diff[0] = acoord[0] - bcoord[0];
        diff[1] = acoord[1] - bcoord[1];
        return diff;
    }

    /**
     * @return un tableau contenant toutes les directions.
     */
    public int[] getDirection(){
        int[] depla = new int[direction.size()];
        for (int i = 0; i < direction.size(); i++) {
            depla[i] = direction.get(i);
        }
        return depla;
    }
}


