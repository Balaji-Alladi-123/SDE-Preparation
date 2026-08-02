package StreamsPractise;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@FunctionalInterface
interface EvenProgram {
    void printNums(Map<Integer, Integer> map);
}

public class StreamsDemo {
    public static void main(String[] args) {
//        HashMap<Integer, Integer> map = new HashMap<>();
//        map.putIfAbsent(1, 0);
//        map.putIfAbsent(2, 1);
//        map.putIfAbsent(3, 34);
//
//        EvenProgram evenProgram = (v) -> {
//            v.keySet().forEach(k -> {
//                if (v.get(k) % 2 == 0) {
//                    System.out.println("Key: " + k + ", Even Value: " + v.get(k));
//                }
//            });
//        };
//        evenProgram.printNums(map);
        //intermediateOp();

//        List<String> strList = new ArrayList<>(
//                List.of("bala", "hari", "ravi", "bala")
//        );
//
//        Map<String, Long> frequencyMap = strList.stream()
//                .collect(Collectors.groupingBy(
//                        Function.identity(),
//                        Collectors.counting()
//                ));

//        frequencyMap.forEach((key,val) ->
//                System.out.println(key + " "+ val));
//        List<String> result = frequencyMap.entrySet().stream()
//                .filter(entry -> entry.getValue() == 1)
//                .map(Map.Entry::getKey)
//                .toList();
//            System.out.println(result);

//        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
//        int[] nums = {12,3,4,5};
//        IntSummaryStatistics s = list.stream().mapToInt(Integer:: intValue).summaryStatistics();
//        System.out.println(s);
        fistRepeatingCharacter("Programming");
        fistNonRepeatingCharacter("Java articles are Awesome");
    }

    private static void fistRepeatingCharacter(String s) {
        Map<Character, Long> map  = s.chars().mapToObj(c-> (char) c)
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()));

        char c = map.entrySet()
                .stream()
                .filter(e -> e.getValue()>1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        System.out.println("First Repeating character:" +c);
    }

    private static void fistNonRepeatingCharacter(String s) {
        Map<Character, Long> map  = s.chars().mapToObj(c-> (char) c)
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()));

        char c = map.entrySet()
                .stream()
                .filter(e -> e.getValue()==1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        System.out.println("First Non Repeating character:" +c);
    }

    public static void intermediateOp() {
        List<String> words = Arrays.asList("apple", "banana", "apr", "cherry", "avocado", "blueberry");
       List<String> res = words.stream().filter(w -> w.startsWith("a")).map(s-> s.toUpperCase())
                       .sorted().distinct().collect(Collectors.toList());
       List<Integer> lengths = words.stream().filter(w-> w.length()>5).
               map(s->s.length()).
               sorted().
               collect(Collectors.toList());

        List<Integer> UniqueLengths = words.stream().filter(w-> w.startsWith("a") || w.startsWith("b")).
                map(s->s.length()).sorted().
                distinct().
                collect(Collectors.toList());
        List<String> sentences = Arrays.asList(
                "Java streams are powerful",
                "flatMap is useful for flattening",
                "Practice makes perfect"
        );

        List<Integer> flat = sentences.stream()
                        .flatMap(sen -> Arrays.stream(sen.split(" "))).map(s->s.length())
                                .collect(Collectors.toList());

        List<String> list  = Arrays.asList("a1","a2","b1","a3","c1","b4","c5");
        Map<Character, Integer> resMap = list
                .stream()
                .collect(
                        Collectors.groupingBy(
                                s-> s.charAt(0),
                                Collectors.summingInt(s -> Character.getNumericValue(s.charAt(1)))
                        ));

        System.out.println(resMap);
    }
}
