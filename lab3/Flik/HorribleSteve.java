public class HorribleSteve {
	public static void main (String[] args) {
		int i = 0;
		for (int j = 0; i < 500; ++i, ++j) {
			boolean notSame = !(Flik.isSameNumber(i, j));
			if (notSame) {
          		break; // break exits the for loop!
			}
		}
		System.out.println("i is " + i);
	}
}

