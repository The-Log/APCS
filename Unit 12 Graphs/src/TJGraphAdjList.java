//name:    date:
//for use with Graphs3: EdgeList
//             Graphs4: DFS-BFS
//             Graphs5: EdgeListCities

import java.io.*;
import java.util.*;

/*********************
 * Graphs 3:  EdgeList
 *******************************/
interface VertexInterface {
    public String toString();     //just return the name

    public String getName(); // returns name

    public ArrayList<Vertex> getAdjacencies(); // returns list adjacencies

    public void addEdge(Vertex v); // adds edge to _
}

interface TJGraphAdjListInterface {
    public List<Vertex> getVertices(); // returns list vertices

    public Vertex getVertex(int i); // returns vertex at i

    public Vertex getVertex(String vertexName); // returns vertex with vertex's name

    public Map<String, Integer> getVertexMap(); // returns map

    public void addVertex(String v); // adds a vertex to list vertices

    public void addEdge(String source, String target);

    public String toString();

    public List<Vertex> depthFirstSearch(String name);

    public List<Vertex> breadthFirstSearch(String name);

    /*********************Graphs 4:  DFS and BFS ****************************/

    // public  List<Vertex> depthFirstRecur(String name);


    /****************
     * Graphs 5:  EdgeList with Cities
     *********/
    public void graphFromEdgeListData(String fileName) throws FileNotFoundException;

    public int edgeCount();

    public boolean isReachable(String source, String target);

    public boolean isConnected();
}

/***********************************************************/
class Vertex implements VertexInterface {
    private final String name;
    private ArrayList<Vertex> adjacencies;

    public Vertex() {
        name = "";
        adjacencies = new ArrayList<Vertex>();
    }

    public Vertex(String myName) {
        name = myName;
        adjacencies = new ArrayList<Vertex>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Vertex> getAdjacencies() {
        return adjacencies;
    }

    public void addEdge(Vertex v) {
        adjacencies.add(v);
    }

    public String toString() {
        return name;
    }
}

/*******************************************************/
public class TJGraphAdjList implements TJGraphAdjListInterface {
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(int i) {
        return vertices.get(i);
    }

    public Vertex getVertex(String vertexName) {
        for (int i = 0; i < vertices.size(); i++)
            if (vertices.get(i).getName().equals(vertexName))
                return vertices.get(i);
        return null;
    }

    public Map<String, Integer> getVertexMap() {
        return nameToIndex;
    }

    public void addVertex(String v) {
        if (getVertex(v) == null) {
            vertices.add(new Vertex(v));
            nameToIndex.put(v, vertices.indexOf(new Vertex(v)));
        }
    }

    public void addEdge(String source, String target) {
        if (getVertex(target) == null) {
            Vertex v = new Vertex();
            vertices.add(new Vertex(target));
            nameToIndex.put(target, vertices.indexOf(v));
        }
        Vertex v = getVertex(source);
        v.addEdge(getVertex(target));
    }

    public String toString() {
        String s = "";
        for (Vertex v : vertices) {
            s += v.getName() +" "+v.getAdjacencies();
            s += "\n";
        }
        return s;
    }

    public List<Vertex> depthFirstSearch(String name) {
        List<Vertex> a = new ArrayList<Vertex>();
        Vertex v = getVertex(name);
        return depthFirstSearch(v, a);
    }

    public List<Vertex> depthFirstSearch(Vertex v, List<Vertex> list) {
        Stack<Vertex> s = new Stack<Vertex>();
        s.push(v);
        while (s.size() > 0) {
            v = s.pop();
            if (!list.contains(v)) {
                list.add(v);
                for (Vertex vertex : v.getAdjacencies()) {
                    s.push(vertex);
                }
            }
        }
        return list;
    }

    public List<Vertex> breadthFirstSearch(String name) {
        List<Vertex> list = new ArrayList<Vertex>();
        Vertex v = getVertex(name);
        return breadthFirstSearch(v, list);
    }

    public void graphFromEdgeListData(String filename) throws FileNotFoundException {
        File file = new File(filename);
        try{
            Scanner infile = new Scanner(file);
            System.out.println();
            while(infile.hasNext()){
                String source = infile.next();
                Vertex v = new Vertex(source);
                addVertex(source);
                String target = infile.next();
                addEdge(source, target);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("error. pls type it again correctly");
        }
    }

    public List<Vertex> breadthFirstSearch(Vertex v, List<Vertex> list) {
        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(v);
        while (q.size() > 0) {
            v = q.remove();
            if (!list.contains(v)) {
                list.add(v);
                for (Vertex vertex : v.getAdjacencies()) {
                    q.add(vertex);
                }
            }
        }
        return list;
    }

    public int edgeCount() {
        int edgeCount = 0;
        for (int i = 0; i < vertices.size(); i++) {
            edgeCount += vertices.get(i).getAdjacencies().size();
        }
        return edgeCount;
    }


    public boolean isReachable(String source, String target) {
        List<Vertex> l = breadthFirstSearch(source);
        return l.contains(target);
    }

    public boolean isConnected() {
        for (int i = 0; i < vertices.size() ;i++) {
            if(vertices.get(i).getAdjacencies().size() < vertices.size()){
                return false;
            }
        }
        return true;
    }
}


