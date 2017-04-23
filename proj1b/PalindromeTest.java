import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests all the methods in the palindrome class.
 * @author Arjun Nair
 */

public class PalindromeTest {
    @Test
    public void testWordToDeque() {
        Deque<Character> driver;

        String empty = "";
        driver = Palindrome.wordToDeque(empty);
        assertEquals(0, driver.size());

        driver = Palindrome.wordToDeque("a");
        assertEquals('a', (char) driver.get(0));
        assertEquals(1, driver.size());
    }

    @Test(timeout = 5000)
    public void testIsPalindrome() {
        boolean driver;

        driver = Palindrome.isPalindrome("");
        assertEquals(true, driver);

        driver = Palindrome.isPalindrome(" ");
        assertEquals(true, driver);

        driver = Palindrome.isPalindrome("abaa");
        assertEquals(false, driver);

        driver = Palindrome.isPalindrome("aba");
        assertEquals(true, driver);

        driver = Palindrome.isPalindrome("abba");
        assertEquals(true, driver);

        driver = Palindrome.isPalindrome("abc");
        assertEquals(false, driver);
    }
}
