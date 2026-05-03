import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class javaStreams {
    public static void main(String[] ar) {

//        LinkedList<Integer> list = new LinkedList<>();
//        for (int i =0;i<=6;i++) {
//          list.add(i);
//        }
//        List<Integer> integerList = list.stream()
//                .filter(it -> it % 2 == 0)
//                .sorted(Comparator.reverseOrder())
//                .collect(Collectors.toList());
//        System.out.println(integerList);


//
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//
//        list.stream().map(it-> it + 1).collect(Collectors.toList()).forEach(System.out::println);

        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );

        //               .max(Integer::compareTo).get();
      List<Integer> values =  listOfLists.stream()
                .flatMap(Collection::stream)
                .map(it -> it +1).sorted()
                        .reduce(new ArrayList<Integer>(), (acc, i) -> {
                            if(i%2 == 0)
                                acc.add(i);
                            return acc;
                            },
                                (acc, i)->{
                            acc.addAll(i);
                                    return acc;
                                });

        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

        // Using reduce to find the sum
        int sum = numbers.reduce(0, (acc, num) -> acc + num);

        System.out.println("Sum: " + sum);

    }
}
