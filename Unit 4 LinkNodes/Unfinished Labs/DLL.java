// name:    date:
   public class DLL
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
         list.addFirst("ARtichoke");
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
      }
    	/* appends obj to end of list; increases size;
   	  @return true  */
      public boolean add(Object obj)
      {
      }
      /* inserts obj at position index.  increments size. 
   		*/
      public void add(int index, Object obj)
      {
      }
      /* return obj at position index.  
   		*/
      public Object get(int index)
      {
      }
    /* replaces obj at position index.  
   		*/
      public void set(int index, Object obj)
      {
      }
    /*  removes the node from position index.  decrements size.
   	  @return the object at position index.
   	 */
      public Object remove(int index)
      {
      }
    /* inserts obj at front of list; increases size;
   	  */
      public void addFirst(Object obj)
      {
      }
   	/* appends obj to end of list; increases size;
   	    */
      public void addLast(Object obj)
      {
      }
      public Object getFirst()
      {
      }
      public Object getLast()
      {
      }
      public Object removeFirst()
      {
      }
      public Object removeLast()
      {
      }
      public String toString()
      {
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
