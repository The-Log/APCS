//name:   date:   
//for use with Graphs0: Intro
//             Graphs1: Wardhall
//             Graphs2: Floyd
import java.util.*;
import java.io.*;

public class TJGraphAdjMat implements AdjacencyMatrix,Warshall ,Floyd
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;
   public TJGraphAdjMat(int size){
      grid = new int[size][size];
      vertices = new TreeMap<String, Integer>();
   }
   public void addEdge(int source, int target){
      grid[source][target] = 1;
   }
   public void removeEdge(int source, int target){
      grid[source][target] = 0;

   }
   public boolean isEdge(int from, int to){
      if(grid[from][to] == 1)
         return true;
      return false;
   }
   public boolean isEdge(String f, String t){
      int from = vertices.get(f);
      int to = vertices.get(t);
      if(grid[from][to] == 1 )
         return true;
      return false;
   }
   public void displayGrid(){
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[0].length; j++) {
            System.out.print(grid[i][j]);
         }
         System.out.println();
      }
   }
   public int edgeCount(){
      int edgeCount = 0;
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[0].length; j++) {
            if(isEdge(i,j)){
               edgeCount += 1;
            }
         }
      }
      return edgeCount;
   }
   public List<Integer> getNeighbors(int source){
      List<Integer> list = new ArrayList<Integer>();
      for (int i = 0; i < grid[0].length; i++) {
         list.add(grid[source][i]);
      }
      return list;
   }
   public void allPairsReachability(){
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid.length; j++) {
            for (int k = 0; k < grid.length; k++) {
               if(grid[i][j] == 1 && grid[j][k] == 1){
                  grid[i][k] = 1;
               }
            }
         }
      }
   }
   public void readNames(String filename){
      File file = new File(filename);
      try{
         Scanner scanner = new Scanner(file);
         int size = scanner.nextInt();
         int i = 0;
         while (scanner.hasNext()){
            vertices.put(scanner.next(), i);
            i++;
         }
      }
      catch (Exception e){
         System.out.println("error. pls type it again correctly");
      }
   }
   public void readGrid(String filename){
      File file = new File(filename);
      try{
         Scanner infile = new Scanner(file);
         int size = infile.nextInt();
         for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
               grid[i][j] = infile.nextInt();
               System.out.println(grid[i][j]);
            }
         }
      }
      catch (Exception e){
         System.out.println("error. pls type it again correctly");
      }

   }
   public void displayVertices(){

   }

   public int getCost(int from, int to) {
      return 0;
   }

   public int getCost(String from, String to) {
      return 0;
   }

   public void allPairsWeighted() {

   }
}

interface AdjacencyMatrix
{
   public void addEdge(int source, int target);
   public void removeEdge(int source, int target);
   public boolean isEdge(int from, int to);
   public void displayGrid();
   public int edgeCount();
   public List<Integer> getNeighbors(int source);
   
  /**********  User-friendly    **************/
   // public boolean isEdge(String from, String to);
   // public Map<String, Integer> getVertices();     
   // public void readNames(String fileName) throws FileNotFoundException;
   // public void readGrid(String fileName) throws FileNotFoundException;
   // public void displayVertices();
 /************* end  User-friendly   **************/
}

interface Warshall
{
   public void allPairsReachability();
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted(); 
}
