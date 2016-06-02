//name:          date:

/*****************************************
 * Demonstrates many ways to reverse a list made of ListNodes.
 ******************************************/
public class ListLabReverse {
    public static void main(String[] args) {
        ListNode head = new ListNode("hello", null);
        head = new ListNode("foo", head);
        head = new ListNode("boo", head);
        head = new ListNode("nonsense", head);
        head = new ListNode("computer",
                new ListNode("science",
                        new ListNode("java",
                                new ListNode("coffee", head))));

        System.out.print("original: \t\t\t");
        print(head);

        System.out.print("recur and print: \t\t");
        recurAndPrint(head);

        System.out.println();
        System.out.print("original: \t\t\t");
        print(head);

        System.out.print("reverse by building a new list: ");
        head = reverseBuildNew(head);
        print(head);

        System.out.print("iterate with 3 pointers:\t");
        head = iterateThreePointers(head);
        print(head);

        System.out.print("recur with 2 pointers: \t\t");
        head = recurTwoPointers(null, head);
        print(head);

        System.out.print("recur with pointers and append: ");
        head = recurPointersAppend(head);
        print(head);
        System.out.print("Mind Bender reverse:\t\t");
         /* head = mindBender(head);
         print(head); */
    }
    public static void print(ListNode head) {
        System.out.print("[");
        while (head != null) {
            System.out.print(head.getValue());
            head = head.getNext();
            if (head != null)
                System.out.print(", ");
        }
        System.out.println("]");
    }
    /*********************************************
     * 1. This approach doesn't actually reverse the list.  It only prints
     * the list in reverse order.  recurAndPrint() prints the square
     * brackets and calls helper().  helper() is recursive.
     ********************************************************/
    public static void recurAndPrint(ListNode h) {
        System.out.print("[");
        if (h.getNext() != null) {
            helper(h.getNext());
        }
        System.out.print(h.getValue());
        System.out.println("]");
    }
    private static void helper(ListNode p) {
        if (p.getNext() != null) {
            helper(p.getNext());
        }
        if (p.getNext() == null) {
            System.out.print(p.getValue() + ", ");
        } else {
            System.out.print(p.getValue() + ", ");
        }

    }
    /*********************************************
     * 2. This iterative method (for or while) produces a copy of the
     * reversed list.	 For each node going forward, make a new node and
     * link it to the list.	The list will naturally be in reverse.
     ***********************************************************/
    public static ListNode reverseBuildNew(ListNode head) {
        ListNode node = new ListNode(head.getValue(), null);
        ListNode temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
            node = new ListNode(temp.getValue(), node);

        }
        return node;
    }
    /*******************************************
     * 3a. This iterative method (while) uses 3 pointers to reverse
     * the list in place.   The two local pointers are called
     * prev and next.
     ********************************************************/
    public static ListNode iterateThreePointers(ListNode head) {
        if (head == null)
            return null;

        ListNode prev = null, next = head.getNext();
        while (next.getNext() != null) {
            prev = head;
            next = next.getNext();
            head = new ListNode(prev.getValue(), head.getNext());
        }
        return head;
    }
    /**************************************************
     * 3b. This method uses two pointers as arguments to reverse
     * the list in place. It is recursive.
     *********************************************************/
    public static ListNode recurTwoPointers(ListNode prev, ListNode head) {
        if (head == null)
            return prev;
        return recurTwoPointers(new ListNode(head.getValue(), prev), head.getNext());
    }
    /**********************************************
     * 3c. On each recursive level, find pointerToLast() and
     * nextToLast(). Make a new last. On way back, append()
     * one level to the other.
     * Believe the picture and see the truth. Two plus four equals six.
     *******************************/
    public static ListNode recurPointersAppend(ListNode head) {
        if (head.getNext().getNext() == null) {
            return new ListNode(head.getNext().getValue(), new ListNode(head.getValue(), null));
        }
        ListNode node = new ListNode(pointerToLast(head).getValue(), null);
        nextToLast(head).setNext(null);
        return append(node, recurPointersAppend(head));
    }
    private static ListNode pointerToLast(ListNode arg) {
        while (arg.getNext() != null) {
            arg = arg.getNext();
        }
        return arg;
    }
    private static ListNode nextToLast(ListNode head) {
        while (head.getNext().getNext() != null) {
            head = head.getNext();
        }
        return head;
    }
    private static ListNode append(ListNode h1, ListNode h2) {
        h1.setNext(h2);
        return h1;
    }
    /**********************************************
     3d. This difficult method reverses the list in place, using one
     local pointer. Start with pointerToLast(). The helper method
     is recursive.
     *******************************************************
     public static ListNode mindBender(ListNode head)
     {
     ListNode temp = pointerToLast(head);
     mindBenderHelper(head);
     head.setNext(null);
     return temp;
     }
     public static void mindBenderHelper(ListNode head)
     {

     }
     */

}