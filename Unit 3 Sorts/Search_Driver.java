/// name:  date:

import java.io.*;    //the File
import java.util.*;   //the Scanner
import javax.swing.*;  //the JOptionPane

public class Search_Driver{
  public static void main(String[] args) throws Exception {
    String filename = JOptionPane.showInputDialog("Enter filename");
    filename = filename + ".txt";
    String[] array = input(filename);
    String find = JOptionPane.showInputDialog("What string do want to look for?");
    int index = Searches.linear(array, find);
    if(index >= 0) {
      System.out.println("It was found at this spot " + index);
      System.out.println("Took " +Searches.linearCount+ " comparisons");
    }
    else
      System.out.println("Sorry I could not find that.");


  }
  public static String[] input(String filename) throws Exception {
    Scanner infile1 = new Scanner(new File(filename));
    Scanner infile = new Scanner(new File(filename));
    int size = 0;
    while (infile1.hasNext()) {
      size++;
      infile1.next();
    }
    infile1.close();
    String[] array = new String[size];
    for (int k = 0; k < array.length; k++)
      array[k] = infile.next();
    Selection.sort(array);
    return array;
  }
}

class Searches {
  public static int linearCount = 0;
  private static int binaryCount = 0;

  public static int binaryCount() {
    return binaryCount;
  }

  public static int linear(Comparable[] array, Comparable target) {
    for (int i = 0; i < array.length; i++) {
      if (array[i].compareTo(target) == 0)
        return i;
      linearCount++;
    }
    return -1;
  }

  public static int binary(Comparable[] array, Comparable target) {
    return binaryhelper(array, target, 0, array.length);
  }

  private static int binaryhelper(Comparable[] array, Comparable target, int start, int end) {
    if(start > end)
      return -1;
    int mid = (start + end) / 2;
    binaryCount++;
    if(target.compareTo(array[mid]) == 0) {
      return mid;
    }
    if(target.compareTo(array[mid]) > 0){
      return binaryhelper(array, target, mid + 1, end);
    }
    else {
      return binaryhelper(array, target, start, mid - 1);
    }
  }
}

