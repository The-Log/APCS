//name:     date:

import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class Josephus {
    private static String WINNER = "Josephus";

    public static void main(String[] args) throws FileNotFoundException {
      /* run it first with J_numbers.txt  */
        ListNode p = null;
        int size = Integer.parseInt(JOptionPane.showInputDialog("How many names (2-20)?"));
        File f = new File("J_numbers.txt");
        p = readNLinesOfFile(size, f);
        int countOff = Integer.parseInt(JOptionPane.showInputDialog("How many names to count off each time?"));
        countingOff(p, countOff, size);
      
   	/* run it next with J_names.txt  */
        System.out.println("\n ****  Now start all over.  Enter the winning position in the JOptionPane.  *** \n");
        p = readNLinesOfFile(size, new File("J_names.txt"));
        int winPos = Integer.parseInt(JOptionPane.showInputDialog("Enter Josephus's preferred position."));
        replaceAt(p, WINNER, winPos);
        countingOff(p, countOff, size);
        System.out.println("Josephus wins!");
    }

    /* reads the names, builds the linked list.
       */
    public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException {
        Scanner infile = new Scanner(f);
        int i = 0;
        ListNode node = new ListNode(infile.next(), null);
        node.setNext(node);
        while(i < n - 1){
            insert(node, infile.next());
            i++;
        }
        return node;
    }

    /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
       Ends with one remaining name, who is the winner.
        */
    public static void countingOff(ListNode p, int startSpot, int size) {
        startSpot--;
        print(p);
        for(int l=0; l<size -1 ; l++)
        {
            p = remove(p,startSpot);
            p = p.getNext();
            print(p);
        }
    }
    /* removes the node p after counting n nodes.
       */
    private static ListNode remove(ListNode p, int n) {
        ListNode pointer = p;
        for (int x = 0; x < n - 1; x++) {
            pointer = pointer.getNext();
        }
        pointer.setNext(pointer.getNext().getNext());
        return pointer.getNext();
    }

    /* prints the circular linked list.
       */
    public static void print(ListNode p) {
        if (p == null) {
            return;
        }
        ListNode l = p;
        do {
            System.out.print(l.getValue() + " ");
            l = l.getNext();
        }
        while (!l.getValue().equals(p.getValue()));
            System.out.println();
    }
    /* helper method to build the list.  Creates the node, then
       inserts it in the circular linked list.
   	 */
    private static ListNode insert(ListNode p, Object obj) {
        ListNode l = new ListNode(obj, p);
        l.setNext(p);
        ListNode node = p;
        while (node.getNext() != p)
            node = node.getNext();
        node.setNext(l);
        return l;
    }
    /* replaces the value (the string) at the winning node.
       */
    private static void replaceAt(ListNode l, Object obj, int pos) {

        for (int x = 0; x < pos-1; x++) {
            l = l.getNext();
        }
        l.setValue(obj);
    }
}
