//  Name: Ankur Mishra      date:
//  This program takes a text file, creates an index (by line numbers)
//  for all the words in the file and writes the index
//  into the output file.  The program prompts the user for the file names.

import java.util.*;
import java.util.ArrayList;
import java.io.*;

public class IndexMaker
{
	public static void main(String[] args) throws IOException
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nEnter input file name: ");
		String fileName = keyboard.nextLine().trim();
		Scanner inputFile = new Scanner(new File(fileName));
		System.out.print("\nEnter output file name: ");
		fileName = keyboard.nextLine().trim();
		PrintWriter outputFile = new PrintWriter(new FileWriter(fileName));
		DocumentIndex index = new DocumentIndex();
		String line = null;
		int lineNum = 0;
		while(inputFile.hasNextLine())
		{
			lineNum++;
			index.addAllWords(inputFile.nextLine(), lineNum);
		}
		for(IndexEntry entry : index)
			outputFile.println(entry);
		inputFile.close();
		outputFile.close();
		System.out.println("Done.");
	}
}
class DocumentIndex extends ArrayList<IndexEntry>
{
	//constructors
	public DocumentIndex(){
		super();
	}
	public DocumentIndex(int i){
		super(i);
	}

	/** calls foundOrInserted, which returns an index.  At that position,  
	 updates that IndexEntry's list of line numbers with num. */
	public void addWord(String word, int num)
	{
		word = word.toUpperCase();
		int index = foundOrInserted(word);
		get(index).add(num);
	}

	/** extracts all the words from str, skipping punctuation and whitespace 
	 and for each word calls addWord(word, num).  A good way to skip punctuation
	 and whitespace is to use String's split method, e.g., split("[., \"!?]") */
	public void addAllWords(String str, int num)
	{
		String[] s = str.split("[., \"!?]");
		for (String string : s) {
			if ( !string.equals("") )
				addWord(string, num);
		}
	}

	/** traverses this DocumentIndex and compares word to the words in the 
	 IndexEntry objects in this list, looking for the correct position of
	 word. If an IndexEntry with word is not already in that position, the
	 method creates and inserts a new IndexEntry at that position. The
	 method returns the position of either the found or the inserted
	 IndexEntry.*/
	private int foundOrInserted(String word){
      /*
      If empty add word and returns the pos. 
      If more than one word, compare each one, finds where it exists then returns positions. 
      If its no there it adds the IndexEntry, finds where it belongs and adds the entry. 
      */
		IndexEntry entry = new IndexEntry(word);
		if (isEmpty()){
			add(entry);
			return 0;
		}
		else{
			for(int i = 0 ; i < size(); i ++){
				if (get(i).getWord().compareTo(word) == 0){
					return i;
				}
			}
			for(int i = 0 ; i < size(); i ++){
				if (get(i).getWord().compareTo(word) > 0){
					add(i, entry);
					return i;
				}
			}
			return size() - 1;
		}
	}
}
class IndexEntry implements Comparable<IndexEntry>
{

	private String word;
	private ArrayList<Integer> numsList;

	public IndexEntry(String str){
		word = str.toUpperCase();
		numsList =new ArrayList<Integer>();
	}

	/**  appends num to numsList, but only if it is not already in that list.    
	 */
	public void add(int num)
	{
        if(!numsList.contains(num))
		    numsList.add(num);
	}

	/** this is a standard accessor method  */
	public String getWord()
	{
		return word;
	}
	public int compareTo(IndexEntry ie){
		return word.compareTo(ie.toString());
	}
	/**  returns a string representation of this Index Entry.  */
	public String toString()
	{
		String s = " ";
		for(int i = 0; i < numsList.size(); i++){
			s = s + numsList.get(i) + ", ";
		}
		return word + s;
	}
}

