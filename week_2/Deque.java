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

        if (length == 0) {
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
        else {
            first.prev = null;
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
        else {
            last.next = null;
        }

        return node.item;

    }

    public Iterator<Item> iterator() {

        return new DequeIterator<Item>(first);
    }

    public static void main(String[] args) {

       // Deque<Integer> d = new Deque<Integer>();

       // d.addFirst(1);
       // d.addFirst(4);
       // System.out.println(d.removeLast());
       // d.addFirst(2);
       // System.out.println(d.removeLast());
       // System.out.println(d.removeFirst());
       // System.out.println(d.isEmpty());
       // d.addLast(3);
       // d.addFirst(5);

       // System.out.println(d.removeFirst());



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

        public DequeIterator(Node<Item> ft) {
            current = ft;
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

