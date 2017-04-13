/** The Planet class used to represent all the different 
 *  bodies in space. 
 *  programmer  Arjun Nair 
 */
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName; 

    public static double G = 6.67e-11; // The universal gravitational constant

    /** Constructor to create planet from scratch. 
     *  @param  xxPos          x co-ordinate of the planet.
     *  @param  yyPos          y co-ordinate of the planet.
     *  @param  xxVel          x component of the planet's velocity.
     *  @param  yyVel          y component of the planet's velocity.
     *  @param  mass           The planet's mass.
     *  @param  imgFileName    The file name to the image used for the planet.   
     */ 
    public Planet(double xxPos, double yyPos, double xxVel,
                  double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    /** Constructor to create a copy of a planet. 
     *  @param  p    The planet to be copied.
     */
    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName; 
    }

    /** Calculates the distance between two planets 
     *  @param  p    The planet used to calculate distance.
     */
    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;

        return Math.sqrt(dx * dx + dy * dy);
    }

    /** Calculates the force exerted by the planet p.
     *  @param  p    The planet to be used in the force calculations.    
     */
    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        double forceExerted = (G * this.mass * p.mass) / (r * r);
        
        return forceExerted; 
    }

    /** Calculates the force exerted by the planet p
     *  in the x direction.
     *  @param p    The planet used to calculate distance.
     */
    public double calcForceExertedByX(Planet p) {
        double forceExerted = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        double cosTheta = dx / r;
        double forceExertedX = forceExerted * cosTheta;

        return forceExertedX;
    }

    /** Calculates the force exerted by the planet p
     *  in the y direction.
     *  @param p    The planet used to calculate distance.
     */
    public double calcForceExertedByY(Planet p) {
        double forceExerted = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        double sinTheta = dy / r;
        double forceExertedY = forceExerted * sinTheta;

        return forceExertedY;
    }

    /** Calculates the net force exerted on a planet by all 
     *  the other planets.
     *  @param  planetArray    A list of planets.
     */
    public double calcNetForceExertedByX(Planet[] planetArray) {
        double netForceX = 0;

        for (Planet p : planetArray) {
            if (!this.equals(p)) {
                netForceX += this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    /** Calculates the net force exerted on a planet by all 
     *  the other planets.
     *  @param  planetArray    A list of planets.
     */
    public double calcNetForceExertedByY(Planet[] planetArray) {
        double netForceY = 0;

        for (Planet p : planetArray) {
            if (!this.equals(p)) {
                netForceY += this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

}


