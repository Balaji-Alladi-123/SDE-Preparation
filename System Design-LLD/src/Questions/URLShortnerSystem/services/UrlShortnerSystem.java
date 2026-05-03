package Questions.URLShortnerSystem.services;

import Questions.URLShortnerSystem.interfaces.ShortCodeFactory;
import Questions.URLShortnerSystem.interfaces.ShortCodeGenerator;
import Questions.URLShortnerSystem.interfaces.UrlValidator;
import Questions.URLShortnerSystem.repository.UrlRepository;

import java.util.concurrent.ConcurrentHashMap;

public class UrlShortnerSystem {
    private static UrlShortnerSystem instance;
    private final UrlRepository urlRepository;
    private final ShortCodeGenerator shortCodeGenerator;
    private final UrlValidator urlValidator;
    private final String baseUrl = "http://short-Url/";

    UrlShortnerSystem(ShortCodeFactory factory, UrlValidator urlValidator) {
        this.shortCodeGenerator = factory.createShortCode();
        this.urlValidator = urlValidator;
        this.urlRepository = new UrlRepository();
    }

    public static synchronized UrlShortnerSystem getInstance(ShortCodeFactory factory, UrlValidator validator) {
        if (instance == null) {
            instance = new UrlShortnerSystem(factory, validator);
        }
        return instance;
    }

    public String processUrlShortner(String longUrl) {
        if (!urlValidator.isValid(longUrl)) {
            throw new IllegalArgumentException("Invalid url, Please provide formatted url");
        }
        String shortCode = shortCodeGenerator.shortCodeGenerator();
        urlRepository.save(longUrl, shortCode);
        return baseUrl + shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        String originalUrl = urlRepository.findByShortCode(shortCode);
        if (originalUrl == null) {
            throw new IllegalArgumentException("No Url Found!");
        }
        return originalUrl;
    }

    public int getTotalUrls() {
        return urlRepository.getUrlCount();
    }

    public ConcurrentHashMap getMap() {
        return urlRepository.getMap();
    }
 }
