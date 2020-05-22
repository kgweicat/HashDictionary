package hashdictionary;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.*;

//TODO: add your imports here

public class Driver {
    
    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
    
    public static void main(String[] args){
	Scanner inDict=null;	// the input dictionary
	try{			// load file for input dictionary
	    inDict = new Scanner(new File("dictionary.csv"));
	}catch(Exception ex){
	    out.println("Please download dictionary.csv");
	    exit(1);
	}
	// hash table to store words and meanings
	HashDictionary dict = new HashDictionary(); // TODO: write this class
	List<String> line;			    // the current line
	// load the dictionary into RAM
	out.print("Loading Dictionary...");
	while(inDict.hasNextLine()){
	    line = parseLine(inDict.nextLine());
	    dict.newDef(line.get(0).toLowerCase(), // word 	    // TODO: write this method
			line.get(1), // part of speech (verb, noun, adverb...)
			line.get(2)); // meaning
	}
	out.println();		// finished loading
	inDict.close();		// done reading in file
	Scanner usrIn = new Scanner(in); // for reading user input
	String wrd="";
	out.println("Enter -1 to exit.");
	while(true){
	    out.print("Look up word: "); // prompt
	    wrd = usrIn.next();		 // get user input
	    if(wrd.equals("-1")) break;	 // exit program if -1
	    String[] defs =  dict.getDefs(wrd.toLowerCase()); // get definitons for wrd
	    if(defs != null){				      // if wrd is a word, give defintions
		out.println(wrd+":\n");
		//TODO: print definitions with definition number part of speech and definitons
		// a single new line should separate each definiton
		/* this format:
		   word\n
		   1\t    partOfSpeech1 meaning1\n
		   2\t    partOfSpeech2 meaning2\n
		   and so on.
		*/
                
                
                for (int i = 0; i < defs.length ; i++){
                    System.out.println(defs[i] + "\n");
                }
		
		
	    }else{
		out.println(wrd+" is not a known word.");
	    }
	    out.println("\n");	// two blank lines to separate words
	}
	out.println("Done");
	usrIn.close();
    }

    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null || cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
	    {}
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }
}
