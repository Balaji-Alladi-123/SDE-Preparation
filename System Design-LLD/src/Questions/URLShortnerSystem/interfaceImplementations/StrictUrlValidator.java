package Questions.URLShortnerSystem.interfaceImplementations;

import Questions.URLShortnerSystem.interfaces.UrlValidator;

public class StrictUrlValidator implements UrlValidator {
    @Override
    public boolean isValid(String url) {
        return false;
    }
}
