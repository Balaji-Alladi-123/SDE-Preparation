package Questions.UrlShortnerDesignLLD.strategy;

import java.security.SecureRandom;

/**
 * Strategy implementation that generates a random alphanumeric short code of a fixed length.
 * Note: The numeric ID passed to encode is ignored as the code is generated randomly.
 */
public class RandomStrategy implements EncodingStrategy {
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final int length;
    private final SecureRandom random = new SecureRandom();

    /**
     * Constructs a RandomStrategy with the specified length for the generated code.
     *
     * @param length The length of the generated short code.
     */
    public RandomStrategy(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        this.length = length;
    }

    /**
     * Generates a random alphanumeric short code of the predefined length.
     *
     * @param id The numeric identifier (ignored).
     * @return A random short code string.
     */
    @Override
    public String encode(long id) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
}
