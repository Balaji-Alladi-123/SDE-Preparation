package Questions.UrlShortnerDesignLLD.mainClass;

import Questions.UrlShortnerDesignLLD.repository.UrlRepository;
import Questions.UrlShortnerDesignLLD.repository.InMemoryUrlRepository;
import Questions.UrlShortnerDesignLLD.strategy.EncodingStrategy;
import Questions.UrlShortnerDesignLLD.strategy.Base62Strategy;
import Questions.UrlShortnerDesignLLD.strategy.RandomStrategy;
import Questions.UrlShortnerDesignLLD.service.UrlShortenerService;
import Questions.UrlShortnerDesignLLD.exception.AliasAlreadyExistsException;
import Questions.UrlShortnerDesignLLD.exception.UrlNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Main demonstration class for the Low Level Design (LLD) of the URL Shortener.
 * Shows instantiation of repository, strategy, and service, basic shortening,
 * custom aliases, exception handling, dynamic strategy switching, and concurrent testing.
 */
public class URLShortenerDemo {

    /**
     * Entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Instantiate the system components
        UrlRepository repository = new InMemoryUrlRepository();
        EncodingStrategy base62 = new Base62Strategy();
        UrlShortenerService service = new UrlShortenerService(repository, base62, 10000, 24);

        // Basic shortening demonstration
        System.out.println("=== Shorten URLs ===");
        String code1 = service.shorten("https://www.example.com/very/long/path/to/resource");
        String code2 = service.shorten("https://docs.oracle.com/javase/tutorial");
        String code3 = service.shorten("https://github.com/user/repo/pull/123");
        System.out.println("Shortened: " + code1);
        System.out.println("Shortened: " + code2);
        System.out.println("Shortened: " + code3);

        // Resolving short codes back to URLs
        System.out.println("\n=== Resolve URLs ===");
        System.out.println(code1 + " -> " + service.resolve(code1));
        System.out.println(code2 + " -> " + service.resolve(code2));

        // Multiple resolves to increment click counts
        service.resolve(code1);
        service.resolve(code1);

        // Retrieving and printing metrics/statistics
        System.out.println("\n=== URL Stats ===");
        System.out.println(service.getStats(code1));

        // Creating shortening mapping with a custom alias
        System.out.println("\n=== Custom Alias ===");
        String alias = service.shortenWithAlias("https://myportfolio.com", "my-site");
        System.out.println("Custom alias: " + alias);
        System.out.println("Resolves to: " + service.resolve(alias));

        // Demonstrating failure when checking for duplicate alias
        System.out.println("\n=== Duplicate Alias ===");
        try {
            service.shortenWithAlias("https://another.com", "my-site");
        } catch (AliasAlreadyExistsException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        // Deleting a mapping and resolving it again to show it was removed
        System.out.println("\n=== Delete URL ===");
        service.delete(code3);
        try {
            service.resolve(code3);
        } catch (UrlNotFoundException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        // Demonstrating behavior with nonexistent short code
        System.out.println("\n=== Not Found ===");
        try {
            service.resolve("nonexistent");
        } catch (UrlNotFoundException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        // Switching the strategy dynamically to random strategy
        System.out.println("\n=== Random Strategy ===");
        service.setEncodingStrategy(new RandomStrategy(8));
        String randomCode = service.shorten("https://random-strategy.example.com");
        System.out.println("Random code: " + randomCode);
        System.out.println("Resolves to: " + service.resolve(randomCode));

        // Concurrent click counting validation
        System.out.println("\n=== Concurrent Click Test ===");
        String concurrentCode = service.shorten("https://popular-page.com");
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            futures.add(executor.submit(() -> service.resolve(concurrentCode)));
        }
        for (Future<?> f : futures) {
            try {
                f.get();
            } catch (Exception ignored) {
            }
        }
        executor.shutdown();
        System.out.println("After 1000 concurrent resolves: " + service.getStats(concurrentCode));
        System.out.println("Expected click count: 1000");
    }
}
