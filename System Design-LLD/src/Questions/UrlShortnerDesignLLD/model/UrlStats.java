package Questions.UrlShortnerDesignLLD.model;

import java.time.LocalDateTime;

/**
 * Statistics data transfer object (DTO) representing the performance metrics of a shortened URL.
 */
public class UrlStats {
    private final String shortCode;
    private final String longUrl;
    private final long clickCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime expiresAt;
    private final boolean expired;

    /**
     * Constructs a new UrlStats by copying metrics from a UrlMapping instance.
     *
     * @param mapping The UrlMapping containing current state.
     */
    public UrlStats(UrlMapping mapping) {
        this.shortCode = mapping.getShortCode();
        this.longUrl = mapping.getLongUrl();
        this.clickCount = mapping.getClickCount();
        this.createdAt = mapping.getCreatedAt();
        this.expiresAt = mapping.getExpiresAt();
        this.expired = mapping.isExpired();
    }

    /**
     * Gets the short code.
     *
     * @return The short code.
     */
    public String getShortCode() {
        return shortCode;
    }

    /**
     * Gets the original long URL.
     *
     * @return The long URL.
     */
    public String getLongUrl() {
        return longUrl;
    }

    /**
     * Gets the total click count.
     *
     * @return The click count.
     */
    public long getClickCount() {
        return clickCount;
    }

    /**
     * Gets the creation time.
     *
     * @return The creation LocalDateTime.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the expiration time.
     *
     * @return The expiration LocalDateTime, or null if none.
     */
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    /**
     * Checks if the mapping is expired.
     *
     * @return true if expired, false otherwise.
     */
    public boolean isExpired() {
        return expired;
    }

    /**
     * Formats the statistics as a string.
     *
     * @return A string representation of the URL statistics.
     */
    @Override
    public String toString() {
        return "UrlStats{code=" + shortCode + ", url=" + longUrl
                + ", clicks=" + clickCount + ", expired=" + expired + "}";
    }
}
