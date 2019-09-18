/* *****************************************************************************
 *  Name:    Just Me
 *  NetID:   voodooism
 *  Precept: P00
 *
 *  Description:  A double-ended queue or deque is a generalization of a stack
 *  and a queue that supports adding and removing items from either the front
 *  or the back of the data structure.
 *
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    // Head and tail of the double-linked list
    private Node head, tail;

    // Current number of elements in the linked list
    private int size = 0;

    private class Node {
        private Node prev, next;
        private final Item item;

        public Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        assertArgumentIsNotNull(item);
        Node newNode = new Node(item);
        newNode.next = head;
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        size++;
        if (size == 1) {
            tail = head;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        assertArgumentIsNotNull(item);
        Node newNode = new Node(item);
        newNode.prev = tail;
        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
        if (size == 1) {
            head = tail;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        assertDequeIsNotEmpty();
        Node node = head;
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        size--;
        if (size == 0) {
            tail = null;
        }

        return node.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        assertDequeIsNotEmpty();
        Node node = tail;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        }
        size--;
        if (size == 0) {
            head = null;
        }
        return node.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node node = current;
            current = current.next;
            return node.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
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
