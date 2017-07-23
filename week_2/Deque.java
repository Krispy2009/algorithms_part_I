import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
   
   Node<Item> first;
   Node<Item> last;
   int length;

   public Deque() {
    first = null;
    last = null;
    length = 0;
   }

   public boolean isEmpty() {

    if(length != 0) {
     return true;
    }

    return false;

   }                
   
   public int size() {
       return this.length;
   }                      
  
   public void addFirst(Item item) {

    if(item == null){
     throw new IllegalArgumentException();
    }
    Node<Item> prev_first_item;
    
    Node<Item> node = new Node<Item>();
    node.item = item;
    
    
    prev_first_item = first;

    node.next = prev_first_item;
    node.prev = null;
    
    if(prev_first_item != null) {
        prev_first_item.prev = node;
    }
    else {
                System.out.println("setting last to : " + item);
        last = node;
    }
    first = node;
    
    length += 1;

   }        


   public void addLast(Item item)  {

    if(item == null){
     throw new IllegalArgumentException();
    }
    System.out.println("in here");
    Node<Item> node = new Node<Item>();
    node.item = item;
    
    Node<Item> prev_last_item = last;

    node.prev = prev_last_item;
    
    if(prev_last_item != null) {
        prev_last_item.next = node;
    }
    else {
        System.out.println("setting first to : " + item);
        first = node;
    }
    last = node;


    length += 1;
   }         // add the item to the end
  
   public Item removeFirst() {

    if(length == 0) {
      throw new NoSuchElementException();
    }

    Node<Item> node = first;
    System.out.println("==========");
    System.out.println(first.item);
    first = first.next;
    
    if (first == null) {
        last = first;
    }

    length -= 1;

    return node.item;
   }               // remove and return the item from the front
  
   public Item removeLast() {

    if(this.length == 0) {
      throw new NoSuchElementException();
    }

    Node<Item> node = last;
    last = last.prev;

    length -= 1;
    
    if (last == null) {
        first = last;
    }

    return node.item;

   }
                   // remove and return the item from the end
   public Iterator<Item> iterator() {

       DequeIterator<Item> iter = new DequeIterator<Item>(first);
       
       return iter;

   }        // return an iterator over items in order from front to end
  
   public static void main(String[] args) {

    Deque<String> dq = new Deque<String>();
    System.out.println(dq.length);
        
        
    dq.addFirst("1");
    dq.addFirst("2");    
    dq.addFirst("3");    
    dq.addFirst("4");    
    dq.addFirst("5");   
    
    int sz = dq.length;
    for (int i=0; i < sz; i++){
        
        System.out.println(Integer.toString(i) +": " + dq.removeFirst());
        
    }
    
    System.out.println(dq.length);
    System.out.println(dq.first);
    System.out.println(dq.last);
    
    
    dq.addLast("a");
    dq.addLast("b");    
    dq.addLast("c");    
    dq.addLast("d");    
    dq.addLast("e"); 
    
        sz = dq.length;
    for (int i=0; i < sz; i++){
        
        System.out.println(Integer.toString(i) +": " + dq.removeLast());
        
    }
    
    
            
    dq.addFirst("1");
    dq.addFirst("2");    
    dq.addFirst("3n");    
    dq.addFirst("4");    
    dq.addFirst("5a");  
    dq.addLast("a");
    dq.addLast("b");    
    dq.addLast("c");    
    dq.addLast("d");    
    dq.addLast("e1");    
    
       for (String s : dq) {
           System.out.println(s);
       }

   }  // unit testing (optional)

}


   class Node<Item> {

     Item item;
     Node<Item> next;
     Node<Item> prev;
     
     public Node() {
      item = null;
      next = null;
      prev = null;
      

     }
   }

  class DequeIterator<Item> implements Iterator<Item> {
     
     private Node<Item> current;
     
     public DequeIterator(Node<Item> first) {
         current = first;
     }
     
     public boolean hasNext() { return current != null; }
     public void remove() { throw new UnsupportedOperationException(); }
     
     @Override
     public Item next() {
         
         if(!hasNext()) {
             throw new NoSuchElementException();
         }   
         
      Item node = current.item;   
      current = current.next;
      return node;
     }
   }
