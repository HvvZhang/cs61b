/**
 * Defines an unconventional equality for chars.
 * @author Arjun Nair
 */
public class OffByN implements CharacterComparator {

    private int N;

    public OffByN(int N) {
        this.N = N;
    }

    /**
     * Returns true if the ASCII
     * value of the chars differs by N.
     */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(y - x) == this.N;
    }
}
