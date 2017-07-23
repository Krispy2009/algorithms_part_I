import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
   
    private Node<Item> first;
    private Node<Item> last;
    private int length;

    public Deque() {
        first = null;
        last = null;
        length = 0;
    }

    public boolean isEmpty() {

        if (length != 0) {
            return true;
        }

        return false;
    }

    public int size() {
        return this.length;
    }

    public void addFirst(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> prevFirstItem;

        Node<Item> node = new Node<Item>();
        node.item = item;

        prevFirstItem = first;

        node.next = prevFirstItem;
        node.prev = null;

        if (prevFirstItem != null) {
            prevFirstItem.prev = node;
        }
        else {
            last = node;
        }
        first = node;

        length += 1;

    }


    public void addLast(Item item)  {

        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> node = new Node<Item>();
        node.item = item;

        Node<Item> prevLastItem = last;

        node.prev = prevLastItem;

        if (prevLastItem != null) {
            prevLastItem.next = node;
        }
        else {
            first = node;
        }

        last = node;
        length += 1;
    }

    public Item removeFirst() {

        if (length == 0) {
            throw new NoSuchElementException();
        }

        Node<Item> node = first;
        first = first.next;

        if (first == null) {
            last = first;
        }

        length -= 1;

        return node.item;
    }

    public Item removeLast() {

        if (this.length == 0) {
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

    public Iterator<Item> iterator() {

        DequeIterator<Item> iter = new DequeIterator<Item>(first);

        return iter;

    }

    public static void main(String[] args) {

   /* Deque<String> dq = new Deque<String>();
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
*/
    }

    private class Node<Item> {

        private Item item;
        private Node<Item> next;
        private Node<Item> prev;

        public Node() {
            item = null;
            next = null;
            prev = null;
        }
    }

    private class DequeIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        public DequeIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); }

        @Override
        public Item next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item node = current.item;
            current = current.next;
            return node;
        }
    }
}

