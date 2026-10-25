package Questions.UrlShortnerDesignLLD.repository;

import Questions.UrlShortnerDesignLLD.model.UrlMapping;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-safe, in-memory implementation of the UrlRepository using a ConcurrentHashMap.
 */
public class InMemoryUrlRepository implements UrlRepository {
    private final ConcurrentHashMap<String, UrlMapping> store = new ConcurrentHashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(UrlMapping mapping) {
        if (mapping != null && mapping.getShortCode() != null) {
            store.put(mapping.getShortCode(), mapping);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UrlMapping findByShortCode(String shortCode) {
        if (shortCode == null) {
            return null;
        }
        return store.get(shortCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsByShortCode(String shortCode) {
        if (shortCode == null) {
            return false;
        }
        return store.containsKey(shortCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String shortCode) {
        if (shortCode != null) {
            store.remove(shortCode);
        }
    }
}
