package Questions.UrlShortnerDesignLLD.exception;

/**
 * Exception thrown when a user attempts to resolve a shortened URL that has expired.
 */
public class UrlExpiredException extends RuntimeException {
    
    /**
     * Constructs a new UrlExpiredException with a detail message.
     *
     * @param shortCode The short code of the expired URL mapping.
     */
    public UrlExpiredException(String shortCode) {
        super("URL has expired: " + shortCode);
    }
}
