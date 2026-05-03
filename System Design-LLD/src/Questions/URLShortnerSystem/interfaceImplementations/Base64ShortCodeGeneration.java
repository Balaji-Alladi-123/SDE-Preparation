package Questions.URLShortnerSystem.interfaceImplementations;

import Questions.URLShortnerSystem.interfaces.ShortCodeGenerator;
import Questions.URLShortnerSystem.repository.UrlRepository;

public class Base64ShortCodeGeneration implements ShortCodeGenerator {
    private final String BaseCode_Chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int codeLength = 6;
    private final UrlRepository urlRepository;

    Base64ShortCodeGeneration(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public String shortCodeGenerator() {
        long counter = urlRepository.getNextCounter();
        return Base62(counter);
    }

    private String Base62(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0 && sb.length() < BaseCode_Chars.length()) {
            sb.append(BaseCode_Chars.charAt((int) num % 62));
            num /= 62;
        }
        while(sb.length()< BaseCode_Chars.length()) {
            sb.append(BaseCode_Chars.charAt(0));
        }
        return  sb.reverse().toString();
    }
}
