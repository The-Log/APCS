//name:   date:   
//for use with Graphs0: Intro
//             Graphs1: Wardhall
//             Graphs2: Floyd
import java.util.*;
import java.io.*;

public class TJGraphAdjMat implements AdjacencyMatrix //,Warshall,Floyd
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;
   public TJGraphAdjMat(int size){
      grid = new int[size][size];
   }
   public void addEdge(int source, int target){
      grid[source][target] = 1;
   }
   public void removeEdge(int source, int target){
      grid[source][target] = 0;

   }
   public boolean isEdge(int from, int to){
      if(grid[from][to] == 1 || grid[to][from] == 1)
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
