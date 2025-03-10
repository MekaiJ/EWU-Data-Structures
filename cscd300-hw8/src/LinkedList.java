public class LinkedList {
    private class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node head;
    private int size;
    public LinkedList() {
        this.head = new Node(0, null, null);
        this.head.next = this.head;
        this.head.prev = this.head;
        this.size = 0;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(int data) {
        Node newNode = new Node(data, this.head.next, this.head);
        this.head.next.prev = newNode;
        this.head.next = newNode;
        if (this.size == 0) {
            this.head.prev = newNode;
        }
        this.size++;
    }

    public void append(int data) {
        Node newNode = new Node(data, this.head, this.head.prev);
        this.head.prev.next = newNode;
        this.head.prev = newNode;
        if (this.size == 0) {
            this.head.next = newNode;
        }
        this.size++;
    }

    public void removeFirst() {
        if (this.size == 0) return;
        this.head.next = this.head.next.next;
        this.head.next.prev = this.head;
        this.size--;
        if (this.size == 0) {
            this.head.next = this.head;
            this.head.prev = this.head;
        }
    }

    public int getFirst() {
        if (this.isEmpty()) throw new IllegalStateException("List is empty");
        return this.head.next.data;
    }

    private LinkedList merge(LinkedList a, LinkedList b) {
        LinkedList s = new LinkedList();
        while (!a.isEmpty() && !b.isEmpty()) {
            if (a.getFirst() < b.getFirst()) {
                s.append(a.getFirst());
                a.removeFirst();
            } else {
                s.append(b.getFirst());
                b.removeFirst();
            }
        }
        while (!a.isEmpty()) {
            s.append(a.getFirst());
            a.removeFirst();
        }
        while (!b.isEmpty()) {
            s.append(b.getFirst());
            b.removeFirst();
        }
        return s;
    }
    public void mergeSort() {
        if (this.size <= 1) return;
        LinkedQueue q = new LinkedQueue();
        Node cur = this.head.next;
        while (cur != this.head) {
            LinkedList newList = new LinkedList();
            newList.append(cur.data);
            q.enqueue(newList);
            cur = cur.next;
        }
        while (q.size > 1) {
            LinkedList sublist1 = q.dequeue();
            LinkedList sublist2 = q.dequeue();
            q.enqueue(merge(sublist1, sublist2));
        }

        if (!q.isEmpty()) {
            LinkedList sortedList = q.dequeue();
            this.head.next = sortedList.head.next;
            this.head.prev = sortedList.head.prev;
            this.head.next.prev = this.head;
            this.head.prev.next = this.head;
        }
    }

    public void insertionSort() {
        if (this.size <= 1) return;
        Node lastSorted, sortedWalker;
        for (lastSorted = this.head.next; lastSorted != this.head.prev; lastSorted = lastSorted.next) {
            Node firstUnsorted = lastSorted.next;
            int firstUnsortedData = firstUnsorted.data;
            sortedWalker = lastSorted;
            while (sortedWalker != this.head && sortedWalker.data > firstUnsortedData) {
                sortedWalker.next.data = sortedWalker.data;
                sortedWalker = sortedWalker.prev;
            }
            sortedWalker.next.data = firstUnsortedData;
        }
    }

    public boolean isSorted() {
        if (this.size <= 1) return true;
        Node cur = this.head.next.next;
        while (cur != this.head) {
            if (cur.prev.data > cur.data) return false;
            cur = cur.next;
        }
        return true;
    }
    public int getSize() {
        return this.size;
    }
}
