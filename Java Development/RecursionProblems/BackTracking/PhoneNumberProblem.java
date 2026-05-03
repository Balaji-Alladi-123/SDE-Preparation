package RecursionProblems.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberProblem {
    public static void main(String[] args) {
        letterCombinations("234");
    }
  static   public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return new ArrayList<>();
        return subSetsString("", digits);
    }

    public static ArrayList<String> subSetsString(String p, String up) {
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        int digit = up.charAt(0) - '0';
        String letters = mapping[digit];
        ArrayList<String> list = new ArrayList<>();
        for (char ch : letters.toCharArray()) {
            list.addAll(subSetsString(p + ch, up.substring(1)));
        }
        return list;
    }
    static String[] mapping = {
            "", // 0
            "", // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs", // 7
            "tuv", // 8
            "wxyz" // 9
    };
}
