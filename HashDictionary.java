package hashdictionary;

/**
 *
 * @author Che-Wei (Joanne) Chou
 */
public class HashDictionary {

    public static final double LOAD_FACTOR = 0.75;  //Load factor
    private String[] keys;      //Store the keys
    private Object[] values;    //Store the LinkedList of definitions   
    private int capacity;       //Capacity of the HashTable
    private int size = 0;       //How many items there are in the HashTable
    private int maxCluster = 0; //Maximum number of clustering in the HashTable
    
    /**
     * Constructor.
     */
    public HashDictionary() {
        capacity = 11;  //Arbitrary number
        keys = new String[capacity];
        values = new Object[capacity];
    }

    /**
     * Get the smallest prime number bigger than 2 * size.
     * @param size the current capacity of the HashTable ( > 0)
     * @return the smallest prime number bigger than 2 * size
     */
    private int smallestPrime(int capacity) {
        int result = 2 * capacity;
        
        boolean isPrime;
        
        while (true) {
            
            isPrime = true;
            int root = (int) Math.sqrt(result); //to save time
            
            for (int i = 2; i <= root; i++) {
                if (result % i == 0) {  //If it can be divided by any number smaller than its squre root
                    isPrime = false;    //It is not a prime number
                }
            }
            
            if (isPrime) {
                return result;
            }
            
            result++;
        }
    }

    /**
     * Hash function: return where the word should be in the hashTable
     * @param word
     * @return the position of the word
     */
    private int hash(String word) {
        if (word.length() <= 1) {
            //These are all arbitrary numbers
            //Take the absolute value so that there can only be positive numbers.
            return Math.abs(35 * word.charAt(0)) % capacity;
        } else {
            return Math.abs(2759 * word.length() + 164 * word.charAt(0) + 83 * word.charAt(1)) % capacity;
        }
    }

    /**
     * The second hash function used for double hashing
     * @param word
     * @return an arbitrary number that can be used for double hashing
     */
    private int hashTwo(String word) {
        int result = 0;
        for (int i = 0; i < word.length(); i++){
            result += word.charAt(i) * word.charAt(i);
        }
        result = result + word.length() * 35 + word.charAt(0) * 79;
        return result;
    }

    /**
     * Insert a new word and definition into the HashTable
     * @param word
     * @param partOfSpeech
     * @param meaning 
     */
    public void newDef(String word, String partOfSpeech, String meaning) {
        int id = hash(word);
        
        int collision = 0;
        
        while (keys[id] != null) {  //The position is already occupied by some other word
            
            if (word.equals(keys[id])) {    //It is actually another definition of an existing word
                int count = ((LinkedList) (values[id])).getFirst().getID() + 1; //# of definition
                Definition def = new Definition(count, partOfSpeech, meaning);
                //Add the new definition into the LinkedList of definition to the word
                ((LinkedList) (values[id])).addFirst(def);
                return;     //Quit
            } else {    //The position is occupied by another word
                collision++;
                id = Math.abs(hash(word) + collision * hashTwo(word)) % capacity;   //Double Hashing
            }
        }
        
        //Update the maximum number of clusters in the HashTable
        if (collision > maxCluster){
            maxCluster = collision;
        }

        //Create a new LinkedList of definition for the new word
        values[id] = new LinkedList<Definition>();
        keys[id] = word;    //Insert the word into the array of keys
        Definition def = new Definition(1, partOfSpeech, meaning);  //New word: 1st definition
        //Add in the definition
        ((LinkedList) (values[id])).addFirst(def);
        
        //Update the size of the HashTable
        size++;

        //Rehash if the hashTable is too big
        if ((double) size / capacity > LOAD_FACTOR) {
            rehash();
        }
    }

    /**
     * Get the array of definition for the given word. 
     * @param word 
     * @return array of definition for the word. Null of word does not exist in the dictionary
     */
    public String[] getDefs(String word) {
        int id = hash(word);
        int collision = 0;
        while (!word.equals(keys[id])) {
            collision++;
            id = Math.abs(hash(word) + collision * hashTwo(word)) % capacity;
            if (collision > maxCluster){    //If number of collision > maximum cluster in the HashTable
                return null;                //the word does not exist in this dictionary
            }
        }
        
        return ((LinkedList) (values[id])).toArray();
    }

    /**
     * Rehash.
     */
    private void rehash() {
        //Calculate new capacity of the HashTable
        capacity = smallestPrime(capacity);
        
        //Create temporary arrays to store the keys and definition
        String[] temp = new String[capacity];
        Object[] tempValues = new Object[capacity];
        
        //Reset size of HashTable and maximum number of clusters
        size = 0;
        maxCluster = 0;
        
        //Loop through original hashTable for words
        for (int i = 0; i < keys.length; i++) {
            //If there is a word
            if (keys[i] != null) {
                int id = hash(keys[i]);     //Calculate new position in new HashTable
                int collision = 0;
                while (temp[id] != null) {  //If the position is already occupied by other word
                    //Since we are rehashing, we can gaurantee they are all different words.
                    //Therefore if a spot is occupoed it must be a collision
                    collision++;
                    id = Math.abs(hash(keys[i]) + collision * hashTwo(keys[i])) % capacity; //Double hashing
                    //Again using absolute values to gaurantee positive numbers
                }
                
                //Update maxClusrer and size
                if (collision > maxCluster){
                    maxCluster = collision;
                }
                size++;
                
                //Store the values into the new position in the new HashTable
                tempValues[id] = values[i];
                temp[id] = keys[i];            
            }
        }
        System.out.println("rehashed"); 
        
        //Substitute the old HashTable with the new HashTable
        keys = temp;
        values = tempValues;
    }
}
