import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private Node front, rear; 
    private int sizeOfQue;
    
    public Deque(){
        this.sizeOfQue = 0;   
    }
    
    private class Node{
        private Item data;
        private Node next; 
        
        Node (Item data){
            this.data = data;
            this.next = null;
        }
    }
    
    public boolean isEmpty()       {          // is the deque empty?
        return sizeOfQue == 0;
    }
    
    public int size(){                        // return the number of datas on the deque
        return sizeOfQue;
    }
    
    
    public void addFirst(Item data)  {        // add the data to the front
        if (data == null){
            throw new NullPointerException();
        }
        if (isEmpty()){
         front = new Node(data); 
         sizeOfQue++;
        }
        else{
         Node temp = front;
         front = new Node(data);
         front.next = temp;
            sizeOfQue++;
        } 
    }
    public void addLast(Item data)   {        // add the data to the end
        
        if (data == null){
            throw new NullPointerException();
        }
        if (isEmpty()){
         front = new Node(data); 
         rear = front;
         sizeOfQue++;
        }
        else{
          Node temp = new Node(data); 
          rear.next = temp;
          rear = temp; 
          sizeOfQue++;
        }
    }
    
    public Item removeFirst()      {          // remove and return the data from the front
        Item i = front.data;
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        else{
            if(sizeOfQue > 1) {
            front = front.next;
            }
            else{
             front = null;
             rear = null;
            }
        }
        sizeOfQue--;
    return i;
    }
    public Item removeLast()     {            // remove and return the data from the end
        Item i = rear.data;
        if(isEmpty()){
            throw new NoSuchElementException(); 
        }
        else{
            if (sizeOfQue < 1){
            front = null;
            rear = null;
            }
            else{
            Node temp = front;
            while(temp.next != rear){
            temp = temp.next;
            }
            rear = temp;
            rear.next = null;
            }
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
   
        public Iterator<Item> iterator(){         // return an iterator over datas in order from front to end
        return this.new DequeIteratior();
    }
        public static void main(String[] args) {  // unit testing (optional)
            
        }
}
