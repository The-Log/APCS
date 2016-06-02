//name: Ankur Mishra    date:

import java.util.*;
public class Postfix
{
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postExp = new ArrayList<String>();
      /*  enter your code here  */
       postExp.add("345*+");
       postExp.add("34*5+");             
       postExp.add("45+53*-");          
       postExp.add("34+56+*");
       postExp.add("345+*2-5/");              
       postExp.add("812*+93/-"); 
      
      for( String pf : postExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   public static int eval(String postfix)
   {
      Stack<Integer> stack = new Stack<Integer>();
      int total = 0; int a = 0, b = 0;
      for (int j = 0; j < postfix.length(); j++) {
         if(isOperator(postfix.charAt(j))){
            a = stack.pop().intValue();
            b = stack.pop().intValue();
            total = eval(a, b, postfix.charAt(j));
            stack.push(total);
         }
         else{
            int temp = Integer.parseInt("" + postfix.charAt(j));
            stack.push(temp);
         }            
      }
      return total;
   }
   public static int eval(int a, int b, char ch)
   {
      String operations = "*/+-";
      if(ch == operations.charAt(0))
         return a * b;
      else if(ch == operations.charAt(1))
         return a / b;
      else if(ch == operations.charAt(2))
         return a + b;
      else if(ch == operations.charAt(3))
         return a - b;
      else 
         return -1;

   }
   public static boolean isOperator(char ch)
   {
      String operations = "*/+-";
      for (int j = 0; j < operations.length(); j++) {
         if(ch == operations.charAt(j)){
            return true;
         }
      }
      return false;
   }
}