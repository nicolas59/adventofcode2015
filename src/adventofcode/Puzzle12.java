package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle12 {

	static class Cumul {
		int c;
	}
	
	public static void main(String...strings ) throws Exception{
		
		
		Pattern pattern = Pattern.compile("([-|+]{0,1}\\d+)");
		 final Cumul cumul = new Cumul();
		
		
		Files.readAllLines(Paths.get(Puzzle2.class.getResource("puzzle12input.txt").toURI()))
        .stream().forEach(line -> {
        	Matcher m = pattern.matcher(line);
        	while(m.find()){
        		System.out.println(m.group(1));
        		cumul.c += Integer.valueOf(m.group(1));
        	}
        	
        	
        });

		
		System.out.println("Total :" + cumul.c);
		
	}
	
}
