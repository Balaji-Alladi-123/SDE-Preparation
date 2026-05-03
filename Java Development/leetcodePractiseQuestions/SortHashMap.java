package leetcodePractiseQuestions;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

public class SortHashMap {
    public static void main(String[] args) {
        HashMap<String,Integer> map = new HashMap<>();
        map.put("banana",10);
        map.put("orange",1);
        map.put("grapes",4);
        map.put("pomogranate",11);

        HashMap<String,Integer> sortedMap = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1,e2)-> e1,
                        LinkedHashMap::new
                ));
        System.out.println(sortedMap);
    }

}
