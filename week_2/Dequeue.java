public class Deque<Item> implements Iterable<Item> {
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
   		throw IllegalArgumentException();
   	}

   	Item prev_item = this.first;

   	this.first = item;
   	this.first.next = prev_item;

   	this.length += 1;

   }        


   public void addLast(Item item)  {

   	if(item == null){
   		throw IllegalArgumentException();
   	}

   	Item prev_last_item = this.last;
   	this.last = item;
   	this.last.prev = prev_last_item;

   	this.length += 1;
   }         // add the item to the end
  
   public Item removeFirst() {
    Item first = this.first;
    this.first = this.first.next;

    this.length -= 1;

    return first;
   }               // remove and return the item from the front
  
   public Item removeLast() {

   	Item last = this.last;
   	this.last = this.last.prev;

    this.length -= 1;

   	return last;

   }
                   // remove and return the item from the end
   public Iterator<Item> iterator() {



   }        // return an iterator over items in order from front to end
  
   public static void main(String[] args) {}  // unit testing (optional)

   private class Node() {

   		public Node(String contents) {
   			
   			Item item;

   			Node next = null;
   			Node prev = null;
   			

   		}
   }

   private class DequeueIterator implements Iterator<Item> {
   		private Node current = first;

   		public boolean hasNext() { return current != null; }
   		public void remove() { throw NoSuchelementException(); }
   		public Item next() {
   			Item item = current.item;
   			current = current.next;
   			return item;
   		}
   }
}