package KI304.Brazhnyk.Lab6;

/**
 * Class that implements a doubly linked list with generic support.
 * 
 * @param <T> the type of elements held in this list
 */
public class DoublyLinkedList<T extends Comparable<T>> {

    // Inner class for nodes
    private class Node {
        T data;
        Node prev;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds an element at the end of the list.
     * @param data the data to be added
     */
    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Removes an element from the list.
     * @param data the data to be removed
     */
    public void remove(T data) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                if (current == head) {
                    head = current.next;
                } else {
                    current.prev.next = current.next;
                }
                if (current == tail) {
                    tail = current.prev;
                } else {
                    current.next.prev = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        }
    }

    /**
     * Finds and returns the maximum element in the list.
     * @return the maximum element
     */
    public T findMaximum() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        T max = head.data;
        Node current = head.next;
        while (current != null) {
            if (current.data.compareTo(max) > 0) {
                max = current.data;
            }
            current = current.next;
        }
        return max;
    }

    /**
     * Prints all elements in the list.
     */
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Getter for size
    public int getSize() {
        return size;
    }
}