package Questions.UrlShortnerDesignLLD.strategy;

/**
 * Strategy implementation that converts a sequential ID into a Base62 representation.
 * Base62 utilizes characters [0-9][A-Z][a-z] to represent numbers.
 */
public class Base62Strategy implements EncodingStrategy {
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * Encodes a sequential numeric ID into a Base62 string.
     *
     * @param id The unique numeric ID.
     * @return The Base62 encoded string.
     */
    @Override
    public String encode(long id) {
        if (id == 0) {
            return String.valueOf(ALPHABET.charAt(0));
        }
        
        StringBuilder sb = new StringBuilder();
        long tempId = id;
        while (tempId > 0) {
            sb.append(ALPHABET.charAt((int) (tempId % 62)));
            tempId /= 62;
        }
        return sb.reverse().toString();
    }
}
