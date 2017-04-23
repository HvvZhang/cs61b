/**
 * A blueprint of what ANY Deque should behave like.
 */
public interface Deque<Item> {

    /** Returns true if the list is empty.*/
    boolean isEmpty();

    /** Returns the size of the list. */
    int size();

    /**
     * Returns the item at the specified index.
     * @param index The index of the i-th item.
     */
    Item get(int index);

    /** Prints out the items in the list. */
    void printDeque();

    /**
     * Adds an item to the front of the list.
     * @param item Item to be added to the list.
     */
    void addFirst(Item item);

    /**
     * Adds an item to the end of the list.
     * @param item Item to be added to the list.
     */
    void addLast(Item item);

    /**
     * Removes and returns the first item from the list.
     */
    Item removeFirst();

    /**
     * Removes and returns the last item from the list.
     */
    Item removeLast();
}
