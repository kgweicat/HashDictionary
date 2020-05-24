# HashDictionary
Make a dictionary using a hash table

I create a hashtable to read from dictionary.csv style to make looking up words easier.
All the keys for the hashtables are words, and values are list of definition for the words. 
For definition, I implemented the IDedObject interface I wrote for the magazine_database project. 
Each definition is its own IDed object, and all the definitions are collectively stored into a LinkedList to be a value in the hashtable. I also use the LinkedList class I wrote in the magazine_database project.

The magazine database allow people to enter a word they want to search, and the program will either provide the user the definition or let the user know it is not a known word.*

\*The dictionary.csv file I found online is not very good and therefore a lot of words are actually missing from the file.
