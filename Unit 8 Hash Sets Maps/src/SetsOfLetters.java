// Name:    Date:

import java.util.*;
import java.io.*;

public class SetsOfLetters {
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner sc = new Scanner(System.in);
        //System.out.print("Enter the file name: ");
        //String fileName = sc.next();
        String fileName = "declarationLast.txt";
        fillTheSets(fileName);
    }

    public static void fillTheSets(String filename) throws FileNotFoundException {
        Scanner infile = new Scanner(new File(filename));
        ArrayList<Set<String>> us = new ArrayList<Set<String>>();
        ArrayList<Set<String>> ls = new ArrayList<Set<String>>();
        ArrayList<Set<String>> os = new ArrayList<Set<String>>();

        while (infile.hasNext()) {
            String sentence = infile.nextLine();
            Set<String> upper = new HashSet<String>();
            Set<String> lower = new HashSet<String>();
            Set<String> other = new HashSet<String>();
            String s = sentence;
            for (int i = 0; i < s.length(); i++) {
                String upperString = ("" + s.charAt(i)).toUpperCase();
                String lowerString = ("" + s.charAt(i)).toLowerCase();
                if (isSymbol("" + s.charAt(i))) {
                    other.add("" + s.charAt(i));
                } else if ((upperString.equals("" + s.charAt(i)))) {
                    upper.add("" + s.charAt(i));
                } else if (lowerString.equals("" + s.charAt(i))) {
                    lower.add("" + s.charAt(i));
                }
            }
            us.add(upper);
            ls.add(lower);
            os.add(other);
            System.out.println(sentence);
            System.out.println("Lower Case: " + lower);
            System.out.println("Upper Case: " + upper);
            System.out.println("Other: " + other);
            System.out.println();
        }
        Object[] commonLC = ls.get(0).toArray();
        commonLC = buildCommon(commonLC, ls);
        String s = "[";
        if(commonLC[0] != (null)) {
            s += commonLC[0] + ", ";
            s = getString(commonLC, s);
        }
        s += "]";

        Object[] commonUC = us.get(0).toArray();
        commonUC = buildCommon(commonUC, us);
        String s1 = "[";
        if(commonUC[0] != (null)) {
            s1 += commonUC[0] + ", ";
            s1 = getString(commonUC, s1);
        }
        s1 += "]";

        Object[] commonOC = os.get(0).toArray();
        commonOC = buildCommon(commonUC, us);
        String s2 = "[";
        if(commonOC[0] != (null)) {
            s2 += commonOC[0] + ", ";
            s2 = getString(commonOC, s2);
        }
        s2 += "]";

        System.out.println("Common Lower Case: " + s);
        System.out.println("Common Upper Case: " + s1);
        System.out.println("Common Other: " + s2);
    }
    private static String getString(Object[] commonLC, String s) {
        for (int i = 1; i < commonLC.length; i++) {
            if(commonLC[i] != null && commonLC[i] != commonLC[i-1]) {
                s += commonLC[i];
                if (i < commonLC.length && commonLC[i] != commonLC[i+1])
                    s += ", ";
            }
        }
        return s;
    }

    public static boolean contains(Object[] array, Object o){

        for (int i = 0; i < array.length; i++) {
            if(array[i] == null)
                return false;
            if(array[i].equals(o))
                return true;
        }
        return false;
    }
    public static Object[] buildCommon(Object[] array, ArrayList<Set<String>> ls){
        Object[] temp = new Object[array.length];
        for (int j = 1; j < ls.size(); j++) {
            int tempI = 0;
            Set set = ls.get(j);
            for (int i = 0; i < array.length; i++) {
                if (set.contains(array[i])) {
                    temp[tempI] = array[i];
                    tempI++;
                }
            }
            array = temp;
        }
        return array;
    }
    public static boolean isSymbol(String s) {
        String symbols = "+.!, -|:;'+[]{},";
        for (int i = 0; i < symbols.length(); i++) {
            if (s.equals("" + symbols.charAt(i)))
                return true;
        }
        return false;
    }
}