import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a; // array of items
    private int N; // number of elements on stack

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        N = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the queue
    public int size() {
        return N;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();

        // double size of array if necessary
        if (N == a.length)
            resize(2 * a.length);
        a[N++] = item;
    }

    // delete and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Randomized Queue underflow");

        int index = StdRandom.uniform(N);
        Item item = a[index];
        a[index] = a[--N]; 
        a[N] = null; // to avoid loitering
        // shrink size of array if necessary
        if (N > 0 && N == a.length / 4)
            resize(a.length / 2);
        return item;
    }

    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Randomized Queue underflow");

        int index = StdRandom.uniform(N);
        return a[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int current = 0;
        private int[] shuffled;
        
        private RandomizedQueueIterator() {
            shuffled = new int [N];
            for (int i = 0; i < N; i++)
                shuffled[i] = i;
            
            StdRandom.shuffle(shuffled);
        }

        @Override
        public boolean hasNext() {
            return current < N;
        }

        @Override
        public Item next() {
            if (current >= N)
                throw new java.util.NoSuchElementException();
            
            int index = shuffled[current++];
            return a[index];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing
    public static void main(String[] args) {

        int N = 10;
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 1; i <= N; i++) {
            rq.enqueue(i);
        }

        StdOut.println("Iterator Test");
        for (int i : rq)
            StdOut.println(i);

        StdOut.println("Dequeue Test");
        while (!rq.isEmpty())
            StdOut.println(rq.dequeue());
    }

}
