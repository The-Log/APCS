//  Name:      date:
//  This program takes a text file, creates an index (by line numbers)
//  for all the words in the file and writes the index
//  into the output file.  The program prompts the user for the file names.
import java.io.*;
import java.util.*;

public class IndexMakerMap
{
    public static void main(String[] args) throws IOException
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("\nEnter input file name: ");
        String infileName = keyboard.nextLine().trim();
        infileName += ".txt";
        Scanner inputFile = new Scanner(new File(infileName));
        String outfileName = "fishIndex.txt";
        PrintWriter outputFile = new PrintWriter(new FileWriter(outfileName));
        indexDocument(inputFile, outputFile);
        inputFile.close();
        outputFile.close();
        System.out.println("Done.");
    }

    public static void indexDocument(Scanner inputFile, PrintWriter outputFile) {
        DocumentIndex index = new DocumentIndex();
        int lineNum = 0;
        while (inputFile.hasNextLine()) {
            lineNum++;
            index.addAllWords(inputFile.nextLine(), lineNum);
        }
        outputFile.println(index.toString());
    }
}

class DocumentIndex extends TreeMap<String, ArrayList<Integer>> {

    public void addWord(String word, int lineNum) {
        word = word.toUpperCase();
        if(containsKey(word)) {
            ArrayList<Integer> arrayList = get(word);
            arrayList.add(lineNum);
            put(word, arrayList);
        }
        else{
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            arrayList.add(lineNum);
            put(word, arrayList);
        }
    }
    /**
     * extracts all the words from str, skipping punctuation and whitespace
     * and for each word calls addWord(word, num).  A good way to skip punctuation
     * and whitespace is to use String's split method, e.g., split("[., \"!?]")
     */
    public void addAllWords(String str, int lineNum) {
        String[] s = str.split("[., \"!?]");
        for (String string : s) {
            if (!string.equals(""))
                addWord(string, lineNum);
        }
    }
    public String toString(){
        String s = "";
        Iterator i  = keySet().iterator();
        while(i.hasNext()){
            Object o = i.next();
            String s1 = "" + get(o);
            s1 = s1.substring(s1.indexOf("[") + 1, s1.indexOf("]"));
            s = s + o + " " + s1;
            s = s + "\n";
        }
        return s;
    }
}


