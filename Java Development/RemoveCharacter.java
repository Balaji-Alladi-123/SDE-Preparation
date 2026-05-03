public class RemoveCharacter {
    public static void main(String[] a) {
//        skip("", "baanjha");
       String s = nextSkip("abbaca");
       System.out.println(s);
    }
    private static void skip(String p, String up) {
        if(up.isEmpty()) {
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);
        if(ch == 'a') {
         skip(p, up.substring(1));
        }
        else {
            skip(p+ch,up.substring(1));
        }
    }

    public static String nextSkip(String up) {
        if(up.isEmpty()) {
            return "";
        }
        char ch = up.charAt(0);
        if(ch == 'a') {
           return nextSkip(up.substring(1));
        }
        else {
           String q= ch +nextSkip(up.substring(1));
            return q;
        }
    }

}
