package Questions.URLShortnerSystem.interfaceImplementations;

import Questions.URLShortnerSystem.interfaces.ShortCodeFactory;
import Questions.URLShortnerSystem.interfaces.ShortCodeGenerator;
import Questions.URLShortnerSystem.repository.UrlRepository;

public class RandomShortCodeFactory implements ShortCodeFactory {

    private final UrlRepository urlRepository;

    public RandomShortCodeFactory(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public ShortCodeGenerator createShortCode() {
        return new RandomShortCodeGenerator(urlRepository);
    }
}
