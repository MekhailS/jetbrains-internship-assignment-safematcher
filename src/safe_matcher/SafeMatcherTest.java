package safe_matcher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SafeMatcherTest {

    @Test
    void matches_StringMatcherItselfRegex_TrueMatch() {
        SafeMatcher matcher = new SafeMatcher(100);

        Assertions.assertTrue(matcher.matches("hello world!", "hello world!"));
    }

    @Test
    void matches_StringMatchesRegex_TrueMatch() {
        SafeMatcher matcher = new SafeMatcher(100);

        Assertions.assertTrue(matcher.matches("hello world!", "hello [world]{5}!"));
    }

    @Test
    void matches_NullString_FalseMatch() {
        SafeMatcher matcher = new SafeMatcher(100);

        Assertions.assertFalse(matcher.matches(null, "regex"));
    }

    @Test
    void matches_NullRegex_FalseMatch() {
        SafeMatcher matcher = new SafeMatcher(100);

        Assertions.assertFalse(matcher.matches("string", null));
    }

    @Test
    void matches_NotValidRegex_FalseMatch() {
        SafeMatcher matcher = new SafeMatcher(100);

        Assertions.assertFalse(matcher.matches("string", "(regex"));
    }

    @Test
    void matches_EvilRegex1_FalseMatch() {
        SafeMatcher matcher = new SafeMatcher(100);

        Assertions.assertFalse(matcher.matches("aaaaaaaaaaaaaaaaaaaaaaaa!", "(a+)+"));
    }

    @Test
    void matches_EvilRegex2_FalseMatch() {
        SafeMatcher matcher = new SafeMatcher(100);

        Assertions.assertFalse(matcher.matches("aaaaaaaaaaaaaaaaaaaaaaaa!", "(a|a?)+"));
    }
}