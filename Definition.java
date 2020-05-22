package hashdictionary;

/**
 *
 * @author Che-Wei (Joanne) Chou
 */
public class Definition implements IDedObject {

    private int id;
    private String partOfSpeech;
    private String meaning;

    /**
     * Constructor
     * @param num id
     * @param part partOfSpeech
     * @param mean meaning
     */
    public Definition(int num, String part, String mean) {
        id = num;
        partOfSpeech = part;
        meaning = mean;
    }

    /**
     * Print out the ID.
     */
    @Override
    public void printID() {
        System.out.println(id);
    }

    /**
     * Return the ID.
     * @return id.
     */
    @Override
    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return id + ". (" + partOfSpeech + ") " + meaning;
    }
}
