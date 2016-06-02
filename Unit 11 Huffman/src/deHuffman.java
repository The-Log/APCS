// Name:              Date:
import java.util.Scanner;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines){
      TreeNode head = new TreeNode(null);
      while(huffLines.hasNext()){
         TreeNode node = head;
         String str = huffLines.nextLine();
         String character = "" + str.charAt(0);
         int i = 1;
         while(i < str.length()){
            TreeNode kai = new TreeNode(null);
            String s = "" + str.charAt(i);
            if(str.charAt(i) == '0'){
               if(node.getLeft() == null)
                  node.setLeft(kai);
               node = node.getLeft();
            }
            else if(str.charAt(i) == '1'){
               if(node.getRight() == null)
                  node.setRight(kai);
               node = node.getRight();
            }
            i++;
         }
         //TreeNode k = new TreeNode();   
         node.setValue(character);
         node = head;
      }
      return head;
   }
   public static String dehuff(String text, TreeNode root){
      String s = ""; TreeNode node = root;
      for(int i = 0; i < text.length(); ){
         String str = "" + text.charAt(i);
         if(text.charAt(i) == '0' && node.getLeft() != null){
            node = node.getLeft();
            i++;
         }
         else if(text.charAt(i) == '1' && node.getRight() != null){
            node = node.getRight();
            i++;
         }
         else{
            s = s + node.getValue();
            node = root;
         }
      }
      s = s + node.getValue();
      node = root;
      return s; 
   }
}

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
