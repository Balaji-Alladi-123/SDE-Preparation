package Questions.URLShortnerSystem.interfaceImplementations;

import Questions.URLShortnerSystem.interfaces.ShortCodeGenerator;
import Questions.URLShortnerSystem.repository.UrlRepository;

import java.util.Random;

public class RandomShortCodeGenerator implements ShortCodeGenerator {
    private final String BaseCode_Chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int codeLength = 6;
    private final UrlRepository urlRepository;

    RandomShortCodeGenerator(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }


    @Override
    public String shortCodeGenerator() {
       StringBuilder sb = new StringBuilder();
       String shortCode = generator(sb);
       while(urlRepository.findByShortCode(shortCode) !=null) {
           sb = new StringBuilder();
           shortCode = generator(sb);
       }
       return shortCode;
    }

    public String generator(StringBuilder sb) {
        Random random = new Random();
        for(int i=0;i<codeLength;i++) {
            sb.append(BaseCode_Chars.charAt(random.nextInt(BaseCode_Chars.length())));
        }
        return sb.toString();
    }
}
