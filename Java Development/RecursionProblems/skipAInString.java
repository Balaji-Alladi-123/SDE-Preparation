package RecursionProblems;

public class skipAInString {
    public static void main(String[] args) {
        System.out.println(skipA("bcabbcaad"));
    }

    public static String skipA( String s) {
        if(s.isEmpty()) {
            return "";
        }
        char ch = s.charAt(0);
        if(ch == 'a') {
            return skipA(s.substring(1));
        } else {
            return ch+skipA(s.substring(1));
        }
    }
}
