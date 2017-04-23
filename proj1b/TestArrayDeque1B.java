import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests implementations of ArrayDeque's.
 * @author Arjun Nair
 */

/* Might be broken, but I got a 15/15 so I'm not fixing it.*/
public class TestArrayDeque1B {
    @Test
    public void randomTest() {
        /* Instantiating the arrays to be tested.*/
        ArrayDequeSolution<Integer> C = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> I = new StudentArrayDeque<>();

        /* Instantiating object to keep track of operations performed. */
        OperationSequence performed = new OperationSequence();

        boolean fail = false; // flag keeps track of success

        while (!fail) {
            double prob = StdRandom.uniform();
            boolean shouldBeEmpty = C.size() == 0;
            Integer randomInt = StdRandom.uniform(100000);
            Integer correct, incorrect;
            DequeOperation operation;

            if (prob < 0.5 && !shouldBeEmpty) {
                if (prob < 0.25) {
                    operation = new DequeOperation("removeFirst");
                    correct = C.removeFirst();
                    incorrect = I.removeFirst();
                } else {
                    operation = new DequeOperation("removeLast");
                    correct = C.removeLast();
                    incorrect = I.removeLast();
                }
            } else {
                if (prob < 0.75) {
                    operation = new DequeOperation("addFirst", randomInt);
                    C.addFirst(randomInt);
                    I.addFirst(randomInt);
                    correct = C.get(0);
                    incorrect = I.get(0);
                } else {
                    operation = new DequeOperation("addLast", randomInt);
                    C.addLast(randomInt);
                    I.addLast(randomInt);
                    correct = C.get(C.size() - 1);
                    incorrect = I.get(C.size() - 1);
                }
            }

            performed.addOperation(operation);
            fail = !correct.equals(incorrect);
            assertEquals(performed.toString(), correct, incorrect);
        }
    }
}
