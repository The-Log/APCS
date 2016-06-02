import java.util.*;
import java.io.*;

public class PigLatin_Period_5_MishraA {
    public static void main(String[] args) throws FileNotFoundException {
        part_1();
        //part_2();
    }

    public static String pig(String s) {
        String vowels = "aeiou";
        int indexOfVowel = 0;
        boolean foundVowelPos = false;
        boolean caps = false;
        String extracted = getPunct(s);
        //System.out.println(extracted);
        s = removePunct(s);
        String fChar = s.charAt(0) + "";
        if (fChar.equals(s.substring(0, 1).toUpperCase())) {
            caps = true;
        }
        //System.out.println(s);
        for (int i = 0; i < s.length(); i++) {//Thomas
            if (i + 2 < s.length() && s.substring(i, i + 2).equalsIgnoreCase("qu")) {
                indexOfVowel = i + 2;
                foundVowelPos = true;
                break;
            }
            for (int j = 0; j < vowels.length(); j++) {
                if (s.substring(i, i + 1).equalsIgnoreCase(vowels.substring(j, j + 1))) {
                    //System.out.println("True");
                    if (i == 0) {
                        indexOfVowel = i;
                        foundVowelPos = true;
                        break;
                    } else {
                        indexOfVowel = i;
                        foundVowelPos = true;
                        break;
                    }
                }
            }
            vowels = vowels + "y";
            if (foundVowelPos) break;
        }
        if (indexOfVowel == 0) {
            s = s + "way";
        } else {
            String firstletters = s.substring(0, indexOfVowel);
            //System.out.println(firstletters);
            s = s.substring(indexOfVowel) + firstletters + "ay";
            //System.out.println(s);
        }
        s = s.toLowerCase();
        if (caps) {
            String upper = "" + s.charAt(0);
            s = upper.toUpperCase() + s.substring(1);
        }
        s = putBack(s, extracted);
        if (!foundVowelPos) {
            s = "INVALID";
        }
        return s;
    }

    public static void part_1() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\nWhat word? ");
            String s = sc.next();
            if (s.equals("-1"))
                System.exit(0);
            String p = pig(s);
            System.out.println("***** " + p + " *****");
        }
    }

    public static void part_2() {
        Scanner sc = new Scanner(System.in);
        System.out.print("What filename? ");
        String filename = sc.next();
        piglatenizeFile(filename);
    }

    /******************************
     * take in a filename, and creates a file that is the inputted file
     * fully piglatenized. The outputFile should be in piglatin form
     * PigLatin.txt should become IgLatinpay.txt.
     * <p/>
     * Note: filename will have .txt on it.
     ******************************/
    public static void piglatenizeFile(String filename) {
        try {
            Scanner infile = new Scanner(new File(filename));
            filename = filename.substring(0, filename.indexOf("."));
            String fileout = pig(filename);
            fileout = fileout + ".txt";
            PrintStream outfile = new PrintStream(new FileOutputStream(fileout));

            while (infile.hasNext()) {
                String s = infile.nextLine();
                String[] tokens = splitLinesToArray(s);

                for (int i = 0; i < tokens.length; i++) {
                    StringBuilder builder = new StringBuilder();
                    StringTokenizer token = new StringTokenizer(tokens[i], " ");
                    while (token.hasMoreTokens()) {
                        builder.append(pig(token.nextToken()));
                        builder.append(" ");
                    }
                    builder.append("\n");
                    outfile.print(builder.toString());
                }
            }
            outfile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getPunct(String s) {
        String punct = ".!,?:;/(){}[]<>";
        String extracted = "";
        if (s.startsWith("\"")) {//extract if 1st char is a punct
            extracted = "\"";
            //System.out.println("Removed the first Punct char");

        }
        for (int i = 0; i < s.length(); i++) {
            for (int k = 0; k < punct.length(); k++) {

                if (s.substring(i, i + 1).equals("" + punct.charAt(k))) { //2nd last
                    extracted = extracted + punct.charAt(k);
                    //System.out.println("Char=" + punct.charAt(k));

                }

            }
        }
        if (s.endsWith("\"")) { //last
            extracted = extracted + "\"";
        }
        return extracted;
    }

    public static String removePunct(String s) {
        String punct = ".,?!:!;\"(){}[]<>";
        for (int i = 0; i < s.length(); i++) {
            for (int k = 0; k < punct.length(); k++) {
                if (s.substring(0, 1).equals(punct.substring(k, k + 1))) {
                    s = s.replace(s.substring(0, 1), "");
                }
                if (s.substring(s.length() - 1, s.length()).equals(punct.substring(k, k + 1))) {
                    s = s.replace(s.substring(s.length() - 1, s.length()), "");
                }
                if (s.substring(s.length()).equals(punct.substring(k, k + 1))) {
                    s = s.replace(s.substring(s.length()), "");
                }
            }
        }
        return s;
    }

    public static String putBack(String s, String e) {
        if (e.length() > 0) {
            if (e.length() == 1) {
                s = s + e;
            } else if (e.length() == 2) {
                s = e.charAt(0) + s + e.charAt(1);
            } else {
                s = e.charAt(0) + s + e.substring(1);
            }
        }
        return s;
    }

    private static String[] splitLinesToArray(String s) {

        String delimiter = "\n";

        String[] tokens = s.split(delimiter);
        for (int i = 0; i < tokens.length; i++) {

        }
        return tokens;
    }
}
