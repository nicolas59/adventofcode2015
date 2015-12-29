package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Puzzle8 {

	
	static int count(String tmp){
		//System.out.println(line);
		String line = tmp.replaceAll("\\\\x[0-9a-fA-F]{2}", "H");
		line = line.replaceAll("\\\\", "S");
		line = line.replaceAll("\\\"", "G");
		//line = line.replaceAll("\\\\(\\\\)", "A");
		line = line.replaceAll("^\"|\"$", "");
		
		System.out.println(tmp +" " + tmp.length() + " \t " +line);
		return line.trim().length();
	}
	
	static class Cumul {
		int a, b;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		List<String> lines = Files.readAllLines(Paths.get(Puzzle2.class.getResource("puzzle8input.txt").toURI()));
		final Cumul c = new Cumul();
		
		lines.forEach(line -> c.a+=line.trim().length());
		
		lines.forEach(line -> c.b+=count(line.trim()));
		
		System.out.println(c.a );
		System.out.println( c.b);
		
		System.out.println(c.a - c.b);
		/*
		
		lines.forEach(System.out::println);
		*/
		System.out.println("===== " + "\"\"".length());
		System.out.println(count("\"\""));
		System.out.println(count("\"abc\""));
		System.out.println(count("\"aaa\\\"aaa\""));
		System.out.println("\"\\x27\"".length() + "----" + count("\"\\x27\""));
		//System.out.println("\"\\x27\"".length());
		
		//System.out.println(count("\"\\\"\\a\""));
		
	}

}
