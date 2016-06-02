// name:    date:
public class DLL_Driver
{
    public static void main(String args[])
    {
        DLL list = new DLL();

        list.addLast("Apple");
        list.addLast("Banana");
        list.addLast("Cucumber");
        list.add("Durian");
        list.add("Eggplant");
        System.out.println(list);
        System.out.println("Size: " + list.size());
        Object obj = list.remove(3);
        System.out.println(list);
        System.out.println("Size: " +list.size());
        System.out.println("Removed "+ obj);
        System.out.print("Add at 3:   ");
        list.add(3,"Carrot");
        System.out.println(list);
        System.out.println("Get values at 1 and first: " + list.get(1)+" and " + list.getFirst());
        System.out.println("No change: " +list);
        System.out.print( list.removeFirst() + " is now removed!  ");
        System.out.println(list);
        System.out.print("Add first:  ");
        list.addFirst("Artichoke");
        System.out.println(list);
        System.out.println("Size: " + list.size());
        System.out.print("Set the second:  ");
        list.set(2, "Broccoli");
        System.out.println(list);

    }
}

//////////////////////////////////////////////////////////

class DLL        //DoubleLinkedList
{
    private int size;
    private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code

    public int size()
    {
        return size;
    }
    /* appends obj to end of list; increases size;
     @return true  */
    public boolean add(Object obj)
    {
        add(size + 1, obj);
        return true;
    }
    /* inserts obj at position index.  increments size.
         */
    public void add(int index, Object obj)
    {
        DLNode temp = head;
        for(int i = 0; i <index-1; i++) {
            temp=temp.getNext();
        }
        DLNode s = new DLNode(obj,temp, temp.getNext());
        temp.getNext().setPrev(s);
        temp.setNext(s);
        size++;
    }
    /* return obj at position index.
         */
    public Object get(int index)
    {
        DLNode temp = head;
        for(int i =0; i <index; i++)
        {
            temp=temp.getNext();
        }
        return temp.getValue();
    }
    /* replaces obj at position index.  
   		*/
    public void set(int index, Object obj)
    {
        DLNode temp = head;
        for(int i =0; i <index; i++) {
            temp=temp.getNext();
        }
        temp.setValue(obj);
    }
    /*  removes the node from position index.  decrements size.
   	  @return the object at position index.
   	 */
    public Object remove(int index)
    {
        DLNode temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        DLNode value = temp;
        temp.getPrev().setNext(value.getNext());
        temp.getNext().setPrev(value.getPrev());
        value.setNext(null);
        value.setPrev(null);
        size--;
        return temp.getValue();
    }
    /* inserts obj at front of list; increases size;
   	  */
    public void addFirst(Object obj)
    {
        DLNode first = new DLNode(obj, head, head.getNext());
        head.getNext().setPrev(first);
        head.setNext(first);
        size++;
    }
    /* appends obj to end of list; increases size;
        */
    public void addLast(Object obj)
    {
        DLNode last = new DLNode(obj, head.getPrev(), head);
        head.getPrev().setNext(last);
        head.setPrev(last);
        size = size + 1;
        //System.out.println(size);
    }
    public Object getFirst()
    {
        return get(1);
    }
    public Object getLast()
    {
        return head.getPrev().getValue();
    }
    public Object removeFirst()
    {
        Object obj =head.getNext().getValue();
        remove(1);
        return obj;
    }
    public Object removeLast()
    {
        Object obj =head.getPrev().getValue();
        remove(size);
        return obj;

    }
    public String toString()
    {
        DLNode value = head;
        String s = " ";
        for(int i =0; i<size; i++){
            value=value.getNext();
            s=s+(" "+value.getValue());
        }
        return s;
    }
}

///////////////////////////////////////


class DLNode
{
    private Object value;
    private DLNode prev;
    private DLNode next;
    public DLNode(Object arg, DLNode p, DLNode n)
    {
        value=arg;
        prev=p;
        next=n;
    }
    public DLNode()
    {
        value=null;
        next=this;
        prev=this;
    }
    public void setValue(Object arg)
    {
        value=arg;
    }
    public void setNext(DLNode arg)
    {
        next=arg;
    }
    public void setPrev(DLNode arg)
    {
        prev=arg;
    }
    public DLNode getNext()
    {
        return next;
    }
    public DLNode getPrev()
    {
        return prev;
    }
    public Object getValue()
    {
        return value;
    }
}
