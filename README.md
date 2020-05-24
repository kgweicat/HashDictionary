# HashDictionary
This program uses a hash table to return dictionary definitions for a user-inputted word.

The program references a dictionary file (*dictionary.csv*) found in an online database. I constructed a class (*HashDictionary*) that streamlines the word lookup process by acting as a hash table. For definitions, I implemented my previously created interface *IDedObject* (see *IDedObject.java* for more details). Each definition is its own *IDedObject*, and all definitions are collectively stored in a LinkedList as a value in the hashtable. I also used the LinkedList class I wrote for the Magazine_database project. The hash table's keys are words, and the values are definitions associated with their respective words. 

The hash dictionary allows the user to enter a word, and the program will either provide the user a list of one or more definitions, or let the user know the given word is unknown.*

\*The dictionary.csv file I found online is, unfortunately, not very good. Therefore, many words are missing from the file.
\*Potential ways to improve this program are as such:
    1. Simply find a better .csv file (unfortunately this is the best .csv file I can find at this time)
    2. If there is a better file in a different format, we can either
       (1.) convert the program to make it read in another file
       (2.) convert the other file into a .csv file
