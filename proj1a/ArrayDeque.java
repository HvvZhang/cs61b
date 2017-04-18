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
5. plusOne(nextLast) will be the new position of nextLast
   when an item is added to the list.
6. minusOne(nextFirst) will be the new position of nextFirst
   when an item is added to the list.
7. plusOne(nextFirst) will be the new position of nextFirst
   one an item is removed from the list.
8. minusOne(nextLast) will be the new position of nextLast
   when an item is removed from the list.
7. size should always represent the number of items
   in the list.
*/

public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private static double usageFactor = 0.25;

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
     * Returns the item at the specified index.
     * @param index The index of the i-th item.
     */
    public T get(int index) {
        if (index >= this.size || index < 0) {
            return null;
        }

        int firstIndex = plusOne(nextFirst);
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
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.get(i) + " ");
        }
        System.out.println();
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

    /**
     * Increases the size of the list.
     * @param capacity The new size of the items array.
     */
    private void increaseSize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];

        for (int i = 0; i < this.size; i++) {
            newItems[i] = this.get(i);
        }

        this.items = newItems;
        this.nextFirst = minusOne(0);
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
        this.nextFirst = minusOne(this.nextFirst);
        this.size += 1;
    }

    /**
     * Adds an item to the end of the list.
     * @param item Item to be added to the list.
     */
    public void addLast(T item) {
        if (this.size == this.items.length) {
            this.increaseSize(this.size * 2);
        }

        this.items[this.nextLast] = item;
        this.nextLast = plusOne(this.nextLast);
        this.size += 1;
    }

    /**
     * Decreases the capacity of the list.
     * @param capacity The new size of the items array.
     */
    private void decreaseSize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];

        for (int i = 0; i < this.size; i++) {
            newItems[i] = this.get(i);
        }

        this.nextFirst = minusOne(0);
        this.nextLast = this.size;
        this.items = newItems;

    }

    private void fixUsageRatio() {
        double usageRatio = (double) this.size / this.items.length;

        if (usageRatio < usageFactor && this.items.length > 16) {
            this.decreaseSize(this.items.length / 2);
        }
    }

    /**
     * Removes and returns the first item from the list.
     */
    public T removeFirst() {
        /* Reducing size first for usageRatio calculations. */
        if (this.size == 0) {
            return null;
        }

        this.size -= 1;
        this.fixUsageRatio();

        int firstIndex = plusOne(this.nextFirst);
        T firstItem = this.items[firstIndex];
        this.items[firstIndex] = null;
        this.nextFirst = firstIndex;

        return firstItem;
    }

    /**
     * Removes and returns the last item from the list.
     */
    public T removeLast() {
        if (this.size == 0) {
            return null;
        }

        this.size -= 1;
        this.fixUsageRatio();

        int lastIndex = minusOne(this.nextLast);
        T lastItem = this.items[lastIndex];
        this.items[lastIndex] = null;
        this.nextLast = lastIndex;

        return lastItem;
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

        /* Testing the addLast method. */
        System.out.println("addLast tests");
        ArrayDeque<Integer> K = new ArrayDeque<>();

        for (int i = 10; i > 0; i--) {
            K.addFirst(i);
        }

        for (int i = 11; i <= 40; i++) {
            K.addLast(i);
        }

        for (int i = 1; i < 40; i++) {
            System.out.println(K.get(i - 1) == i);
        }

        /* Testing the removeFirst method. */
        System.out.println("removeFirst tests");
        ArrayDeque<Integer> D = new ArrayDeque<>();
        System.out.println(D.removeFirst() == null);
        System.out.println(D.size() == 0);
        D.addFirst(2);
        System.out.println(D.removeFirst() == 2);
        System.out.println(D.size() == 0);
        D.addFirst(2);
        D.removeFirst();

        for (int i = 0; i < 100; i++) {
            D.addFirst(1);
            D.addLast(2);
        }
        D.printDeque(); // expected 100 1's followed by 100 2's.

        for (int i = 0; i < 100; i++) {
            D.removeFirst();
        }
        D.printDeque(); // expected 100 2's.

        ArrayDeque<Integer> J = new ArrayDeque<>();
        J.addLast(2);
        System.out.println(J.removeFirst() == 2);
        J.addFirst(2);
        System.out.println(J.removeLast() == 2);
        /* Testing the decreaseSize method. */
    }

    public static void main(String[] args) {
        test();
    }
}
