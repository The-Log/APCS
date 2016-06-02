import java.util.Scanner;

/**
 * Created by amishra on 11/25/15.
 */
public class Test {
    public static void main(String[] args) {
        String[] array = {"Tom", "Jerry", "Dog", "Cat", "Elephant"};
        ListNode head = new ListNode(array[0], null);
        head = copyArray(array, head);
        System.out.print("The ListNode created is : ");
        print(head);
        System.out.println("Give me the the term you wish to remove: ");
        Scanner input = new Scanner(System.in);
        int i = Integer.parseInt(input.next());
        removeNode(head, i - 2);
        System.out.print("Your new list: ");
        print(head);
    }

    public static ListNode copyArray(String[] array, ListNode l){
        return copyArray(array, l, 0);
    }
    public static ListNode copyArray(String[] array, ListNode l, int i){
        ListNode node =  new ListNode(array[i], null);
        if (i < array.length - 1){
            ListNode next =  new ListNode(array[i + 1], null);
            l = new ListNode(array[i], copyArray(array, next, i + 1));
            return l;
        }
        else{
            return node;
        }
    }
    public static ListNode itCopyArray(String[] array, ListNode l, int i){
        while(i >= 0){
            l = new ListNode(array[i], l);
            i--;
        }
        return l;
    }
    private static void removeNode(ListNode l, int i){
        removeNodeHelper(l, i, 0);
    }
    private static void removeNodeHelper(ListNode l, int i, int j){
        if(j == i){
            l.setNext(l.getNext().getNext());
        }
        else{
            removeNodeHelper(l.getNext(), i, j + 1);
        }
    }
    private static void print(ListNode head)
    {
        System.out.print("[");
        while(head != null)
        {
            System.out.print(head.getValue());
            head = head.getNext();
            if(head != null)
                System.out.print(", ");
        }
        System.out.println("]");
    }
}
