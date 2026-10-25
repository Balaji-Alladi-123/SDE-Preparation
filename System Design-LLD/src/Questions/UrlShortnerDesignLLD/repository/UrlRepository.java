package Questions.UrlShortnerDesignLLD.repository;

import Questions.UrlShortnerDesignLLD.model.UrlMapping;

/**
 * Interface defining the storage and retrieval contract for URL mapping entities.
 */
public interface UrlRepository {

    /**
     * Saves a new or updated URL mapping.
     *
     * @param mapping The UrlMapping object to store.
     */
    void save(UrlMapping mapping);

    /**
     * Finds a URL mapping by its unique short code.
     *
     * @param shortCode The short code to search for.
     * @return The matching UrlMapping, or null if not found.
     */
    UrlMapping findByShortCode(String shortCode);

    /**
     * Checks if a URL mapping exists for a given short code.
     *
     * @param shortCode The short code to check.
     * @return true if it exists, false otherwise.
     */
    boolean existsByShortCode(String shortCode);

    /**
     * Deletes a URL mapping from storage by its short code.
     *
     * @param shortCode The short code to delete.
     */
    void delete(String shortCode);
}
