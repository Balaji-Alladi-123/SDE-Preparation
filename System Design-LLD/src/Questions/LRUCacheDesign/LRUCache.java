package Questions.LRUCacheDesign;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> map;
    private final  DoublyLinkedList doublyLinkedList;

    LRUCache(int capacity){
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.doublyLinkedList = new DoublyLinkedList();
    }

    String get(int key) {
        if(!map.containsKey(key)) {
            return "-1";
        }
        Node node = map.get(key);
        doublyLinkedList.moveToHead(node);
        return node.value;
    }

    void put(int key, String value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            doublyLinkedList.moveToHead(node);
        } else {
            if(capacity == map.size()) {
              Node leastRecentlyUsed = doublyLinkedList.popTail();
              map.remove(leastRecentlyUsed.key);
            }
            Node newNode = new Node(key,value);
            map.put(key, newNode);
            doublyLinkedList.addFirst(newNode);
        }
    }
}
