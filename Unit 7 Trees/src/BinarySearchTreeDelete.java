//name:    date:

import java.util.Scanner;

/****************************************************************
 * Practice with a Binary Search Tree. Uses TreeNode.
 * Prompt the user for an input string.  Build a BST from the letters.
 * Display it as a sideways tree.  Prompt the user for a target and delete
 * that node, if it exists. Show the updated tree.
 *****************************************************************/
public class BinarySearchTreeDelete {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input string: ");
        String s = sc.nextLine();
        //Case 1:  		ECSBPWANR
        //Case 2a:  	SNTPOR
        //Case 2b:	   HBRNVJSZIK
        //Case 3.a.i:  DBNACFSEJHM
        //Case 3.a.ii: NFSAKPXGQ
        //Case 3b:     RNVGPCAE
        //Case root:   N
        //on the handout: HDJAGKFEOLTMNSU

        TreeNode root = null;
        root = buildTree(root, s);
        display(root, 0);
        System.out.print("Delete? ");
        String target = sc.next();
        if (contains(root, target)) {
            root = delete(root, target);
            System.out.println("\n" + target + " deleted.");
        } else
            System.out.println("\n" + target + " not found");
        display(root, 0);
    }

    public static TreeNode buildTree(TreeNode t, String s) {
        for (int k = 0; k < s.length(); k++)
            t = insert(t, "" + s.charAt(k));
        return t;
    }

    /**************************
     * Recursive algorithm to build a BST:  if the node is null, insert the
     * new node.  Else, if the item is less, set the left node and recur to
     * the left.  Else, if the item is greater, set the right node and recur
     * to the right.
     *****************************/
    public static TreeNode insert(TreeNode t, String s) {
        if (t == null)
            return new TreeNode(s);
        if (s.compareTo(t.getValue() + "") < 0)
            t.setLeft(insert(t.getLeft(), s));
        else
            t.setRight(insert(t.getRight(), s));
        return t;
    }

    public static void display(TreeNode t, int level) {
        if (t == null)
            return;
        display(t.getRight(), level + 1); //recurse right
        for (int k = 0; k < level; k++)
            System.out.print("\t");
        System.out.println(t.getValue());
        display(t.getLeft(), level + 1); //recurse left
    }

    public static boolean contains(TreeNode current, String target) {
        while (current != null) {
            int compare = target.compareTo((String) current.getValue());
            if (compare == 0)
                return true;
            else if (compare < 0)
                current = current.getLeft();
            else if (compare > 0)
                current = current.getRight();
        }
        return false;
    }
    public static TreeNode minimum(TreeNode root)
    {
        String minv = "" + root.getValue();
        while (root.getLeft() != null)
        {
            minv = "" + root.getLeft().getValue();
            root = root.getLeft();
        }
        return root;
    }
    public static TreeNode delete(TreeNode current, String target) {
        TreeNode root = current;
        TreeNode parent = null;
        if (root == null)
            return root;
        if (target.compareTo("" + root.getValue()) < 0)
            root.setLeft(delete(root.getLeft(), target));
        if (target.compareTo("" + root.getValue()) > 0)
            root.setRight(delete(root.getRight(), target));
        else{
            if(root.getLeft() != null && root.getRight() != null){
                TreeNode right = minimum(root.getRight());
                root.setValue(right.getValue());
                delete(right, "" + right.getValue());
            }
            else if(root.getLeft() != null && root.getRight() == null) {
                root = root.getLeft();
            }
            else if(root.getLeft() == null && root.getRight() != null) {
                root = root.getRight();
            }
            else{
                root = null;
            }
        }
        return root;
    }


}
