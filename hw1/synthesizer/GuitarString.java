package synthesizer;

/** Represents a guitar string. */
public class GuitarString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /** Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int bufferSize = (int) Math.round(SR / frequency);
        this.buffer = new ArrayRingBuffer<>(bufferSize);

        /* Sets every value in the buffer to 0. */
        for (int i = 0; i < this.buffer.capacity(); i++) {
            this.buffer.enqueue(0.0);
        }
    }

    /** Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        for (int i = 0; i < this.buffer.capacity(); i++) {
            this.buffer.dequeue();

            double random = Math.random() - 0.5;
            this.buffer.enqueue(random);
        }
    }

    /** Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        double first = this.buffer.dequeue();
        double second = this.buffer.peek();

        double average = (first + second) / 2;
        double averageDecayed = average * DECAY;
        this.buffer.enqueue(averageDecayed);
    }

    /** Return the double at the front of the buffer. */
    public double sample() {
        return this.buffer.peek();
    }
}
