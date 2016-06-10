//name:   date: 
//for use with Graphs6: Dijkstra
//             Graphs7: Dijkstra with Cities

import java.io.*;
import java.util.*;

class Edge {
   public final wVertex target;
   public final double weight;
   
   public Edge(wVertex argTarget, double argWeight) {
      target = argTarget;
      weight = argWeight;
   }
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;

   public wVertex() {
      name = "";
      adjacencies = new ArrayList<Edge>();
   }

   public wVertex(String myName) {
      name = myName;
      adjacencies = new ArrayList<Edge>();
   }

   public String getName() {
      return name;
   }

   public double getMinDistance() {
      return minDistance;
   }

   public void setMinDistance(double m) {
      minDistance = m;
   }

   public ArrayList<Edge> getAdjacencies() {
      return adjacencies;
   }

   public void addEdge(Edge v) {
      adjacencies.add(v);
   }

   public int compareTo(wVertex wVertex) {
      if(minDistance < wVertex.minDistance)
         return -1;
      if(minDistance > wVertex.minDistance)
         return 1;
      else
         return 0;
   }

   public String toString() {
      return name;
   }

}

interface wVertexInterface 
{
   public String toString();
   
   public String getName();
   
   public double getMinDistance();
   
   public void setMinDistance(double m);
   
   // public wVertex getPrevious();         //Graphs 7
    
   // public void setPrevious(wVertex v);   //Graphs 7
   
   public ArrayList<Edge> getAdjacencies();
   
   public int compareTo(wVertex other);
}


public class TJGraphAdjListWeighted implements TJGraphAdjListWeightedInterface
{
   private ArrayList<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();

   public List<wVertex> getVertices() {
      return vertices;
   }

   public wVertex getVertex(int i) {
      return vertices.get(i);
   }

   public wVertex getVertex(String vertexName) {
      for (int i = 0; i < vertices.size(); i++)
         if (vertices.get(i).getName().equals(vertexName))
            return vertices.get(i);
      return null;
   }

   public void addVertex(String v) {
      if (getVertex(v) == null) {
         vertices.add(new wVertex(v));
         nameToIndex.put(v, vertices.indexOf(new wVertex(v)));
      }
   }

   public void addEdge(String source, String target, double weight) {
      if (getVertex(target) == null) {
         wVertex v = new wVertex();
         vertices.add(new wVertex(target));
         nameToIndex.put(target, vertices.indexOf(v));
      }
      Edge e = new Edge(getVertex(target), weight);
      wVertex v = getVertex(source);
      v.addEdge(e);
   }

   public void minimumWeightPath(String vertexName) {
      wVertex wVertex= getVertex(vertexName);
      wVertex.setMinDistance(0);
      PriorityQueue<wVertex> pq = new PriorityQueue<wVertex>();
      pq.add(wVertex);
      while(!pq.isEmpty()){
         wVertex = pq.remove();
         int i = 0; double pathWeight = wVertex.getMinDistance();
         while(i < wVertex.getAdjacencies().size()) {
            wVertex vertex = wVertex.getAdjacencies().get(i).target;
            double weight = wVertex.getAdjacencies().get(i).weight;
            if( pathWeight + weight < vertex.getMinDistance()) {
               vertex.setMinDistance(pathWeight + weight);
            }
            pq.add(vertex);
            i++;
         }
      }
   }
   
}    
interface TJGraphAdjListWeightedInterface 
{
   public List<wVertex> getVertices();
   
   public wVertex getVertex(int i);
   
   public wVertex getVertex(String vertexName);
   
   public void addVertex(String v);
   
   public void addEdge(String source, String target, double weight);
     
   public void minimumWeightPath(String vertexName);   //Dijkstra's
   
   /*  Graphs 7 */
//  public List<wVertex> getShortestPathTo(String vertexName)
       
//  public List<wVertex> getShortestPathTo(wVertex v)
    
//  public TJGraphAdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException 
 
}