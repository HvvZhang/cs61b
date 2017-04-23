/**
 * Some basic functions related to palindromes.
 * @author Arjun Nair
 */

public class Palindrome {

    /**
     * Returns a Deque which contains all characters in
     * word.
     * @param word The string which is to be converted into a list.
     */
    public static Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> wordList = new ArrayDeque<>();

        for (int i = 0; i < word.length(); i++) {
            wordList.addLast(word.charAt(i));
        }
        return wordList;
    }

    /**
     * Returns true if the word is a palindrome.
     * @param word The word to be tested.
     */
    public static boolean isPalindrome(String word) {
        return isPalindrome(word, new BasicCharEquality());
    }

    /**
     * Returns true if the word is a palindrome
     * according to the comparison rules defined by cc.
     * @param word The word to be tested.
     * @param cc   Defines the comparison rules.
     */
    public static boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else {
            boolean firstAndLast = cc.equalChars(word.charAt(0), word.charAt(word.length() - 1));
            return firstAndLast && isPalindrome(word.substring(1, word.length() - 1), cc);
        }
    }
}
