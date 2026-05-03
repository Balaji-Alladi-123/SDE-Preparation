package Questions.URLShortnerSystem.interfaceImplementations;

import Questions.URLShortnerSystem.interfaces.UrlValidator;
import java.net.URL;
public class BasicUrlValidator implements UrlValidator {
    @Override
    public boolean isValid(String url) {
        try {
            new URL(url);
             return url.length() <= 2048;
        } catch (Exception e) {
            return false;
        }
    }
}
