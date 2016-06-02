// name:        date: 

import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
public class Huffman {
    private static TreeMap<String,String> treeMap = new TreeMap<String,String>();
    private static TreeMap<String,String> treeMap1 = new TreeMap<String,String>();
    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a message: \n");
        String string = keyboard.nextLine();

        //System.out.print(string + "\n");
        TreeMap<String, Integer> tree = frequencyPerLetter(string);
        Set<String> set = tree.keySet();
        PriorityQueue<HuffmanTreeNode> pq = new PriorityQueue<HuffmanTreeNode>();
        for (Object o : set) {
            pq.add(new HuffmanTreeNode(o, tree.get(o)));
        }
        while (pq.size() != 1) {
            HuffmanTreeNode node1 = pq.remove();
            HuffmanTreeNode node2 = pq.remove();
            HuffmanTreeNode papa = new HuffmanTreeNode("*", node1.getFrequency() + node2.getFrequency(), node1, node2);
            pq.add(papa);
        }
        HuffmanTreeNode node = pq.remove();
        HuffmanTreeNode temp = node;
        pathBuilder(temp, "");
        PrintStream fileout1 = new PrintStream(new FileOutputStream("scheme.xxx.txt"));
        for (String s : treeMap.keySet()){
            fileout1.println(treeMap.get(s) + s);
        }
        PrintStream fileout2 = new PrintStream(new FileOutputStream("message.xxx.txt"));
        for(int i = 0; i <string.length(); i++){
            fileout2.print(treeMap1.get("" + string.charAt(i)));
        }
        //Process the string letter-by-letter and search the tree for the
        //       letter.  As you go, build the binary path, where going
        //       left is 0 and going right is 1.
        //Write the binary path to the hard drive as message.xxx.txt
        //Write the scheme to the hard drive as scheme.xxx.txt
    }

    public static TreeMap frequencyPerLetter(String s) {
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
        for (int i = 0; i < s.length(); i++) {
            String str = "" + s.charAt(i);
            Integer integer = 0;
            if (treeMap.containsKey(str)) {
                integer = treeMap.get(str);
            }
            integer = integer + 1;
            treeMap.put(str, integer);

        }
        return treeMap;
    }
    public static void pathBuilder(HuffmanTreeNode root, String s) {
        if (root == null)
            return;
        if(root.getValue().equals("*")) {
            pathBuilder(root.getLeft() ,  s + "0");
            pathBuilder(root.getRight(),  s + "1");
        }
        else{
            treeMap.put(s,"" + root.getValue());
            treeMap1.put("" + root.getValue(), s);
        }
    }
}
/*
  * This node stores two values.
  * The compareTo method must ensure that the lowest frequency has the highest priority.
  */
/*
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
    private Object value;
    private Integer frequency;
    private HuffmanTreeNode left, right;

    public HuffmanTreeNode(Object initValue, Integer freq)
    {
        value = initValue;
        frequency = freq;
        left = null;
        right = null;
    }

    public HuffmanTreeNode( Object initValue, Integer freq, HuffmanTreeNode initLeft, HuffmanTreeNode initRight)
    {
        value = initValue;
        frequency = freq;
        left = initLeft;
        right = initRight;
    }

    public Integer getFrequency()
    {
        return frequency;
    }

    public Object getValue() { return value; }

    public HuffmanTreeNode getLeft()
    {
        return left;
    }

    public HuffmanTreeNode getRight()
    {
        return right;
    }

    public void setFrequency(Integer theNewFrequency)
    {
        frequency = theNewFrequency;
    }

    public void setValue(Object theNewValue)
    {
        value = theNewValue;
    }

    public void setLeft(HuffmanTreeNode theNewLeft)
    {
        left = theNewLeft;
    }

    public void setRight(HuffmanTreeNode theNewRight)
    {
        right = theNewRight;
    }

    public int compareTo(HuffmanTreeNode o) {
        if(o.frequency < frequency)
            return 1;
        else if (o.frequency > frequency)
            return -1;
        else
            return 0;
    }

    @Override
    public String toString() {
        String s =  value + " : " +frequency;
        if(right !=  null)
            s += " Right Child: " + right;
        if(left != null)
            s +=" Left Child: " + left;
        return s;
    }
}
*/