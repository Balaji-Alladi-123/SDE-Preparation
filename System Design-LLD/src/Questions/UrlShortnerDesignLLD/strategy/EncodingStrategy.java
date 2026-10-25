package Questions.UrlShortnerDesignLLD.strategy;

/**
 * Strategy interface defining the mechanism to encode/shorten unique IDs into short codes.
 */
public interface EncodingStrategy {
    
    /**
     * Encodes a given long identifier into a short code string.
     *
     * @param id The unique incremental numeric identifier.
     * @return The shortened unique alphanumeric code.
     */
    String encode(long id);
}
