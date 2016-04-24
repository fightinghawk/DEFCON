package tpdds;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
        // latitud y logitud
		Location loc1 = new Location( -34.621854, -58.402526); //"Mi casa",
        Location loc2 = new Location( -34.635926, -58.363816); //"La Bombonera", 
        double distance = loc1.distanceTo(loc2);
        System.out.printf("%6.3f Kilometros desde\n", distance);
        System.out.println(loc1 + " a " + loc2);
		
	}
}
