//name: Ankur     date:
import java.util.*;

/*******************
 Represents a binary expression tree.
 The BXT can build itself from a postorder expression.  It can
 evaluate and print itself. It also prints an inorder string and a preorder string.
 **********************/
class BXT
{
    private int count;
    private TreeNode root;
    public BXT()
    {
        count = 0;
        root = null;
    }
    public TreeNode buildTree(String s){
        String s1 = "";
        StringTokenizer stringTokenizer = new StringTokenizer(s);
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (stringTokenizer.hasMoreTokens()) {
            s1 = stringTokenizer.nextToken();
            if (isOperator(s1)){
                TreeNode right = stack.pop();
                TreeNode left =stack.pop();
                root = new TreeNode(s1, left, right);
                stack.push(root);
            }
            else{
                root = new TreeNode(s1);
                stack.push(root);
            }
        }
        root = stack.pop();
        return root;
    }
    public void display(){
        display(root, 0);
    }
    private static void display(TreeNode t, int level)
    {
        if(t == null)
            return;
        display(t.getRight(), level + 1);
        for(int k = 0; k < level; k++)
            System.out.print("\t");
        System.out.println(t.getValue());
        display(t.getLeft(), level + 1);
    }
    public void inorderTraverse(){
        inorderTraverse(root);
    }
    private static void inorderTraverse(TreeNode t)
    {
        if(t != null) {
            inorderTraverse(t.getRight());
            System.out.print(t.getValue() + " ");
            inorderTraverse(t.getLeft());
        }
    }
    public void preorderTraverse(){
        preorderTraverse(root);
    }
    private static void preorderTraverse(TreeNode t)
    {
        if(t == null)
            return;
        System.out.print(t.getValue() + " ");  //preorder visit
        preorderTraverse(t.getLeft());         //recurse left
        preorderTraverse(t.getRight());        //recurse right
    }
    public double evaluateTree(){
        return evaluateNode(root);
    }
    private double evaluateNode(TreeNode root)
    {
        if (root != null)
        {
            if (!isOperator(("" + root.getValue())))
                return Integer.parseInt("" + root.getValue());
            else
            {
                double left = evaluateNode(root.getLeft());
                double right = evaluateNode(root.getRight());
                return computeTerm("" + root.getValue(), left, right);
            }
        }
        return 0;
    }
    private double computeTerm(String s, double a, double b)
    {
        if(s.equals("*"))
            return a * b;
        if(s.equals("/"))
            return a / b;
        if(s.equals("+"))
            return a + b;
        if(s.equals("-"))
            return a - b;
        return -1.0;
    }
    public boolean isOperator(String s)
    {
        String operations = "*/+-";
        for (int j = 0; j < operations.length(); j++) {
            if(s.equals("" + operations.charAt(j))){
                return true;
            }
        }
        return false;
    }
}
/*******************
 Driver for a binary expression tree class.
 Input: a postfix string, each token separated by a space.
 **********************/
public class BXT_Driver
{
    public static void main(String[] args)
    {
        ArrayList<String> postExp = new ArrayList<String>();
        postExp.add("14 -5 / ");
        postExp.add("20 3 -4 + * ");
        postExp.add("2 3 + 5 / 4 5 - *");

        for( String postfix : postExp )
        {
            System.out.println("Postfix Exp: "  + postfix);
            BXT tree = new BXT();
            tree.buildTree( postfix ).getValue();
            System.out.println("BXT: ");
            tree.display();
            System.out.print("Infix order:  ");
            tree.inorderTraverse();
            System.out.print("\nPrefix order:  ");
            tree.preorderTraverse();
            System.out.print("\nEvaluates to " + tree.evaluateTree());
            System.out.println( "\n------------------------");
        }
    }
}

/***************************************
 Postfix Exp: 14 -5 /
 Infix order:  14 / -5 
 Prefix order:  / 14 -5
 Prefix order:  / -5 14
 Evaluates to -2.8

 ------------------------
 Postfix Exp: 20 3 -4 + *
 Infix order:  20 * 3 + -4 
 Prefix order:  * 20 + 3 -4
 Prefix order:  * + -4 3 20
 Evaluates to -20.0

 ------------------------
 Postfix Exp: 2 3 + 5 / 4 5 - *
 Infix order:  2 + 3 / 5 * 4 - 5 
 Prefix order:  * / + 2 3 5 - 4 5
 Prefix order:  * - 5 4 / 5 + 3 2
 Evaluates to -1.0

 ------------------------
 */