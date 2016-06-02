//name: Ankur Mishra
//date: 10/11/15
import java.util.Scanner;
import java.io.*;
public class AreaFill
{
   public static char[][] grid = null;

   public static void main(String[] args) throws FileNotFoundException
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Filename: ");
      String filename = sc.next();
      grid = read(filename);
      display(grid);
      System.out.print("\nEnter ROW COL to fill from: ");
      int row = sc.nextInt();
      int col = sc.nextInt();
      fill(grid, row, col, grid[row][col]);
      display(grid);
      //  Extension
      // int count = fillAndCount(grid, row, col, grid[row][col]);
      // display(grid);
      // System.out.println("count = " + count);
      System.out.println();
      sc.close();
   }
   public static char[][] read(String filename)throws FileNotFoundException
   {
      filename = filename + ".txt";
      Scanner infile = new Scanner( new File( filename ) );
      int row = infile.nextInt();
      int column = infile.nextInt();
      char[][] array = new char[row][column];
      //System.out.println(row);System.out.println(column);
      for (int i =  0; i < row; i ++ ){
         String str = infile.nextLine();
         for(int col = 0; col < str.length(); col++){
            array[i][col] = str.charAt((col));
         }
      }
      return array;
   }
   public static void display(char[][] g)
   {
      for (int i =  0; i < g.length; i ++ ){
         for(int col = 0; col < g[0].length; col++){
            System.out.print(g[i][col]);
         }
         System.out.println();
      }
   }
   public static void fill(char[][] g, int r, int c, char ch) //recursive method
   {
      if((g[r][c] != ch )|| (r == 0 || r == g.length - 1) && (c == 0 || c == g.length -1 )){
         return;
      }
      else{
         g[r][c] = '*';
         if(r < g.length -1)
            fill(g,r + 1,c,ch);
         if(r > 0)
            fill(g,r - 1,c,ch);
         if(c < g[0].length - 1)
            fill(g,r,c + 1,ch);
         if(c > 0)
            fill(g,r,c - 1,ch);

      }
   }

   // Extension-- count and return the number of asterisks
   public static int fillAndCount(char[][] g, int r, int c, char ch)
   {
      return 0;
   }
}