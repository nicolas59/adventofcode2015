package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;

import adventofcode.Puzzle2.Cumul;

public class Puzzle3 {

	enum Sens {
		NORTH(), SOUTH, EAST, WEST;
		
		static Sens toSens(char c){
			switch(c){
			case '>' : 
				return EAST;
			case '<' : 
				return WEST;
			case '^' : 
				return Sens.NORTH;
			case 'v' : 
				return SOUTH;
			}
			return null;
		}
	}
	
	static class Map {
		
		private int[][] houses = new int[1][1]; //map
		
		private int x=0,y=0; //current postion
		
		Map(){
			houses[x][y] = 1;
			print();
		}
		
		private void resize(){
			System.out.println("Redimensionnement");
			int[][] temp = new int[houses.length+2][houses[0].length + 2];
			for(int abs=0;abs <houses.length; abs++){
				for(int ord=0;ord <houses[0].length; ord++){
					temp[abs+1][ord+1] = houses[abs][ord];
				}	
			}
			//if(x == 0)
			x++;
			//if(y == 0)
			y++;
			
			this.houses = temp;
			
		}
		
		public void next(Sens s) {
			
			switch(s){
			case NORTH:
				if(y == 0){
					//resize area
					this.resize();
				}
				y--;
				this.houses[y][x]++;
				break;
			case SOUTH:
				if(y == houses[0].length-1){
					this.resize();
				}
				y++;
				this.houses[y][x]++;
				
				break;
			case EAST:
				if(x == houses.length-1){
					this.resize();
				}
				x++;
				this.houses[y][x]++;
				break;
			case WEST:
				if(x == 0){
					this.resize();
				}
				x--;
				this.houses[y][x]++;
			}
		//	print();
		}
		
		void print(){
			  for(int abs=0;abs <houses.length; abs++){
					for(int ord=0;ord <houses[0].length; ord++){
						System.out.print(" " + houses[abs][ord]);
					}
					System.out.println("");
				}
				System.out.println("");
				
		}
		
	}
	
	static int countHouse(int rest) throws Exception{
		Map map = new Map();
		
		final Cumul sum = new Cumul(); 
	        Files.readAllLines(Paths.get(Puzzle2.class.getResource("puzzle3input.txt").toURI()))
	        .stream().forEach(line ->  {
	        	long pos =0;
	        	for(char c:line.toCharArray()){
	        		if(pos%2 == rest){
	        			map.next(Sens.toSens(c));
	        			System.out.println(pos);
	        		}
	        		pos++;
	        		
	        		
	        	}
	        });
	        int count = 0;
		    for(int abs=0;abs <map.houses.length; abs++){
				for(int ord=0;ord <map.houses[0].length; ord++){
					if(map.houses[abs][ord]>0) count++;
				}
			}
		    return count;
	}
	
	public static void main(String ...args) throws Exception{
		int santa =countHouse(1);
		int robot = countHouse(0);
	    //System.out.println(santa);
	    System.out.println(robot);
	    System.out.println(santa+robot);
	}
	
}
