/**
 * Defines an unconventional equality for chars.
 * @author Arjun Nair
 */
public class OffByOne implements CharacterComparator {

    /** Returns true if the ascii for two chars
     * differs by one.
     */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(y - x) == 1;
    }
}
