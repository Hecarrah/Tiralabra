package polunetsintatiralabra.dataStructures;

import polunetsintatiralabra.gui.Node;

public class Queue<E> {
    Node[] table = new Node[32*32];
    
public boolean isEmpty(){
    for(Node test : table){
        if(test != null){
            return false;
        }
    }return true;
}
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
}
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

