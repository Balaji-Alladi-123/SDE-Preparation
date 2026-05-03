package Questions.LRUCacheDesign;

public class LRUCacheDemo {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1,"A");
        lruCache.put(2,"B");
        lruCache.put(4,"X");
        lruCache.get(2);
        lruCache.put(3,"C");

        lruCache.get(2);
        lruCache.put(3,"P");
    }


}
