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
        this.sentinel = new ItemNode(null, null);
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
     * Returns the i-th item in the list.
     * Written recursively.
     * @param index The position of the item in the list.
     */
    private T getRecursive(ItemNode p, int index) {
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
    public T getRecursive(int index) {
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
    public void addFirst(T item) {
        ItemNode firstNode = new ItemNode(item, this.sentinel, this.sentinel.next);
        this.sentinel.next.prev = firstNode;
        this.sentinel.next = firstNode;
        this.size += 1;
    }

    /**
     * Adds an item to the end of the list.
     * @param item The item to be added.
     */
    public void addLast(T item) {
        ItemNode prevLastNode = this.sentinel.prev;
        ItemNode newLastNode = new ItemNode(item, prevLastNode, this.sentinel);
        prevLastNode.next = newLastNode;
        this.sentinel.prev = newLastNode;
        this.size += 1;
    }

    /**
     * Removes and returns the first item from the list.
     */
    public T removeFirst() {
        ItemNode oldFirstNode = this.sentinel.next;
        ItemNode newFirstNode = oldFirstNode.next;
        newFirstNode.prev = this.sentinel;
        this.sentinel.next = newFirstNode;

        if (this.size > 0) {
            this.size -= 1;
        }
        return oldFirstNode.item;
    }

    /**
     * Removes and returns the last item from the list.
     */
    public T removeLast() {
        ItemNode oldLastNode = this.sentinel.prev;
        ItemNode newLastNode = oldLastNode.prev;
        newLastNode.next = this.sentinel;
        this.sentinel.prev = newLastNode;

        if (this.size > 0) {
            this.size -= 1;
        }
        return oldLastNode.item;
    }

    /** Writing some temporary tests here. */
    private static void test() {
        /* Constructor tests */
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

        /* Testing the addFirst method. */
        System.out.println("addFirst tests");
        L.addFirst(2);

        M.addFirst(5);
        S.addFirst("booga");
        System.out.println(M.sentinel.prev.item == 3); // expected true
        System.out.println(L.sentinel.prev.item == 2); // expected true


        /* Testing the isEmpty method. */
        System.out.println("isEmpty tests");
        LinkedListDeque<String> P = new LinkedListDeque<>();
        System.out.println(P.isEmpty()); // expected true
        P.addLast("booga");
        System.out.println(!P.isEmpty()); // expected true

        /* Testing the addLast method. */
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

        /* Testing the printDeque method. */
        System.out.println("printDeque tests");
        LinkedListDeque<Integer> T = new LinkedListDeque<>();
        T.printDeque(); // should print out nothing
        T.addLast(3);
        T.printDeque(); // should print out 3
        T.addFirst(2);
        T.printDeque(); // should print out 2 3
        T.addLast(5);
        T.printDeque(); // should print out 2 3 5

        LinkedListDeque<Integer> O = new LinkedListDeque<>(3);
        O.printDeque(); // expected 3

        /* Testing the removeFirst method. */
        System.out.println("removeFirst tests");
        LinkedListDeque<Integer> I = new LinkedListDeque<>();
        System.out.println(I.removeFirst() == null); // expected true
        System.out.println(I.size() == 0); // expected true
        I.addLast(1);
        I.printDeque(); // expected 1
        System.out.println(I.size() == 1); // expected true
        System.out.println(I.removeFirst() == 1); // expected true
        System.out.println(I.size() == 0); // expected true
        I.printDeque(); // expected empty line
        System.out.println(I.sentinel.next == I.sentinel); // expected true
        System.out.println(I.sentinel.prev == I.sentinel); // expected true
        I.addFirst(2);
        I.addLast(3);
        I.addFirst(1);
        I.printDeque(); // expected 1 2 3
        System.out.println(I.removeFirst() == 1);
        I.printDeque(); // expected 2 3
        System.out.println(I.sentinel.prev.item == 3); // expected true
        System.out.println(I.sentinel.next.item == 2); // expected true
        System.out.println(I.sentinel.next.prev == I.sentinel); // expected true
        System.out.println(I.sentinel.next.next.item == 3); // expected true
        System.out.println(I.size() == 2); // expected true
        I.removeFirst();
        System.out.println(I.size() == 1); // expected true

        /* Testing the removeLast method. */
        System.out.println("removeLast tests");
        LinkedListDeque<Integer> H = new LinkedListDeque<>();
        System.out.println(H.removeLast() == null); // expected true
        H.addLast(2);
        H.addFirst(1);
        H.printDeque(); // expected 1 2
        System.out.println(H.removeLast() == 2); // expected true
        H.printDeque(); // expected 1
        System.out.println(H.sentinel.prev.item == 1); // expected true

        /* Testing the getRecursive method. */
        System.out.println("getRecursive tests");
        L = new LinkedListDeque<>();
        M = new LinkedListDeque<>(3);
        S = new LinkedListDeque<>("ho-ho-ho");
        System.out.println("get tests");
        System.out.println(L.getRecursive(0) == null); // expected true
        System.out.println(M.getRecursive(0) == 3); // expected true
        System.out.println(M.getRecursive(-1) == null); // expected true
        System.out.println(S.getRecursive(0) == "ho-ho-ho"); // expected true
    }

    public static void main(String[] args) {
        test();
    }
}
