//name:    date:  
import java.util.*;         //for the queue interface
public class TreeLab
{
    public static TreeNode root = null;
    public static String s =   "XCOMPUTERSCIENCE";
    //public static String s = "XThomasJeffersonHighSchool";
    //public static String s = "XAComputerScienceTreeHasItsRootAtTheTop";
    public static void main(String[] args)
    {
        root = buildTree(root,s);
        display(root, 0);

        System.out.print("\nPreorder: ");
        preorderTraverse(root);
        System.out.print("\nInorder: ");
        inorderTraverse(root);
        System.out.print("\nPostorder: ");
        postorderTraverse(root);

        System.out.println("\n\nNodes = " + countNodes(root));
        System.out.println("Leaves = " + countLeaves(root));
        System.out.println("Grandparents = " + countGrands(root));
        System.out.println("Only childs = " + countOnlys(root));

        System.out.println("\nHeight of tree = " + height(root));
        System.out.println("Width = " + width(root));
        System.out.println("Min = " + min(root));
        System.out.println("Max = " + max(root));

        System.out.println("\nBy Level: ");
        displayLevelOrder(root);
    }
    public static TreeNode buildTree(TreeNode root, String s)
    {
        root = new TreeNode("" + s.charAt(1), null, null);
        for(int pos = 2; pos < s.length(); pos++)
            insert(root, "" + s.charAt(pos), pos,
                    (int)(1 + Math.log(pos) / Math.log(2)));

        insert(root, "A", 17, 5);
        insert(root, "B", 18, 5);
        insert(root, "C", 37, 6); //B's right child
        return root;
    }

    public static void insert(TreeNode t, String s, int pos, int level)
    {
        TreeNode p = t;
        for(int k = level - 2; k > 0; k--)
            if((pos & (1 << k)) == 0)
                p = p.getLeft();
            else
                p = p.getRight();
        if((pos & 1) == 0)
            p.setLeft(new TreeNode(s, null, null));
        else
            p.setRight(new TreeNode(s, null, null));
    }
    public static void display(TreeNode t, int level)
    {
        if(t == null)
            return;
        display(t.getRight(), level + 1);
        for(int k = 0; k < level; k++)
            System.out.print("\t");
        System.out.println(t.getValue());
        display(t.getLeft(), level + 1);
    }
    public static void preorderTraverse(TreeNode t)
    {
        if(t == null)
            return;
        System.out.print(t.getValue() + " ");  //preorder visit
        preorderTraverse(t.getLeft());         //recurse left
        preorderTraverse(t.getRight());        //recurse right
    }
    public static void inorderTraverse(TreeNode t)
    {
        if(t == null)
            return;
        inorderTraverse(t.getLeft());
        System.out.print(t.getValue() + " ");
        inorderTraverse(t.getRight());


    }
    public static void postorderTraverse(TreeNode t)
    {
        if(t == null)
            return;
        postorderTraverse(t.getLeft());
        postorderTraverse(t.getRight());
        System.out.print(t.getValue() + " ");


    }
    public static int countNodes(TreeNode t)
    {
        if(t == null)
            return 0;
        return countNodes(t.getLeft()) + countNodes(t.getRight()) + 1;
    }
    public static int countLeaves(TreeNode t)
    {
        if(t == null)
            return 0;
        if(t.getLeft() == null && t.getRight() == null)
            return 1;
        return countLeaves(t.getLeft()) + countLeaves(t.getRight());
    }
    public static int countGrands(TreeNode t)
    {
        if(t == null)
            return 0;
        if(height(t) >= 2){
            return 1 + countGrands(t.getRight()) + countGrands(t.getLeft());
        }
        return 0;
    }
    public static int countOnlys(TreeNode t)
    {
        if(t == null)
            return 0;
        if(t.getLeft() != null && t.getRight() == null)
            return 1 + countOnlys(t.getLeft());
        if(t.getLeft() == null && t.getRight() != null)
            return 1 + countOnlys(t.getRight());
        return countOnlys(t.getRight()) + countOnlys(t.getLeft());
    }
    public static int height(TreeNode t)
    {
        if(t == null){
            return -1;
        }
        else
            return 1 + Math.max(height(t.getLeft()) , height(t.getRight()));
    }
    /* "width" is the longest path from leaf to leaf */
    public static int width(TreeNode t)
    {
        int maxWidth =  0;
        for (int i = 0; i < height(t); i++) {
            if(maxWidth(t,i) > maxWidth)
                maxWidth = maxWidth(t,i);
        }
        return maxWidth;
    }
    public static int maxWidth(TreeNode t, int l)
    {
        if(t == null)
            return 0;
        if(l == 1)
            return 1;
        if(l > 1)
            return maxWidth(t.getLeft(), l - 1) + maxWidth(t.getRight(), l - 1);
        return 0;
    }

    public static Object min(TreeNode t) throws NullPointerException {
        Object r = t.getValue();
        Object l = t.getValue();
        Object min = t.getValue();
        if (t == null)
            return null;
        if(t.getLeft() != null)
             l = min(t.getLeft());
        if(t.getRight() != null)
            r = min(t.getRight());
        if((("" + l).compareTo("" + r)) < 0)
            min = l;
        else
            min = r;
        if((("" + t.getValue()).compareTo("" + min)) < 0)
            min = t.getValue();
        return min;
    }
    public static Object max(TreeNode t) {
        Object r = t.getValue();
        Object l = t.getValue();
        Object max = t.getValue();
        if (t == null)
            return null;
        if(t.getLeft() != null)
             l = max(t.getLeft());
        if(t.getRight() != null)
            r = max(t.getRight());
        if((("" + l).compareTo("" + r)) > 0)
            max = l;
        else
            max = r;
        if((("" + t.getValue()).compareTo("" + max)) > 0)
            max = t.getValue();
        return max;
    
    }
    /* this method is not recursive.  Use a local queue
     to store the children of the current node.*/
    public static void displayLevelOrder(TreeNode t)
    {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        queue.add(t);
        while(!queue.isEmpty())
        {
            TreeNode tempNode= queue.poll();
            System.out.print(tempNode.getValue());
            if(tempNode.getLeft() != null)
                queue.add(tempNode.getLeft());
            if(tempNode.getRight() != null)
                queue.add(tempNode.getRight());
        }

    }
}
/***************************************************

 ----jGRASP exec: java Lab01


 Preorder: C O P R A S B C U C I M T E N E C E 
 Inorder: R A P B C S O C U I C E T N M C E E 
 Postorder: A R C B S P C I U O E N T C E E M C 

 Nodes = 18
 Leaves = 8
 Grandparents = 5
 Only childs = 3

 Height of tree = 5
 Width = 8
 Min = A
 Max = U

 By Level: 
 COMPUTERSCIENCEABC   
 *******************************************************/
	  /* TreeNode class for the AP Exams */

class TreeNode
{
    private Object value;
    private TreeNode left, right;

    public TreeNode(Object initValue)
    {
        value = initValue;
        left = null;
        right = null;
    }

    public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
    {
        value = initValue;
        left = initLeft;
        right = initRight;
    }

    public Object getValue()
    {
        return value;
    }

    public TreeNode getLeft()
    {
        return left;
    }

    public TreeNode getRight()
    {
        return right;
    }

    public void setValue(Object theNewValue)
    {
        value = theNewValue;
    }

    public void setLeft(TreeNode theNewLeft)
    {
        left = theNewLeft;
    }

    public void setRight(TreeNode theNewRight)
    {
        right = theNewRight;
    }
}
