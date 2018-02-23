public class TestPlanet{
	public static void main(String[] args){
		Planet p1 = new Planet(0,0,0,0,1,"planet1");
		Planet p2 = new Planet(p1);
		p2.imgFileName = "planet2";
		p2.xxPos = 10;
		System.out.println("p1 calc p2: "+ p1.calcForceExertedByX(p2) +";  p2 calc p1: " + p2.calcForceExertedByX(p1));
	}
}