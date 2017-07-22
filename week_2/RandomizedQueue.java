import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    public RandomizedQueue()  {
    
        
        
    }               // construct an empty randomized queue
    public boolean isEmpty()  {return true;}               // is the queue empty?
    public int size() { return 0; }                       // return the number of items on the queue
    public void enqueue(Item item) {}          // add the item
    public Item dequeue() {
        Item item = (Item) new Object();
        return item;
    }                    // remove and return a random item
    public Item sample()   {
        Item item = (Item) new Object();
        return item;
    }                  // return (but do not remove) a random item
    public Iterator<Item> iterator() {
    
        return new QueueIterator<Item>();
    }// return an independent iterator over items in random order
    public static void main(String[] args) {}   // unit testing (optional)

   private class QueueIterator<Item> implements Iterator<Item>{
   
       private Item current;
       
       public boolean hasNext() {
       
           return false;
       }
       
       public Item next() {
        Item item = (Item) new Object();
        return item;
       }
       
       public void remove() {}
   
   }

}

