package Questions.UrlShortnerDesignLLD.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Model class representing the mapping between a short code and its original long URL.
 */
public class UrlMapping {
    private final String shortCode;
    private final String longUrl;
    private final String userId;
    private final AtomicLong clickCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime expiresAt;

    /**
     * Constructs a new UrlMapping.
     *
     * @param shortCode The unique shortened code for the URL.
     * @param longUrl   The original long URL.
     * @param userId    The ID of the user who created the mapping (can be null).
     * @param expiresAt The expiration date and time (can be null if it doesn't expire).
     */
    public UrlMapping(String shortCode, String longUrl, String userId, LocalDateTime expiresAt) {
        this.shortCode = shortCode;
        this.longUrl = longUrl;
        this.userId = userId;
        this.clickCount = new AtomicLong(0);
        this.createdAt = LocalDateTime.now();
        this.expiresAt = expiresAt;
    }

    /**
     * Increments the click count of the URL mapping in a thread-safe manner.
     */
    public void incrementClicks() {
        clickCount.incrementAndGet();
    }

    /**
     * Checks whether the URL mapping has expired.
     *
     * @return true if the URL has expired, false otherwise.
     */
    public boolean isExpired() {
        return expiresAt != null && LocalDateTime.now().isAfter(expiresAt);
    }

    /**
     * Gets the unique shortened code.
     *
     * @return The short code string.
     */
    public String getShortCode() {
        return shortCode;
    }

    /**
     * Gets the original long URL.
     *
     * @return The long URL string.
     */
    public String getLongUrl() {
        return longUrl;
    }

    /**
     * Gets the ID of the user who created the mapping.
     *
     * @return The user ID, or null if none.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets the current number of clicks for the short URL.
     *
     * @return The click count.
     */
    public long getClickCount() {
        return clickCount.get();
    }

    /**
     * Gets the creation timestamp.
     *
     * @return The creation LocalDateTime.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the expiration timestamp.
     *
     * @return The expiration LocalDateTime, or null if infinite.
     */
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
}
