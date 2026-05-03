import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        HashMap<String,Integer> map = new HashMap<>();
        map.putIfAbsent("balu",1);
        map.putIfAbsent("ravi",2);
        map.putIfAbsent("john",3);map.putIfAbsent("david",300);

//        for(Map.Entry<String, Integer> m: map.entrySet()) {
//            System.out.println(m.getKey()+":" + m.getValue() );
//        }
//        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
//        for(Object str: list) {
//            System.out.println(str);
//        }
//        Random random = new Random();
//        System.out.println(list.get(ThreadLocalRandom.current().nextInt(list.size())));
        Integer[] arr = {1,2,3,4,5};
        ArrayList<Integer> list = new ArrayList<>(List.of(arr));
        Integer[] nums = new Integer[list.size()];
        nums = list.toArray(nums);

    }
}