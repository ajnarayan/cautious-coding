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
        private Node prev;
        
        public Node (Item data){
            this.data = data;
            this.next = null;
            this.prev = null;
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
            rear = front;
        }
        else{  
            Node temp = new Node(data);
            temp.next = front; 
            front.prev = temp;
            front = temp;
        } 
        sizeOfQue++;
    }
    
    public void addLast(Item data)   {        // add the data to the end
        if (data == null){
            throw new NullPointerException();
        }
        if (isEmpty()){
            front = new Node(data); 
            rear = front;
        }
        else{
            Node newNode = new Node(data); 
            newNode.prev = rear; 
            rear.next = newNode;
            rear = newNode;
        }
        sizeOfQue++; 
    }
    
    public Item removeFirst()      {          // remove and return the data from the front
        Item i = front.data;
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        else{
            if(sizeOfQue > 1) {
                front = front.next;
                front.prev = null;
            }
            else{
                front = null;
                rear = null;
            }
        }
        sizeOfQue--;
        return i;
    }
    
    public Item removeLast(){            // remove and return the data from the end
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
                rear = rear.prev;
                rear.next = null;
            }
        }
        sizeOfQue--;
        return i;
    }
    
    public Iterator<Item> iterator(){         // return an iterator over datas in order from front to end
        return new DequeIteratior();
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
    
    
    
    public static void main(String[] args) {  // unit testing (optional)
        
    }
}
