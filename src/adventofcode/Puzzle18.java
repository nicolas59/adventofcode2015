package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Puzzle18 {
	
	
	static char[][] play(char[][] tab){
		char[][] temp = new char[tab.length][tab[0].length];
		
		int nbOfLightOnCorner = (tab[0][0] == '#'?1:0)
				+ (tab[0][tab[0].length-1] == '#'?1:0)
				+ (tab[tab.length-1][0] == '#'?1:0 )
				+ (tab[tab.length-1][tab[0].length-1] == '#'?1:0);
		
		for(int y=0;y<tab.length;y++){
			for(int x=0;x<tab[0].length;x++){
				short numberOfLigth = 0;
				
				int nY = y==0?0:y-1;
				int nX = x==0?0:x-1;
				int maxY = y==tab.length-1?tab.length:y+2;
				int maxX = x==tab[y].length-1?tab[y].length:x+2;
				
				//System.out.println("Verification " + y + "-" + x);
				
				for(int startY=nY;startY<maxY;startY++){
					for(int startX=nX;startX<maxX;startX++){
						if(startY==y && startX==x){
							continue;
						}
					//	System.out.println("Verification " + startY + "-" + startX);
						
						if(tab[startY][startX] == '#'){
							numberOfLigth++;
						}
					}	
				}
				
				
				if(tab[y][x] == '#' && (numberOfLigth == 2 || numberOfLigth == 3)){
					temp[y][x] = '#';
				}else if(tab[y][x] == '.' && numberOfLigth == 3){
					temp[y][x] = '#';
				}else{
					temp[y][x] = '.';
				}
				
				
				//System.out.println("Val : " + temp[y][x] );
				//System.out.println("---------------------");
			}	
		}
		
		if(nbOfLightOnCorner == 4){
			temp[0][0] = '#';
			temp[0][tab[0].length-1] = '#';
			temp[tab.length-1][0] = '#'; 
			temp[tab.length-1][tab[0].length-1] = '#';
		}
		
		
		
		
		return temp;
	}
	
	
	static int print(char[][] tab){
		System.out.println("\n");
		int nbAlive = 0;
		
		
		
		for(char[] chars : tab){
			for(char c:chars){
				System.out.print(c + " ");
				
				if(c == '#'){
					nbAlive++;
				}
			}
			System.out.println("");
		}
		
		
		
		
		return nbAlive;
	}
	
	public static void main(String... strings) throws Exception{
		List<String> lines = Files.readAllLines(Paths.get(Puzzle2.class.getResource("puzzle18input.txt").toURI()));
	 char[][] tab = new char[100][100];
		
		for(int l=0;l<lines.size();l++){
			for(int c=0;c<lines.get(l).length();c++){
				tab[l][c] = lines.get(l).charAt(c); 
			}
		}
		
		
		for(int i=0;i<100;i++){
			tab = play(tab);
			
			int nb = print(tab);
			System.out.println("------ Nb : " + nb + " -------");
			
		}
		
	}
}
