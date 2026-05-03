package Questions.URLShortnerSystem.services;

import Questions.URLShortnerSystem.interfaceImplementations.BasicUrlValidator;
import Questions.URLShortnerSystem.interfaceImplementations.RandomShortCodeFactory;
import Questions.URLShortnerSystem.interfaces.UrlValidator;
import Questions.URLShortnerSystem.repository.UrlRepository;

import java.util.concurrent.ConcurrentHashMap;

public class UrlShortnerSystemDemo {
    public static void main(String[] args) {

        UrlShortnerSystem urlShortnerSystem = UrlShortnerSystem.getInstance(
                new RandomShortCodeFactory(new UrlRepository()), new BasicUrlValidator());

        String originalLongUrl = "https://www.geeksforgeeks.org/courses?source=google&medium=cpc&device=c&keyword=geeksforgeeks&matchtype=e&campaignid=20039445781&adgroup=147845288105&gad_source=1&gclid=Cj0KCQjwiMmwBhDmARIsABeQ7xROB0LbWxJeUF83hr2ijoLDAH1ekmIO428qwtzQpjaR6FvHiSVwQ2QaAsdCEALw_wcB";
        String shortUrl = urlShortnerSystem.processUrlShortner(originalLongUrl);
        System.out.println(shortUrl);
        String originalUrl1 = urlShortnerSystem.getOriginalUrl(shortUrl.replace("http://short-Url/", ""));
//       ConcurrentHashMap map = urlShortnerSystem.getMap();
//       for(Object values : map.keySet()) {
//           System.out.println(values+""+ map.get(values));
//       }
    }
}
