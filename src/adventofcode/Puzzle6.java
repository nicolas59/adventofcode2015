package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle6 {

	
	static boolean[][] turn(boolean [][] tab, int x, int y, int x1, int y1, boolean state){
		for(int posY=y; posY<=y1; posY++){
			for(int posX=x; posX<=x1; posX++){
				tab[posY][posX] = state;
			}
		}
		return tab;
	}
	
	static boolean[][] toggle(boolean [][] tab, int x, int y, int x1, int y1){
		for(int posY=y; posY<=y1; posY++){
			for(int posX=x; posX<=x1; posX++){
				tab[posY][posX] = !tab[posY][posX] ;
			}
		}
		return tab;
	}
	
	public static boolean[][] transform(String instruction, boolean[][] tab){
		Pattern pattern = Pattern.compile("(turn off|turn on|toggle) (\\d+)\\,(\\d+) through (\\d+)\\,(\\d+)");
		
		Matcher matcher = pattern.matcher(instruction);
		if(matcher.find()){
			String inst = matcher.group(1);
			int x = Integer.parseInt(matcher.group(2));
			int y = Integer.parseInt(matcher.group(3));
			
			int x1 = Integer.parseInt(matcher.group(4));
			int y1 = Integer.parseInt(matcher.group(5));
			
			if(inst.equals("toggle")){
				return toggle(tab, x,y,x1,y1);
			} else if(inst.equals("turn on")){
				return turn(tab, x,y,x1,y1, true);
			} else {
				return turn(tab, x,y,x1,y1, false);
			}
		}
		
		return tab;
	}
	
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		final boolean[][] tab = new boolean[1000][1000];
		//transform("turn on 0,0 through 9,9", tab);
		//transform("turn off 1,1 through 2,2", tab);
		
		Files.readAllLines(Paths.get(Puzzle2.class.getResource("puzzle6input.txt").toURI()))
		        .stream().forEach(line -> transform(line, tab));
		
		int count = 0;
		
		for(int i=0; i<tab.length;i++){
			for(int j=0; j<tab[i].length;j++){
				System.out.print(tab[i][j]?"1":"0");
				if(tab[i][j]) count++;
				
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		System.out.println(count);
	}

}
