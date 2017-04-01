import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private Node front, rear; 
    private int sizeOfQue;
    
    public void Deque(){
        this.sizeOfQue = 0;   
    }
    
    private class Node{
        private Item data;
        private Node next; 
        
        Node (Item item){
            this.data = item;
            this.next = null;
        }
    }
    
    
    public Deque()    {
        // construct an empty deque  
    }
    
    public boolean isEmpty()       {          // is the deque empty?
        return sizeOfQue == 0;
    }
    
    public int size(){                        // return the number of items on the deque
        return sizeOfQue;
    }
    
    
    public void addFirst(Item item)  {        // add the item to the front
        if (item == null){
            throw new NullPointerException();
        }
        if (isEmpty()){
         front = new Node(item); 
         sizeOfQue++;
        }
        else{
         Node temp = front;
         front = new Node(item);
         front.next = temp;
            sizeOfQue++;
        } 
    }
    public void addLast(Item item)   {        // add the item to the end
        
        if (item == null){
            throw new NullPointerException();
        }
        if (isEmpty()){
         front = new Node(item); 
         rear = front;
         sizeOfQue++;
        }
        else{
          Node temp = new Node(item); 
          rear.next = temp;
          rear = temp; 
          sizeOfQue++;
        }
    }
    
    public Item removeFirst()      {          // remove and return the item from the front
        Item i = front.data;
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        else{
            front = front.next;
            sizeOfQue--;
            return i;
        }
    }
    public Item removeLast()     {            // remove and return the item from the end
        Item i = rear.data;
        if(isEmpty()){
            throw new NoSuchElementException(); 
        }
        else{
            Node temp = front;
            while(temp.next != rear){
            temp = temp.next;
            }
            rear = temp;
            rear.next = null;
            sizeOfQue--;
            return i;
        }
    }
    
    private class DequeIteratior implements Iterator<Item>{
        private Node current = front;
        
        public boolean hasNext(){
          return current!= null;  
        }
        
        public void remove(){
        throw new UnsupportedOperationException();
        }
        
        public Item next(){
            if (current == null){
            throw new NoSuchElementException();
            }
            else{
                Item temp = current.data; 
                current = current.next;
                return temp;    
            }
    }
    }
   
        public Iterator<Item> iterator(){         // return an iterator over items in order from front to end
        return this.new DequeIteratior();
    }
//   public static void main(String[] args)   // unit testing (optional)
}
