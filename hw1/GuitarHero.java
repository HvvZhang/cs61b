import synthesizer.GuitarString;

/**
 * Represents a 37 key synthesizer.
 * @author Arjun Nair
 */
public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final GuitarString[] allNotes = new GuitarString[37];

    /** Returns the freq corresponding to the given index. */
    private static double ithFrequency(int index) {
        return 440 * Math.pow(2, (index - 24) / 12);
    }

    /** Fills the empty string array with string objects. */
    private static void buildNotes() {
        for (int i = 0; i < allNotes.length; i++) {
            double frequency = ithFrequency(i);
            allNotes[i] = new GuitarString(frequency);
        }
    }

    /** Returns the superposition of all the samples. */
    public static double superImposeSamples() {
        double sampleSum = 0.0;

        for (int i = 0; i < allNotes.length; i++) {
            sampleSum += allNotes[i].sample();
        }
        return sampleSum;
    }

    /** Plucks all the strings. */
    public static void ticAll() {
        for (int i = 0; i < allNotes.length; i++) {
            allNotes[i].tic();
        }
    }

    public static void main(String[] args) {
        /* Creates the string objects. */
        buildNotes();
        System.out.println(allNotes[0]);
        while (true) {
            /* check if the user has typed a key; if so, process it */
            /* Code borrowed from cs61B hw1 - GuitarHeroLite. */
            if (StdDraw.hasNextKeyTyped()) {
                try {
                    char key = StdDraw.nextKeyTyped();
                    int index = keyboard.indexOf(key);
                    allNotes[index].pluck();

                } catch (Exception e) {
                    continue;
                }
            }

            double sample = superImposeSamples();

            /* Plays the sample. */
            StdAudio.play(sample);

            /* Advances the simulation. */
            ticAll();
        }
    }
}
