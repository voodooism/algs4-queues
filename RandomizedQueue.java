/* *****************************************************************************
 *  Name:    Just Me
 *  NetID:   voodooism
 *  Precept: P00
 *
 *  Description:  Resizable array based randomized queue.
 *  A randomized queue is similar to a stack or queue, except that the item
 *  removed is chosen uniformly at random from items in the data structure.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // Resizable array
    private Item[] deque;
    private int size = 0;

    // Minimum array capacity.
    private static final int MINCAPACITY = 2;

    // construct an empty randomized queue
    public RandomizedQueue() {
        deque = (Item[]) new Object[MINCAPACITY];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        assertArgumentIsNotNull(item);
        if (size == deque.length) {
            resize(2 * deque.length);
        }
        deque[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        assertDequeIsNotEmpty();
        if (size <= deque.length / 4) {
            resize(deque.length / 2);
        }
        int index = findIndex();
        Item item = deque[index];
        if (index == size() - 1) {
            deque[index] = null;
        }
        else {
            deque[index] = deque[size() - 1];
            deque[size() - 1] = null;
        }
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        assertDequeIsNotEmpty();
        return deque[findIndex()];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int currentSize = size;
        private Item[] iteratorItems;

        public RandomizedQueueIterator() {
            iteratorItems = (Item[]) new Object[deque.length];
            for (int i = 0; i < deque.length; i++) {
                iteratorItems[i] = deque[i];
            }
        }

        @Override
        public boolean hasNext() {
            return currentSize > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int index = StdRandom.uniform(0, currentSize);
            Item item = iteratorItems[index];
            iteratorItems[index] = index == currentSize - 1 ? null : iteratorItems[currentSize - 1];
            currentSize--;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int capacity) {
        Item[] newDeque = (Item[]) new Object[capacity];
        for (int i = 0; i < size(); i++) {
            newDeque[i] = deque[i];
        }
        deque = newDeque;
    }

    private int findIndex() {
        return StdRandom.uniform(0, size());
    }

    private void assertArgumentIsNotNull(Item argument) {
        if (argument == null) {
            throw new IllegalArgumentException();
        }
    }

    private void assertDequeIsNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // test client can be here
    }

}
