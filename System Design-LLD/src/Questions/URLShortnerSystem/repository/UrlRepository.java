package Questions.URLShortnerSystem.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UrlRepository {

    private final ConcurrentHashMap<String, String> urlMap;
    private long counter;

    public UrlRepository() {
        this.urlMap = new ConcurrentHashMap<>();
        this.counter = 0;
    }

    public void save(String longUrl, String shortCode) {
        urlMap.put(shortCode, longUrl);
    }

    public String findByShortCode(String shortCode) {
        return urlMap.get(shortCode);
    }

    public long getNextCounter() {
        return counter++;
    }

    public int getUrlCount() {
        return urlMap.size();
    }

    public ConcurrentHashMap getMap() {
        return urlMap;
    }
}
