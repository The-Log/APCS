//your name and date
import java.util.Scanner;
public class ListLab1
{
    public static void main(String[] args)
    {
        ListNode head = new ListNode("hello", null);
        head = new ListNode("foo", head);
        head = new ListNode("boo", head);
        head = new ListNode("nonsense", head);
        head = new ListNode("computer",
                new ListNode("science",
                        new ListNode("java",
                                new ListNode("coffee", head)
                        )
                )
        );
        print(head);
        print(head);

        /**** uncomment the code below for ListLab1 Extension  ****/

         System.out.println("First = " + first(head));
         System.out.println("Second = " + second(head));
         ListNode p = pointerToLast(head);
         System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
         ListNode c = copyOfLast(head);
         System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);

         Scanner in = new Scanner(System.in);
         System.out.print("Insert what? ");
         String x = in.next();
         head = insertFirst(head, x);
         head = insertLast(head, x);
         print(head);
    }
    public static void print(ListNode head)
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
    public static Object getNext(ListNode arg) {
        if(arg==null)
            return null;
        return new ListNode(arg.getValue(), arg.getNext());
    }
    public static Object first(ListNode arg)
    {
        return arg.getValue();
    }
    public static Object second(ListNode arg) {

        return arg.getNext().getValue();
    }
    public static Object last(ListNode arg) {
        while(arg.getNext()!=null)
        {
            arg.getNext();
        }
        return arg.getNext().getValue();
    }
    public static ListNode pointerToLast(ListNode arg) {
        ListNode p= null;
        while(arg.getNext()!=null)
        {
            arg=arg.getNext();
            p = arg.getNext();
        }
        return new ListNode(arg.getValue(), arg.getNext());
    }
    public static ListNode copyNode(ListNode arg) {
        if(arg==null)
            return null;
        return new ListNode(arg.getValue(), arg.getNext());
    }
    public static ListNode copyList(ListNode arg) {
        if(arg==null)
            return null;
        return new ListNode(arg.getValue(), copyList(arg.getNext()));
    }
    public static ListNode copyOfLast(ListNode arg)
    {
        return copyNode(pointerToLast(arg));
    }
    public static ListNode insertFirst(ListNode head, Object arg)
    {
        return new ListNode(arg,head);
    }
    public static ListNode insertLast(ListNode head, Object arg) {
        ListNode temp = head;
        while(temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(new ListNode(arg, null));
        return head;
    }
}
