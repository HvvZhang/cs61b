package synthesizer;

/**
 * An abstract class which implements the bounded queue.
 * @author Arjun Nair
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    /** Returns the maximum capacity of the queue. */
    @Override
    public int capacity() {
        return  this.capacity;
    }

    /** Returns the number of items currently in the queue. */
    @Override
    public int fillCount() {
        return this.fillCount;
    }
}
