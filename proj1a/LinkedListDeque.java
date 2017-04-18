/**
 * Represents a doubly linked linked list.
 * @author Arjun Nair
 */

/* Invariants
1. Each ItemNode must always have a prev and a next node.
   i.e the prev and next nodes must never be null.
2. size should always represent the number of items in the list.
3. The empty and non-empty lists both should have the
   same underlying structure. An empty list must not be null.
*/

public class LinkedListDeque<Item> {

    /**
     * Represents a naked DLList.
     */
    private class ItemNode {
        ItemNode prev;
        Item item;
        ItemNode next;

        private ItemNode(Item item, ItemNode prev, ItemNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private ItemNode sentinel;
    private int size;

    /** Constructor to instantiate empty lists. */
    public LinkedListDeque() {
        this.sentinel = new ItemNode(null, null, null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
        this.size = 0;
    }

    /** Returns the size of the list. */
    public int size() {
        return this.size;
    }

    /** Returns true if the list is empty. */
    public boolean isEmpty() {
        return this.sentinel.next == this.sentinel;
    }

    /**
     * Returns the i-th item in the list.
     * Written iteratively.
     * @param index The position of the item in the list.
     */
    public Item get(int index) {
        ItemNode p = this.sentinel;

        if (index >= this.size || index < 0) {
            return null;
        }

        while (index >= 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /**
     * Returns the i-th item in the list.
     * Written recursively.
     * @param index The position of the item in the list.
     */
    private Item getRecursive(ItemNode p, int index) {
        if (index == 0) {
            return p.next.item;
        } else {
            return getRecursive(p.next, index - 1);
        }
    }

    /**
     * Returns the i-th item in the list.
     * @param index The position of the item in the list.
     */
    public Item getRecursive(int index) {
        if (index >= this.size || index < 0) {
            return null;
        } else {
            return getRecursive(this.sentinel, index);
        }
    }

    /** Prints out the items in the list separated by spaces. */
    public void printDeque() {
        ItemNode p = this.sentinel;

        while (p.next != this.sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * Adds an item to the front of the list.
     * @param item The item to be added.
     */
    public void addFirst(Item item) {
        ItemNode firstNode = new ItemNode(item, this.sentinel, this.sentinel.next);
        this.sentinel.next.prev = firstNode;
        this.sentinel.next = firstNode;
        this.size += 1;
    }

    /**
     * Adds an item to the end of the list.
     * @param item The item to be added.
     */
    public void addLast(Item item) {
        ItemNode oldLastNode = this.sentinel.prev;
        ItemNode newLastNode = new ItemNode(item, oldLastNode, this.sentinel);
        oldLastNode.next = newLastNode;
        this.sentinel.prev = newLastNode;
        this.size += 1;
    }

    /** Returns the new size after an item has been removed. */
    private static int minusOne(int size) {
        if (size > 0) {
            return size - 1;
        }
        return size;
    }

    /**
     * Removes and returns the first item from the list.
     */
    public Item removeFirst() {
        ItemNode oldFirstNode = this.sentinel.next;
        ItemNode newFirstNode = oldFirstNode.next;
        newFirstNode.prev = this.sentinel;
        this.sentinel.next = newFirstNode;

        this.size = minusOne(this.size);
        return oldFirstNode.item;
    }

    /**
     * Removes and returns the last item from the list.
     */
    public Item removeLast() {
        ItemNode oldLastNode = this.sentinel.prev;
        ItemNode newLastNode = oldLastNode.prev;
        newLastNode.next = this.sentinel;
        this.sentinel.prev = newLastNode;

        this.size = minusOne(this.size);
        return oldLastNode.item;
    }
}
