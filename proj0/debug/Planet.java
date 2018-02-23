public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public void draw(){
		//the following .picture(x,y,image) draws the specified image, centered at (x, y).
		StdDraw.picture(xxPos, yyPos, "./images/"+imgFileName);
		/* Shows the drawing to the screen, and waits 2000 milliseconds. */
		StdDraw.show(10);//t:number of miniseconds
	}

	public double calcDistance(Planet p){
		return Math.sqrt((xxPos - p.xxPos)*(xxPos - p.xxPos)+ (yyPos - p.yyPos)*(yyPos - p.yyPos));
	}

	public double calcForceExertedBy(Planet p){
		double G = 6.67E-11;
		return G*mass*p.mass/Math.pow(calcDistance(p),2);
	}

	public double calcForceExertedByX(Planet p){
		return calcForceExertedBy(p)*(p.xxPos - xxPos)/calcDistance(p);
	}

	public double calcForceExertedByY(Planet p){
		return calcForceExertedBy(p)*(p.yyPos - yyPos)/calcDistance(p);
	}	

	public double calcNetForceExertedByX(Planet[] allPlanets){
		double sum = 0;
		int i = 0;
		while (i<allPlanets.length){
			if(this.equals(allPlanets[i])){
				i += 1;				
			}else{
				sum += calcForceExertedByX(allPlanets[i]);
				i += 1;
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets){
		double sum = 0;
		int i = 0;
		while (i<allPlanets.length){
			if(this.equals(allPlanets[i])){
				i += 1;				
			}else{
				sum += calcForceExertedByY(allPlanets[i]);
				i += 1;
			}
		}
		return sum;
	}	

	public void update(double dt, double fX, double fY){
		double ax = fX/mass;
		double ay = fY/mass;
		xxVel = xxVel + dt*ax;
		yyVel = yyVel + dt*ay;
		xxPos = xxPos + dt*xxVel;
		yyPos = yyPos + dt*yyVel;
	}

}
