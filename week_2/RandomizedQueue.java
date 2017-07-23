import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
 
    private Item[] items;
    private int size;
    
    public RandomizedQueue()  {

        items = createNewArray(16);

    }

    private RandomizedQueue(RandomizedQueue<Item> other) {

        this.items = other.items;
        this.size = other.size;
    }

    public boolean isEmpty()  {
        return size() == 0;
    }

    public int size() {
        return size;
    }
    
    public void enqueue(Item item) {
        // Check if we need to resize
        if (items.length == size) {
            int newSize = size * 2;
            Item [] newArray = createNewArray(newSize);

            int i = 0;
            for (Item it: items) {
                newArray[i] = it;
                i++;
            }

            items = newArray;
        }
        
        items[size] = item;
        size += 1;
    }
    
    public Item dequeue() {
        
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        // Check if we need to resize
        if (items.length == size*4) {
        
            int newSize = items.length/2;
            Item[] newArray = createNewArray(newSize);
            int currIdx = 0;
            for (int i = 0; i < newSize*2; i++) {
                if (items[i] != null) {
                    newArray[currIdx] = items[i];
                    currIdx++;
                }
            }

            items = newArray;

        }

        int idx = pickRandom();
        Item randomItem = items[idx];

        items[idx] = null;

        size -= 1;

        return randomItem;
    }

    public Item sample()   {
        
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
         
        int idx = pickRandom();
        Item randomItem = items[idx];
        
        return randomItem;
        
    }
    
    private int pickRandom() {
        int idx = StdRandom.uniform(items.length);
        Item randomItem = items[idx];
        
        while (randomItem == null) {
            idx = StdRandom.uniform(items.length);
            randomItem = items[idx];
        }
         
        return idx;
    }
    
    public Iterator<Item> iterator() { 
        return new QueueIterator<Item>(this);
    }
    
    private Item[] createNewArray(int newSize) {
    
        return (Item[]) new Object[newSize];
    }
    
    public static void main(String[] args) {
    
    
        RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
    
        System.out.println(r.size());
        r.enqueue(1);
        r.enqueue(2);
        r.enqueue(3);
        r.enqueue(4);
        r.enqueue(5);
        r.enqueue(6);
        r.enqueue(7);
        // for (int i=8; i < 100; i++){ r.enqueue(i); }
        
        System.out.println(r.size());
        System.out.println(r.dequeue());
        System.out.println(r.size());
        
        System.out.println("========");
        
        for (int i : r) {
            System.out.println(i);
        }
    
    }

    
    private class QueueIterator<Item> implements Iterator<Item> {

        private final RandomizedQueue<Item> newQueue;

        public QueueIterator(final RandomizedQueue<Item> queue) {

            newQueue = new RandomizedQueue<Item>(queue);
            // System.arraycopy(queue.items, 0, newQueue.items, 0, queue.items.length);
            StdRandom.shuffle(newQueue.items);
        }
 
        public boolean hasNext() {
            return !newQueue.isEmpty();
        }

        public Item next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return newQueue.dequeue();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}