/**
 * Represents an Array List.
 * @author Arjun Nair
 */

/* Invariants
1.  For an empty list, the absolute difference b/w
    nextFirst and nextLast should always be 1.
2.  nextFirst is the position used when a new item
    is added to the front of the list.
3.  nextLast is the position used when a new item
    is added to the back of the list.
4.  plusOne(nextFirst) will always be the position
    of the first item.
5.  minusOne(nextLast) will always be the position
    of the last item.
6.  plusOne(nextLast) will be the new position of nextLast
    when an item is added to the list.
7.  minusOne(nextFirst) will be the new position of nextFirst
    when an item is added to the list.
8.  plusOne(nextFirst) will be the new position of nextFirst
    one an item is removed from the list.
9.  minusOne(nextLast) will be the new position of nextLast
    when an item is removed from the list.
10. size should always represent the number of items
    in the list.
*/

public class ArrayDeque<Item> implements Deque<Item> {

    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private static double usageFactor = 0.25;

    /** Constructor to create an empty AList. */
    public ArrayDeque() {
        this.items = (Item[]) new Object[8];
        this.size = 0;
        this.nextFirst = 0;
        this.nextLast = 1;
    }

    /** Returns true if the list is empty.*/
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /** Returns the size of the list. */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns the item at the specified index.
     * @param index The index of the i-th item.
     */
    @Override
    public Item get(int index) {
        if (index >= this.size || index < 0) {
            return null;
        }

        int firstIndex = plusOne(nextFirst);
        int requiredIndex = (firstIndex + index) % this.items.length;

        return this.items[requiredIndex];
    }

    /** Prints out the items in the list. */
    @Override
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
        return (x + 1) % this.items.length;
    }

    /**
     * Returns the new index when subtracting
     * one from a circular array.
     * @param x The index one is being subtracted from.
     */
    private int minusOne(int x) {
        /* Tried return (x - 1) % this.items.length, but fails. */
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
        Item[] newItems = (Item[]) new Object[capacity];

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
    @Override
    public void addFirst(Item item) {
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
    @Override
    public void addLast(Item item) {
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
        Item[] newItems = (Item[]) new Object[capacity];

        for (int i = 0; i < this.size; i++) {
            newItems[i] = this.get(i);
        }

        this.items = newItems;
        this.nextFirst = minusOne(0);
        this.nextLast = this.size;
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
    @Override
    public Item removeFirst() {
        if (this.size == 0) {
            return null;
        }

        int firstIndex = plusOne(this.nextFirst);
        Item firstItem = this.items[firstIndex];
        this.items[firstIndex] = null;
        this.nextFirst = firstIndex;

        this.size -= 1;
        this.fixUsageRatio();

        return firstItem;
    }

    /**
     * Removes and returns the last item from the list.
     */
    @Override
    public Item removeLast() {
        if (this.size == 0) {
            return null;
        }

        int lastIndex = minusOne(this.nextLast);
        Item lastItem = this.items[lastIndex];
        this.items[lastIndex] = null;
        this.nextLast = lastIndex;


        this.size -= 1;
        this.fixUsageRatio();

        return lastItem;
    }

}
