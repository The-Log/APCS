 //name:    date:
import java.util.*;
import java.io.*;
//////////////////////////////////
interface BSTinterface<E extends Comparable<E>>
{
    public E add( E element );     //returns the object
    public boolean contains( E element );
    public boolean isEmpty();
    public E delete( E element );  //returns the object, not a Node<E>
    public int size();
    public String toString();
}
//////////////////////////////////
public class BSTobject_Driver
{
    public static BSTobject<String> tree = null;
    public static BSTobject<Widget> treeOfWidgets = null;
    public static int numberWidgets = 10;
    public static void main( String[] args )
    {
        //day one

        tree = new BSTobject<String>();
        treeOfWidgets = new BSTobject<Widget>();

        /*
        build(tree, "T");
        System.out.print(tree);
        System.out.println("Size: " + tree.size());
        System.out.println("Is empty: "+ tree.isEmpty());
        */

        //day two
        //	Your assignment the second day is to finish the BSTobject class.
        //	Specifically, prompt the user for a string, put the characters into a BST,
        //	call toString on this tree, and print the size of the tree.
        /*
        Scanner sc = new Scanner(System.in);
        System.out.print("Input string: ");
        String s = sc.nextLine();
        for (int i = 0; i< s.length(); i++){
            build(tree, "" + s.charAt(i));
        }
        System.out.println(tree);
        System.out.println("Size: " + tree.size());
        System.out.println("Is empty: "+ tree.isEmpty());
        */
        //day two, Widgets
        //	Next, fill your BST with 57 Widget objects from widgets.txt.  Display the tree.
        //	Then prompt the user to enter pounds and ounces.  If the tree contains that
        //	Widget, delete it, of course restoring the BST order. Display the new tree
        //  and its size. If the tree does not contain that Widget, print "Not found".
        File f = new File("widget.txt");
        build(treeOfWidgets, f);
        System.out.println(treeOfWidgets);
        Scanner sc = new Scanner(System.in);
        System.out.println("Remove a widget.");
        System.out.print("Input pounds: ");
        String p = sc.nextLine();
        System.out.print("Input ounces: ");
        String o = sc.nextLine();
        Widget w = new Widget(Integer.parseInt(p), Integer.parseInt(o));
        if(treeOfWidgets.contains(w)){
            treeOfWidgets.delete(w);
            System.out.println(treeOfWidgets);
        }
        else{
            System.out.println("Not found.");
        }
        System.out.println("Size: " + treeOfWidgets.size());
        System.out.println("Is empty: "+ treeOfWidgets.isEmpty());
        //day three -- AVL tree  -----------------------

    }
    public static void build(BSTobject<String> tree, String str)
    {
        tree.add(str);
    }
    public static void build(BSTobject<Widget> tree, File file)
    {
        Scanner infile = null;
        try{
            infile = new Scanner( file  );
        }
        catch (Exception e)
        {
            System.exit(0);
        }
        for(int i = 0; i < 10; i++)
        {
            String p = infile.nextLine();
            String o = infile.nextLine();
            Widget w = new Widget(Integer.parseInt(p), Integer.parseInt(o));
            treeOfWidgets.add(w);
        }
        System.out.println();
    }
}
////////////////////////////////
class BSTobject <E extends Comparable<E>> implements BSTinterface<E>
{
    // 2 fields
    private Node root;
    private int size;
    // 1 default constructor
    public BSTobject (){
        root = null;
        size = 0;
    }
    public BSTobject(Node n){
        root = n;
        size = 0;
    }
    //instance methods
    public E add( E obj )
    {
        root = add(root, obj );
        size++;
        return obj;
    }
    //recursive helper method
    private Node<E> add(Node<E> t, E s)
    {
        if(t == null) {
            t = new Node(s);
            return t;
        }
        if ((s).compareTo((E)t.getValue()) < 0) {
            t.setLeft(add(t.getLeft(), s));
        }
        if (((s).compareTo((E)t.getValue()) >= 0)) {
            t.setRight(add(t.getRight(), s));
        }
        return t;
    }
    public Node<E> minimum(Node root)
    {
        while (root.getLeft() != null)
        {
            root = root.getLeft();
        }
        return root;
    }
    public boolean contains( E element ){
        return contains(root, element);
    }
    private boolean contains(Node current, E target) {
        while (current != null) {
            int compare = (target).compareTo((E) current.getValue());
            if (compare == 0)
                return true;
            else if (compare < 0)
                current = current.getLeft();
            else if (compare > 0)
                current = current.getRight();
        }
        return false;
    }
    public boolean isEmpty(){
        if(size == 0)
            return true;
        else
            return false;
    }
    public E delete( E element ) {
        root = delete(root, element);
        size--;
        return element;
    }
    private Node<E> delete(Node current, E target) {
        Node root = current;
        Node parent = null;
        if (root == null)
            return root;
        if ((target).compareTo((E) root.getValue()) < 0) {
            root.setLeft(delete(root.getLeft(), target));
            return root;
        }
        if ((target).compareTo((E) root.getValue()) > 0) {
            root.setRight(delete(root.getRight(), target));
            return root;
        }
        else{
            if(root.getLeft() != null && root.getRight() != null){
                E right = minimum(root.getRight()).getValue();
                root.setValue(right);
                root.setRight(delete(root.getRight(), right));
                return root;
            }
            else if(root.getLeft() != null && root.getRight() == null) {
                return root.getLeft();
            }
            else if(root.getLeft() == null && root.getRight() != null) {
                return root.getRight();
            }
            else{
                return null;
            }
        }
    }

    public int size(){
        return size;
    }
    public String toString(){
        return toString(root, 0);
    }
    private String toString(Node node, int level){
        String s = "";
        if (node == null)
            return s;
        s+= toString(node.getRight(), level + 1); //recurse right
        for (int k = 0; k < level; k++) {
            s += ("\t");
        }
        s += (node.getValue());
        s += "\n";
        s += toString(node.getLeft(), level + 1); //recurse left

        return s;
    }
      
      /* implement the interface here.  Use TreeNode as an example,
       but root is a field. You need add, contains, isEmpty, 
       delete, size, and toString.  */

    /***************************private inner class **************/
    private class Node<E>
    {
        private Node left, right;
        private E value;
        public Node(E initValue)
        {
            value = initValue;
            left = null;
            right = null;
        }
        public Node(E initValue, Node initLeft, Node initRight)
        {
            value = initValue;
            left = initLeft;
            right = initRight;
        }

        public E getValue()
        {
            return value;
        }

        public Node getLeft()
        {
            return left;
        }

        public Node getRight()
        {
            return right;
        }

        public void setValue(E theNewValue)
        {
            value = theNewValue;
        }

        public void setLeft(Node theNewLeft)
        {
            left = theNewLeft;
        }

        public void setRight(Node theNewRight)
        {
            right = theNewRight;
        }

    }
}
class Widget implements Comparable<Widget> {
    private int myPounds, myOunces;
    public Widget(int x, int y)
    {
        myPounds = x;
        myOunces = y;
    }
    public int getPounds()
    {
        return myPounds;
    }
    public int getOunces()
    {
        return myOunces;
    }
    public void setPounds(int x)
    {
        myPounds = x;
    }
    public void setOunces(int x)
    {
        myOunces = x;
    }
    public int compareTo(Widget w)
    {
        if(w.getOunces() + w.getPounds() > myOunces + myPounds )
            return -1;
        else if(w.getOunces() + w.getPounds() < myOunces + myPounds )
            return 1;
        else
            return 0;
    }
    public String toString()
    {
        return myPounds + " lbs. " +
                myOunces + " oz.";
    }
}