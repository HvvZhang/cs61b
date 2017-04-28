package synthesizer;

/**
 * An interface for a limited capacity queue.
 * @author Arjun Nair
 */
public interface BoundedQueue<T> extends Iterable<T> {

    /** Returns the maximum capacity of the queue. */
    int capacity();

    /** Returns the number of items currently in the queue. */
    int fillCount();

    /**
     * Adds an item to the end of the queue.
     * @param x The item to be added to the queue.
     */
    void enqueue(T x);

    /** Removes an item from the front of the queue. */
    T dequeue();

    /**
     * Returns the item at the front of the queue.
     * Does not delete it.
     */
    T peek();

    /** Returns true if the queue is empty. */
    default boolean isEmpty() {
        return this.fillCount() == 0;
    }

    /** Returns true if the queue is full. */
    default boolean isFull() {
        return this.fillCount() == this.capacity();
    }
}
