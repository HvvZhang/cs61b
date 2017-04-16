import sun.awt.image.ImageWatched;

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

    /** Returns true if the list is empty. */
    public boolean isEmpty() {
        return this.size == 0;
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

        /* Have to change previous if this is the first item being added. */
        if (this.size == 0) {
            this.sentinel.prev = firstNode;
        }
        this.size += 1;
    }

    /**
     * Adds an item to the end of the list.
     * @param item The item to be added.
     */
    public void addLast(T item) {
        /* Different behaviour if list is empty. */
        if (this.size == 0) {
            this.addFirst(item);
        } else {
            ItemNode prevLastNode = this.sentinel.prev;
            ItemNode newLastNode = new ItemNode(item, prevLastNode, this.sentinel);
            prevLastNode.next = newLastNode;
            this.sentinel.prev = newLastNode;
            this.size += 1;
        }
    }

    /** Writing some temporary tests here. */
    private static void test() {
        /* Constructor tests*/
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        LinkedListDeque<Integer> M = new LinkedListDeque<>(3);
        LinkedListDeque<String> S = new LinkedListDeque<>("ho-ho-ho");

        /* Testing the initial sizes of the lists. */
        System.out.println("size tests");
        System.out.println(L.size() == 0); // expected true
        System.out.println(M.size() == 1); // expected true

        /* Testing the get method. */
        System.out.println("get tests");
        System.out.println(L.get(0) == null); // expected true
        System.out.println(M.get(0) == 3); // expected true
        System.out.println(M.get(-1) == null); // expected true
        System.out.println(S.get(0) == "ho-ho-ho"); // expected true

        /* Testing the addFirst method */
        System.out.println("addFirst tests");
        L.addFirst(2);
        M.addFirst(5);
        S.addFirst("booga");
        System.out.println(M.sentinel.prev.item == 3); // expected true
        System.out.println(L.sentinel.prev.item == 2); // expected true

        /* Test the isEmpty method */
        System.out.println("isEmpty tests");
        LinkedListDeque<String> P = new LinkedListDeque<>();
        System.out.println(P.isEmpty()); // expected true
        P.addLast("booga");
        System.out.println(P.isEmpty() == false); // expected true

        /* Testing the addLast method */
        System.out.println("addLast tests");
        LinkedListDeque<Integer> K = new LinkedListDeque<>();
        LinkedListDeque<Integer> N = new LinkedListDeque<>(3);
        K.addLast(2);
        System.out.println(K.sentinel.prev.item == 2); // expected true
        System.out.println(K.sentinel.next.item == 2); // expected true
        System.out.println(K.size() == 1); // expected true
        K.addLast(3);
        System.out.println(K.get(1) == 3); // expected true
        System.out.println(K.size() == 2); // expected true
        System.out.println(N.size() == 1); // expected true
        N.addLast(4);
        System.out.println(N.get(1) == 4); // expected true
        System.out.println(N.sentinel.prev.item == 4); // expected true
        System.out.println(N.sentinel.next.item == 3); // expected true
        System.out.println(N.sentinel.next.next.item == 4); // expected true
        System.out.println(N.sentinel.next.next.prev.item == 3); // expected true
    }

    public static void main(String[] args) {
        test();
    }
}
