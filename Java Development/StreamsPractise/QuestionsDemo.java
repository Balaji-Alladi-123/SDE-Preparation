package StreamsPractise;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}


class Employee {
    int id;
    String name;
    String department;
    long salary;
    int age;
    String city;

    public Employee(int id, String name, String department, long salary,int age, String city) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age  = age;
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public long getSalary() {
        return salary;
    }
    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return String.format(
                "Employee{id=%d, name='%s', department='%s', salary=%d, age=%d}",
                id, name, department, salary, age
        );
    }
}

public class QuestionsDemo {
    public static void main(String[] args) {
        String[] str = {"Balaji", "Ram CHaran", " Ravi", "AB"};
        int[] nums = {1, 2, 3, 4, 4};
//        System.out.println(getLongestString(nums));
//        List<Person> personList = Arrays.asList(
//                new Person("Balaji",247),
//                new Person("Ram charan", 452),
//                new Person("Balu",70)
//        );
//        System.out.println(averageAge(personList));
//          mergedTwoLists();
//        intersection();
//        frequencyOfWords();
//        partitioningList();
//        groupSumming();
        //conversions();
        employeeSorting();
    }

    public static String getLongestString(String[] str) {
        try {
            Optional<String> res = Arrays.stream(str).max(Comparator.comparingInt(String::length));
            return res.get();
        } catch (NoSuchElementException error) {
            System.out.println(error);
        }
        return "";
    }

    public static int getLongestString(int[] arr) {
        OptionalInt res = Arrays.stream(arr).max();

        // Check if result is present
        if (res.isPresent()) {
            return res.getAsInt();  // ✅ unwrap the value
        } else {
            System.out.println("Array is empty.");
            return -1; // or throw an exception or use any default value
        }
    }

    public static double averageAge(List<Person> persons) {
        double age = persons.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);
        return age;
    }


    static void mergedTwoLists() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(799, 664, 9, 8, 0, 546);
        List<Integer> merged = Stream.concat(list1.stream(), list2.stream()).sorted().collect(Collectors.toList());
        System.out.println(merged);
        //unique numbers
        List<Integer> uniqueNumbers = merged
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    static void intersection() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(799, 664, 9, 8, 0, 546, 5, 4);
        List<Integer> merged = list1.stream().filter(list2::contains).sorted().collect(Collectors.toList());
        System.out.println(merged);
    }

    static void frequencyOfWords() {
        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry",
                "banana", "apple");
        Map<String, Long> map = words.stream()
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ));
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    static void partitioningList() {
        List<String> words = Arrays.asList("apple", "orange", "apple", "cherry",
                "banana", "apple", "dragon fruit", "dsfsf", "zxdsf", "bfzdsfah");
        Map<Boolean, List<String>> partitionVowels = words.stream().distinct()
                .collect(Collectors.partitioningBy(QuestionsDemo::vowelChecker));

        List<String> even = partitionVowels.get(true);
        List<String> odd = partitionVowels.get(false);
        System.out.println(even);
        System.out.println(odd);
    }

    static boolean vowelChecker(String word) {
        return word.toLowerCase().matches(".*[aeiou].*");
    }

    static void groupSumming() {
        List<Person> people = Arrays.asList(
                new Person("balu", 23),
                new Person("ram", 56),
                new Person("balu", 56),
                new Person("mahesh", 34)
        );

        Map<String, Integer> map = people
                .stream()
                .collect(Collectors.groupingBy(
                        Person::getName,
                        Collectors.summingInt(Person::getAge)
                ));
        System.out.println(map);
    }

    static void getKthSmallestAndLargest() {
        int[] nums = {10, 23, 7, 89, 893, 67, 100, 145, 62};
        int k = 3;
        int kthSmallest = Arrays
                .stream(nums)
                .sorted()
                .skip(k - 1)
                .findFirst()
                .orElse(-1);

        int kthLargest = Arrays
                .stream(nums)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(k - 1)
                .findFirst()
                .orElse(-1);
    }


    public static void conversions() {
        int[] nums = {1, 2, 3, 4};
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        // arr to list
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());

        //list to arr
        int[] res = list1.stream().mapToInt(Integer::intValue).toArray();

        List<Integer> even = Arrays.stream(nums)
                .boxed()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    public static void employeeSorting() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Balaji", "Software", 9,29,"Hyderabad"));
        employees.add(new Employee(1, "Ravi", "hardware", 4,45, "Vijayawada"));
        employees.add(new Employee(1, "Subu", "testing", 1,67, "Hyderabad"));
        employees.add(new Employee(1, "Satya", "Software", 29,33,"Chennai"));
        employees.add(new Employee(1, "Zunaid", "QA", 27,55,"Kolkata"));

        //sort based on name and department
        List<Employee> result = employees.stream().sorted(Comparator.comparing(Employee::getName)
                .thenComparing(Employee::getDepartment)
        ).collect(Collectors.toList());

        List<String> getNames  = employees.stream()
                        .filter(e -> e.department.equals("Software"))
                                .map(Employee:: getName)
                                        .distinct().collect(Collectors.toList());
        //Increase salary by 10% for employees under 35, then collect to new list.
        List<Employee> resList = employees
                .stream()
                        .filter(e-> e.age <60)
                                .map(e -> {
                                    e.setSalary((long)(e.getSalary()+ e.getSalary() * 0.20));
                                    return e;
                                }
                                )
                .collect(Collectors.toList());

        List<Employee> skip = employees.stream()
                        .skip(2)
                                .collect(Collectors.toList());
        skip.forEach(System.out::println);

        // How do you group employees by city and get the list of employees for each city
        Map<String, List<String>> groupEmp = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee:: getCity,
                        Collectors.mapping(Employee:: getName, Collectors.toList())
                ));
        groupEmp.forEach((city, names) ->
                System.out.println(city + " -> " + names)
        );

        //How do you group employees by city and count the number of employees in each city
        Map<String, Long> countEmployees = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getCity,
                        Collectors.counting()
                ));

        countEmployees.forEach((city, count) ->
                System.out.println(city + " -> " + count)
        );
        //What happens if there are duplicate keys when using Collectors.toMap? How do you resolve it?
        //How would you use Collectors.toMap to convert a list of employees into a map by ID while handling duplicate keys by updating the existing value?
        //If multiple employees have the same city, you’ll get:
//        Map<String, Employee> map = employees.stream()
//                .collect(Collectors.toMap(
//                        Employee::getCity,
//                        emp -> emp
//                ));
        Map<String, Employee> map = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getCity,
                        emp -> emp,
                        (existing, newEmp) -> existing
                ));
       //👉 Keeps the first employee, ignores others
       // Exception in thread "main" java.lang.IllegalStateException: Duplicate key

        //How do you find the employee with the maximum salary using streams?
        Employee emp = employees.stream()
                .max(Comparator.comparing(Employee:: getSalary))
                .orElse(null);

        //How do you find the Nth highest paid employee?
        Employee highest = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .skip(0)
                .findFirst()
                .orElse(null);
        System.out.println("nth highest salary employee"+ highest);

        //Find the employee with the maximum salary department-wise.
        Map<String, Employee> listEmp = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Employee:: getSalary)),
                                opt ->opt.orElse(null)
                        )
                ));

    }

}
