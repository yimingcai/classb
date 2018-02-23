import java.util.Scanner;

public class NBody {
	public static void main(String[] args){
		//Scanner sc = new Scanner(System.in);
		//System.out.println("Please enter T in double type, enter key must be pressed at some point: ");
		//double T = sc.nextDouble();
		double T = Double.parseDouble(args[0]);
        System.out.println("You entered T: "+T);
		//System.out.println("Please enter dt in double type ended by enter key");
		//double dt = sc.nextDouble();
		double dt = Double.parseDouble(args[1]);
        System.out.println("You entered dt: "+dt);
		//sc.nextLine();  // Consume newline left-over. this is to eat the "\n" caused by by the enter key after dt input
		//System.out.println("Please enter filename with location ended by enter key and without quotation keys");
		//String filename = sc.nextLine();
		String filename = args[2];
        System.out.println("You entered filename: "+filename);
		//String filename = "./data/planets.txt";
		double r = readRadius(filename);
		System.out.println("File is loaded.");
		Planet[] planets = new Planet[readPlanets(filename).length];
		planets = readPlanets(filename);

		//StdAudio.play("./audio/2001.mid");
		String imageToDraw = "./images/starfield.jpg";

		double[] xForces = new double[planets.length];
		double[] yForces = new double[planets.length];
		double t =0 ;
		int k = 0;
		while (t< T){
			k=0;
			while(k<planets.length){
				xForces[k]=planets[k].calcNetForceExertedByX(planets);
				yForces[k]=planets[k].calcNetForceExertedByY(planets);
				k += 1;
			}
			k=0;
			while(k<planets.length){
				planets[k].update(dt, xForces[k], yForces[k]);
				k += 1;
			}
			drawBackground(r,imageToDraw);
			k=0;
			while (k< planets.length){
				planets[k].draw();
				k += 1;
			}
			t = t + dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}


	public static void drawBackground(double radius, String image){
		/** setScale(min, max) sets up the universe so it goes from (min, min) up to (max,max) .
		 * The arguments are the coordinates of the minimum and maximum x- or y-coordinates that will appear in the canvas.
		 * */
		StdDraw.setScale(-radius, radius);
		/* Clears the drawing window. */
		StdDraw.clear();
		//the following .picture(x,y,image) draws the specified image, centered at (x, y).
		StdDraw.picture(0, 0, image);
		/* Shows the drawing to the screen, and waits 2000 milliseconds. */
		StdDraw.show(10);//t: number of miniseconds
	}

	public static double readRadius(String filename){
		In in = new In(filename);
	//	double radius;
	//	while (!in.isEmpty()){
		int numb = in.readInt(); //read the first int, which is the nuber of planets
		double radius = in.readDouble();// read the second data, which is the radius of the universe.
	//	}

		return radius;
	}

	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int numb = in.readInt(); //read the first int, which is the nuber of planets
		double radius = in.readDouble();// read the second data, which is the radius of the universe.
		Planet[] planetArray = new Planet[numb];
		int i =0;
		while(i< planetArray.length){
			planetArray[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString() );
			i += 1;
		}
		return planetArray;
	}
}
