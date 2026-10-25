package Questions.UrlShortnerDesignLLD.exception;

/**
 * Exception thrown when a requested short URL code cannot be found in the repository.
 */
public class UrlNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new UrlNotFoundException with a detail message.
     *
     * @param shortCode The short code that was not found.
     */
    public UrlNotFoundException(String shortCode) {
        super("URL not found for code: " + shortCode);
    }
}
