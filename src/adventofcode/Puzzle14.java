package adventofcode;

public class Puzzle14 {
	
	static long calculDistance(int vit, int during, int rest, int delay){
		int total =  delay / (during + rest);
		long distance = vit * total * during; 
		
		int tmp = (delay%(during + rest)) ;
		//int tmp = delay - total * ( during + rest);
		int tmp2 = tmp == 0 ? 0 :(during == tmp ? during: during%tmp);
		
		return distance + tmp2*vit;
	}

	public static void main(String[] args) {
		
		System.out.println("Distance Vixen : " + calculDistance(14,10, 127, 1000));
		System.out.println("Distance Blitzen : " + calculDistance(16, 11, 162, 1000));
		
		System.out.println("---------------------------------------------------------");
		
		
		System.out.println("Distance Vixen : " + calculDistance(8, 8, 53, 2503));
		System.out.println("Distance Blitzen : " + calculDistance(13, 4, 49, 2503));
		System.out.println("Distance Rudolph : " + calculDistance(20, 7, 132, 2503));
		System.out.println("Distance Cupid : " + calculDistance(12, 4, 43, 2503));
		System.out.println("Distance Donner : " + calculDistance(9, 5, 38, 2503));
		System.out.println("Distance Dasher : " + calculDistance(10, 4, 37, 2503));
		System.out.println("Distance Comet : " + calculDistance(3, 37, 76, 2503));
		System.out.println("Distance Prancer : " + calculDistance(9, 12, 97, 2503));
		System.out.println("Distance Dancer : " + calculDistance(37, 1, 36, 2503));
	}

}
