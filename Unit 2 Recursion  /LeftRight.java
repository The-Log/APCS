//Akur Mishra, 10/02/2015

import java.util.*;

public class LeftRight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nHow many digits? ");
        String s = sc.next();
        int n = Integer.parseInt(s);
        recursive("", n);
        System.out.println();
        System.out.println();
        oddDigits("", n);
        System.out.println();
        System.out.println();
        superprime(n);
    }

    public static void recursive(String s, int n) {
        if (s.length() == n)
            System.out.println(s);
        else {
            recursive(s + "L", n);
            recursive(s + "R", n);
        }
    }

    public static void oddDigits(String s, int n) {
        if (s.length() == n)
            System.out.println(s);
        else {
            oddDigits(s + "1", n);
            oddDigits(s + "3", n);
            oddDigits(s + "5", n);
            oddDigits(s + "7", n);
            oddDigits(s + "9", n);

        }
    }

    //the SuperPrime methods
    public static void superprime(int n) {
        recur(2, n); //try leading 2, etc.
        recur(3, n); //all the primes < 10
        recur(5, n);
        recur(7, n);
    }

    private static void recur(int k, int n) {
        int[] array = {1, 2, 3, 5, 7, 9};
        String s = "" + k;
        if (s.length() == n)
            System.out.println(s);
        else {
            for (int i = 0; i < array.length; i++) {
                String temp = s + array[i];
                int temp1 = Integer.parseInt(temp);
                if (isPrime(temp1)) {
                    recur(temp1, n);
                }
            }
        }
    }

    private static boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                return false;
        return true;
    }
}
