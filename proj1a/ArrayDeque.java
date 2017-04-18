/**
 * Represents an Array List.
 * @author Arjun Nair
 */

/* Invariants
1. nextFirst is the position used when a new item
   is added to the front of the list.
2. nextLast is the position used when a new item
   is added to the back of the list.
3. plusOne(nextFirst) will always be the position
   of the first item.
4. minusOne(nextLast) will always be the position
   of the last item.
5. plusOne(nextLast) will be the new position of nextLast.
6. minusOne(nextFirst) will be the new position of nextFirst.
7. size should always represent the number of items
   in the list.
*/

public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Constructor to create an empty AList. */
    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = 0;
        this.nextLast = 1;
    }

    /** Returns true if the list is empty.*/
    public boolean isEmpty() {
        return this.size == 0;
    }

    /** Returns the size of the list. */
    public int size() {
        return this.size;
    }

    /**
     * Returns the new index when adding
     * one to a circular array.
     * @param x The index one is being added to.
     */
    private int plusOne(int x) {
        if (x + 1 == this.items.length) {
            return 0;
        }
        return x + 1;
    }

    /**
     * Returns the new index when subtracting
     * one from a circular array.
     * @param x The index one is being subtracted from.
     */
    private int minusOne(int x) {
        if (x == 0) {
            return this.items.length - 1;
        }
        return x - 1;
    }

    /* Example for increaseSize
    Initial Array
    0 1 2 3 4 5 6 7
    a b c d e f g h
    new Array
    0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
    a b c d e f g h
    */

    /**
     * Increases the size of the list.
     * @param capacity The new size of the list.
     */
    private void increaseSize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int firstIndex = this.plusOne(this.nextFirst);
        int numFirstCopy = this.size - firstIndex;
        int numSecondCopy = this.size - numFirstCopy;

        /* Two copy operations needed depending on where firstIndex is. */
        System.arraycopy(this.items, firstIndex, newItems, 0, numFirstCopy);
        System.arraycopy(this.items, 0, newItems, numFirstCopy, numSecondCopy);

        this.items = newItems;
        this.nextFirst = this.minusOne(0);
        this.nextLast = this.size;
    }

    /**
     * Adds an item to the front of the list.
     * @param item Item to be added to the list.
     */
    public void addFirst(T item) {
        if (this.size == this.items.length) {
            this.increaseSize(this.size * 2);
        }

        this.items[this.nextFirst] = item;
        this.nextFirst  = minusOne(this.nextFirst);
        this.size += 1;
    }

    /**
     * Returns the item at the specified index.
     * @param index The index of the i-th item.
     */
    public T get(int index) {
        if (index >= this.size || index < 0) {
            return null;
        }

        int firstIndex = this.plusOne(nextFirst);
        int requiredIndex = firstIndex + index;

        if (requiredIndex < this.items.length) {
            return this.items[requiredIndex];
        } else {
            int numEarlierItems = this.items.length - firstIndex;
            requiredIndex = index - numEarlierItems;
            return this.items[requiredIndex];
        }
    }

    /** Prints out the items in the list. */
    public void printDeque() {
        int firstIndex = this.plusOne(this.nextFirst);
        int counter = 0;

        while (counter < this.size) {
            System.out.print(this.items[firstIndex] + " ");
            firstIndex = this.plusOne(firstIndex);
            counter += 1;
        }
        System.out.println();
    }

    /** Writing some temporary tests here. */
    private static void test() {

        /* Testing the isEmpty method. */
        System.out.println("isEmpty tests");
        ArrayDeque<Integer> E = new ArrayDeque<>();
        System.out.println(E.isEmpty()); // expected true
        E.addFirst(3);
        System.out.println(!E.isEmpty()); // expected true

        /* Testing the plusOne/misOne methods. */
        System.out.println("plusOne/minusOne tests");
        ArrayDeque<Integer> P = new ArrayDeque<>();
        System.out.println(P.minusOne(0) == 7);
        System.out.println(P.minusOne(1) == 0);
        System.out.println(P.plusOne(7) == 0);
        System.out.println(P.plusOne(0) == 1);


        /* Testing the addFirst method. */
        System.out.println("addFirst tests");
        ArrayDeque<Integer> A = new ArrayDeque<>();

        for (int i = 20; i > 0; i--) {
            A.addFirst(i);
        }

        System.out.println(A.size() == 20); // expected true
        A.printDeque(); // Expected numbers 1 to 20 printed out in order.

        ArrayDeque<String> F = new ArrayDeque<>();

        for (int i = 0; i < 10; i++) {
            F.addFirst("P");
        }

        System.out.println(F.size() == 10);
        F.printDeque(); // Expected 10 P's printed out on a single line.

        /* Testing the get method. */
        System.out.println("get tests");
        ArrayDeque<Integer> G = new ArrayDeque<>();

        for (int i = 28; i >= 0; i--) {
            G.addFirst(i);
        }

        for (int i = 0; i < 29; i++) {
            System.out.println(G.get(i) == i); // expected true
        }
    }

    public static void main(String[] args) {
        test();
    }
}
