//Name: Ankur Mishra  date: 9/21/15  
public class Sentence_Period_5_MishraA
{
    public static void main(String[] args)
    {
        System.out.println("PALINDROME TESTER");
        Sentence s = new Sentence( "\"Hello there!\" she said." );
        System.out.println( s.getSentence() );
        System.out.println( s.getNumWords() );
        System.out.println( s.isPalindrome() );
        System.out.println();

        s = new Sentence( "A Santa lived as a devil at NASA." );
        System.out.println( s.getSentence() );
        System.out.println( s.getNumWords() );
        System.out.println( s.isPalindrome() );
        System.out.println();


        s = new Sentence( "Flo, gin is a sin! I golf." );
        System.out.println( s.getSentence() );
        System.out.println( s.getNumWords() );
        System.out.println( s.isPalindrome() );
        System.out.println();


        s = new Sentence( "Eva, can I stab bats in a cave?" );
        System.out.println( s.getSentence() );
        System.out.println( s.getNumWords() );
        System.out.println( s.isPalindrome() );
        System.out.println();

        s = new Sentence( "Madam, I'm Adam." );
        System.out.println( s.getSentence() );
        System.out.println( s.getNumWords() );
        System.out.println( s.isPalindrome() );
        System.out.println();

        // Lots more test cases.  Test every line of code.  Test
        // the extremes, test the boundaries.  How many test cases do you need?

    }
}
class Sentence
{
    private String mySentence;
    private int myNumWords;

    //Constructor.  Creates sentence from String str.
    //						Finds the number of words in sentence.
    //Precondition:  Words in str separated by exactly one blank.
    public Sentence( String str ){
        mySentence = str;
        int num = 1;
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i, i+1).equals(" ")) {
                num++;
            }
        }
        myNumWords = num;
    }
    public int getNumWords()
    {
        return myNumWords;
    }

    public String getSentence()
    {
        return mySentence;
    }

    //Returns true if mySentence is a palindrome, false otherwise.
    public boolean isPalindrome()
    {
        mySentence = removeBlanks(mySentence);
        mySentence = removePunctuation(mySentence);
        System.out.println(mySentence);
        //System.out.println("len:"+ mySentence.length());
        return isPalindrome(mySentence, 0, mySentence.length() - 1);
    }
    //Precondition: s has no blanks, no punctuation, and is in lower case.
    //Returns true if s is a palindrome, false otherwise.
    private static boolean isPalindrome( String s, int start, int end )
    {
        boolean bool =  false;

        if(s.length() == 1 || s.length() == 0) {
            bool = true;
        }
        String startWord = "" + s.charAt(start);
        String endWord = "" + s.charAt(end);
        if(!startWord.equalsIgnoreCase(endWord)){
            bool = false;
            return bool;
        }
        if(s.length() > 1 && startWord.equalsIgnoreCase(endWord)){
            s = s.substring(start + 1 ,end);
            //System.out.println(s);
            if(s.length() > 1) {
                return isPalindrome(s, start, end - 2);
            }
            else{
                bool = true;
                return bool;
            }
        }
        return bool;
    }
    //Returns copy of String s with all blanks removed.
    //Postcondition:  Returned string contains just one word.
    private static String removeBlanks( String s ){
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(i, i+1).equals(" ")) {
                s = s.replace(s.substring(i, i+1), "");
            }
        }
        return s;
    }

    //Returns copy of String s with all letters in lowercase.
    //Postcondition:  Number of words in returned string equals
    //						number of words in s.
    private static String lowerCase( String s )
    {
        s = s.toLowerCase();
        return s;
    }

    //Returns copy of String s with all punctuation removed.
    //Postcondition:  Number of words in returned string equals
    //						number of words in s.
    private static String removePunctuation( String s )
    {
        String punct = ".,?'!:!;\"(){}[]<>";
        for (int i = 0; i < s.length(); i++) {
            for (int k = 0; k < punct.length(); k++) {
                String firstChar = s.charAt(i) + "";
                String secondChar = punct.charAt(k) + "";
                if(firstChar.equalsIgnoreCase(secondChar)){
                    s = s.replace(firstChar, "");
                    if(i == s.length()) {
                       break;
                    }
                }
            }
        }
        return s;
    }
}
