import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] que;
    private int size;
    
    public RandomizedQueue()  {               // construct an empty randomized queue    
        // we create an array of objects and then we cast it down to an array of items
        que = (Item[]) new Object[1];
        size = 0;
    }
    public boolean isEmpty(){                 // is the queue empty?
        return size == 0 ; 
    }
    public int size(){                 // return the number of items on the queue
        return size;
    }
    
    private void resize(int newlength){
        //increase the length of array to 2 times when it reaches the end 
        if (newlength > size){
            Item[] temp = (Item[]) new Object[newlength];
            //copy contents:
            for (int i=0; i < size; i++) 
                temp[i] = que[i];
            que = temp;
        }
    }
    
    public void enqueue(Item item) {          // add the item
        if (item == null){
            throw new NullPointerException();
        }
        else{
            if(size == que.length) 
                resize(que.length * 2);
            que[size++] = item;
        }
    }
    
    public Item dequeue() {                   // remove and return a random item
        if (isEmpty()){   
            throw new NoSuchElementException();
        }
        //pick a random value:
        int index = StdRandom.uniform(0,size);
        Item temp = que[index];
        //put the last element in the removed elements place
        que[index] = que[size-1];
        que[size-1] = null;
        size--;
        //need to shrink the array        
        //The efficient solution is to wait until the array gets one quarter full before you have it
        if (size > 0 && size == que.length/4) 
            resize(que.length/2);
        return temp;
    }
    public Item sample()    {                 // return (but do not remove) a random item
        if (isEmpty()){   
            throw new NoSuchElementException();
        }
        //pick a random value:
        int index = StdRandom.uniform(0,size);
        return que[index];
    }
    public Iterator<Item> iterator()   {      // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item>{
        private Item[] i;
        private int n;
        
        public RandomizedQueueIterator(){
            i = (Item[]) new Object[size];
            //n = size;
            for(int k=0; k<size; k++)
                i[k] = que[k];
        }
        
        public boolean hasNext(){
            return n < size;  
        }
        
        public void remove(){
            throw new UnsupportedOperationException();
        }
        
        public Item next(){
            if(!hasNext())
                throw new NoSuchElementException();
            else
                return i[n++];
        }
        
    }
    public static void main(String[] args) {  // unit testing (optional)
        
    }
}