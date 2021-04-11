package safe_matcher;

import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class SafeMatcher {
    public SafeMatcher(long timeout) {
        this.timeout = timeout;
    }

    public boolean matches(String text, String regex) {
        Matcher matcher;
        try {
            matcher = Pattern.compile(regex).matcher(text);
        } catch (PatternSyntaxException | NullPointerException e) {
            return false;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(matcher::matches);

        boolean res = false;
        try {
            res = future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            future.cancel(true);
        }
        executor.shutdown();

        return res;
    }

    private final long timeout;
}
