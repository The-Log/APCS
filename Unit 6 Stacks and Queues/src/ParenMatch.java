// name: Ankur Mishra    date:

import java.util.*;
public class ParenMatch
{
    public static final String left  = "([{<";
    public static final String right = ")]}>";
    public static void main(String[] args)
    {
        System.out.println("Parentheses Match");
        ArrayList<String> parenExp = new ArrayList<String>();
         /* enter tests here */
        parenExp.add("5+7");
        parenExp.add("(5+7)");
        parenExp.add(")5+7(");
        parenExp.add("((5+7)*3)");
        parenExp.add("<{5+7}*3>");
        parenExp.add("[(5+7)*]3");
        parenExp.add("(5+7)*3");
        parenExp.add(" 5+(7*3)");
         
        parenExp.add("((5+7)*3"); 
        parenExp.add("[(5+7]*3)"); 
        parenExp.add("[(5+7)*3])");
        parenExp.add("([(5+7)*3]");


        for( String s : parenExp )
        {
            boolean good = checkParen(s);
            if(good)
                System.out.println(s + "\t good!");
            else
                System.out.println(s + "\t BAD");
        }
    }
    public static boolean checkParen(String exp)
    {
        
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < exp.length(); i++) {
            String currentChar = "" + exp.charAt(i);
            for (int j = 0; j < left.length(); j++) {
                String leftParen = "" + left.charAt(j);
                String rightParen = "" + right.charAt(j);
                if (currentChar.equals(leftParen)){
                    stack.push(currentChar);
                    break;
                }
                if(currentChar.equals(rightParen)){
                    if(stack.isEmpty())
                        return false;
                    if(!stack.peek().equals(leftParen)){
                        return false;
                    }    
                    stack.pop();
                    
                }
            }
            
        }
        if(stack.isEmpty())
            return true;
        else
            return false;
    }
}

/*
 Parentheses Match
 5+7	 good!
 (5+7)	 good!
 )5+7(	 BAD
 ((5+7)*3)	 good!
 <{5+7}*3>	 good!
 [(5+7)*]3	 good!
 (5+7)*3	 good!
 5+(7*3)	 good!
 ((5+7)*3	 BAD
 [(5+7]*3)	 BAD
 [(5+7)*3])	 BAD
 ([(5+7)*3]	 BAD
 */
