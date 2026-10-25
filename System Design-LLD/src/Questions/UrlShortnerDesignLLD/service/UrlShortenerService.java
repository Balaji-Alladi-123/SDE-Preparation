package Questions.UrlShortnerDesignLLD.service;

import Questions.UrlShortnerDesignLLD.repository.UrlRepository;
import Questions.UrlShortnerDesignLLD.strategy.EncodingStrategy;
import Questions.UrlShortnerDesignLLD.model.UrlMapping;
import Questions.UrlShortnerDesignLLD.model.UrlStats;
import Questions.UrlShortnerDesignLLD.exception.AliasAlreadyExistsException;
import Questions.UrlShortnerDesignLLD.exception.UrlExpiredException;
import Questions.UrlShortnerDesignLLD.exception.UrlNotFoundException;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import java.time.LocalDateTime;

/**
 * Service class that manages the business logic for shortening URLs,
 * resolving short codes back to their original URLs, and maintaining statistics.
 */
public class UrlShortenerService {
    private final AtomicLong counter;
    private final UrlRepository repository;
    private EncodingStrategy strategy;
    private final ReentrantLock aliasLock = new ReentrantLock();
    private final long defaultTtlHours;

    /**
     * Constructs a new UrlShortenerService.
     *
     * @param repository      The repository to store the URL mappings.
     * @param strategy        The strategy used to encode IDs to short codes.
     * @param startCounter    The starting sequential number for the ID generator.
     * @param defaultTtlHours The default Time-To-Live in hours for shortened URLs.
     */
    public UrlShortenerService(UrlRepository repository, EncodingStrategy strategy,
                               long startCounter, long defaultTtlHours) {
        this.repository = repository;
        this.strategy = strategy;
        this.counter = new AtomicLong(startCounter);
        this.defaultTtlHours = defaultTtlHours;
    }

    /**
     * Updates the encoding strategy dynamically.
     *
     * @param strategy The new EncodingStrategy to use.
     */
    public void setEncodingStrategy(EncodingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Shortens a long URL using the default TTL and no user ID.
     *
     * @param longUrl The original long URL.
     * @return The shortened unique code.
     */
    public String shorten(String longUrl) {
        return shorten(longUrl, null, null);
    }

    /**
     * Shortens a long URL for a specific user.
     *
     * @param longUrl The original long URL.
     * @param userId  The ID of the user creating the link.
     * @return The shortened unique code.
     */
    public String shorten(String longUrl, String userId) {
        return shorten(longUrl, userId, null);
    }

    /**
     * Shortens a long URL with a custom alias.
     *
     * @param longUrl     The original long URL.
     * @param customAlias The custom alias requested.
     * @return The custom alias.
     * @throws AliasAlreadyExistsException if the alias is already in use.
     */
    public String shortenWithAlias(String longUrl, String customAlias) {
        return shortenWithAlias(longUrl, customAlias, null);
    }

    /**
     * Shortens a long URL with a custom alias for a specific user.
     *
     * @param longUrl     The original long URL.
     * @param customAlias The custom alias requested.
     * @param userId      The ID of the user creating the link.
     * @return The custom alias.
     * @throws AliasAlreadyExistsException if the alias is already in use.
     */
    public String shortenWithAlias(String longUrl, String customAlias, String userId) {
        validateUrl(longUrl);
        validateAlias(customAlias);

        aliasLock.lock();
        try {
            if (repository.existsByShortCode(customAlias)) {
                throw new AliasAlreadyExistsException(customAlias);
            }
            LocalDateTime expiresAt = LocalDateTime.now().plusHours(defaultTtlHours);
            UrlMapping mapping = new UrlMapping(customAlias, longUrl, userId, expiresAt);
            repository.save(mapping);
            return customAlias;
        } finally {
            aliasLock.unlock();
        }
    }

    /**
     * Resolves a short code back to its original long URL.
     * Increments the click count on successful resolution.
     *
     * @param shortCode The short code to resolve.
     * @return The original long URL.
     * @throws UrlNotFoundException if the short code does not exist.
     * @throws UrlExpiredException  if the short URL has expired.
     */
    public String resolve(String shortCode) {
        UrlMapping mapping = repository.findByShortCode(shortCode);
        if (mapping == null) {
            throw new UrlNotFoundException(shortCode);
        }
        if (mapping.isExpired()) {
            repository.delete(shortCode);
            throw new UrlExpiredException(shortCode);
        }
        mapping.incrementClicks();
        return mapping.getLongUrl();
    }

    /**
     * Deletes a short URL mapping.
     *
     * @param shortCode The short code to delete.
     * @throws UrlNotFoundException if the short code does not exist.
     */
    public void delete(String shortCode) {
        if (!repository.existsByShortCode(shortCode)) {
            throw new UrlNotFoundException(shortCode);
        }
        repository.delete(shortCode);
    }

    /**
     * Retrieves the current usage statistics for a short URL.
     *
     * @param shortCode The short code to query statistics for.
     * @return The UrlStats representation.
     * @throws UrlNotFoundException if the short code does not exist.
     */
    public UrlStats getStats(String shortCode) {
        UrlMapping mapping = repository.findByShortCode(shortCode);
        if (mapping == null) {
            throw new UrlNotFoundException(shortCode);
        }
        return new UrlStats(mapping);
    }

    /**
     * Internal helper method to shorten a URL with explicit parameters.
     *
     * @param longUrl   The original long URL.
     * @param userId    The user ID.
     * @param expiresAt The expiration date and time.
     * @return The generated short code.
     */
    private String shorten(String longUrl, String userId, LocalDateTime expiresAt) {
        validateUrl(longUrl);
        if (expiresAt == null) {
            expiresAt = LocalDateTime.now().plusHours(defaultTtlHours);
        }

        long id = counter.incrementAndGet();
        String shortCode = strategy.encode(id);

        // Handle potential collision for strategies like random generation
        aliasLock.lock();
        try {
            while (repository.existsByShortCode(shortCode)) {
                id = counter.incrementAndGet();
                shortCode = strategy.encode(id);
            }
            UrlMapping mapping = new UrlMapping(shortCode, longUrl, userId, expiresAt);
            repository.save(mapping);
        } finally {
            aliasLock.unlock();
        }
        return shortCode;
    }

    /**
     * Validates that the input URL is not empty and has a valid protocol.
     *
     * @param url The URL string to validate.
     */
    private void validateUrl(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL cannot be empty");
        }
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            throw new IllegalArgumentException("URL must start with http:// or https://");
        }
    }

    /**
     * Validates custom alias length and characters.
     *
     * @param alias The custom alias string to validate.
     */
    private void validateAlias(String alias) {
        if (alias == null || alias.isBlank()) {
            throw new IllegalArgumentException("Alias cannot be empty");
        }
        if (alias.length() < 3 || alias.length() > 20) {
            throw new IllegalArgumentException("Alias must be 3-20 characters");
        }
        if (!alias.matches("[a-zA-Z0-9_-]+")) {
            throw new IllegalArgumentException("Alias must be alphanumeric with hyphens and underscores only");
        }
    }
}
