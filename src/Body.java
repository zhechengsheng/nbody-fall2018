
/**
 * @author Zhecheng Sheng
 */

public class Body{
	/**
	 * Initialize 6 instance variables
	 */
	private double myXPos, myYPos, myXVel, myYVel,myMass;
	private String myFileName;
	/**
	 * Create a Body from parameters
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public Body(double xp, double yp, double xv,double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
		;
		
	}
	/**
	 * Copy constructor:copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public Body(Body b){
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFileName = b.myFileName;
		
		
	}
	public double getX() {
		return myXPos;
	}
	
	public double getY() {
		return myYPos;
	}
	
	public double getXVel() {
		return myXVel;
	}
	
	public double getYVel() {
		return myYVel;
	}
	
	public double getMass() {
		return myMass;
	}
	
	public String getName() {
		return myFileName;
	}
	
	public double calcDistance(Body b) {
		double r = Math.sqrt(Math.pow(this.myXPos-b.myXPos, 2)+Math.pow(this.myYPos-b.myYPos, 2));
		return r;
		
	}
	
	public double calcForceExertedBy(Body p) {
		final double G = 6.67 * 1e-11;
		double m1 = this.myMass;
		double m2 = p.myMass;
		double r = this.calcDistance(p);
		double F = G * (m1 * m2)/(r * r);
		
		return F;
	}
	
	public double calcForceExertedByX(Body p) {
		double F = this.calcForceExertedBy(p);
		double dx = p.myXPos-this.myXPos;
		double r = this.calcDistance(p);
		double Fx = F * dx/r;
		
		return Fx;
	}
	
	public double calcForceExertedByY(Body p) {
		double F = this.calcForceExertedBy(p);
		double dy = p.myYPos-this.myYPos;
		double r = this.calcDistance(p);
		double Fy = F * dy/r;
		
		return Fy;
	}
	
	public double calcNetForceExertedByX(Body[] bodies) {
		double xforce = 0;
		for(Body b : bodies) {
			if(!b.equals(this)) {
				xforce += this.calcForceExertedByX(b);
			}
		}
		return xforce;
	}
	
	public double calcNetForceExertedByY(Body[] bodies) {
		double yforce = 0;
		for(Body b : bodies) {
			if(!b.equals(this)) {
				yforce += this.calcForceExertedByY(b);
			}
		}
		return yforce;
	}
	
	public void update(double deltaT, double xforce, double yforce) {
		double m = this.myMass;
		double ax = xforce/m;
		double ay = yforce/m;
		double nvx = this.myXVel + deltaT * ax;
		double nvy = this.myYVel + deltaT * ay;
		double nx = this.myXPos + deltaT * nvx;
		double ny = this.myYPos + deltaT * nvy;
		
		this.myXPos = nx;
		this.myYPos = ny;
		this.myXVel = nvx;
		this.myYVel = nvy;
	}
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
	
}