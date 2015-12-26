package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Puzzle8 {

	
	static int count(String line){
		//System.out.println(line);
		line = line.replaceAll("\\\\x[0-9a-fA-F]{2}", "A");
		line = line.replaceAll("\\\\\"", "A");
		line = line.replaceAll("\\\\(\\\\)", "A");
		line = line.replaceAll("^\"|\"$", "");
		
		//System.out.println(" " +line);
		return line.length();
	}
	
	static class Cumul {
		int a, b;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		List<String> lines = Files.readAllLines(Paths.get(Puzzle2.class.getResource("puzzle8input.txt").toURI()));
		final Cumul c = new Cumul();
		
		lines.forEach(line -> c.a+=line.length());
		
		lines.forEach(line -> c.b+=count(line));
		
		System.out.println(c.a );
		System.out.println( c.b);
		
		System.out.println(c.a - c.b);
		/*
		
		lines.forEach(System.out::println);
		*/
		
		System.out.println(count("\"\""));
		System.out.println(count("\"abc\""));
		System.out.println(count("\"aaa\\\"aaa\""));
		System.out.println(count("\"\\x27\""));
		//System.out.println("\"\\x27\"".length());
		
		//System.out.println(count("\"\\\"\\a\""));
		
	}

}
