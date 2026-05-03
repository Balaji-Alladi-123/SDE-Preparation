public class Subset {
    public static void main(String[] ag) {
        //subSeq("", "abc");
    }

    private static void subSeq(String p, String up) {
        if(up.isEmpty()) {
            System.out.print(p + " ");
            return;
        }
        char ch = up.charAt(0);
        subSeq(p+ch, up.substring(1));
        subSeq(p,up.substring(1));
    }
}
