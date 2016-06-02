//name: Ankur Mishra   date: 1/17/16

import java.util.*;

public class Infix {
    public static void main(String[] args) {
        System.out.println("Infix  -->  Postfix  -->  Evaluate");
      /*enter code here  */
        ArrayList<String> infixExp = new ArrayList<String>();
        infixExp.add("3+4*5");
        infixExp.add("3*4+5");
        infixExp.add("(4+5)-5*3");
        infixExp.add("(3+4)*(5+6)");
        infixExp.add("(3*(4+5)-2)/5");
        infixExp.add("8+1*2-9/3");

        for (String s : infixExp) {
            String pf = infixToPostfix(s);
            System.out.println(s + "       " + pf + "       " + Postfix.eval(pf));
        }
    }

    public static String infixToPostfix(String infix) {
        {

            String postfix = "";
            Stack<String> stack = new Stack<String>();
            for (int i = 0; i < infix.length(); i++) {
                char ci = infix.charAt(i);
                if (ci == ('+') || ci == ('*') || ci == ('-') || ci == ('/')) {
                    while (!stack.empty() && isLower(ci, stack.peek().charAt(0)))
                        postfix += stack.pop();
                    stack.push("" + ci);
                }
                else if (ci == '(')
                    stack.push("" + ci);
                else if (ci == ')') {
                    while (!stack.peek().equals("("))
                        postfix = postfix + stack.pop();
                    stack.pop();
                }
                else
                    postfix = postfix + ci;
            }
            while (!stack.empty())
                postfix = postfix + stack.pop();
            return postfix;
        }
    }

    //returns true if c1 has strictly lower precedence than c2
    public static boolean isLower(char c1, char c2) {
        if ((c1 == '+' || c1 == '-') && (c2 == '/' || c2 == '*'))
            return true;
        return false;
    }
}

	/*
 Infix  -->  Postfix  -->  Evaluate
 3+4*5       345*+       23
 3*4+5       34*5+       17
 (4+5)-5*3       45+53*-       -6
 (3+4)*(5+6)       34+56+*       77
 (3*(4+5)-2)/5       345+*2-5/       5
 8+1*2-9/3       812*+93/-       7
	*/
