package Questions.UrlShortnerDesignLLD.exception;

/**
 * Exception thrown when a requested custom alias for a shortened URL is already in use.
 */
public class AliasAlreadyExistsException extends RuntimeException {
    
    /**
     * Constructs a new AliasAlreadyExistsException with a detail message.
     *
     * @param alias The custom alias that is already taken.
     */
    public AliasAlreadyExistsException(String alias) {
        super("Alias already taken: " + alias);
    }
}
