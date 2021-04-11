package safe_matcher;

public class Main {

    public static void main(String[] args) {
        SafeMatcher safeMatcher = new SafeMatcher(100);

        boolean res = safeMatcher.matches("text", "text");
    }
}
