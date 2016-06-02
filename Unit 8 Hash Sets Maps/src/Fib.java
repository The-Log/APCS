//Name:       Date:
import java.util.*;

public class Fib
{
    public static final int DEFAULT = 40;

    public static void main(String[] args)
    {
        int n = DEFAULT;
        System.out.println("Recursive");
        calculate(new Fib1(), n);
        System.out.println("Iterative, stored in an array");
        calculate(new Fib2(), n);
        System.out.println("Recursive, stored in an array list");
        calculate(new Fib3(), n);
        System.out.println("Recursive, stored in a hash map");
        calculate(new Fib4(), n);
    }

    public static void calculate(Fibber fibber, int n)
    {
        long start = System.nanoTime();
        int f = fibber.fib(n);
        long finish = System.nanoTime();
        long time = finish - start;

        System.out.print("fib(" + n + ") = " + f);
        System.out.println(" (" + time + " nanoseconds)");
        System.out.println();
    }

    private static class Fib1 implements Fibber
    {
        public int fib(int n)
        {
            if(n == 1 || n == 2)
                return 1;
            else
                return fib(n - 1) + fib(n - 2);
        }
    }
    private static class Fib2 implements Fibber
    {

        public int fib(int n)
        {
            int[] feb = new int[n + 1];
            feb[0] = 0;
            feb[1] = 1;
            for(int i=2; i < n + 1; i++){
                feb[i] = feb[i-1] + feb[i-2];
            }
            return feb[n];
        }
    }
    private static class Fib3 implements Fibber {
        private static ArrayList<Integer> a = new ArrayList<Integer>();
        public int fib(int n) {
            int num = 0;
            if(n <= 2){
                num = 1;
                if(a.size() <= 1)
                    a.add(num);
                return num;
            }
            num = (n < a.size()) ? a.get(n - 2) + a.get(n - 1) : fib(n - 2) + fib(n - 1);
            /*if(num > a.size()){
                num = a.get(n - 2) + a.get(n - 1);
            }
            else{
                num = fib(n - 2) + fib(n - 1);
            }*/
            if(n >= a.size())
                a.add(num);
            return num;
        }
    }
    private static class Fib4 implements Fibber
    {
        private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        public int fib(int n) {
            if (n == 0) {
                return 0;
            }
            else if (n <= 2) { // if
                return 1;
            }
            if (map.get(n) != null) {
                return map.get(n);
            } else {
                int v = fib(n - 1) + fib(n - 2);
                map.put(n, v);
                return v;
            }
        }
    }

    private interface Fibber
    {
        public abstract int fib(int n);
    }
}
   	/*
    Recursive
    fib(42) = 267914296 (3276558048 nanoseconds)
    
    Iterative, stored in an array
    fib(42) = 267914296 (4988 nanoseconds)
    
    Recursive, stored in an arrayList
    fib(42) = 267914296 (64025 nanoseconds)
    
    Recursive, stored in a hashMap
    fib(42) = 267914296 (177793 nanoseconds)
    
   	*/
