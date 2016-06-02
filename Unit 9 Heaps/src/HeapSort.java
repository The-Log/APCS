//Name:     Date:

public class HeapSort
{
   public static final int SIZE = 100;  //9 or 100

   public static void main(String[] args)
   {
   /*Part 1: Given a heap, sort it. Do this part first.
      double heap[] = {-1,99,80,85,17,30,84,2,16,1};
      display(heap);
      sort(heap);
      display(heap);
    */
   //Part 2:  Generate 100 random numbers, make a heap, sort it.
      // SIZE = 100;
       double[] heap = new double[SIZE + 1];
       heap = createRandom(heap);
       display(heap);
       makeHeap(heap, SIZE);
       display(heap);
       sort(heap);
       display(heap);
   }

	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");
   }
   public static void sort(double[] array)
   {
       for(int i = array.length - 1; i > 1; i--)
       {
           swap(array, i, 1);
           heapDown(array, 1, i - 1);
       }
   }
   public static void heapDown(double[] array, int k, int size) {
       int left = 2 * k;
       int right = 2 * k + 1;
       if(k > size || left > size)
           return;
       if(right > size)
       {
           if(array[k] < array[left])
               swap(array, k, left);
       }
       else
       {
           int maxChild = right;
           if(array[left] > array[right])
               maxChild = left;
           if(array[k] < array[maxChild])
           {
               swap(array, k, maxChild);
               heapDown(array, maxChild, size);
           }
       }
   }
   private static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }

   // ****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places)
   public static double[] createRandom(double[] array)
   {
      array[0] = -1;   //because it will become a heap
      for (int i = 1; i < array.length; i++) {
         double rand = (Math.random() * 100) + 1;
         array[i] = rand;
      }
      return array;

   }
   //turn the random array into a heapHeapSort.java:68
   private static void makeHeap(double[] array, int size)
   {
       for (int i = size/ 2; i >= 1; i--) {
            heapDown(array, i, size);
       }
   }
}

