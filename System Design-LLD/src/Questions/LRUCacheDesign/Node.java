package Questions.LRUCacheDesign;

public class Node {
    int key;
    String value;
    Node prev;
    Node next;

    Node(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
