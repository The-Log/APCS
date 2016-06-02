//Name:   Date:
//modeling a polynomial using a treeMap

import java.util.Iterator;
import java.util.Objects;
import java.util.TreeMap;

public class Polynomial
{
   public static void main(String[] args)
   {
      Polynomial_Class poly = new Polynomial_Class();
      poly.makeTerm(1, -4);
      poly.makeTerm(3, 2);
      poly.makeTerm(0, 2);
      System.out.println(poly.toString());
      double evaluateAt = 2;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));

      Polynomial_Class poly2 = new Polynomial_Class();
      poly2.makeTerm(1, -5);
      poly2.makeTerm(4, 2);
      poly2.makeTerm(0, -3);
      poly2.makeTerm(2, 1);
      System.out.println(poly2.toString());

      System.out.println(poly.add(poly2));
      System.out.println(poly.multiply(poly2));
   }
}
interface PolynomialInterface
{
   public void makeTerm(Integer exp, Integer coef);
   public double evaluateAt(double x);
   public Polynomial_Class add(Polynomial_Class other);
   public Polynomial_Class multiply(Polynomial_Class other);
   public String toString();
}

class Polynomial_Class implements PolynomialInterface
{
   private TreeMap map = new TreeMap<Integer,Integer>();
   public void makeTerm(Integer exp, Integer coef){
      if(map.containsKey(exp))
         coef = coef + Integer.parseInt(""+ map.get(exp));
      map.put(exp, coef);
   }
   public double evaluateAt(double x) {
      Iterator i = map.keySet().iterator();
      double sol = 0.0;
      while (i.hasNext()) {
         Object o = i.next(); //exp
         Object o1 = map.get(o); //coef
         String s = o + "";
         String s1 = o1 + "";
         double exp = Double.parseDouble(s);
         double coef = Double.parseDouble(s1);
         double simplified_expression = Math.pow(x,exp) * coef;
         sol += simplified_expression;
      }
      return sol;
   }
   public Polynomial_Class add(Polynomial_Class other){
      Iterator i  = other.map.keySet().iterator();
      Polynomial_Class polynomial = new Polynomial_Class();
      while (i.hasNext()) {
         Object o = i.next();
         if(map.containsKey(o)) {
            String s = "" + other.map.get(o);
            String s1 = "" + map.get(o);
            Integer j = Integer.parseInt(s);
            Integer k = Integer.parseInt(s1);
            Integer coef = j + k;
            polynomial.map.put(o, coef);
         }
         else{
            polynomial.map.put(o, other.map.get(o));
         }
      }
      i = map.keySet().iterator();
      while (i.hasNext()) {
         Object o = i.next();
         if(!polynomial.map.containsKey(o)) {
            polynomial.map.put(o, map.get(o));
         }
      }
      return polynomial;
   }
   public Polynomial_Class multiply(Polynomial_Class other){
      Iterator i  = other.map.keySet().iterator();
      Polynomial_Class polynomial = new Polynomial_Class();
      while (i.hasNext()) {
         Object o = i.next();
         Iterator i1  = map.keySet().iterator();
         while (i1.hasNext()) {
            Integer exp ,coef, j, k ;
            Object o1 = i1.next();
            String s, s1;
            s = "" + other.map.get(o);
            s1 = "" + map.get(o1);
            j = Integer.parseInt(s);
            k = Integer.parseInt(s1);
               coef = j * k;
            s = "" + o;
            s1 = "" + o1;
            j = Integer.parseInt(s);
            k = Integer.parseInt(s1);
               exp = j + k;
            if(polynomial.map.containsKey(exp)){
               coef = coef + Integer.parseInt("" + polynomial.map.get(exp));
            }
            polynomial.map.put(exp, coef);
         }
      }
      return polynomial;
   }
   public String toString(){
      String s = "";
      Iterator i  = map.keySet().iterator();
      while(i.hasNext()){
         Object o = i.next();
         if(("" + map.get(o)).equals("1") && ("" + o).equals("1"))
            s = "x" + s;
         else if(("" + map.get(o)).equals("0"))
              s = "" + s;
         else if(("" + map.get(o)).equals("-1") && ("" + o).equals("1"))
            s = "-x" + s;
         else if(("" + map.get(o)).equals("1"))
            s = "x^" + o + s;
         else if(("" + map.get(o)).equals("-1"))
             s = "-x^" + o + s;
         else if(("" + o).equals("0"))
            s = "" + map.get(o) + s;
         else if(("" + o).equals("1"))
            s = "" + map.get(o) + "x" + s;
         else
            s = map.get(o) +"x^" + o + s;
         if(i.hasNext())
            s = " + " + s;
      }
      return s;
   }
}
/*  
expected output
   2x^3 + -4x + 2
   Evaluated at 2.0: 10.0
   2x^4 + x^2 + -5x + -3
   2x^4 + 2x^3 + x^2 + -9x + -1
   4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 */