package Questions.URLShortnerSystem.interfaceImplementations;

import Questions.URLShortnerSystem.interfaces.ShortCodeFactory;
import Questions.URLShortnerSystem.interfaces.ShortCodeGenerator;
import Questions.URLShortnerSystem.repository.UrlRepository;

 class Base62ShortCodeFactory implements ShortCodeFactory {
    private final UrlRepository urlRepository;
    public Base62ShortCodeFactory(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
     public ShortCodeGenerator createShortCode() {
        return new Base64ShortCodeGeneration(urlRepository);
    }
}
