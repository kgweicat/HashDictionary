package hashdictionary;

/**
 *
 * @author Che-Wei Chou
 * @param <E>
 */

public class LinkedList<E extends IDedObject> {

    private Node head;  //first item of the linkedlist
    private Node tail;  //last item of the linkedlist

    /**
     * Add an item to the beginning of the list.
     *
     * @param n the item to be added
     */
    public void addFirst(E n) {
        Node<E> item = new Node<>(n);
        //If list is empty, then set the item to be the first item of the list.
        if (head == null) {
            head = item;
        } else {    //list not empty
            item.setNext(head);
            item.setPrevious(null);
            head.setPrevious(item);
            //Pervious three lines: connect item and head. 
            head = item;    //Make the added item the first item of the list
        }
    }

    /**
     * Delete the first item of the list.
     *
     * @return the deleted object
     */
    public IDedObject deleteFirst() {

        //if list is not empty, delete the first item
        if (head != null) {
            IDedObject n = head.getData();
            head = head.getNext();
            return n;
        }
        return null;
    }

    /**
     * Clear all items in the list.
     */
    //Note: if it is not doubly linkes, just make head null
    public void clearList() {
        while (head != null) {
            deleteFirst();
        }
    }
    
    /**
     * Get the first item stored in the list.
     * @return first item in the list. null if list is empty.
     */
    public IDedObject getFirst(){
        if (head != null){  //if list is not empty
            return head.getData();
        }
        return null;
    }

    /**
     * Check if the list is empty,
     *
     * @return true if the list is empty (first item of the list is null)
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Delete the requested node in list.
     *
     * @param n the node that is supposed to be deleted
     */
    private IDedObject deleteNode(Node n) {

        //Connect the node before and after the deleted node
        IDedObject deleted = n.getData();
        Node previous = n.getPrevious();
        Node next = n.getNext();
        if (previous != null) {
            //If n is the first node in the list
            previous.setNext(next);
        }
        if (next != null) {
            //If n is the last node in the list
            next.setPrevious(previous);
        }
        return deleted;

    }

    /**
     * Search for the IDed object that has the given ID.
     *
     * @param id the id the user want to search for
     * @return true of the object with the given id exist in the list
     */
    public boolean contains(int id) {
        //Start searching from head
        Node current = head;

        //While we haven't reach through the end of list
        while (current != null) {

            //Convert Node to IDed Object
            IDedObject obj = current.getData();
            if (obj.getID() == id) {
                return true;
            }
            //If the current node does not match the id, switch to the next one.
            current = current.getNext();
        }
        //If we reach the end of list and have not find the object with the given id
        return false;
    }

    /**
     * Find the IDedObject with the given ID in the list
     *
     * @param id
     * @return The object. Null if it doesn't exist in the list
     */
    public IDedObject getIDedObject(int id) {
        Node current = head;

        if (contains(id)) {
            while (current != null) {
                IDedObject obj = current.getData();
                if (obj.getID() == id) {
                    return obj;
                }
                current = current.getNext();
            }
        }
        return null;
    }

    /**
     * Delete the item with given ID and return the deleted item
     *
     * @param id
     * @return the deleted item. Null if item is not in the list.
     */
    public IDedObject deleteSearch(int id) {
        IDedObject obj = null;

        Node current = head;

        while (current != null) {
            IDedObject temp = current.getData();
            if (temp.getID() == id) {
                obj = current.getData();
                deleteNode(current);
            }
            current = current.getNext();
        }

        return obj;
    }
    
    /**
     * Return an array of String of IDedObject
     * @return an array of toStrings of IDedObject stored in the list
     */
    public String[] toArray(){
        Node current = head;
       
        String[] result = new String[size()];
        //start filling from the end of the array
        for (int i = result.length-1; i >= 0; i--){
            result[i] = current.getData().toString();
            current = current.getNext();
        }
        return result;
    }
    
    /**
     * Get the size of the LinkedList.
     * @return size of list.
     */
    public int size(){
        int result = 0;
        Node current = head;
        while (current!=null){
            result++;
            current = current.getNext();
        }
        return result;
    }
  
    

    /**
     * Print out the list.
     *
     * @return All the items in the list.
     */
    @Override
    public String toString() {
        String result = "";

        Node current = head;
        while (current != null) {
            result += current.toString() + "\n";
            current = current.getNext();
        }
        return result;
    }

}
