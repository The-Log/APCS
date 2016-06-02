//name: Aye Lmao   date:  

import java.io.*;      //the File class
import java.util.*;    //the Scanner class
   
public class Widgets
{
   public static final int numberOfWidgets = 57;
   public static void main(String[] args) throws Exception
   {
      Comparable[] array = input("widget.txt");
      Insertion.sort(array);
      print(array);
   }
          	
   public static Comparable[] input(String filename) throws Exception
   {
      Scanner infile = new Scanner( new File(filename) );
      Comparable[] array = new Widget[numberOfWidgets];
      for(int k = 0; k < numberOfWidgets; k++)
      {
         int pounds = Integer.parseInt(infile.next());
         int ozs = Integer.parseInt(infile.next());
         array[k] = new Widget(pounds, ozs);
      }
      infile.close();
      return array;
   }
   	  
   public static void print(Object[] mango)
   {
      for (int i = 0; i < mango.length; i++) {
         System.out.println(mango[i]);
      }
   }
}
   /////////////////////////////////////////////////////
	//name: Ankur Mishra   date:
