/**
 * Created by arjunnair on 2017-04-23.
 */
public class BasicCharEquality implements CharacterComparator {

    /** Basic equality test for chars. */
    @Override
    public boolean equalChars(char x, char y) {
        return x == y;
    }
}
