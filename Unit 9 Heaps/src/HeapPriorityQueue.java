//Name: Ankur Mishra   Date:
import java.util.*;
public class HeapPriorityQueue<E extends Comparable<E>>
{
    private ArrayList<E> myHeap;
    private int mySize;
    public  HeapPriorityQueue(){
        mySize = 0;
        myHeap = new ArrayList<E>();
    }
    public HeapPriorityQueue(int size) {
        mySize = size;
        myHeap = new ArrayList<E>(size);
    }
    public void add(E e){
        if(myHeap.add(e)){
            heapUp(myHeap.indexOf(e));
            mySize ++;
        }
    }
    public void remove (E e){
        heapDown(myHeap.indexOf(e), mySize);
        myHeap.remove(e);
        mySize --;
    }
    public E peek()
    {
        return myHeap.get(1);
    }
    public E remove()
    {
        mySize --;
        return myHeap.remove(0);
    }
    public int size()
    {
        return mySize;
    }
    public void heapUp(int k) {
        int parent = (k - 1) / 2;
        E bottom = myHeap.get(k);
        while(k > 1 && myHeap.get(k).compareTo(myHeap.get(k)) < 0)
        {
            swap(k, k / 2);
            k = k / 2;
        }
        myHeap.set(k, bottom);
    }
    public void heapDown(int k, int size) {
        int left = 2 * k;
        int right = 2 * k + 1;
        if(k > size || left > size)
            return;
        if(right > size)
        {
            if(myHeap.get(k).compareTo(myHeap.get(left)) > 0)
                swap(k, left);
        }
        else
        {
            int maxChild = right;
            if(myHeap.get(left).compareTo(myHeap.get(right)) > 0)
                maxChild = left;
            if(myHeap.get(k).compareTo(myHeap.get(maxChild)) < 0)
            {
                swap(k, maxChild);
                heapDown(maxChild, size);
            }
        }
    }
    public void swap(int a, int b){
        E temp = myHeap.get(a);
        myHeap.set(a, myHeap.get(b));
        myHeap.set(b, temp);
    }
    public boolean isEmpty(){
        return mySize == 0;
    }
    public String toString() {
        return myHeap.toString();
    }
}
