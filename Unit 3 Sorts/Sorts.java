/* M.L. Billington, 10/02/2006.
Uses the helper classes Selection and Insertion.
 Students are to write the Selection and Insertion classes.
*/

import java.util.*;
import java.io.*;

public class Sorts {
    public static void main(String[] args) throws Exception {
        //Part 1, for doubles
        int n = (int) (Math.random() * 100);
        double[] array = new double[n];
        for (int k = 0; k < array.length; k++)
            array[k] = Math.random();
        print(array);
        System.out.println("*************  *************");
        //Selection.sort(array);
        Insertion.sort(array);
        print(array);

        //Part 2, for Strings
        int size = 100;
        Scanner sc = new Scanner(new File("declaration.txt"));
        String[] arrayStr = new String[size];
        for (int k = 0; k < arrayStr.length; k++)
            arrayStr[k] = sc.next();
        print(arrayStr);
        System.out.println("**************************");
        //Selection.sort(arrayStr);
        Insertion.sort(arrayStr);
        print(arrayStr);
    }

    public static void print(double[] a) {
        // for(int k = 0; k < a.length; k++)
        // System.out.println(a[k]);
        for (double d : a)
            System.out.println(d);
        System.out.println();
    }

    public static void print(Object[] papaya) {
        for (Object item : papaya)     //for-each
            System.out.println(item);
    }
}

class Selection {
    public static void sort(double[] array) {
        for (int i = 0; i < array.length; i++) {
            int maxpos = findMax(array, array.length - i - 1);
            swap(array, maxpos, array.length - i - 1);
        }
    }

    private static int findMax(double[] array, int n) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                swap(array, i, i - 1);
            }
        }
        return n;
    }

    private static void swap(double[] array, int a, int b) {
        double temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**************************************************
     * for Strings
     **************************************************/
    public static void sort(String[] array) {
        for (int i = 0; i < array.length; i++) {
            int maxpos = findMax(array, array.length - i - 1);
            swap(array, maxpos, array.length - i - 1);
        }
    }

    public static int findMax(String[] array, int upper) {
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareToIgnoreCase(array[i - 1]) < 0)
                swap(array, i, i - 1);
        }
        return upper;
    }

    public static void swap(String[] array, int a, int b) {
        String temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /***************************************************
     * for Comparables,
     * Swap() is for Objects.
     * make sure that print() is for Objects, too.
     ***********************************************/
//   @SuppressWarnings("unchecked") this removes the warning for Comparable
    public static void sort(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            int maxPos = findMax(array, array.length - i - 1);
            swap(array, maxPos, array.length - i - 1);
        }
    }

    @SuppressWarnings("unchecked")
    public static int findMax(Comparable[] array, int upper) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(array[i + 1]) > 0)
                swap(array, i, i + 1);
        }
        return upper;
    }

    public static void swap(Object[] array, int a, int b) {
        Object temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}

// **********************************************************
// Name: Ankur Mishra       Date:
// The Insertion class
// write enough methods (sort() and shift()) to handle doubles and Comparables.
class Insertion {
    public static void sort(double[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j - 1] > array[j]) {
                shift(array, j, j - 1);
                j = j - 1;
            }
        }
    }

    private static void shift(double[] array, int a, int b) {
        double temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void sort(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i; Comparable temp = array[j];
            while (j > 0 && array[j - 1].compareTo(temp) > 0) {
                shift(array, j, j - 1);
                j--;
            }
            array[j] = temp;
        }
    }

    @SuppressWarnings("unchecked")

    public static void shift(Object[] array, int a, int b) {
        Object temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
