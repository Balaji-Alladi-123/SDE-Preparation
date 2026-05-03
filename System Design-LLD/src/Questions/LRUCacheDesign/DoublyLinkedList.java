package Questions.LRUCacheDesign;

public class DoublyLinkedList {

    private final Node head;
    private final Node tail;

    DoublyLinkedList() {
        this.head = new Node(0, "");
        this.tail = new Node(0, "");
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Move node to head (mark as most recently used)
    public void moveToHead(Node node) {
        removeNode(node);
        addFirst(node);
    }

    // Pop the least recently used node (before tail)
    public Node popTail() {
        Node res = tail.prev;
        removeNode(res);
        return res;
    }
}
