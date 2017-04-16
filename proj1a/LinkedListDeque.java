/**
 * Represents a doubly linked linked list.
 * @author Arjun Nair
 */
public class LinkedListDeque<T> {

    /**
     * Represents a recursive ItemList.
     */
    private class ItemNode {
        ItemNode prev;
        T item;
        ItemNode next;

        public ItemNode(T item, ItemNode prev, ItemNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        /* We'll let item have whatever default value here. */
        public ItemNode(ItemNode prev, ItemNode next) {
            this.prev = prev;
            this.next = next;
        }
    }

    private ItemNode sentinel;
    private int size;

    /** Constructor to instantiate non-empty lists. */
    public LinkedListDeque(T item) {
        this();
        ItemNode nextNode = new ItemNode(item, this.sentinel, this.sentinel);
        this.sentinel.next = nextNode;
        this.sentinel.prev = nextNode;
        this.size = 1;
    }

    /** Constructor to instantiate empty lists. */
    public LinkedListDeque() {
        this.sentinel = new ItemNode(this.sentinel, this.sentinel);
        this.size = 0;
    }

    /** Returns the size of the list. */
    public int size() {
        return this.size;
    }

    /**
     * Returns the i-th item in the list.
     * Written iteratively.
     * @param index The position of the item in the list.
     */
    public T get(int index) {
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
     * Adds an item to the front of the list.
     * @param item The item to be added.
     */
    public void addFirst(T item) {
        ItemNode firstNode = new ItemNode(item, this.sentinel, this.sentinel.next);
        this.sentinel.next = firstNode;
        this.size += 1;

        /* Have to change previous if this is the first item being added. */
        if (this.size == 1) {
            this.sentinel.prev = firstNode;
        }
    }

    /** Writing some temporary tests here. */
    public static void main(String[] args) {
        /* Constructor tests*/
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        LinkedListDeque<Integer> M = new LinkedListDeque<>(3);
        LinkedListDeque<String> S = new LinkedListDeque<>("ho-ho-ho");

        /* Testing the initial sizes of the lists. */
        System.out.println("size tests");
        System.out.println(L.size()); // expected 0
        System.out.println(M.size()); // expected 1

        /* Testing the get method. */
        System.out.println("get tests");
        System.out.println(L.get(0)); // expected null
        System.out.println(M.get(0)); // expected 3
        System.out.println(M.get(-1)); // expected null
        System.out.println(S.get(0)); // expected ho-ho-ho

        /* Testing the addFirst method */
        L.addFirst(2);
        M.addFirst(5);
        S.addFirst("booga");
        System.out.println(M.sentinel.prev.item == 3); // expected true
        System.out.println(L.sentinel.prev.item == 2); // expected true
    }
}
