import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    // private class representing a Node
    private class Node {
        
        private Item item;
        private Node next;
        private Node prev;
        
        public Node(Item i)   { this.item = i; }
    }

    private Node first, last;
    private int size;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // insert the item at the front
    public void addFirst(Item item) {

        if (item == null)
            throw new NullPointerException();

        // corner case
        if (size == 0) {
            first = new Node(item);
            first.next = null;
            first.prev = null;
            last = first;
        } else {
            Node oldFirst = first;
            first = new Node(item);
            first.next = oldFirst;
            first.prev = null;
            oldFirst.prev = first;
        }

        size++;
    }

    // insert the item at the end
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();

        // corner case
        if (size == 0) {
            last = new Node(item);
            last.next = null;
            last.prev = null;
            first = last;
        } else {
            Node oldLast = last;
            last = new Node(item);
            last.prev = oldLast;
            last.next = null;
            oldLast.next = last;
        }

        size++;
    }

    // delete and return the item at the front
    public Item removeFirst() {

        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        Item front = first.item;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        
        size--;
        return front;
    }

    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        Item back = last.item;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        
        size--;
        return back;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null)
                throw new java.util.NoSuchElementException();
            
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        
        int N = 10;
        for (int i = 1; i <= N; i++) {
            deque.addFirst(i);
        }
        

        StdOut.println("Printing in Reversed Order");
        for (int i : deque) 
            StdOut.print(i + " ");
                
                for (int i : deque) 
                    StdOut.print(i + " ");
                
        StdOut.println();
                
        for (int i = 1; i <= N; i++) {
            deque.addFirst(i);
        }
        
        StdOut.println("Printing in Order");        
        for (int i = 1; i <= N; i++)
            StdOut.print(deque.removeLast() + " ");
    }

}
