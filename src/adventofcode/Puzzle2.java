package adventofcode;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Nicolas on 18/12/2015.
 */
public class Puzzle2 {

    public static long calculate(int length, int width, int height){

        int surface1 = length * height;
        int surface2 = length * width;
        int surface3 = width * height;


        long area = 2 * surface1 + 2 * surface2 + 2 * surface3;


        return area + Math.min(Math.min(surface1, surface2) , surface3);
    }
    
    public static long calculateRibbon(int length, int width, int height){

    	int p1 = 2 * (length + height);
        int p2 = 2 * (length + width);
        int p3 = 2 * (width + height);


        long volume = length * width * height;


        return volume + Math.min(Math.min(p1, p2) , p3);
    }

    
    static class Cumul {
    	long sum; 
    	int index;
    }

    public static void main(String... args) throws Exception{
        System.out.println("Test");

        final Cumul sum = new Cumul(); 
        Files.readAllLines(Paths.get(Puzzle2.class.getResource("puzzle2input.txt").toURI()))
        .stream().forEach(line ->  {
        	String[] tabs = line.split("x");
        	/*sum.sum += calculate(Integer.parseInt(tabs[0]),
        			Integer.parseInt(tabs[1]),
        			Integer.parseInt(tabs[2]));
        	*/
        	sum.sum += calculateRibbon(Integer.parseInt(tabs[0]),
        			Integer.parseInt(tabs[1]),
        			Integer.parseInt(tabs[2]));
        	System.out.println(line + " " + (++sum.index) + " : " + sum.sum );
        });
        
        System.out.println(sum.sum);
    }
}
