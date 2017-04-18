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

public class ArrayDeque<Item> {

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
    public Item get(int index) {
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
    public Item removeFirst() {
        /* Reducing size first for usageRatio calculations. */
        if (this.size == 0) {
            return null;
        }

        this.size -= 1;
        this.fixUsageRatio();

        int firstIndex = plusOne(this.nextFirst);
        Item firstItem = this.items[firstIndex];
        this.items[firstIndex] = null;
        this.nextFirst = firstIndex;

        return firstItem;
    }

    /**
     * Removes and returns the last item from the list.
     */
    public Item removeLast() {
        if (this.size == 0) {
            return null;
        }

        this.size -= 1;
        this.fixUsageRatio();

        int lastIndex = minusOne(this.nextLast);
        Item lastItem = this.items[lastIndex];
        this.items[lastIndex] = null;
        this.nextLast = lastIndex;

        return lastItem;
    }
}
