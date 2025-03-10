import java.util.EmptyStackException;

public class LinkedQueue {
    private class Node {
        private LinkedList data;
        private Node next;
        public Node(LinkedList elem) {
            this.data = elem;
            this.next = null;
        }
    }
    protected Node head;
    protected Node tail;
    protected int size;


    public LinkedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    public boolean isEmpty() {
        return this.size == 0;
    }

    public void enqueue(LinkedList elem) {
        Node node = new Node(elem);
        if (this.head == null) {
            this.head = node;
        } else {
            this.tail.next = node;
        }
        this.tail = node;
        this.size++;
    }

    public LinkedList front() {
        if(this.size == 0) throw new EmptyStackException();
        return this.head.data;
    }
    public LinkedList dequeue() {
        if(this.size == 0) throw new EmptyStackException();
        LinkedList temp = this.head.data;
        this.head = this.head.next;
        this.size--;
        if(this.size == 0) this.tail = null;
        return temp;
    }
}
