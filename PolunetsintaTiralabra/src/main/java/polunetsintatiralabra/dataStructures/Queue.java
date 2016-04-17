package polunetsintatiralabra.dataStructures;
import polunetsintatiralabra.gui.Node;

/**
 * Queue olio jonka idea on korvata Javan PriorityQueue projektissa.
 * @author Peter
 * @param <E> Olion tyyppi.
 */
public class Queue<E> {
    Node[] table = new Node[32*32];
    /**
     * Tarkistetaan onko jono tyhjä.
     * @return totuusarvo tyhjyydestä.
     */
public boolean isEmpty(){
    for(Node test : table){
        if(test != null){
            return false;
        }
    }return true;
}
/**
 * Lisätään arvo jonoon.
 * @param o Lisättävä node
 * @return totuusarvo metodin suorituksesta
 */
public boolean add(Node o){
    int index = 0;
    for(Node test : table){
        if(test == null){
        table[index] = o;
        break;
        }else{
            index++;
        }
    }return true;
} /**
 * Haetaan arvo jonosta sen tärkeyden perusteella.
 * Verrataan nodejen priority arvoja, ja palautetaan se jolla on paras (pienin) arvo.
 * @return palautettava node.
 */
public Node poll(){
    int min = Integer.MAX_VALUE;
    Node minNode = table[0];
    int minIndex = 0;
    for(int i = 0; i < table.length-1; i++){
        if(table[i] != null && table[i].getPriority() < min){
        min = table[i].getPriority();
        minNode = table[i];
        table[i] = null;
        minIndex = i;
        }
    }
    for(int j = minIndex; j < table.length-2;j++){
        table[j] = table[j+1];
        }return minNode;
    }
}

