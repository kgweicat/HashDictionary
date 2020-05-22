package hashdictionary;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Che-Wei Chou
 * @param <E>
 */
public class Node <E extends IDedObject>{
    private E data;   //item in the node
    private Node previous;      //Linked to previous node
    private Node next;          //Linked to next node
    
    /**
     * Constructor: Fill the node using the given parameter
     * @param o An object 
     */
    protected Node (E o){
        data = o;
    }

    /**
     * Access to the content of the node
     * @return the item in the node
     */
    protected E getData(){
        return data;
    }
    
    /**
     * SetPrevious
     * @param n the node you want to set it to
     */
    protected void setPrevious(Node n){
        previous = n;
    }
    
    /**
     * setNext
     * @param n the node you want to set it to
     */
    protected void setNext(Node n){
        next = n;
    }
    
    /**
     * Access the node before the node
     * @return the node previous to this node
     */
    protected Node getPrevious(){
        return previous;
    }
    
    /**
     * Access the node after the node
     * @return the node after this node
     */
    protected Node getNext(){
        return next;
    }
    
    @Override
    public String toString(){ 
        return data.toString();
    }
    
}
