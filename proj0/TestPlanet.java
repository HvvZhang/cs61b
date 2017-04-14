public class TestPlanet {

	/** Tests the force calculations between two planets */
	public static void testForce() {
		Planet p1 = new Planet(0, 0, 0, 0, 10, "no_image");
		Planet p2 = new Planet(0, 1, 2, 3, 222, "no_image");
		double forceExerted = p1.calcForceExertedBy(p2);

		if (forceExerted != 1.48074e-7) {
			System.out.println("FAIL    " + forceExerted + "    1.48074e-7");
		}
		System.out.println("PASS    " + forceExerted + "    1.48074e-7");
	} 

	public static void main(String[] args) {
		System.out.println("RESULT    value    expected");
		testForce();
	}
}